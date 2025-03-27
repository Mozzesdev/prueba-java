package me.mozzesdev.notification;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Notification {
    private static final String MALAGA_CAMPUS = "Málaga";

    public static final String KEY_MATHS = "MATHS";
    public static final String KEY_FRENCH = "FRENCH";
    public static final String KEY_BOTH = "BOTH";

    /**
     * Organiza y filtra los estudiantes por asignatura para notificar, considerando
     * únicamente aquellos
     * que pertenecen al campus de Málaga.
     *
     * <p>
     * Se notifica a los estudiantes que solo cursan una asignatura (matemáticas o
     * francés) si no están
     * inscritos en ambas, para evitar notificaciones duplicadas.
     * </p>
     *
     * @param mathStudents         Conjunto de estudiantes de matemáticas.
     * @param frenchStudents       Conjunto de estudiantes de francés.
     * @param bothSubjectsStudents Conjunto de estudiantes que cursan ambas
     *                             asignaturas.
     * @return Un mapa con las claves "MATHS", "FRENCH" y "BOTH" y los conjuntos de
     *         estudiantes filtrados.
     * @throws IllegalArgumentException si alguno de los conjuntos es nulo.
     */
    public static Map<String, Set<Student>> getStudentsToNotify(
            Set<Student> mathStudents,
            Set<Student> frenchStudents,
            Set<Student> bothSubjectsStudents) {

        if (mathStudents == null || frenchStudents == null || bothSubjectsStudents == null) {
            throw new IllegalArgumentException("Los conjuntos de estudiantes no deben ser nulos.");
        }

        // Filtrar por campus
        Set<Student> malagaMath = filterByCampus(mathStudents, MALAGA_CAMPUS);
        Set<Student> malagaFrench = filterByCampus(frenchStudents, MALAGA_CAMPUS);

        // Calcular intersección real de ambas materias
        Set<Student> malagaBoth = new HashSet<>(malagaMath);
        malagaBoth.retainAll(malagaFrench);

        // Remover de las listas individuales los que están en ambas asignaturas
        malagaMath.removeAll(malagaBoth);
        malagaFrench.removeAll(malagaBoth);

        // Usar un mapa mutable por si se necesita modificar después
        Map<String, Set<Student>> result = new HashMap<>();
        result.put(KEY_MATHS, malagaMath);
        result.put(KEY_FRENCH, malagaFrench);
        result.put(KEY_BOTH, malagaBoth);

        return result;
    }

    /**
     * Filtra un conjunto de estudiantes, devolviendo solo aquellos que pertenecen
     * al campus especificado.
     *
     * @param students Conjunto de estudiantes a filtrar.
     * @param campus   Campus por el que se filtra (la comparación es insensible a
     *                 mayúsculas/minúsculas).
     * @return Conjunto de estudiantes que coinciden con el campus.
     * @throws IllegalArgumentException si el conjunto de estudiantes o el campus
     *                                  son nulos.
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
