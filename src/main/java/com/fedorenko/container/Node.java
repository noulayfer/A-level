package com.fedorenko.container;

import com.fedorenko.model.Car;
import lombok.Getter;

@Getter
public class Node<E extends Car>{
    E element;
    protected Node<E> next;
    protected Node <E> prev;

    protected Node(E car, Node <E> prev, Node<E> next) {
       element = car;
       this.prev = prev;
       this.next = next;
    }
}
