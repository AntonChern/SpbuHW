package com.AntonChernikov.g144;

import java.io.*;
import java.util.ArrayList;

/** Class describing work with trie*/
public class Trie implements Serializer {

    private Node root;

    public Trie() {
        root = new Node('\0');
        root.quantity = 0;
    }

    /** Method returning node with a given character from parent*/
    private Node getChildFrom(Node parent, char symbol) {
        for (Node child : parent.next) {
            if (child.symbol == symbol) {
                return child;
            }
        }
        return null;
    }

    /** Method adding word to trie*/
    public boolean add(String element) {
        if (contains(element)) {
            return false;
        }
        Node current = root;
        int index = -1;
        for (int i = 0; i < element.length(); i++) {
            current.quantity++;
            Node child = getChildFrom(current, element.charAt(i));
            if (child == null) {
                index = i;
                break;
            }
            current = child;
        }
        if (index >= 0) {
            for (char symbol : element.substring(index).toCharArray()) {
                Node newNode = new Node(symbol);
                current.next.add(newNode);
                current = newNode;
            }
        }
        current.isTerminal = true;
        return true;
    }

    /** Method checking word for existence*/
    public boolean contains(String element) {
        Node current = root;
        for (int i = 0; i < element.length(); i++) {
            Node child = getChildFrom(current, element.charAt(i));
            if (child == null) {
                return false;
            }
            current = child;
        }
        return current.isTerminal;
    }

    /** Method removing word from trie*/
    public boolean remove(String element) {
        if (!contains(element)) {
            return false;
        }
        Node current = root;
        Node fork = root;
        int oddSymbolIndex = 0;
        for (int i = 0; i < element.length(); i++) {
            current.quantity--;
            if (current.next.size() > 1 || current.isTerminal) {
                fork = current;
                oddSymbolIndex = i;
            }
            current = getChildFrom(current, element.charAt(i));
        }
        if (current.next.isEmpty()) {
            Node deletedNode = null;
            for (Node possible : fork.next) {
                if (possible.symbol == element.charAt(oddSymbolIndex)) {
                    deletedNode = possible;
                }
            }
            fork.next.remove(deletedNode);
        } else {
            current.isTerminal = false;
        }
        return true;
    }

    /** Method returning the number of words from trie*/
    public int size() {
        return root.quantity;
    }

    /** Method returning the number of words from trie starting with the given prefix*/
    public int howManyStartWithPrefix(String prefix) {
        Node current = root;
        for (int i = 0; i < prefix.length(); i++) {
            Node child = getChildFrom(current, prefix.charAt(i));
            if (child == null) {
                return 0;
            }
            current = child;
        }
        return current.quantity;
    }

    /** {@inheritDoc}*/
    @Override
    public void serialize(OutputStream out) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        writeNode(writer, root);
        writer.close();
    }

    /** Method writing node to stream*/
    private void writeNode(BufferedWriter writer, Node node) throws IOException {
        writer.write(node.isTerminal + "\n");
        writer.write(node.quantity + "\n");
        writer.write(node.next.size() + "\n");

        for (Node current : node.next) {
            writer.write(current.symbol + "\n");
            writeNode(writer, current);
        }
    }

    /** {@inheritDoc}*/
    @Override
    public void deserialize(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        root.next = new ArrayList<>();
        root = readNode(reader, root);
    }

    /** Method returning the converted node from the stream*/
    private Node readNode(BufferedReader reader, Node node) throws IOException {
        node.isTerminal = Boolean.parseBoolean(reader.readLine());
        node.quantity = Integer.parseInt(reader.readLine());

        int size = Integer.parseInt(reader.readLine());
        for (int i = 0; i < size; i++) {
            Node newNode = new Node(reader.readLine().charAt(0));
            node.next.add(readNode(reader, newNode));
        }
        return node;
    }

    /** Class describing trie element*/
    private class Node {
        private char symbol;
        private boolean isTerminal = false;
        private int quantity = 1;
        private ArrayList<Node> next = new ArrayList<>();

        private Node(char symbol) {
            this.symbol = symbol;
        }
    }
}
