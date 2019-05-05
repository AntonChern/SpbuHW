package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    void add() {
        Trie trie = new Trie();
        assertTrue(trie.add("string1"));
        assertFalse(trie.add("string1"));
        assertTrue(trie.add("string2"));
        assertTrue(trie.add("string3"));
    }

    @Test
    void contains() {
        Trie trie = new Trie();
        trie.add("string");

        assertTrue(trie.contains("string"));
        assertFalse(trie.contains("temp"));
    }

    @Test
    void remove() {
        Trie trie = new Trie();
        trie.add("string");
        assertTrue(trie.remove("string"));
        assertFalse(trie.remove("string"));
    }

    @Test
    void size() {
        Trie trie = new Trie();
        trie.add("string1");
        trie.add("string2");
        trie.add("string3");

        assertEquals(3, trie.size());
    }

    @Test
    void howManyStartWithPrefix() {
        Trie trie = new Trie();
        trie.add("Immortal");
        trie.add("Immortality");
        trie.add("Immovable");
        trie.add("Impossible");
        trie.add("Impress");
        trie.add("Chain");
        trie.add("Chair");

        assertEquals(trie.size(), trie.howManyStartWithPrefix(""));
        assertEquals(3, trie.howManyStartWithPrefix("Immo"));
        assertEquals(2, trie.howManyStartWithPrefix("Imp"));
        assertEquals(2, trie.howManyStartWithPrefix("Chai"));
    }

    @Test
    void serializeAndDeserialize() throws IOException {
        Trie trie = new Trie();
        trie.add("Hull");
        trie.add("Hum");
        trie.add("Human");
        trie.add("Humanity");
        trie.add("Neck");
        trie.add("Neatly");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        trie.serialize(out);

        trie.add("odd");

        ByteArrayInputStream inputStream = new ByteArrayInputStream(out.toByteArray());
        trie.deserialize(inputStream);

        assertFalse(trie.contains("odd"));

        assertTrue(trie.contains("Hull"));
        assertTrue(trie.contains("Hum"));
        assertTrue(trie.contains("Human"));
        assertTrue(trie.contains("Humanity"));
        assertTrue(trie.contains("Neck"));
        assertTrue(trie.contains("Neatly"));
    }
}