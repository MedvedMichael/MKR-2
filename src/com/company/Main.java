package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Input expression: ");
        Scanner scanner = new Scanner(System.in);

        Tree tree = new Tree(scanner.nextLine());

        System.out.print("Preorder: ");
        tree.preorder(tree.root);
        System.out.println();

        System.out.print("Inorder: ");
        tree.inorder(tree.root);
        System.out.println();

        System.out.print("Postorder: ");
        tree.postorder(tree.root);
        System.out.println();

        scanner.close();
    }
}
