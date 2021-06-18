package com.muntian;

import com.muntian.testclasses.A;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        System.out.println("Create object of class " + A.class.getName());
        Object obj = getInstance(A.class);
        System.out.println("**********************");
        System.out.println();

        System.out.println("Invoke all methods without parameters:");
        invokeAllMethodsWithoutParameters(obj);
        System.out.println("**********************");
        System.out.println();

        System.out.println("All methods with final:");
        printAllSignatureWithFinal(obj);
        System.out.println("**********************");
        System.out.println();

        System.out.println("Print all non-public methods:");
        printNonPublicMethods(A.class);
        System.out.println("**********************");
        System.out.println();

        System.out.println("Print all parent classes and interfaces:");
        printParentsAndInterfaces(A.class);
        System.out.println("************************");
        System.out.println();


        System.out.println("Before reset to default");
        printPrivateFields(obj);
        System.out.println("************************");
        System.out.println();

        System.out.println("Set private fields to default value");
        setFieldsByDefaultValues(obj);
        System.out.println("************************");
        System.out.println();

        System.out.println("After reset");
        printPrivateFields(obj);
        System.out.println("************************");
        System.out.println();
    }

    //    Метод принимает класс и возвращает созданный объект этого класса
    public static Object getInstance(Class clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor constructor = clazz.getConstructor();
        return constructor.newInstance();
    }

    //    Метод принимает object и вызывает у него все методы без параметров
    public static void invokeAllMethodsWithoutParameters(Object object) throws InvocationTargetException, IllegalAccessException {
        Class clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getParameterCount() == 0) {
                if (method.toString().contains("private")) {
                    method.setAccessible(true);
                }
                System.out.println(method.getName());
                method.invoke(object);
            }
        }
    }

    //    Метод принимает object и выводит на экран все сигнатуры методов в который есть final
    public static void printAllSignatureWithFinal(Object object) {
        Class clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.toString().contains("final")) {
                System.out.println(method);
            }
        }
    }

    //    Метод принимает Class и выводит все не публичные методы этого класса
    public static void printNonPublicMethods(Class clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (!method.toString().contains("public")) {
                System.out.println(method);
            }
        }
    }

    //    Метод принимает Class и выводит всех предков класса и все интерфейсы которое класс имплементирует
    public static void printParentsAndInterfaces(Class clazz) {
        printAncestors(clazz);
        System.out.println("__________________________");
        printInterfaces(clazz);
    }

    //    Метод принимает объект и меняет всего его приватные поля на их нулевые значение (null, 0, false etc)+
    public static void setFieldsByDefaultValues(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.toString().contains("private")) {
                field.setAccessible(true);

                Class<?> type = field.getType();

                if (field.get(object) instanceof Number) {
                    field.set(object, (byte)0);
                } else if (type == boolean.class) {
                    field.set(object, false);
                } else if (type == char.class) {
                    field.set(object, '\u0000');
                } else {
                    field.set(object, null);
                }
            }
        }
    }

    public static void printPrivateFields(Object object) throws IllegalAccessException {
        System.out.println("Display all private fields with their values:");
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.toString().contains("private")) {
                field.setAccessible(true);
                System.out.println(field.getName() + " = " + field.get(object));
            }
        }
    }

    private static void printAncestors(Class classUnit) {
        System.out.println("Ancestors of " + classUnit.getName());
        Class<?> parent;
        while (true) {
            parent = classUnit.getSuperclass();
            if (parent == null) {
                break;
            }
            System.out.println(parent);
            classUnit = parent;
        }
    }

    private static void printInterfaces(Class clazz) {
        System.out.println("All implemented interfaces:");
        for (String interfaceName : getInterfaces(clazz)) {
            System.out.println(interfaceName);
        }
    }

    private static Set<String> getInterfaces(Class classUnit) {
        Class[] implInterfaces;
        HashSet<String> interfaces = new HashSet();

        implInterfaces = classUnit.getInterfaces();
        for (Class interfaceUnit : implInterfaces) {
            interfaces.add(interfaceUnit.toString());
            if (interfaceUnit.getInterfaces().length == 0) {
                continue;
            }
            interfaces.addAll(getInterfaces(interfaceUnit));
        }
        return interfaces;
    }
}
