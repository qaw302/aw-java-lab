package list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayList<E> implements List<E> {
    private final static int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initalCapacity) {
        elements = (E[]) new Object[initalCapacity];
    }

        public boolean addAll(int index, Collection c) {
        return false;
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
        for (E element : elements) {
            if (element.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new iterator.ListIterator<>(this);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i=0; i<size; i++) {
            array[i] = elements[i];
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) new Object[size];
        } else if (a.length > size) {
            a[size] = null;
        }
        for (int i=0; i<size; i++) {
            a[i] = (T) elements[i];
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        if (size >= elements.length) {
            return false;
        }
        elements[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty() || !contains(o)) {
            return false;
        }
        for (int i=0; i<size; i++) {
            if (elements[i].equals(o)) {
                elements[i] = null;
                System.arraycopy(elements, i+1, elements, i, elements.length-i-1);

                break;
            }
        }
        size--;
        return true;
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
        if (c.size() > elements.length - size) {
            return false;
        }

        for (E e : c) {
            add(e);
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (!containsAll(c)) {
            return false;
        }
        for (Object o : c) {
            remove(o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (E e : elements) {
            if (!c.contains(e)) {
                remove(e);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        for (int i=0; i<size; i++) {
            elements[i] = null;

        }
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        if (elements[index] == null) {
            elements[index] = element;
        } else {
            System.arraycopy(element, index, element,index +1, size - index);
            elements[index] = element;
        }
        size++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        E old = elements[index];
        elements[index] = null;
        size--;
        return old;
    }

    @Override
    public int indexOf(Object o) {
        for (int i=0; i<size; i++) {
            if (elements[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i=0; i<size; i++) {
            if (elements[i].equals(o)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new iterator.ListIterator<E>(this);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return List.of();
    }

    public static void main(String[] args) {
        // MyList라는 이름의 사용자 정의 List 클래스 인스턴스 생성
        List<String> myList = new ArrayList<>();

        // List에 요소 추가
        myList.add("Apple");
        myList.add("Banana");
        myList.add("Cherry");

        // 예상 결과와 실제 결과 비교
        boolean sizeTest = myList.size() == 3;
        System.out.println("Test size == 3: " + sizeTest);  // true

        // List의 요소 비교
        boolean elementsTest = myList.get(0).equals("Apple") &&
                myList.get(1).equals("Banana") &&
                myList.get(2).equals("Cherry");
        System.out.println("Test elements == [Apple, Banana, Cherry]: " + elementsTest);  // true

        // 특정 요소 제거
        myList.remove("Banana");

        // 요소 제거 후 다시 테스트
        boolean removeTest = myList.size() == 2 &&
                myList.get(0).equals("Apple") &&
                myList.get(1).equals("Cherry");
        System.out.println("Test after removing 'Banana': " + removeTest);  // true

        // List의 첫 번째 요소 가져오기
        boolean firstElementTest = myList.get(0).equals("Apple");
        System.out.println("Test first element == 'Apple': " + firstElementTest);  // true

        // List 비우기
        myList.clear();
        boolean clearTest = myList.size() == 0;
        System.out.println("Test size == 0 after clearing: " + clearTest);  // true
    }
}
