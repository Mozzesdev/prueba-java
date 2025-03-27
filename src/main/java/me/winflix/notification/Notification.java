package me.winflix.notification;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Notification {
    public static Map<String, Set<Student>> getStudentsToNotify(
            Set<Student> mathStudents,
            Set<Student> frenchStudents,
            Set<Student> bothSubjectsStudents) {

        Set<Student> malagaMath = filterByCampus(mathStudents, "Málaga");
        Set<Student> malagaFrench = filterByCampus(frenchStudents, "Málaga");
        Set<Student> malagaBoth = filterByCampus(bothSubjectsStudents, "Málaga");

        // Eliminar duplicados
        malagaMath.removeAll(malagaBoth);
        malagaFrench.removeAll(malagaBoth);

        return Map.of(
                "MATHS", malagaMath,
                "FRENCH", malagaFrench,
                "BOTH", malagaBoth);
    }

    private static Set<Student> filterByCampus(Set<Student> students, String campus) {
        return students.stream()
                .filter(s -> s.getCampus().equalsIgnoreCase(campus))
                .collect(Collectors.toSet());
    }
}