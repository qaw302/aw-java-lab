package queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class ArrayQueue<E> implements Queue<E> {
    private final static int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;
    private int front;
    private int rear;

    public ArrayQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
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
        int index = front;
        while (index != rear) {
            if (index == elements.length) {
                index = 0;
            }
            if (elements[index++].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator<>();
    }

    @Override
    public Object[] toArray() {
        Queue<E> queue = new ArrayQueue<>();
        queue.addAll(this);

        Object[] array = new Object[size()];
        for (int i=0; i<size(); i++) {
            array[i] = queue.poll();
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Queue<E> queue = new ArrayQueue<>();
        queue.addAll(this);

        if (a.length < size()) {
            a = (T[]) new Object[size];
        }

        for (int i=0; i<size; i++) {
            a[i] = (T) queue.poll();
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    private void resize() {
        E[] resized = (E[]) new Object[elements.length * 2];
        int idx = front;
        for (int i=0; i<elements.length; i++) {
            if (idx == elements.length) {
                idx = 0;
            }
            resized[i] = elements[idx++];
        }
        front = 0;
        rear = elements.length;
    }

    @Override
    public boolean add(E e) {
        if (size == elements.length) {
            resize();
        }
        if (front > 0 && rear == elements.length) {
            rear = 0;
        }
        elements[rear++]=e;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }
        if (elements[front].equals(o)) {
            elements[front++] = null;
            size--;
            return true;
        } else if (elements[rear-1].equals(o)) {
            elements[--rear] = null;
            size--;
            return true;
        } else {
            int index = front;
            while (index != rear) {
                if (index == elements.length) {
                    index = 0;
                }
                if (elements[index].equals(o)) {
                    elements[index] = null;
                    size--;
                    return true;
                }
                index++;
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
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            remove(o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (E e : this) {
            if (!c.contains(e)) {
                remove(e);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        for (int i=0; i < elements.length; i++) {
            elements[i] = null;
        }
        size = 0;
        front = 0;
        rear = 0;
    }

    @Override
    public boolean offer(E e) {
        if (size == elements.length) {
            resize();
        }
        if (front > 0 && rear == elements.length) {
            rear = 0;
        }
        elements[rear++]=e;
        size++;

        return true;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E old = elements[front];
        elements[front++] = null;
        if (front == elements.length) {
            front=0;
        }
        if (front == rear) {
            front = 0;
            rear = 0;
        }
        size--;

        return old;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E old = elements[front];
        elements[front++] = null;

        if (front == elements.length) {
            front=0;
        }
        if (front == rear) {
            front = 0;
            rear = 0;
        }

        size--;

        return old;
    }

    @Override
    public E element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements[front];
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elements[front];
    }

    private class QueueIterator<E> implements Iterator<E> {
        private int index = front;

        @Override
        public boolean hasNext() {
            return index != rear;
        }

        @Override
        public E next() {
            if (index  == elements.length) {
                index = 0;
            }
            return (E) elements[index++];
        }
    }
}
