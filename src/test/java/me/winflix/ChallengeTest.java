package me.winflix;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChallengeTest {

    // ------------------- CASO BÁSICO 1 -------------------
    @Test
    void canWrite_EmptyNote_ReturnsTrue() {
        // Prueba que una nota vacía siempre se puede escribir
        // Independientemente del contenido de la revista
        assertTrue(Challenge.canWrite("", "abc"));
    }

    // ------------------- CASO BÁSICO 2 -------------------
    @Test
    void canWrite_EmptyMagazine_ReturnsFalse() {
        // Verifica que no se puede escribir una nota no vacía si la revista está vacía
        assertFalse(Challenge.canWrite("a", ""));
    }

    // ------------------- PRUEBAS PARAMETRIZADAS -------------------
    @ParameterizedTest(name = "Test #{index}: Nota ''{0}'', Revista ''{1}'' => {2}")
    @CsvSource({
            "abc, abbc, true", // Caso 1: Suficientes caracteres (incluyendo repeticiones)
            "abcd, abc, false", // Caso 2: Falta un carácter ('d')
            "aabb, ab, false", // Caso 3: Frecuencia insuficiente de letras
            "ñ, ñú, true", // Caso 4: Caracteres Unicode/complejos
            "A, a, false", // Caso 5: Sensibilidad a mayúsculas/minúsculas
            "aaaa, aaaaa, true", // Caso 6: Suficientes repeticiones
            "hello, heyllo, true", // Caso 7: Caracteres mezclados en diferente orden
            "test, tst, false" // Caso 8: Faltan letras (solo hay 1 'e' en la revista)
    })
    void parameterizedTests(String note, String magazine, boolean expected) {
        // Prueba múltiples escenarios con diferentes combinaciones usando datos
        // proporcionados en formato CSV
        assertEquals(expected, Challenge.canWrite(note, magazine));
    }

    // ------------------- CASO DE TAMAÑO -------------------
    @Test
    void canWrite_NoteLargerThanMagazine_ReturnsFalse() {
        // Verifica que no se puede escribir una nota más larga que la revista (caso de
        // tamaño imposible)
        String magazine = "abc";
        String note = "a".repeat(magazine.length() + 1); // Nota de 4 caracteres vs revista de 3
        assertFalse(Challenge.canWrite(note, magazine));
    }

    // ------------------- PRUEBA DE RENDIMIENTO -------------------
    @Test
    void canWrite_PerformanceTest() {
        // Prueba de estrés con grandes volúmenes de datos:
        // - Revista: 1,000,000 'a' + 500,000 'b' (total 1.5M caracteres)
        // - Nota: 999,999 'a' + 499,999 'b' (total 1,499,998 caracteres)
        // Verifica que el algoritmo maneja eficientemente grandes inputs
        String magazine = "a".repeat(1_000_000) + "b".repeat(500_000);
        String note = "a".repeat(999_999) + "b".repeat(499_999);
        assertTrue(Challenge.canWrite(note, magazine));
    }

    // ------------------- CARACTERES ESPECIALES -------------------
    @Test
    void canWrite_SpecialCharacters() {
        // Prueba caracteres no alfanuméricos:
        // Caso 1: Símbolos especiales (!@#$%^) presentes en la revista
        assertTrue(Challenge.canWrite("!@#$%^", "!@#$%^&*()"));

        // Caso 2: Carácter Unicode (π griega) no presente en la revista
        assertFalse(Challenge.canWrite("π", "3.1416"));
    }
}
