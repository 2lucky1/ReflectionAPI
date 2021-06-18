package com.muntian.testclasses;

import com.muntian.interfaces.MyInterface;
import com.muntian.interfaces.MySecondInterface;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class A extends B implements MyInterface, MySecondInterface {

    private byte ofPie = 127;
    private short socks = 545;
    private final int interest = 2;
    private long island = 1_000_000;

    private float boat = 30.4F;
    private double decker = 25.5;

    private char symbol = '!';

    private boolean chicago = true;

    private final int Max = 1000;
    private String name = "A-Class";
    public boolean isTest = true;

    public final int network = 255;
    public String music = "Rock";

    private int count;

    public A() {
        System.out.println("A");
    }

    public final void increment() {
        count++;
    }

    private Object getMe() {
        return this;
    }

    protected final void increment(int size) {
        count += size;
    }

    public void print() {
        System.out.println("count = " + count);
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public boolean tryAdvance(Consumer action) {
        return false;
    }

    @Override
    public Spliterator trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return 0;
    }

    @Override
    public int characteristics() {
        return 0;
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return null;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 0;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 0;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}
