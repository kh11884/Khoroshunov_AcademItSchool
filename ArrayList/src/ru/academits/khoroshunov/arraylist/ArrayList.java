package ru.academits.khoroshunov.arraylist;

import java.util.*;

public class ArrayList<E> implements List<E> {
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
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Передан пустой массив.");
        }

        if (a.length < length) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, length);
        }
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, length);
        if (a.length > length)
            a[length] = null;
        return a;
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
    public boolean addAll(Collection<? extends E> c) {
        int resultLength = length;
        if (length + c.size() >= items.length) {
            ensureCapacity(length + c.size());
        }
        for (Object element : c) {
            //noinspection unchecked
            items[length] = (E) element;
            length++;
        }
        if (resultLength != length) {
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Значение индекса за пределами списка.");
        }
        if (c == null) {
            throw new NullPointerException("Передана пустая коллекция.");
        }

        int resultLength = length;
        int newLength = length - index + c.size();
        if (newLength > items.length) {
            ensureCapacity(length - index + c.size());
        }
        System.arraycopy(items, index, items, index + c.size(), length - index);
        for (Object element : c) {
            //noinspection unchecked
            items[index] = (E) element;
            index++;
        }
        length += c.size();
        if (resultLength != length) {
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean isModified = false;
        for (int i = 0; i < length; i++) {
            boolean mustBeDeleted = true;
            for (Object element : c) {
                if (Objects.equals(items[i], element)) {
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
        for (Object element : c) {
            for (int j = 0; j < length; j++) {
                if (Objects.equals(items[j], element)) {
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
        for (Object element : c) {
            isContains = false;
            for (int i = 0; i < length; i++) {
                if (Objects.equals(items[i], element)) {
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
    public ListIterator<E> listIterator() {
        return null;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
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


