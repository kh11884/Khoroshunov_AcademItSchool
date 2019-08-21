package ru.academits.khoroshunov.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> treeRoot;
    private int size;
    private Comparator<? super T> comparator;

    public Tree() {
        comparator = null;
    }

    public Tree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public int getSize() {
        return size;
    }

    public void recursionDeepGoRound(Consumer<T> method) {
        doRecursionDeepGoRound(treeRoot, method);
    }

    private void doRecursionDeepGoRound(TreeNode<T> node, Consumer<T> method) {
        if (node != null) {
            method.accept(node.getData());

            if (node.getLeft() != null) {
                doRecursionDeepGoRound(node.getLeft(), method);
            }
            if (node.getRight() != null) {
                doRecursionDeepGoRound(node.getRight(), method);
            }
        }
    }

    public void treeWidthGoRound(Consumer<T> method) {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        if (treeRoot != null) {
            queue.add(treeRoot);
        }

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.remove();
            method.accept(node.getData());
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public void treeDeepGoRound(Consumer<T> method) {
        Deque<TreeNode<T>> stack = new LinkedList<>();
        if (treeRoot != null) {
            stack.addLast(treeRoot);
        }

        while (!stack.isEmpty()) {
            TreeNode<T> node = stack.removeLast();
            method.accept(node.getData());

            if (node.getRight() != null) {
                stack.addLast(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.addLast(node.getLeft());
            }
        }
    }

    public void insert(T data) {
        if (treeRoot == null) {
            treeRoot = new TreeNode<>(data);
            size = 1;
        } else {
            insertNode(treeRoot, new TreeNode<>(data));
        }
    }

    private int getCompareResult(T nodeData, T data) {
        if (comparator != null) {
            return comparator.compare(data, nodeData);
        } else {
            if (nodeData == null) {
                if (data == null) {
                    return 0;
                } else {
                    return 1;
                }
            } else if (data == null) {
                return -1;
            }
            //noinspection unchecked
            Comparable<? super T> cpr = (Comparable<? super T>) data;
            return cpr.compareTo(nodeData);
        }
    }

    private void insertNode(TreeNode<T> currentNode, TreeNode<T> insertedNode) {
        do {
            int compareResult = getCompareResult(currentNode.getData(), insertedNode.getData());
            if (compareResult > 0) {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(insertedNode);
                    size++;
                    return;
                } else {
                    currentNode = currentNode.getRight();
                }
            } else if (compareResult < 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(insertedNode);
                    size++;
                    return;
                } else {
                    currentNode = currentNode.getLeft();
                }
            } else {
                return;
            }
        } while (currentNode != null);
    }

    public boolean contains(T data) {
        if (treeRoot == null) {
            return false;
        }
        return findNode(treeRoot, data) != null;
    }

    private TreeNode<T> findNode(TreeNode<T> currentNode, T data) {
        do {
            if (getCompareResult(currentNode.getData(), data) > 0) {
                currentNode = currentNode.getRight();
            } else if (getCompareResult(currentNode.getData(), data) < 0) {
                currentNode = currentNode.getLeft();
            } else {
                return currentNode;
            }
        } while (currentNode != null);
        return null;
    }

    public boolean delete(T data) {
        if (treeRoot == null) {
            return false;
        }

        TreeNode<T> parentNode = null;
        TreeNode<T> currentNode = treeRoot;
        boolean isRight = false;

        do {
            int compareResult = getCompareResult(currentNode.getData(), data);

            if (compareResult > 0) {
                parentNode = currentNode;
                currentNode = currentNode.getRight();
                isRight = true;
            } else if (compareResult < 0) {
                parentNode = currentNode;
                currentNode = currentNode.getLeft();
                isRight = false;
            } else {
                // если нет детей
                if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                    deleteNode(parentNode, null, isRight);
                    return true;
                }
                // если один ребенок
                if (currentNode.getLeft() == null || currentNode.getRight() == null) {
                    TreeNode<T> singleChild = currentNode.getLeft() == null ? currentNode.getRight() : currentNode.getLeft();
                    deleteNode(parentNode, singleChild, isRight);
                    return true;
                }
                // если два ребенка
                Object[] smallestNodes = getSmallestNodes(currentNode.getRight());
                //noinspection unchecked
                TreeNode<T> smallestChildParent = (TreeNode<T>) smallestNodes[0];
                //noinspection unchecked
                TreeNode<T> smallestChild = (TreeNode<T>) smallestNodes[1];
                if (smallestChildParent != null) {
                    smallestChildParent.setLeft(smallestChild.getRight());
                }
                if (currentNode == treeRoot) {
                    smallestChild.setRight(currentNode.getRight());
                    smallestChild.setLeft(currentNode.getLeft());
                } else {
                    if (isRight) {
                        smallestChild.setRight(currentNode.getRight());
                    } else {
                        smallestChild.setLeft(currentNode.getLeft());
                    }
                }
                deleteNode(parentNode, smallestChild, isRight);
                return true;
            }
        } while (currentNode != null);
        return false;
    }

    private Object[] getSmallestNodes(TreeNode<T> node) {
        Object[] smallestNodes = new Object[2];
        while (node.getLeft() != null) {
            smallestNodes[0] = node;
            node = node.getLeft();
        }
        smallestNodes[1] = node;
        return smallestNodes;
    }

    private void deleteNode(TreeNode<T> parentNode, TreeNode<T> usedNode, boolean isRight) {
        if (parentNode == null) {
            treeRoot = usedNode;
        } else if (isRight) {
            parentNode.setRight(usedNode);
        } else {
            parentNode.setLeft(usedNode);
        }
        size--;
    }
}
