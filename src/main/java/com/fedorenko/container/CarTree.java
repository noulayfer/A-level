package com.fedorenko.container;

import com.fedorenko.model.Car;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CarTree<T extends Car> {
    private TreeNode<T> root;

    public CarTree(final T car) {
        this.root = new TreeNode(car, null, null);
    }

    public void addElement(TreeNode node, @NonNull T car) {
        if (node == null) {
            node = root;
        }
        if (car.getCount() < node.car.getCount()) {
            if (node.left == null) {
                node.left = new TreeNode<>(car, null, null);
                return;
            } else {
                addElement(node.left, car);
            }
        }
        if (car.getCount() > node.car.getCount()) {
            if (node.right == null) {
                node.right = new TreeNode(car, null, null);
            } else {
                addElement(node.right, car);
            }
        }
    }


    public void print(TreeNode startNode) {
        if (startNode != null) {
            print(startNode.left);
            System.out.println(startNode.car);
            print(startNode.right);
        }
    }

    public int sumCarCount(TreeNode startNode) {
        int x;
        int y;
        if (startNode != null) {
            x = startNode.right != null ? sumCarCount(startNode.right) : 0;
            y = startNode.left != null ? sumCarCount(startNode.left) : 0;
            return x + y + startNode.car.getCount();
        }
        return 0;
    }
}


@Getter
@Setter
class TreeNode<T extends Car> {
    Car car;
    TreeNode left;
    TreeNode right;

    public TreeNode(final Car car, final TreeNode left, final TreeNode right) {
        this.car = car;
        this.left = left;
        this.right = right;
    }
}
