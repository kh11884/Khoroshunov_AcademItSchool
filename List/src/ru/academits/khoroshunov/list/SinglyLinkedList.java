package ru.academits.khoroshunov.list;

import ru.academits.khoroshunov.item.ListItem;

import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getSize() {
        return count;
    }

    public T getFirst() {
        if (count == 0) {
            throw new IllegalArgumentException("Метод вызывается от пустого массива");
        }

        return head.getData();
    }

    private ListItem<T> getNode(int index) {
        ListItem<T> p = head;
        for (int i = 0; i < index; i++) {
            p = p.getNext();
        }
        return p;
    }

    public T get(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Значение индекса за пределами списка.");
        }
        return getNode(index).getData();
    }

    public T set(int index, T data) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Значение индекса за пределами списка.");
        }

        ListItem<T> node = getNode(index);
        T value = node.getData();
        node.setData(data);
        return value;
    }

    public T delete(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Значение индекса за пределами списка.");
        }

        ListItem<T> p = head;
        if (index == 0) {
            head = head.getNext();
        } else {
            ListItem<T> prev = getNode(index - 1);
            p = prev.getNext();
            prev.setNext(p.getNext());
        }
        count--;
        return p.getData();
    }

    public void add(T data) {
        ListItem<T> newElement = new ListItem<>(data);
        if (count == 0) {
            head = newElement;
        } else {
            ListItem<T> p = getNode(count - 1);
            p.setNext(newElement);
        }
        count++;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void insert(int index, T data) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Значение индекса за пределами списка.");
        }

        if (index == 0) {
            addFirst(data);
        } else {
            ListItem<T> item = new ListItem<>(data);
            ListItem<T> prev = getNode(index - 1);
            item.setNext(prev.getNext());
            prev.setNext(item);
            count++;
        }
    }

    public boolean delete(T data) {
        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (Objects.equals(data, p.getData())) {
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
        for (ListItem<T> p = head, prev = null; p != null; p = p.getNext()) {
            if (Objects.equals(data, p.getData())) {
                if (prev != null) {
                    prev.setNext(p.getNext());
                } else {
                    head = p.getNext();
                }
                count--;
                result = true;
            } else {
                prev = p;
            }
        }
        return result;
    }

    public T deleteFirst() {
        if (count == 0) {
            throw new IllegalArgumentException("Метод вызывается от пустого массива");
        }

        T data = head.getData();
        head = head.getNext();
        count--;
        return data;
    }

    public void reverse() {
        if (head == null || count == 1) {
            return;
        }

        ListItem<T> previous = null;
        ListItem<T> current = head;
        ListItem<T> next = head.getNext();

        while (next != null) {
            current.setNext(previous);
            previous = current;
            current = next;
            next = next.getNext();
        }
        current.setNext(previous);
        head = current;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();
        if (head == null) {
            return newList;
        }

        newList.head = new ListItem<>(head.getData());
        newList.count++;
        for (ListItem<T> p = head.getNext(), newP = newList.head; p != null; p = p.getNext(), newP = newP.getNext()) {
            newP.setNext(new ListItem<>(p.getData()));
            newList.count++;
        }
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
