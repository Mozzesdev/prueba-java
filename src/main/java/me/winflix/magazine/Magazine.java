package me.winflix.magazine;

public class Magazine {

    /**
     * Verifica si es posible escribir la nota usando los caracteres disponibles en
     * la revista.
     *
     * @param note     el mensaje a escribir
     * @param magazine la cadena de texto de la revista
     * @return {@code true} si es posible construir la nota a partir de la revista,
     *         {@code false} en caso contrario
     * @throws IllegalArgumentException si alguno de los parámetros es nulo
     */
    public static boolean canWrite(String note, String magazine) {
        if (note == null || magazine == null) {
            throw new IllegalArgumentException("La nota y la revista no pueden ser nulas.");
        }

        // Si la nota es más larga que la revista, es imposible formar la nota
        if (note.length() > magazine.length()) {
            return false;
        }

        // Se usa Character.MAX_VALUE + 1 para mayor claridad
        int[] magazineCounts = new int[Character.MAX_VALUE + 1];

        // Contar caracteres en la revista (O(m))
        for (char c : magazine.toCharArray()) {
            magazineCounts[c]++;
        }

        // Verificar que la revista tenga suficientes caracteres para la nota (O(n))
        for (char c : note.toCharArray()) {
            if (--magazineCounts[c] < 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Recorta el texto a una longitud máxima. Si el texto es mayor que la longitud
     * especificada,
     * se devuelve un substring seguido de "..." para indicar que ha sido truncado.
     *
     * @param text      el texto a recortar
     * @param maxLength la longitud máxima permitida para el texto
     * @return el texto recortado o el original si no supera maxLength
     */
    public static String truncate(String text, int maxLength) {
        if (text == null) {
            return null;
        }
        // Si maxLength es demasiado pequeño para agregar puntos suspensivos, se retorna
        // el texto sin modificar.
        if (maxLength <= 3) {
            return text;
        }
        return text.length() > maxLength
                ? text.substring(0, maxLength - 3) + "..."
                : text;
    }
}
