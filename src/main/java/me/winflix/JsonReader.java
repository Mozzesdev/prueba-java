package me.winflix;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import me.winflix.magazine.MagazineCase;
import me.winflix.notification.Student;

public class JsonReader {
    public static Map<String, Set<Student>> readAllStudents(String filePath) throws IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Set<Student>>>() {
        }.getType();

        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, type);
        }
    }

    public static Set<MagazineCase> readMagazineCases(String filePath) throws IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<Set<MagazineCase>>() {
        }.getType();

        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, type);
        }
    }
}