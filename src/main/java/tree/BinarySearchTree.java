package tree;

import java.util.NoSuchElementException;

public class BinarySearchTree<K extends Comparable<K>,V> {
    private Node<K,V> root;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(K key,V value){
        if (isEmpty()) {
            root = new Node<>(key, value, null, null);
            size++;
        } else {
            Node<K,V> current = root;
            while (current != null) {
                if (key.compareTo(current.item.key) == 0) {
                    current.item.value = value;
                } else if (key.compareTo(current.item.key) < 0) {
                    if (current.left == null) {
                        current.left = new Node<>(key, value, null, null);
                        size++;
                        break;
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new Node<>(key, value, null, null);
                        size++;
                        break;
                    }
                    current = current.right;
                }
            }
        }
    }

    public void remove(K key){
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            if (key.compareTo(root.item.key) == 0) {
                Node<K,V> rRight = root.right;

                if (root.left == null) {
                    root = rRight;
                } else {
                    Node<K,V> current = root.left;
                    while (current.right != null) {
                        current = current.right;
                    }
                    current.right = rRight;
                }
            } else {


            }
        }

    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            sb.append(root.toString());
        }

        return sb.insert(1, "[").append("]").toString();
    }

    private static class Entry<K extends  Comparable<K>, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + ":" + value;
        }
    }

    private static class Node<K extends Comparable<K>,V> {
        private Entry<K,V> item;
        private Node<K,V> left;
        private Node<K,V> right;

        public Node(K key, V value, Node<K,V> left, Node<K,V> right) {
            this.item = new Entry<>(key, value);
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            if (left == null) {
                sb.append("*");
            } else {
                sb.append(left.toString());
            }
            sb.append(" , ");
            sb.append(item.toString());
            sb.append(" , ");

            if (right == null) {
                sb.append("*");
            } else {
                sb.append(right.toString());
            }

            return sb.insert(0,"(").append(")").toString();
        }
    }
}
