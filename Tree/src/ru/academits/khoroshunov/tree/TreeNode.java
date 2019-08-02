package ru.academits.khoroshunov.tree;

class TreeNode<T> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T data;

    TreeNode(T data) {
        this.data = data;
        left = null;
        right = null;
    }

    T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
    }

    TreeNode<T> getLeft() {
        return left;
    }

    TreeNode<T> getRight() {
        return right;
    }

    void setLeft(TreeNode<T> node) {
        left = node;
    }

    void setRight(TreeNode<T> node) {
        right = node;
    }
}
