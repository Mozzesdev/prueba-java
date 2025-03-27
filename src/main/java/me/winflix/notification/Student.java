package me.winflix.notification;

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
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", campus='" + campus + '\'' +
                '}';
    }
}