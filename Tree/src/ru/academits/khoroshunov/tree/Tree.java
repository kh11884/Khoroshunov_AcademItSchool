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

    public void printTreeRecursionDeepGoRound(Consumer<T> method) {
        recursionDeepGoRound(treeRoot, method);
    }

    private void recursionDeepGoRound(TreeNode<T> node, Consumer<T> method) {
        method.accept(node.getData());
        if (node.getLeft() != null && node.getRight() != null) {
            recursionDeepGoRound(node.getLeft(), method);
            recursionDeepGoRound(node.getRight(), method);
        } else if (node.getLeft() != null) {
            recursionDeepGoRound(node.getLeft(), method);
        } else if (node.getRight() != null) {
            recursionDeepGoRound(node.getRight(), method);
        }
    }

    public void treeWidthGoRound(Consumer<T> method) {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(treeRoot);

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
        stack.addLast(treeRoot);

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
            insertNode(treeRoot, data);
            size++;
        }
    }

    private int getCompareResult(TreeNode<T> currentNode, T data) {
        if (comparator != null) {
            return comparator.compare(data, currentNode.getData());
        } else {
            if (currentNode.getData() == null) {
                if (data == null) {
                    return 0;
                } else {
                    return 1;
                }
            } else if (data == null) {
                return -1;
            } else {
                //noinspection unchecked
                Comparable<? super T> cpr = (Comparable<? super T>) data;
                return cpr.compareTo(currentNode.getData());
            }
        }
    }

    private void insertNode(TreeNode<T> currentNode, T data) {
        int compareResult = getCompareResult(currentNode, data);

        if (compareResult < 0) {
            if (currentNode.getLeft() != null) {
                insertNode(currentNode.getLeft(), data);
            } else {
                currentNode.setLeft(new TreeNode<>(data));
            }
        } else if (compareResult > 0) {
            if (currentNode.getRight() != null) {
                insertNode(currentNode.getRight(), data);
            } else {
                currentNode.setRight(new TreeNode<>(data));
            }
        }
    }

    public boolean isContains(T data) {
        return findNode(treeRoot, data);
    }

    private boolean findNode(TreeNode<T> currentNode, T data) {
        if (currentNode == null) {
            return false;
        }
        if (Objects.equals(data, currentNode.getData())) {
            return true;
        }
        return getCompareResult(currentNode, data) < 0 ?
                findNode(currentNode.getLeft(), data)
                : findNode(currentNode.getRight(), data);
    }

    public boolean delete(T data) {
        boolean isDeleted = isContains(data);
        deleteNode(treeRoot, data);
        if (isDeleted) {
            size--;
        }
        return isDeleted;
    }

    private TreeNode<T> deleteNode(TreeNode<T> currentNode, T data) {
        if (currentNode == null) {
            return null;
        }

        T currentData = currentNode.getData();
        if (Objects.equals(data, currentData)) {
            if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                return null;
            }

            if (currentNode.getRight() == null) {
                return currentNode.getLeft();
            }
            if (currentNode.getLeft() == null) {
                return currentNode.getRight();
            }

            T smallData = findSmallData(currentNode.getRight());
            currentNode.setData(smallData);
            currentNode.setRight(deleteNode(currentNode.getRight(), smallData));
            return currentNode;
        }

        if (getCompareResult(currentNode, data) < 0) {
            currentNode.setLeft(deleteNode(currentNode.getLeft(), data));
            return currentNode;
        }

        currentNode.setRight(deleteNode(currentNode.getRight(), data));
        return currentNode;
    }

    private T findSmallData(TreeNode<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getData();
    }
}
