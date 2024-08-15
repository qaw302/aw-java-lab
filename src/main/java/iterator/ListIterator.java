package iterator;

import java.util.List;
import java.util.NoSuchElementException;

public class ListIterator<E> implements java.util.ListIterator<E> {
    private List<E> list;
    private int index;

    public ListIterator(List<E> list) {
       this(list, -1);
    }

    public ListIterator(List<E> list, int index) {
        this.list = list;
        this.index = index-1;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public E next() {
        if (hasNext()) {
            return list.get(++index);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public E previous() {
        if (index < 1) {
            throw new NoSuchElementException();
        }
        return list.get(index--);
    }

    @Override
    public int nextIndex() {
        return index+1;
    }

    @Override
    public int previousIndex() {
        return index;
    }

    @Override
    public void remove() {
        list.remove(index--);
    }

    @Override
    public void set(E e) {
        list.set(index, e);
    }

    @Override
    public void add(E e) {
        list.add(++index, e);
    }
}
