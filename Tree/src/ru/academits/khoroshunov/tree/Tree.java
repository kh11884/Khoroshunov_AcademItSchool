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

    private int compare(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }
        if (data1 == null) {
            if (data2 == null) {
                return 0;
            } else {
                return -1;
            }
        } else if (data2 == null) {
            return 1;
        }
        //noinspection unchecked
        Comparable<? super T> cpr = (Comparable<? super T>) data1;
        return cpr.compareTo(data2);
    }

    private void insertNode(TreeNode<T> currentNode, TreeNode<T> insertedNode) {
        do {
            int compareResult = compare(insertedNode.getData(), currentNode.getData());
            if (compareResult >= 0) {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(insertedNode);
                    size++;
                    return;
                } else {
                    currentNode = currentNode.getRight();
                }
            } else {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(insertedNode);
                    size++;
                    return;
                } else {
                    currentNode = currentNode.getLeft();
                }
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
            int compareResult = compare(data, currentNode.getData());
            if (compareResult > 0) {
                currentNode = currentNode.getRight();
            } else if (compareResult < 0) {
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
            int compareResult = compare(data, currentNode.getData());

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
                TreeNode<T>[] smallestNodes = getSmallestNodes(currentNode.getRight());
                TreeNode<T> smallestChildParent = smallestNodes[0];
                TreeNode<T> smallestChild = smallestNodes[1];

                if (smallestChildParent != null) {
                    smallestChildParent.setLeft(smallestChild.getRight());
                }
                if (currentNode == treeRoot) {
                    if (smallestChild == currentNode.getRight()) {
                        smallestChild.setRight(null);
                    } else {
                        smallestChild.setRight(currentNode.getRight());
                    }
                    smallestChild.setLeft(currentNode.getLeft());
                } else {
                    smallestChild.setLeft(currentNode.getLeft());
                    if (currentNode.getRight() != smallestChild) {
                        smallestChild.setRight(currentNode.getRight());
                    }
                }
                deleteNode(parentNode, smallestChild, isRight);
                return true;
            }
        } while (currentNode != null);
        return false;
    }

    private TreeNode<T>[] getSmallestNodes(TreeNode<T> node) {
        //noinspection unchecked
        TreeNode<T>[] smallestNodes = (TreeNode<T>[]) new TreeNode[2];

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
