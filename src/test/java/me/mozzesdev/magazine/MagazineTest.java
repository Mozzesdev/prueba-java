package me.mozzesdev.magazine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;

class MagazineTest {

    // ------------------- CASO BÁSICO 1 -------------------
    @Test
    @DisplayName("Nota vacía: siempre se puede escribir")
    void canWrite_EmptyNote_ReturnsTrue() {
        // Una nota vacía siempre es posible, sin importar el contenido de la revista
        assertTrue(Magazine.canWrite("", "abc"));
    }

    // ------------------- CASO BÁSICO 2 -------------------
    @Test
    @DisplayName("Revista vacía y nota no vacía: no se puede escribir")
    void canWrite_EmptyMagazine_ReturnsFalse() {
        // Si la revista está vacía, no es posible escribir una nota no vacía
        assertFalse(Magazine.canWrite("a", ""));
    }

    // ------------------- PRUEBAS PARAMETRIZADAS -------------------
    @ParameterizedTest(name = "Test #{index}: Nota \"{0}\", Revista \"{1}\" => {2}")
    @DisplayName("Pruebas parametrizadas con distintos escenarios")
    @CsvSource({
            "abc, abbc, true", // Suficientes caracteres (incluyendo repeticiones)
            "abcd, abc, false", // Falta un carácter ('d')
            "aabb, ab, false", // Frecuencia insuficiente de letras
            "ñ, ñú, true", // Caracteres Unicode/complejos
            "A, a, false", // Sensibilidad a mayúsculas/minúsculas
            "aaaa, aaaaa, true", // Suficientes repeticiones
            "hello, heyllo, true", // Caracteres mezclados en diferente orden
            "test, tst, false" // Faltan letras (solo hay 1 'e' en la revista)
    })
    void parameterizedTests(String note, String magazine, boolean expected) {
        // Comprobación de múltiples escenarios usando datos CSV
        assertEquals(expected, Magazine.canWrite(note, magazine));
    }

    // ------------------- CASO DE TAMAÑO -------------------
    @Test
    @DisplayName("Nota más larga que la revista: retorna false")
    void canWrite_NoteLargerThanMagazine_ReturnsFalse() {
        // Se verifica que si la nota es más larga que la revista, el resultado es false
        String magazine = "abc";
        String note = "a".repeat(magazine.length() + 1); // Nota de 4 caracteres vs revista de 3
        assertFalse(Magazine.canWrite(note, magazine));
    }

    // ------------------- PRUEBA DE RENDIMIENTO -------------------
    @Test
    @DisplayName("Prueba de rendimiento con grandes volúmenes de datos")
    void canWrite_PerformanceTest() {
        // Prueba de estrés:
        // Revista: 1,000,000 'a' + 500,000 'b' (1.5M caracteres)
        // Nota: 999,999 'a' + 499,999 'b' (1,499,998 caracteres)
        String magazine = "a".repeat(1_000_000) + "b".repeat(500_000);
        String note = "a".repeat(999_999) + "b".repeat(499_999);
        assertTrue(Magazine.canWrite(note, magazine));
    }

    // ------------------- CARACTERES ESPECIALES -------------------
    @Test
    @DisplayName("Verificación de caracteres especiales y Unicode")
    void canWrite_SpecialCharacters() {
        // Caso 1: Símbolos especiales presentes en la revista
        assertTrue(Magazine.canWrite("!@#$%^", "!@#$%^&*()"));

        // Caso 2: Carácter Unicode (π griega) no presente en la revista
        assertFalse(Magazine.canWrite("π", "3.1416"));
    }
}
