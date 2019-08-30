package ru.academits.khoroshunov.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Graph {
    private int[][] graph;

    public Graph(int[][] graph) {
        this.graph = graph;
    }

    public void widthGoRound(Consumer<Integer> method) {
        if (graph == null) {
            throw new NullPointerException("Метод вызван от пустого графа.");
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            queue.add(i);
        }

        while (!queue.isEmpty()) {
            int node = queue.remove();
            if (!visited[node]) {
                visited[node] = true;
                method.accept(node);
                for (int i = 0; i < graph[node].length; i++) {
                    int value = graph[node][i];
                    if (value > 0) {
                        queue.add(i);
                    }
                }
            }
        }
    }

    public void deepGoRound(Consumer<Integer> method) {
        if (graph == null) {
            throw new NullPointerException("Метод вызван от пустого графа.");
        }
        Deque<Integer> stack = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            stack.addLast(i);
        }

        while (!stack.isEmpty()) {
            int node = stack.removeLast();
            if (!visited[node]) {
                visited[node] = true;
                method.accept(node);
                for (int i = 0; i < graph[node].length; i++) {
                    int value = graph[node][i];
                    if (value > 0) {
                        stack.add(i);
                    }
                }
            }
        }
    }

    public void recursionDeepGoRound(Consumer<Integer> method) {
        if (graph == null) {
            throw new NullPointerException("Метод вызван от пустого графа.");
        }
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            doRecursionDeepGoRound(i, visited, method);
        }
    }

    private void doRecursionDeepGoRound(int node, boolean[] visited, Consumer<Integer> method) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;
        method.accept(node);

        for (int i = node; i < graph[node].length; i++) {
            if (graph[node][i] > 0) {
                doRecursionDeepGoRound(i, visited, method);
            }
        }
    }
}
