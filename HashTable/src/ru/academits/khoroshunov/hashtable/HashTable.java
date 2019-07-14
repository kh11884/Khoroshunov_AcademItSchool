package ru.academits.khoroshunov.hashtable;




import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class HashTable<T> implements Collection<T>  {
    private int size;
    private ArrayList<T>[] hashCell;

    public HashTable(){
        size = 11;
        hashCell = new ArrayList[size];
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return hashCell == null;
    }

    @Override
    public boolean contains(Object o) {
        int objectHash = Math.abs(o.hashCode()%size());
        for(Object element: hashCell[objectHash]){
            if(Objects.equals(o, element)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        for (ArrayList<T> cell: hashCell)
              {ArrayList<T> array = new ArrayList<>();
                      array.addAll(cell);

        }
        return null ;
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
