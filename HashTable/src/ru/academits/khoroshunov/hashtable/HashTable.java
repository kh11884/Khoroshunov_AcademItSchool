package ru.academits.khoroshunov.hashtable;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private int size;
    private int hashLevels;
    private ArrayList<E>[] hashCell;
    private int modCount;

    public HashTable() {
        this(11);
    }

    public HashTable(int hashLevels) {
        if (hashLevels < 1) {
            throw new IllegalArgumentException("Размер Хэш-таблицы не может быть меньше 1.");
        } else {
            this.hashLevels = hashLevels;
            //noinspection unchecked
            hashCell = new ArrayList[hashLevels];
            for (int i = 0; i < hashLevels; i++) {
                hashCell[i] = new ArrayList<>();
            }
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
        int objectHash = Math.abs(o.hashCode() % hashLevels);
        return hashCell[objectHash].contains(o);
    }

    private class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private int saveModCount = modCount;
        private Object[] array = toArray();

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
            ++currentIndex;
            //noinspection unchecked
            return (E) array[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        ArrayList<E> array = new ArrayList<>();
        for (ArrayList<E> cell : hashCell) {
            array.addAll(cell);
        }
        return array.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Передан пустой массив.");
        }

        if (a.length < size) {
            //noinspection unchecked
            return Arrays.copyOf((T[]) toArray(), size);
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
        int hashCode;
        if (e == null) {
            hashCode = 0;
        } else {
            hashCode = Math.abs(e.hashCode() % hashLevels);
        }
        isAdded = hashCell[hashCode].add(e);
        size++;
        modCount++;
        return isAdded;
    }

    @Override
    public boolean remove(Object o) {
        boolean isRemoved;
        int hashCode;
        if (o == null) {
            hashCode = 0;
        } else {
            hashCode = Math.abs(o.hashCode() % hashLevels);
        }
        isRemoved = hashCell[hashCode].remove(o);

        if (isRemoved) {
            modCount++;
            size--;
        }
        return isRemoved;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean isContains;
        for (Object element : c) {
            isContains = false;
            int hashCode = Math.abs(element.hashCode() % hashLevels);
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
    public boolean addAll(Collection<? extends E> c) {
        boolean isAdded = false;
        for (Object element : c) {
            //noinspection unchecked
            isAdded = add((E) element);
        }
        return isAdded;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isModified = false;
        for (int i = 0; i < hashLevels; i++) {
            size -= hashCell[i].size();
            if (hashCell[i].retainAll(c)) {
                isModified = true;
                modCount++;
            }
            size += hashCell[i].size();
        }
        return isModified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isModified = false;
        for (Object element : c) {
            int hashCode;
            if (element == null) {
                hashCode = 0;
            } else {
                hashCode = Math.abs(element.hashCode() % hashLevels);
            }
            //noinspection SuspiciousMethodCalls
            while (hashCell[hashCode].remove(element)) {
                isModified = true;
                modCount++;
                size--;
            }
        }
        return isModified;
    }

    @Override
    public void clear() {
        for (int i = 0; i < hashLevels; i++) {
            hashCell[i].clear();
        }
        modCount++;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; i < hashLevels; i++) {
            b.append(i);
            b.append("=");
            b.append(Arrays.toString(hashCell[i].toArray()));
            if (i < hashLevels - 1) {
                b.append(", ");
            }
        }
        return b.append(']').toString();
    }
}
