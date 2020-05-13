package com.company;

import java.util.ArrayList;
import java.util.Stack;

public class Tree {
    Node root;
    ArrayList<Character> OPERATORS;

    Tree(String expression) {
        this.OPERATORS = new ArrayList<>();
        OPERATORS.add('+');
        OPERATORS.add('-');
        OPERATORS.add('*');
        OPERATORS.add('/');
//        root = new Node("");
        String polishLine = getPolishLine(expression);
        System.out.println("Polish line: " + polishLine);
        System.out.println("Decoded expression: " + getDecodedExpression(polishLine));
        buildTree(polishLine);
//        System.out.println(root.right.value);
    }

    public void preorder(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    public void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.value + " ");
        }
    }

    public void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.value + " ");
            inorder(node.right);
        }
    }

    private int prior(char symbol) {
        switch (symbol) {
            case '+':
            case '-':
                return 2;

            case '*':
            case '/':
                return 3;

            case '(':
                return 1;
        }
        return -1;
    }


    private String getDecodedExpression(String polishLine) {
        Stack<String> stack = new Stack<>();
        char[] charsOfLine = polishLine.toCharArray();
        String output = "";
        int i = 0;
        while (i < charsOfLine.length) {
            if (charsOfLine[i] >= 97 && charsOfLine[i] <= 122) {
                stack.add(Character.toString(charsOfLine[i]));
            }
            else if (OPERATORS.contains(charsOfLine[i])) {
                String secondPart = stack.pop(), firstPart = stack.pop();
                String newPart = "(" + firstPart + charsOfLine[i] + secondPart + ")";
                stack.add(newPart);
            }
            i++;
        }

        output += stack.pop();
        return output;
    }

    private String getPolishLine(String expression) {
        char[] charsOfExression = expression.toCharArray();
        Stack<Character> stack = new Stack<>();
        int i = 0;
        String output = "";
        while (i < charsOfExression.length) {
            if (charsOfExression[i] >= 97 && charsOfExression[i] <= 122) {
                output += Character.toString(charsOfExression[i]);
            }
            else if (charsOfExression[i] == '(') {
                stack.add(charsOfExression[i]);
            }
            else if (charsOfExression[i] == ')') {
                do {
                    output += stack.pop();
                } while (stack.lastElement() != '(');
                stack.pop();

            }
            else if (this.OPERATORS.contains(charsOfExression[i])) {

                while (stack.size() != 0 && prior(stack.lastElement()) >= prior(charsOfExression[i])) {
                    output += Character.toString(stack.pop());
                }
                if (stack.size() == 0 || prior(stack.lastElement()) < prior(charsOfExression[i])) {
                    stack.add(charsOfExression[i]);
                }

            }
            i++;
        }

        while (stack.size() != 0)
            output += stack.pop();

        return output;

    }


    private void buildTree(String polishLine) {
        Stack<Node> stack = new Stack<>();
        char[] charsOfLine = polishLine.toCharArray();
        int i = 0;
        while (i < charsOfLine.length) {
            if (charsOfLine[i] >= 97 && charsOfLine[i] <= 122) {
                Node node = new Node(Character.toString(charsOfLine[i]));
                stack.add(node);
            }
            else if (OPERATORS.contains(charsOfLine[i])) {
                Node secondNode = stack.pop(), firstNode = stack.pop();
                Node parentNode = new Node(Character.toString(charsOfLine[i]));
                firstNode.prev = parentNode;
                secondNode.prev = parentNode;

                parentNode.left = firstNode;
                parentNode.right = secondNode;
                stack.add(parentNode);
            }
            i++;
        }
        this.root = stack.pop();
    }


}
