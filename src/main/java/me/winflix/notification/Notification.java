package me.winflix.notification;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Notification {
    private static final String MALAGA_CAMPUS = "Málaga";

    public static final String KEY_MATHS = "MATHS";
    public static final String KEY_FRENCH = "FRENCH";
    public static final String KEY_BOTH = "BOTH";

    /**
     * Organiza y filtra los estudiantes por asignatura para notificar, considerando únicamente aquellos
     * que pertenecen al campus de Málaga.
     *
     * <p>Se notifica a los estudiantes que solo cursan una asignatura (matemáticas o francés) si no están
     * inscritos en ambas, para evitar notificaciones duplicadas.</p>
     *
     * @param mathStudents         Conjunto de estudiantes de matemáticas.
     * @param frenchStudents       Conjunto de estudiantes de francés.
     * @param bothSubjectsStudents Conjunto de estudiantes que cursan ambas asignaturas.
     * @return Un mapa con las claves "MATHS", "FRENCH" y "BOTH" y los conjuntos de estudiantes filtrados.
     * @throws IllegalArgumentException si alguno de los conjuntos es nulo.
     */
    public static Map<String, Set<Student>> getStudentsToNotify(
            Set<Student> mathStudents,
            Set<Student> frenchStudents,
            Set<Student> bothSubjectsStudents) {

        if (mathStudents == null || frenchStudents == null || bothSubjectsStudents == null) {
            throw new IllegalArgumentException("Los conjuntos de estudiantes no deben ser nulos.");
        }

        // Filtrar estudiantes por el campus de Málaga
        Set<Student> malagaMath = filterByCampus(mathStudents, MALAGA_CAMPUS);
        Set<Student> malagaFrench = filterByCampus(frenchStudents, MALAGA_CAMPUS);
        Set<Student> malagaBoth = filterByCampus(bothSubjectsStudents, MALAGA_CAMPUS);

        // Excluir a los estudiantes que están en ambas asignaturas para evitar notificaciones duplicadas
        Set<Student> finalMath = malagaMath.stream()
                .filter(student -> !malagaBoth.contains(student))
                .collect(Collectors.toSet());

        Set<Student> finalFrench = malagaFrench.stream()
                .filter(student -> !malagaBoth.contains(student))
                .collect(Collectors.toSet());

        return Map.of(
                KEY_MATHS, finalMath,
                KEY_FRENCH, finalFrench,
                KEY_BOTH, malagaBoth);
    }

    /**
     * Filtra un conjunto de estudiantes, devolviendo solo aquellos que pertenecen al campus especificado.
     *
     * @param students Conjunto de estudiantes a filtrar.
     * @param campus   Campus por el que se filtra (la comparación es insensible a mayúsculas/minúsculas).
     * @return Conjunto de estudiantes que coinciden con el campus.
     * @throws IllegalArgumentException si el conjunto de estudiantes o el campus son nulos.
     */
    public static Set<Student> filterByCampus(Set<Student> students, String campus) {
        if (students == null || campus == null) {
            throw new IllegalArgumentException("El conjunto de estudiantes y el campus no deben ser nulos.");
        }
        return students.stream()
                .filter(s -> campus.equalsIgnoreCase(s.getCampus()))
                .collect(Collectors.toSet());
    }
}
