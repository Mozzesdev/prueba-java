package me.winflix.notification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class NotificationTest {
    @Test
    void testNotificationLogic() {
        // Datos de prueba
        Set<Student> math = Set.of(
                new Student("Alice", "Málaga"),
                new Student("Bob", "Málaga")
        );

        Set<Student> french = Set.of(
                new Student("Alice", "Málaga"),
                new Student("Carlos", "Málaga")
        );

        Set<Student> both = Set.of(
                new Student("Alice", "Málaga")
        );

        // Ejecutar método
        Map<String, Set<Student>> result = Notification.getStudentsToNotify(math, french, both);

        // Verificaciones
        assertEquals(1, result.get("MATHS").size());
        assertEquals(1, result.get("FRENCH").size());
        assertEquals(1, result.get("BOTH").size());
        
        assertTrue(result.get("MATHS").contains(new Student("Bob", "Málaga")));
        assertTrue(result.get("FRENCH").contains(new Student("Carlos", "Málaga")));
        assertTrue(result.get("BOTH").contains(new Student("Alice", "Málaga")));
    }

    @Test
    void testCampusFiltering() {
        Set<Student> students = Set.of(
                new Student("Eva", "Málaga"),
                new Student("David", "Madrid")
        );

        Set<Student> filtered = Notification.filterByCampus(students, "Málaga");
        assertEquals(1, filtered.size());
        assertTrue(filtered.contains(new Student("Eva", "Málaga")));
    }
}