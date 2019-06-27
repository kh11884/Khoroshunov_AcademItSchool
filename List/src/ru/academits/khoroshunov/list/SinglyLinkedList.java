package ru.academits.khoroshunov.list;

import ru.academits.khoroshunov.item.ListItem;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getSize() {
        return count;
    }

    public T getFirst() {
        return head.getData();
    }

    public T get(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Значение индекса за пределами списка.");
        }

        int i = 0;
        ListItem<T> p = head;
        if (p == null) {
            return null;
        } else {
            while (p.getNext() != null && i != index) {
                p = p.getNext();
                i++;
            }
            return p.getData();
        }
    }

    public T set(int index, T data) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Значение индекса за пределами списка.");
        }

        int i = 0;
        ListItem<T> p = head;
        while (p.getNext() != null && i != index) {
            p = p.getNext();
            i++;
        }

        T value = p.getData();
        p.setData(data);
        return value;
    }

    public T delete(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Значение индекса за пределами списка.");
        }

        int i = 0;
        ListItem<T> p = head;
        ListItem<T> prev = null;
        while (p.getNext() != null && i != index) {
            prev = p;
            p = p.getNext();
            i++;
        }

        if (prev != null) {
            prev.setNext(p.getNext());
        } else {
            head = p.getNext();
        }
        count--;
        return p.getData();
    }

    public void add(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void insert(int index, T data) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Значение индекса за пределами списка.");
        }

        int i = 0;
        ListItem<T> p = head;
        ListItem<T> prev = null;
        while (p != null && i != index) {
            prev = p;
            p = p.getNext();
            i++;
        }

        ListItem<T> item = new ListItem<>(data);
        if (prev != null) {
            prev.setNext(item);
        } else {
            head = item;
        }
        item.setNext(p);
        count++;
    }

    public boolean delete(T data) {
        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (data.equals(p.getData())) {
                if (prev != null) {
                    prev.setNext(p.getNext());
                } else {
                    head = p.getNext();
                }
                count--;
                return true;
            }
        }
        return false;
    }

    public boolean deleteAll(T data) {
        boolean result = false;
        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (data.equals(p.getData())) {
                if (prev != null) {
                    prev.setNext(p.getNext());
                } else {
                    head = p.getNext();
                }
                count--;
                result = true;
            }
        }
        return result;
    }

    public T deleteFirst() {
        T data = head.getData();
        head = head.getNext();
        count--;
        return data;
    }

    public void reverse() {
        if (head == null) {
            return;
        }
        ListItem<T> next = head.getNext();
        int listSize = count;

        add(head.getData());
        head.setNext(null);

        for (int i = 1; i < listSize; i++) {
            add(next.getData());
            next = next.getNext();
        }
        count = listSize;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            newList.add(p.getData());
        }
        newList.reverse();
        return newList;
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            b.append(p.getData());
            if (p.getNext() != null) {
                b.append(", ");
            }
        }
        return b.append(']').toString();
    }
}
