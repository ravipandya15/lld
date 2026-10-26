package com.example.lld.practise.Coding_Questions.immutability;

public final class ImmutableClass {
    private final int x;
    private final int y;

    // Constructor
    public ImmutableClass(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Optionally, you can override equals(), hashCode(), and toString() for better usability
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ImmutableClass)) return false;
        ImmutableClass point = (ImmutableClass) obj;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}
