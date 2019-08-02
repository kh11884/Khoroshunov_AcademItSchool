package ru.academits.khoroshunov.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Tree<T> {
    private TreeNode<T> treeRoot;
    private int size;

    public Tree(T data) {
        treeRoot = new TreeNode<>(data);
        size++;
    }

    public int getSize() {
        return size;
    }

    public void printTreeRecursionDeepGoRound() {
        recursionDeepGoRound(treeRoot);
    }

    private void recursionDeepGoRound(TreeNode<T> node) {
        System.out.print(node.getData() + ", ");

        if (node.getLeft() != null && node.getRight() != null) {
            recursionDeepGoRound(node.getLeft());
            recursionDeepGoRound(node.getRight());
        } else if (node.getLeft() != null) {
            recursionDeepGoRound(node.getLeft());
        } else if (node.getRight() != null) {
            recursionDeepGoRound(node.getRight());
        }
    }

    public void printTreeWidthGoRound() {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(treeRoot);

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.remove();
            System.out.print(node.getData() + ", ");
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public void printTreeDeepGoRound() {
        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.addLast(treeRoot);

        while (!stack.isEmpty()) {
            TreeNode<T> node = stack.removeLast();
            System.out.print(node.getData() + ", ");

            if (node.getRight() != null) {
                stack.addLast(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.addLast(node.getLeft());
            }
        }
    }

    public void insert(T data) {
        insertNode(treeRoot, data);
        size++;
    }

    private void insertNode(TreeNode<T> currentNode, T data) {
        if (data.hashCode() < currentNode.getData().hashCode()) {
            if (currentNode.getLeft() != null) {
                insertNode(currentNode.getLeft(), data);
            } else {
                currentNode.setLeft(new TreeNode<>(data));
            }
        } else if (data.hashCode() > currentNode.getData().hashCode()) {
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
        if (data.equals(currentNode.getData())) {
            return true;
        }
        return data.hashCode() < currentNode.getData().hashCode() ?
                findNode(currentNode.getLeft(), data)
                : findNode(currentNode.getRight(), data);
    }

    public void delete(T data) {
        deleteNode(treeRoot, data);
        size--;
    }

    private TreeNode<T> deleteNode(TreeNode<T> currentNode, T data) {
        if (currentNode == null) {
            return null;
        }
        T currentData = currentNode.getData();
        if (data.equals(currentData)) {
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
        if (data.hashCode() < currentNode.getData().hashCode()) {
            currentNode.setLeft(deleteNode(currentNode.getLeft(), data));
            return currentNode;
        }
        currentNode.setRight(deleteNode(currentNode.getRight(), data));
        return currentNode;
    }

    private T findSmallData(TreeNode<T> node) {
        return node.getLeft() == null ? node.getData() : findSmallData(node.getLeft());
    }
}
