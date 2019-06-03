package com.AntonChernikov.g144;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Class describing printing and class comparison */
public class ClassWorker {

    private final String directory;
    private StringBuilder classCode = new StringBuilder();

    public ClassWorker() {
        directory = System.getProperty("user.dir") + "\\src\\main\\java\\com\\AntonChernikov\\g144\\ResultOfPrinting";
    }

    /** Method returning name of class with type parameters */
    private String getFullName(Class<?> someClass) {
        StringBuilder result = new StringBuilder(someClass.getSimpleName());
        if (someClass.getTypeParameters().length != 0) {
            result.append("<");
            for (TypeVariable variable : someClass.getTypeParameters()) {
                result.append(variable.getName()).append(", ");
            }
            result = new StringBuilder(result.substring(0, result.length() - 2));
            result.append(">");
        }
        return result.toString();
    }

    /** Method writing modifier of class to result */
    private void addModifier(Class<?> someClass) {
        if (someClass.getModifiers() != 0) {
            classCode.append(Modifier.toString(someClass.getModifiers())).append(" ");
        }
    }

    /** Method writing fields of class to result */
    private void addFields(Class<?> someClass) {
        if (someClass.getDeclaredFields().length != 0) {
            classCode.append("\n");
            for (Field field : someClass.getDeclaredFields()) {
                if (field.getModifiers() != 0) {
                    classCode.append(Modifier.toString(field.getModifiers())).append(" ");
                }

                classCode.append(getFullName(field.getType()))
                        .append(" ")
                        .append(field.getName())
                        .append(" = ");

                if (field.getType().isPrimitive()) {
                    if (field.getType().getName().equals("boolean")) {
                        classCode.append("false;");
                    } else {
                        classCode.append("0;");
                    }
                } else {
                    classCode.append("null;");
                }
                classCode.append("\n");
            }
            classCode.append("\n");
        }
    }

    /** Method writing methods of class to result */
    private void addMethods(Class<?> someClass) {
        for (Method method : someClass.getDeclaredMethods()) {
            classCode.append(toString(method)).append(" {\n");

            if (!method.getReturnType().getSimpleName().equals("void")) {
                classCode.append("return");
                if (method.getReturnType().isPrimitive()) {
                    if (method.getReturnType().getSimpleName().equals("boolean")) {
                        classCode.append(" false");
                    } else {
                        classCode.append(" 0");
                    }
                } else {
                    classCode.append(" ").append(method.getDefaultValue());
                }
                classCode.append(";\n");
            }
            classCode.append("}\n\n");
        }
    }

    /** Method writing inner classes of class to result */
    private void addInnerClasses(Class<?> someClass) {
        for (Class<?> innerClass : someClass.getDeclaredClasses()) {
            addClass(innerClass);
            classCode.append("\n");
        }
    }

    /** Method writing class to result */
    private void addClass(Class<?> someClass) {
        addModifier(someClass);
        classCode.append("class ")
                .append(getFullName(someClass))
                .append(" extends ")
                .append(someClass.getSuperclass().getSimpleName());

        if (someClass.getInterfaces().length != 0) {
            classCode.append(" implements ");
            for (Class<?> clazz : someClass.getInterfaces()) {
                classCode.append(getFullName(clazz)).append(", ");
            }
            classCode = new StringBuilder(classCode.substring(0, classCode.length() - 2));
        }

        classCode.append(" {\n");
        addFields(someClass);
        addMethods(someClass);
        addInnerClasses(someClass);
        classCode.append("}\n");
    }

    /** Method writing structure of class to file */
    public void printStructure(Class<?> someClass) {
        String fileName = directory + "\\" + someClass.getSimpleName() + ".java";
        try(FileWriter file = new FileWriter(fileName, false)) {
            file.write(ClassWorker.class.getPackage() + ".ResultOfPrinting;\n\n");

            addClass(someClass);

            file.write(classCode.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method comparing two classes
     * @return true if there are differences between classes
     * */
    public boolean diffClasses(Class<?> firstClass, Class<?> secondClass) {
        List<String> firstFields = Arrays.stream(firstClass.getDeclaredFields())
                .map(this::toString)
                .collect(Collectors.toList());
        List<String> secondFields = Arrays.stream(secondClass.getDeclaredFields())
                .map(this::toString)
                .collect(Collectors.toList());

        List<String> firstMethods = Arrays.stream(firstClass.getDeclaredMethods())
                .map(method -> toString(method).replaceAll(" extends java.lang.Object", ""))
                .collect(Collectors.toList());
        List<String> secondMethods = Arrays.stream(secondClass.getDeclaredMethods())
                .map(method -> toString(method).replaceAll(" extends java.lang.Object", ""))
                .collect(Collectors.toList());

        String result = different(firstFields, secondFields) + different(firstMethods, secondMethods);
        System.out.println(result);
        return !result.isEmpty();
    }

    /** Method returning different fields and methods of two comparing classes */
    private String different(List<String> first, List<String> second) {
        return notContain(first, second) + notContain(second, first);
    }

    /** Method returning fields or methods from the first list that are not contained in the second*/
    private String notContain(List<String> first, List<String> second) {
        StringBuilder result = new StringBuilder();
        for (String current : first) {
            if (!second.contains(current)) {
                result.append(current).append("\n");
            }
        }
        return result.toString();
    }

    /** Method transforming field of class to string */
    private String toString(Field field) {
        StringBuilder result = new StringBuilder();
        if (field.getModifiers() != 0) {
            result.append(Modifier.toString(field.getModifiers())).append(" ");
        }
        result.append(getFullName(field.getType())).append(" ").append(field.getName()).append(";");
        return result.toString();
    }

    /** Method transforming method of class to string */
    private String toString(Method method) {
        StringBuilder result = new StringBuilder();
        if (method.getModifiers() != 0) {
            result.append(Modifier.toString(method.getModifiers())).append(" ");
        }

        result.append(getFullName(method.getReturnType()))
                .append(" ")
                .append(method.getName());

        int index = 0;
        result.append("(");
        if (method.getParameters().length != 0) {
            for (Parameter parameter : method.getParameters()) {
                result.append(parameter.getParameterizedType().getTypeName())
                        .append(" param")
                        .append(index++)
                        .append(", ");
            }
            result = new StringBuilder(result.substring(0, result.length() - 2));
        }
        result.append(")");

        if (method.getExceptionTypes().length != 0) {
            result.append("throws ");
            for (Class<?> clazz : method.getExceptionTypes()) {
                result.append(clazz.getSimpleName()).append(", ");
            }
            result = new StringBuilder(result.substring(0, result.length() - 2));
        }
        return result.toString();
    }
}