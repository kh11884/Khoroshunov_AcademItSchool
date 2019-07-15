package ru.academits.khoroshunov.arraylist;

import java.util.*;

public class ArrayList<E> implements List {
    private int length;
    private int modCount;
    private E[] items;

    public ArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[10];
        length = 0;
    }

    public ArrayList(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Длина списка не может быть меньше 1");
        }
        //noinspection unchecked
        items = (E[]) new Object[capacity];
        length = 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
            return indexOf(o) > -1;
    }

    private class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private int saveModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < length;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (saveModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            ++currentIndex;
            return items[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    @Override
    public E[] toArray() {
        return Arrays.copyOf(items, length);
    }

    @Override
    public boolean add(Object data) {
        if (length == items.length) {
            increaseCapacity();
        }
        //noinspection unchecked
        items[length] = (E) data;
        modCount++;
        length++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(o, items[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        //noinspection unchecked
        E[] addedArray = (E[]) c.toArray();
        if (length + addedArray.length >= items.length) {
            ensureCapacity(length + addedArray.length);
        }
        System.arraycopy(addedArray, 0, items, length, addedArray.length);
        length += addedArray.length;
        modCount++;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        //noinspection unchecked
        E[] addedArray = (E[]) c.toArray();
        int resultArrayLength = length + addedArray.length;
        //noinspection unchecked
        E[] resultArray = (E[]) new Object[resultArrayLength];
        System.arraycopy(items, 0, resultArray, 0, index);
        System.arraycopy(addedArray, 0, resultArray, index, addedArray.length);
        System.arraycopy(items, index, resultArray, index + addedArray.length, length - index);
        items = resultArray;
        length = resultArrayLength;
        modCount++;
        return true;
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean isModified = false;
        //noinspection unchecked
        E[] newArray = (E[]) c.toArray();
        for (int i = 0; i < length; i++) {
            boolean mustBeDeleted = true;
            for (E e : newArray) {
                if (Objects.equals(items[i], e)) {
                    mustBeDeleted = false;
                    break;
                }
            }
            if (mustBeDeleted) {
                remove(i);
                i--;
                isModified = true;
            }
        }
        return isModified;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean isModified = false;
        //noinspection unchecked
        E[] newArray = (E[]) c.toArray();
        for (E e : newArray) {
            for (int j = 0; j < length; j++) {
                if (Objects.equals(items[j], e)) {
                    remove(j);
                    j--;
                    isModified = true;
                }
            }
        }
        return isModified;
    }

    @Override
    public boolean containsAll(Collection c) {
        boolean isContains;
        //noinspection unchecked
        E[] newArray = (E[]) c.toArray();
        for (E e : newArray) {
            isContains = false;
            for (int i = 0; i < length; i++) {
                if (Objects.equals(items[i], e)) {
                    isContains = true;
                    break;
                }
            }
            if (!isContains) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (a.length >= length) {
            a = Arrays.copyOf(toArray(), a.length);
        } else {
            a = toArray();
        }
        return a;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Значение индекса за пределами списка.");
        }
        return items[index];
    }

    @Override
    public E set(int index, Object data) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Значение индекса за пределами списка.");
        }
        E result = items[index];
        //noinspection unchecked
        items[index] = (E) data;
        return result;
    }

    @Override
    public void add(int index, Object element) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Значение индекса за пределами списка.");
        }

        if (length >= items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, length - index);
        //noinspection unchecked
        items[index] = (E) element;
        modCount++;
        length++;
    }

    public E remove(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Значение индекса за пределами списка.");
        }
        E result = items[index];
        if (index < length - 1) {
            System.arraycopy(items, index + 1, items, index, length - index - 1);
        }
        length--;
        modCount++;
        return result;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int i = length - 1;
        for (; i > -1; ) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
            i--;
        }
        return i;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public ListIterator listIterator() {
        return null;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public ArrayList subList(int fromIndex, int toIndex) {
        return null;
    }

    public void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity > items.length) {
            int newSize = items.length;
            while (newSize < requiredCapacity) {
                newSize *= 2;
            }
            items = Arrays.copyOf(items, newSize);
        }
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void trimToSize() {
        if (length < items.length) {
            items = Arrays.copyOf(items, length);
        }
    }

    public void clear() {
        Arrays.fill(items, null);
        modCount++;
        length = 0;
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; i < length; i++) {
            b.append(items[i]);
            if (i < length - 1) {
                b.append(", ");
            }
        }
        return b.append(']').toString();
    }

    public int getArrayLengthForTest() {
        return items.length;
    }

    public E[] getArrayForTest() {
        return items;
    }
}


