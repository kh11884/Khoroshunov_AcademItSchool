package ru.academits.khoroshunov.hashtable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private int size;
    private ArrayList<T>[] hashCell;
    private int modCount;

    public HashTable() {
        size = 11;
        //noinspection unchecked
        hashCell = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            hashCell[i] = new ArrayList<>();
        }
    }

    public HashTable(int size) {
        this.size = size;
        //noinspection unchecked
        hashCell = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            hashCell[i] = new ArrayList<>();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (ArrayList<T> cell : hashCell) {
            if (cell != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        if (isEmpty()) {
            throw new NullPointerException("Метод вызывется от пустой Хэш-таблицы.");
        }
        int objectHash = Math.abs(o.hashCode() % size());
        return hashCell[objectHash].contains(o);
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int saveModCount = modCount;

        public boolean hasNext() {
            Object[] array = toArray();
            return currentIndex + 1 < array.length;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (saveModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            ++currentIndex;
            Object[] array = toArray();
            //noinspection unchecked
            return (T) array[currentIndex];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        ArrayList<T> array = new ArrayList<>();
        for (ArrayList<T> cell : hashCell) {
            array.addAll(cell);
        }
        return array.toArray();
    }

    @Override
    public boolean add(Object o) {
        int hashCode = Math.abs(o.hashCode() % size);
        int checkValue = hashCell[hashCode].size();
        //noinspection unchecked
        hashCell[hashCode].add((T) o);
        if (checkValue == hashCell[hashCode].size()) {
            return false;
        }
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int hashCode = Math.abs(o.hashCode() % size);
        int checkValue = hashCell[hashCode].size();
        hashCell[hashCode].remove(o);
        if (checkValue == hashCell[hashCode].size()) {
            return false;
        }
        modCount++;
        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        int checkValue = 0;
        for (Object element : c) {
            if (add(element)) {
                checkValue++;
            }
        }
        return checkValue > 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            hashCell[i].clear();
        }
        modCount++;
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean isModified = false;
        for (int i = 0; i < size; i++) {
            //noinspection SuspiciousMethodCalls
            if (hashCell[i].retainAll(c)) {
                isModified = true;
                modCount++;
            }
        }
        return isModified;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean isModified = false;
        for (Object element : c) {
            int hashCode = Math.abs(element.hashCode() % size);
            //noinspection SuspiciousMethodCalls
            while (hashCell[hashCode].remove(element)) {
                isModified = true;
                modCount++;
            }
        }
        return isModified;
    }

    @Override
    public boolean containsAll(Collection c) {
        boolean isContains;
        for (Object element : c) {
            isContains = false;
            int hashCode = Math.abs(element.hashCode() % size);
            if (hashCell[hashCode] == null) {
                return false;
            }
            for (int i = 0; i < hashCell[hashCode].size(); i++) {
                if (Objects.equals(hashCell[hashCode].get(i), element)) {
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
    public <E> E[] toArray(E[] a) {
        if (a == null) {
            throw new NullPointerException("Передан пустой массив.");
        }

        //noinspection unchecked
        E[] array = (E[]) toArray();
        if (a.length < array.length) {
            return Arrays.copyOf(array, array.length);
        }

        System.arraycopy(array, 0, a, 0, array.length);
        if (a.length > array.length) {
            a[array.length] = null;
        }
        return a;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; i < size; i++) {
            b.append(i);
            b.append("=");
            b.append(Arrays.toString(hashCell[i].toArray()));
            if (i < size - 1) {
                b.append(", ");
            }
        }
        return b.append(']').toString();
    }
}
