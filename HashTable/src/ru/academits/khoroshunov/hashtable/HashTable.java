package ru.academits.khoroshunov.hashtable;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private int size;
    private ArrayList<E>[] hashCell;
    private int modCount;

    public HashTable() {
        this(11);
    }

    public HashTable(int hashLevels) {
        if (hashLevels < 1) {
            throw new IllegalArgumentException("Размер Хэш-таблицы не может быть меньше 1.");
        }
        //noinspection unchecked
        hashCell = new ArrayList[hashLevels];
        for (int i = 0; i < hashLevels; i++) {
            hashCell[i] = new ArrayList<>();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return hashCell[0].contains(null);
        }
        return hashCell[getHashTableLevel(o)].contains(o);
    }

    private class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private int saveModCount = modCount;
        private int level = 0;
        private int position = 0;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Следующий элемент отсутсвует.");
            }
            if (saveModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменен во время выполенния итератора");
            }
            while (position >= hashCell[level].size()) {
                level++;
                position = 0;
            }
            currentIndex++;
            return hashCell[level].get(position++);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (E element : this) {
            array[i] = element;
            i++;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Передан пустой массив.");
        }

        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        boolean isAdded;
        isAdded = hashCell[getHashTableLevel(e)].add(e);
        size++;
        modCount++;
        return isAdded;
    }

    @Override
    public boolean remove(Object o) {
        boolean isRemoved;
        isRemoved = hashCell[getHashTableLevel(o)].remove(o);

        if (isRemoved) {
            modCount++;
            size--;
        }
        return isRemoved;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            int level = getHashTableLevel(element);
            //noinspection SuspiciousMethodCalls
            if (hashCell[level] == null || !hashCell[level].contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean isAdded = false;
        for (E element : c) {
            isAdded = add(element);
        }
        return isAdded;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isModified = false;
        for (ArrayList<E> cell : hashCell) {
            size -= cell.size();
            if (cell.retainAll(c)) {
                isModified = true;
                modCount++;
            }
            size += cell.size();
        }
        return isModified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isModified = false;
        for (Object element : c) {
            int level = getHashTableLevel(element);
            //noinspection SuspiciousMethodCalls
            while (hashCell[level].remove(element)) {
                isModified = true;
                modCount++;
                size--;
            }
        }
        return isModified;
    }

    @Override
    public void clear() {
        for (ArrayList<E> cell : hashCell) {
            cell.clear();
        }
        modCount++;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; i < hashCell.length; i++) {
            b.append(i);
            b.append("=");
            b.append(hashCell[i]);
            if (i < hashCell.length - 1) {
                b.append(", ");
            }
        }
        return b.append(']').toString();
    }

    private int getHashTableLevel(Object o) {
        if (o == null) {
            return 0;
        }
        return Math.abs(o.hashCode() % hashCell.length);
    }
}
