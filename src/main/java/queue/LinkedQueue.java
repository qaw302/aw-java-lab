package queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class LinkedQueue<E> implements Queue<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node<E> findNode(Object o) {
        if (o == null || isEmpty()) {
            return null;
        }
        Node<E> node = first;
        while (node != null) {
            if (node.value.equals(o)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return findNode(o) != null;
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];

        int i = 0;
        while (this.iterator().hasNext()) {
            result[i++] = this.iterator().next();
        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) new Object[size];
        }

        int i = 0;
        while (this.iterator().hasNext()) {
            a[i++] = (T) this.iterator().next();
        }

        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        if (isEmpty()) {
            first = new Node<>(e, null, null);
            last = first;
        } else {
            last.next = new Node<>(e, last, null);
            last = last.next;
        }

        size++;
        return true;
    }



    @Override
    public boolean remove(Object o) {
        Node<E> target = findNode(o);
        if (target == null) {
            return false;
        } else {
            target.prev.next = target.next;
            target.next.prev = target.prev;
            size --;
        }

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
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
    public boolean removeAll(Collection<?> c) {
        for (Object e : c) {
            if (!remove(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (E e : this) {
            if (!c.contains(e)) {
                if (!remove(e)) {
                    return false;
                }
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
    public boolean offer(E e) {
        boolean result;
        try {
            result = add(e);
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E value = first.value;
        first = first.next;
        first.prev = null;
        size--;

        return value;
    }

    @Override
    public E poll() {
        E value = null;
        try {
            value = remove();
        } catch (Exception e) {
            value = null;
        }
        size--;

        return value;
    }

    @Override
    public E element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.value;
    }

    @Override
    public E peek() {
        return first.value;
    }

    private static class Node<E> {
        private E value;
        private Node<E> prev;
        private Node<E> next;

        public Node(E value, Node<E> prev, Node<E> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private class QueueIterator implements Iterator<E> {
        Node<E> current = first;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public E next() {
            E next = current.value;
            current = current.next;
            return next;
        }
    }
}
