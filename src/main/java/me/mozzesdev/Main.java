package me.mozzesdev;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import me.mozzesdev.magazine.Magazine;
import me.mozzesdev.magazine.MagazineCase;
import me.mozzesdev.notification.Notification;
import me.mozzesdev.notification.Student;

public class Main {
    public static void main(String[] args) {
        Main.notifyStudents();
        Main.checkMagazines();
    }

    public static void notifyStudents() {
        try {
            // Cargar datos desde el JSON
            Map<String, Set<Student>> allStudents = JsonReader.readAllStudents("data/students.json");

            // Extraer estudiantes
            Set<Student> math = allStudents.getOrDefault("math", Set.of());
            Set<Student> french = allStudents.getOrDefault("french", Set.of());
            Set<Student> both = allStudents.getOrDefault("both", Set.of());

            // Obtener estudiantes a notificar
            Map<String, Set<Student>> result = Notification.getStudentsToNotify(math, french, both);

            // Mostrar resultados
            System.out.println("Notificaciones para Málaga:");
            System.out.println("Matemáticas: " + result.get("MATHS"));
            System.out.println("Francés: " + result.get("FRENCH"));
            System.out.println("Ambas asignaturas: " + result.get("BOTH"));

        } catch (IOException e) {
            System.err.println("Error leyendo el archivo JSON: " + e.getMessage());
        }
    }

    public static void checkMagazines() {
        try {
            // Leer casos de prueba desde JSON
            Set<MagazineCase> testCases = JsonReader.readMagazineCases("data/magazines.json");

            int passed = 0;
            int total = testCases.size();

            System.out.println("\n--- Pruebas de Revista/Nota ---");
            for (MagazineCase testCase : testCases) {
                boolean result = Magazine.canWrite(testCase.getNote(), testCase.getMagazine());
                boolean success = result == testCase.isExpected();

                if (success)
                    passed++;

                System.out.printf("%nPrueba:%n" +
                        "  Nota: %s%n" +
                        "  Revista: %s%n" +
                        "  Esperado: %b%n" +
                        "  Resultado: %b%n" +
                        "  Estado: %s%n",
                        Magazine.truncate(testCase.getNote(), 30),
                        Magazine.truncate(testCase.getMagazine(), 40),
                        testCase.isExpected(),
                        result,
                        success ? "✅ ÉXITO" : "❌ FALLO");
            }

            // Resumen final
            System.out.println("\n--- Resumen ---");
            System.out.printf("Total de pruebas: %d%n", total);
            System.out.printf("Pruebas exitosas: %d%n", passed);
            System.out.printf("Porcentaje de éxito: %.0f%%%n", (passed * 100.0) / total);

        } catch (IOException e) {
            System.err.println("Error leyendo casos de prueba: " + e.getMessage());
        }
    }

}
