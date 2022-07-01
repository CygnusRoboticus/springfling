package com.example.springfling.kata.ds;

import java.util.*;

public class LinkedList<T> implements List<T> {
    private Optional<Node<T>> Head;
    private Optional<Node<T>> Tail;

    public LinkedList() {
        Head = Optional.empty();
        Tail = Optional.empty();
    }

    @Override
    public int size() {
        Optional<Node<T>> current = Head;
        int size = 0;
        while (current.isPresent()) {
            size++;
            current = current.flatMap(c -> c.Next);
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return Head.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            var curr = iterator.next();
            if (curr.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Iterator<Node<T>> nodeIterator = nodeIterator();

            @Override
            public boolean hasNext() {
                return nodeIterator.hasNext();
            }

            @Override
            public T next() {
                var current = nodeIterator.next();
                return current != null ? current.Data : null;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        int i = 0;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            array[i] = iterator.next();
            i++;
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        Optional<Node<T>> subject = Optional.of(new LinkedList.Node(t));
        if (Head.isEmpty()) {
            Head = Tail = subject;
        } else {
            subject.get().Prev = Tail;
            Tail.get().Next = subject;
            Tail = subject;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Iterator<Node<T>> iterator = nodeIterator();
        while (iterator.hasNext()) {
            var curr = iterator.next();
            if (curr.Data.equals(o)) {
                removeNode(curr);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().filter(item -> contains(item)).count() == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (var item : c) {
            if (!add(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (var item : c) {
            if (!remove(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            var curr = iterator.next();
            if (!c.contains(curr)) {
                remove(curr);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        Iterator<Node<T>> iterator = nodeIterator();
        while (iterator.hasNext()) {
            var curr = iterator.next();
            curr.Next = Optional.empty();
            curr.Prev = curr.Next;
        }
        Head = Optional.empty();
        Tail = Head;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            return null;
        }

        int i = 0;
        Iterator<T> iterator = iterator();
        while (i <= index && iterator.hasNext()) {
            var curr = iterator.next();
            if (i == index) {
                return curr;
            }
            i++;
        }
        return null;
    }

    @Override
    public T set(int index, T element) {
        if (index < 0) {
            return null;
        }

        int i = 0;
        Iterator<Node<T>> iterator = nodeIterator();
        while (i <= index && iterator.hasNext()) {
            var curr = iterator.next();
            if (i == index) {
                return curr.Data = element;
            }
            i++;
        }
        return null;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0) {
            return;
        } else if (Head.isEmpty() && index == 0 || index == size()) {
            add(element);
            return;
        }

        int i = 0;
        Iterator<Node<T>> iterator = nodeIterator();
        while (i <= index && iterator.hasNext()) {
            var curr = iterator.next();
            if (i == index) {
                Node<T> node = new Node(element);
                var next = curr.Next;
                curr.Next = Optional.of(node);
                node.Next = next;
                node.Prev = Optional.of(curr);
                if (next.isPresent()) {
                    next.get().Prev = Optional.of(node);
                }
                return;
            }
            i++;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            return null;
        }

        int i = 0;
        Iterator<Node<T>> iterator = nodeIterator();
        while (iterator.hasNext()) {
            var curr = iterator.next();
            if (i == index) {
                var next = curr.Next;
                var prev = curr.Prev;
                next.map(n -> n.Prev = prev);
                prev.map(p -> p.Next = next);
            }
            i++;
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        int i = 0;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            var curr = iterator.next();
            if (curr.equals(o)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Iterator<Node<T>> iterator = reverseNodeIterator();
        while (iterator.hasNext()) {
            var curr = iterator.next();
            if (curr.Data.equals(o)) {
                return nodeIndexOf(curr);
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            Optional<Node<T>> current = Head;

            @Override
            public boolean hasNext() {
                return current.isPresent();
            }

            @Override
            public T next() {
                var curr = current;
                current = current.flatMap(c -> c.Next);
                return curr.map(c -> c.Data).orElse(null);
            }

            @Override
            public boolean hasPrevious() {
                return current.flatMap(c -> c.Prev).isPresent();
            }

            @Override
            public T previous() {
                current = current.flatMap(c -> c.Prev);
                return current.map(c -> c.Data).orElse(null);
            }

            @Override
            public int nextIndex() {
                var index = indexOf(current);
                return index + 1 < size() ? index + 1 : -1;
            }

            @Override
            public int previousIndex() {
                var index = indexOf(current);
                return index > 0 ? index - 1 : -1;
            }

            @Override
            public void remove() {
                var curr = next();
                if (curr != null) {
                    LinkedList.this.remove(curr);
                }
            }

            @Override
            public void set(T t) {
                current.map(c -> c.Data = t);
            }

            @Override
            public void add(T t) {
                LinkedList.this.add(t);
            }
        };
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        int i = 0;
        ListIterator<T> iterator = listIterator();
        while (i <= index && iterator.hasNext()) {
            iterator.next();
        }
        return iterator;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        int i = 0;
        ArrayList<T> list = new ArrayList<T>();
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            var curr = iterator.next();
            if (i >= fromIndex && i < toIndex) {
                list.add(curr);
            } else if (i >= toIndex) {
                break;
            }
            i++;
        }
        return list;
    }

    protected void removeNode(Node<T> node) {
        if (node.equals(Head.orElse(null)) && node.equals(Tail.orElse(null))) {
            Head = Optional.empty();
            Tail = Optional.empty();
        } else if (node.equals(Head.orElse(null))) {
            Head = node.Next;
        } else if (node.equals(Tail.orElse(null))) {
            Tail = node.Prev;
        } else {
            var next = node.Next;
            var prev = node.Prev;
            if (next.isPresent()) {
                next.get().Prev = prev;
                prev.get().Next = next;
            }
        }
        node.Next = Optional.empty();
        node.Prev = Optional.empty();
    }

    protected int nodeIndexOf(Node<T> node) {
        int i = 0;
        Iterator<Node<T>> iterator = nodeIterator();
        while (iterator.hasNext()) {
            var curr = iterator.next();
            if (curr.equals(node)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    protected Iterator<Node<T>> nodeIterator() {
        return new Iterator<Node<T>>() {
            Optional<Node<T>> current = Head;

            @Override
            public boolean hasNext() {
                return current.isPresent();
            }

            @Override
            public Node<T> next() {
                var curr = current;
                current = current.flatMap(c -> c.Next);
                return curr.orElse(null);
            }
        };
    }

    protected Iterator<Node<T>> reverseNodeIterator() {
        return new Iterator<Node<T>>() {
            Optional<Node<T>> current = Tail;

            @Override
            public boolean hasNext() {
                return current.isPresent();
            }

            @Override
            public Node<T> next() {
                var curr = current;
                current = current.flatMap(c -> c.Prev);
                return curr.orElse(null);
            }
        };
    }

    protected class Node<T> {
        T Data;
        Optional<Node<T>> Next;
        Optional<Node<T>> Prev;

        Node(T data, Node next, Node prev) {
            Data = data;
            Next = Optional.of(next);
            Prev = Optional.of(prev);
        }

        Node(T data, Node prev) {
            Data = data;
            Next = Optional.empty();
            Prev = Optional.of(prev);
        }

        Node(T data) {
            Data = data;
            Next = Optional.empty();
            Prev = Optional.empty();
        }
    }
}
