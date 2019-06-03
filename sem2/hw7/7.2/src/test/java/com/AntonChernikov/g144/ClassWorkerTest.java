package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ClassWorkerTest {

    @Test
    void printStructure() throws MalformedURLException, ClassNotFoundException, FileNotFoundException {
        ClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> clazz = classLoader.loadClass("com.AntonChernikov.g144.Man");

        ClassWorker classWorker = new ClassWorker();
        classWorker.printStructure(clazz);

        String path = "src\\main\\java\\com\\AntonChernikov\\g144\\ResultOfPrinting\\Man.java";

        FileReader in = new FileReader(path);
        Scanner scanner = new Scanner(in);

        String[] actualStrings = new String[19];
        for (int i = 0; i < actualStrings.length; i++) {
            actualStrings[i] = scanner.nextLine();
        }

        String[] expectedStrings = {"package com.AntonChernikov.g144.ResultOfPrinting;",
                "",
                "public class Man extends Object {",
                "",
                "private String name = null;",
                "private int age = 0;",
                "",
                "public String getName() {",
                "return null;",
                "}",
                "",
                "public void changeName(java.lang.String param0) {",
                "}",
                "",
                "public int getAge() {",
                "return 0;",
                "}",
                "",
                "}"};

        Arrays.sort(expectedStrings);
        Arrays.sort(actualStrings);
        assertArrayEquals(expectedStrings, actualStrings);
    }

    @Test
    void diffClasses() {
        ClassWorker classWorker = new ClassWorker();
        assertFalse(classWorker.diffClasses(HashMap.class, HashMap.class));
        assertTrue(classWorker.diffClasses(Integer.class, Object.class));
    }

    @Test
    void comparingOriginalClassWithResultOfPrinting() throws ClassNotFoundException, MalformedURLException {
        ClassLoader originalClassLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> originalClass = originalClassLoader.loadClass("com.AntonChernikov.g144.Man");

        ClassWorker classWorker = new ClassWorker();
        classWorker.printStructure(originalClass);

        ClassLoader resultClassLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> resultClass = resultClassLoader.loadClass("com.AntonChernikov.g144.ResultOfPrinting.Man");

        assertFalse(classWorker.diffClasses(originalClass, resultClass));
    }
}