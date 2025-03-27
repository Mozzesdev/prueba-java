package me.mozzesdev.notification;

import java.util.Objects;

public class Student {
    private final String name;
    private final String campus;

    public Student(String name, String campus) {
        this.name = name;
        this.campus = campus;
    }

    public String getCampus() {
        return campus;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
               Objects.equals(campus, student.campus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, campus);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, campus);
    }
}