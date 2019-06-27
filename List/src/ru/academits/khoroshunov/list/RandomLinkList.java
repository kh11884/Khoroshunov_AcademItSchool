package ru.academits.khoroshunov.list;

import ru.academits.khoroshunov.item.RandomLinkListItem;

public class RandomLinkList<T> {

    private RandomLinkListItem<T> head;
    private int count;


    private void add(T data) {
        head = new RandomLinkListItem<>(data, head);
        count++;
    }

    public void add(RandomLinkListItem<T> item) {
        item.setNext(head);
        head = item;
        count++;
    }

    private void reverse() {
        if (head == null) {
            return;
        }
        RandomLinkListItem<T> next = head.getNext();
        int listSize = count;

        add(head.getData());
        head.setNext(null);

        for (int i = 1; i < listSize; i++) {
            add(next.getData());
            next = next.getNext();
        }
        count = listSize;
    }

    public RandomLinkList<T> copy() {
        RandomLinkList<T> newList = new RandomLinkList<>();
        for (RandomLinkListItem<T> p = head; p != null; p = p.getNext()) {
            newList.add(p.getData());
        }
        newList.reverse();
        RandomLinkListItem<T> linkedItem;
        RandomLinkListItem<T> newListCurrentItem = newList.head;

        for (RandomLinkListItem<T> p = head; p != null; p = p.getNext()) {
            linkedItem = p.getRandomLink();
            if (linkedItem == null) {
                newListCurrentItem = newListCurrentItem.getNext();
                continue;
            }
            RandomLinkListItem<T> newListStepItem = newList.head;
            for (RandomLinkListItem<T> stepItem = head; !stepItem.equals(linkedItem); stepItem = stepItem.getNext()) {
                newListStepItem = newListStepItem.getNext();
            }
            newListCurrentItem.setRandomLink(newListStepItem);
            newListCurrentItem = newListCurrentItem.getNext();
        }
        return newList;
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (RandomLinkListItem<T> p = head; p != null; p = p.getNext()) {
            b.append(p.getData());
            if (p.getNext() != null) {
                b.append(", ");
            }
        }
        return b.append(']').toString();
    }

    public void printDataLinkedElement() {
        for (RandomLinkListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getRandomLink() == null) {
                System.out.print("null");
            } else {
                System.out.print(p.getRandomLink().getData());

            }
            if (p.getNext() != null) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
