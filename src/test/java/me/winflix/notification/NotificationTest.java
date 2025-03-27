package me.winflix.notification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class NotificationTest {
    // Datos de prueba reutilizables
    private final Student s1 = new Student("Ana", "Málaga"); // Estudiante en Málaga (ambas asignaturas)
    private final Student s2 = new Student("Luis", "Málaga"); // Estudiante en Málaga (solo francés)
    private final Student s3 = new Student("Eva", "Madrid"); // Estudiante en Madrid (no afectada)

    @Test
    void testNotificationGroups() {
        // 1. Preparación de datos de prueba
        Set<Student> math = Set.of(s1, s3); // Matemáticas: Ana (Málaga), Eva (Madrid)
        Set<Student> french = Set.of(s2, s3); // Francés: Luis (Málaga), Eva (Madrid)
        Set<Student> both = Set.of(s1); // Ambos: Ana (Málaga)

        // 2. Ejecución del método bajo prueba
        Map<String, Set<Student>> result = Notification.getStudentsToNotify(math, french, both);

        // 3. Verificaciones (Assertions)

        // Grupo MATHS debería estar vacío porque:
        // - s1 está en "both" (se elimina de maths)
        // - s3 está en Madrid (se filtra)
        assertEquals(0, result.get("MATHS").size());

        // Grupo FRENCH debería contener solo a Luis porque:
        // - s2 está solo en francés y en Málaga
        // - s3 está en Madrid (filtrada)
        assertEquals(1, result.get("FRENCH").size());

        // Grupo BOTH debería contener solo a Ana porque:
        // - s1 está en la lista "both" y en Málaga
        assertEquals(1, result.get("BOTH").size());
    }
}