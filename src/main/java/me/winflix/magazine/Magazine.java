package me.winflix.magazine;

public class Magazine {

    public static boolean canWrite(String note, String magazine) {
        // Optimización: Si la nota es más larga que la revista, es imposible
        if (note.length() > magazine.length())
            return false;

        int[] magazineCounts = new int[65536]; // Cubre todos los caracteres Unicode de 16 bits

        // Contar caracteres en la revista (O(m))
        for (char c : magazine.toCharArray())
            magazineCounts[c]++;

        // Verificar disponibilidad de caracteres en la nota (O(n))
        for (char c : note.toCharArray()) {
            if (--magazineCounts[c] < 0)
                return false;
        }

        return true;
    }

    public static String truncate(String text, int maxLength) {
        return text.length() > maxLength
                ? text.substring(0, maxLength - 3) + "..."
                : text;
    }

}
