package me.mozzesdev.notification;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotificationTest {

        @Test
        @DisplayName("Verifica la lógica de notificaciones con estudiantes únicos")
        void testNotificationLogic() {
                // Datos de prueba
                Set<Student> math = Set.of(
                                new Student("Alice", "Málaga"),
                                new Student("Bob", "Málaga"));
                Set<Student> french = Set.of(
                                new Student("Alice", "Málaga"),
                                new Student("Carlos", "Málaga"));
                Set<Student> both = Set.of(
                                new Student("Alice", "Málaga"));

                // Ejecutar método
                Map<String, Set<Student>> result = Notification.getStudentsToNotify(math, french, both);

                // Verificaciones agrupadas
                assertAll("Validación de resultados",
                                () -> assertEquals(1, result.get("MATHS").size(),
                                                "Debería haber 1 estudiante para matemáticas"),
                                () -> assertEquals(1, result.get("FRENCH").size(),
                                                "Debería haber 1 estudiante para francés"),
                                () -> assertEquals(1, result.get("BOTH").size(),
                                                "Debería haber 1 estudiante para ambas asignaturas"),
                                () -> assertTrue(result.get("MATHS").contains(new Student("Bob", "Málaga")),
                                                "Bob debería estar en MATHS"),
                                () -> assertTrue(result.get("FRENCH").contains(new Student("Carlos", "Málaga")),
                                                "Carlos debería estar en FRENCH"),
                                () -> assertTrue(result.get("BOTH").contains(new Student("Alice", "Málaga")),
                                                "Alice debería estar en BOTH"));
        }

        @Test
        @DisplayName("Filtrado de estudiantes por campus")
        void testCampusFiltering() {
                Set<Student> students = Set.of(
                                new Student("Eva", "Málaga"),
                                new Student("David", "Madrid"));

                Set<Student> filtered = Notification.filterByCampus(students, "Málaga");

                assertAll("Validación de filtrado por campus",
                                () -> assertEquals(1, filtered.size(), "Debería filtrarse 1 estudiante"),
                                () -> assertTrue(filtered.contains(new Student("Eva", "Málaga")),
                                                "Eva debería estar en el campus Málaga"));
        }
}
