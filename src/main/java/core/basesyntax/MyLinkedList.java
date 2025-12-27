package core.basesyntax;

import java.util.List;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private int size;
    private Node<T>  first;
    private Node<T> last;

    private static class Node<T> {
        Node<T> prev;
        T value;
        Node<T> next;

        Node(Node<T> prev, T value, Node<T> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private void checkIndex(int index) {
        if (index >= size && index < 0) {
            throw new IndexOutOfBoundsException("Index out of bound");
        }
    }


    @Override
    public void add(T value) {
            Node<T> newNode = new Node<>(last, value, null);
            if (size == 0) {
                last = newNode;
                first = newNode;
            } else {
                last.next = newNode;
                newNode.prev = last;
                last = newNode;
            }
            size++;
    }

    @Override
    public void add(T value, int index) {

    }

    @Override
    public void addAll(List<T> list) {
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    @Override
    public T set(T value, int index) {
        checkIndex(index);

        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        T removed = current.value;
        current.value = value;
        return removed;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    private Node<T> findNode(T object) {
        Node<T> current = first;

        while (current != null){
            if (object == null) {
                if (current == null){
                    return current;
                }
            }
            if (current.equals(object)){
                return current;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public boolean remove(T object) {
        if (size == 0) {
            return false;
        }
        Node<T> current = findNode(object);
        if (current == null) {
            return false;
        }
        if (current.prev == null){
            first = current.next;
            if(first != null) {
                first.prev = null;
            }
        }
        if (current.next == null) {
            last = current.prev;
            if(last != null) {
                last.next = null;
            }
        }

        if (current.prev != null && current.next != null) {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
