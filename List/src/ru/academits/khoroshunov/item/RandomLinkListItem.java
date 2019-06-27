package ru.academits.khoroshunov.item;

public class RandomLinkListItem<T> {
    private T data;
    private RandomLinkListItem<T> next;
    private RandomLinkListItem<T> randomLink;

    public RandomLinkListItem(T data) {
        this.data = data;
    }

    public RandomLinkListItem(T data, RandomLinkListItem<T> next) {
        this.data = data;
        this.next = next;
    }

    public RandomLinkListItem<T> getRandomLink() {
        return randomLink;
    }

    public void setRandomLink(RandomLinkListItem<T> randomLink) {
        this.randomLink = randomLink;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RandomLinkListItem<T> getNext() {
        return next;
    }

    public void setNext(RandomLinkListItem<T> next) {
        this.next = next;
    }
}
