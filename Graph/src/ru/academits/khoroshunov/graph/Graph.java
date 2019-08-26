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

    public void widthGoRound(Consumer method) {
        if (graph == null) {
            throw new NullPointerException("Метод вызван от пустого графа.");
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        queue.add(0);

        while (!queue.isEmpty()) {
            int node = queue.remove();
            if (!visited[node]) {
                visited[node] = true;
                //noinspection unchecked
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

    public void deepGoRound(Consumer method) {
        if (graph == null) {
            throw new NullPointerException("Метод вызван от пустого графа.");
        }
        Deque<Integer> stack = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        stack.add(0);

        while (!stack.isEmpty()) {
            int node = stack.removeLast();
            if (!visited[node]) {
                visited[node] = true;
                //noinspection unchecked
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

    public void recursionDeepGoRound(Consumer method) {
        if (graph == null) {
            throw new NullPointerException("Метод вызван от пустого графа.");
        }
        boolean[] visited = new boolean[graph.length];
        doRecursionDeepGoRound(0, visited, method);
    }

    private void doRecursionDeepGoRound(int node, boolean[] visited, Consumer method) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;
        //noinspection unchecked
        method.accept(node);

        for (int i = node; i < graph[node].length; i++) {
            if (graph[node][i] > 0) {
                doRecursionDeepGoRound(i, visited, method);
            }
        }
    }
}
