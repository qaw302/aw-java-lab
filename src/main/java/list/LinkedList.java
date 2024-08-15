package list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList<E> implements List<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && first == null && last == null;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null || isEmpty()) {
            return false;
        }
        Node<E> p = first;
        while (p != null) {
            if (p.value.equals(o)) {
                return true;
            }
            p = p.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new iterator.ListIterator<>(this);
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        Node<E> p = first;

        for (int i = 0; i < size; i++) {
            result[i] = p.value;
            p = p.next;
        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) new Object[size];
        }
        Node<E> p = first;
        for (int i = 0; i < size; i++) {
            a[i] = (T) p.value;
        }
        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        if (isEmpty()) {
            first = last = new Node<>(e, null, null);
        } else {
            last.next = new Node<>(e, last, null);
            last = last.next;
        }

        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null || isEmpty()) {
            throw new NullPointerException();
        }

        Node<E> p;
        if ((p=first).value.equals(o)) {
            first = first.next;
            first.prev = null;
            size--;
            return true;
        } else if ((p=last).value.equals(o)) {
            last = last.prev;
            last.next = null;
            size--;
            return true;
        } else {
            p = first.next;
            while (p.next != null) {
                if (p.value.equals(o)) {
                    p.prev.next = p.next;
                    p.next.prev = p.prev;
                    size--;
                    return true;
                }
                p = p.next;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        for (E e : c) {
            add(index++, e);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            remove(o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (E o : this) {
            if (!c.contains(o)) {
                remove(o);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        Node<E> p = first;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.value;
    }

    @Override
    public E set(int index, E element) {
        Node<E> p = first;
        for (int i=0; i<index; i++) {
            p = p.next;
        }
        E old = p.value;
        p.value = element;

        return old;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            first = new Node<>(element, null, first);
        } else if (index == size - 1) {
            add(element);
        } else {
            Node<E> p = first;
            for (int i=0; i<index; i++) {
                p = p.next;
            }
            p.prev.next = new Node<>(element, p.prev, p);
            p.prev = p.prev.next;
            size++;
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E old;
        if (index == 0) {
            old = first.value;
            first = first.next;
            first.prev = null;
        } else if (index == size - 1) {
            old = last.value;
            last = last.prev;
            last.next = null;
        } else {
            Node<E> p = first;
            for (int i=0; i<index; i++) {
                p = p.next;
            }
            old = p.value;

            p.prev.next = p.next;
            p.next.prev = p.prev;
        }

        size--;
        return old;
    }

    @Override
    public int indexOf(Object o) {
        Node<E> p = first;
        for (int i=0; i<size; i++) {
            if (p.value.equals(o)) {
                return i;
            }
            p = p.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        Node<E> p = first;
        for (int i=0; i<size; i++) {
            if (p.value.equals(o)) {
                index = i;
            }
            p = p.next;
        }
        return index;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new iterator.ListIterator<>(this);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new iterator.ListIterator<>(this, index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    private static class Node<E> {
        private E value;
        private  Node<E> prev;
        private Node<E> next;

        public Node (E value, Node<E> prev, Node<E> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}