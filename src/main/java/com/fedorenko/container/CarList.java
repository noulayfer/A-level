package com.fedorenko.container;

import com.fedorenko.model.Car;
import lombok.Getter;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;


public class CarList<E extends Car> implements Iterable<E> {
    protected Node<E> first;
    protected Node<E> last;
    @Getter
    private int size;

    public CarList() {
    }

    public void add(final E car) {
        if (first == null) {
            first = last = new Node<>(car, null, null);
        } else if (first.element.getId().equals(last.element.getId())) {
            first = new Node<>(car, null, last);
            last.prev = new Node<>(first.element, null, first.next);
        } else {
            Node<E> tempNode = new Node<>(first.element, first, first.next);
            first = new Node<>(car, null, tempNode);
            tempNode.next.prev = tempNode;
            first.next.prev = first;
        }
        size++;
    }

    public void addInEnd(final E car) {
        if (last == null) {
            last = first = new Node<>(car, null, null);
        } else if (last.element.getId().equals(first.element.getId())) {
            last = new Node<>(car, first, null);
            first.next = new Node<>(last.element, first, null);
        } else {
            Node<E> tempNode = new Node<>(last.element, last.prev, last);
            last = new Node<>(car, tempNode, null);
            tempNode.prev.next = tempNode;
            last.prev.next = last;
        }
        size++;
    }

    public int findByElement(final E element) {
        int i = 0;
        for (E car : this) {
            if (car.getId().equals(element.getId())) {
                break;
            }
            i++;
        }
        return i;
    }

    public void insertCar(final int index, final E element) {
        if (index == 0) {
            add(element);
            return;
        }
        if (index == this.size) {
            addInEnd(element);
            return;
        }
        Node<E> node = getNodeByIndex(index).orElseThrow();
        Node<E> tempNode = node.prev;
        Node<E> newNode = new Node<>(element, tempNode, node);
        tempNode.next = newNode;
        node.prev = newNode;
    }

    private Optional<Node<E>> getNodeByIndex(final int index) {
        if (index > this.size || index < 0) {
            return Optional.empty();
        }
        int i = 0;
        Node<E> current = first;
        while (i < index) {
            current = current.next;
            i++;
        }
        return Optional.of(current);
    }

    public void delete(final int index) {
        if (index < 0 || index > this.size) {
            return;
        }
        final Node<E> nodeByIndex = getNodeByIndex(index).orElseThrow();
        Node<E> prevNode = nodeByIndex.prev;
        Node<E> nextNode = nodeByIndex.next;
        prevNode.next = nextNode;
        nextNode.prev = nextNode;
    }

    public int sumAllCount() {
        int sum = 0;
        for (E car : this) {
            sum += car.getCount();
        }
        return sum;
    }

    @Override
    public String toString() {
        String result = "";
        for (E car : this) {
            result += car.toString();
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator<>(this);
    }

    class MyLinkedListIterator<T extends Car> implements Iterator<T> {
        Node<T> current;

        public MyLinkedListIterator(CarList<T> carList) {
            current = carList.first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.element;
            current = current.next;
            return data;
        }
    }
}
