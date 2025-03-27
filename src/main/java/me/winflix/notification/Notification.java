package me.winflix.notification;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Notification {
    private static final String MALAGA_CAMPUS = "Málaga";

    public static Map<String, Set<Student>> getStudentsToNotify(
            Set<Student> mathStudents,
            Set<Student> frenchStudents,
            Set<Student> bothSubjectsStudents) {

        Set<Student> malagaMath = filterByCampus(mathStudents, MALAGA_CAMPUS);
        Set<Student> malagaFrench = filterByCampus(frenchStudents, MALAGA_CAMPUS);
        Set<Student> malagaBoth = filterByCampus(bothSubjectsStudents, MALAGA_CAMPUS);

        Set<Student> finalMath = malagaMath.stream()
                .filter(student -> !malagaBoth.contains(student))
                .collect(Collectors.toSet());

        Set<Student> finalFrench = malagaFrench.stream()
                .filter(student -> !malagaBoth.contains(student))
                .collect(Collectors.toSet());

        return Map.of(
                "MATHS", finalMath,
                "FRENCH", finalFrench,
                "BOTH", malagaBoth);
    }

    public static Set<Student> filterByCampus(Set<Student> students, String campus) {
        return students.stream()
                .filter(s -> campus.equalsIgnoreCase(s.getCampus()))
                .collect(Collectors.toSet());
    }
}