package me.winflix.magazine;

public class Magazine {

    public static boolean canWrite(String note, String magazine) {
        int[] magazineCounts = new int[65536]; // 1. Arreglo para contar todos los caracteres Unicode (16 bits)
        for (char c : magazine.toCharArray()) { // 2. Recorrer la revista (longitud m)
            magazineCounts[c]++;
        }
        for (char c : note.toCharArray()) { // 3. Recorrer la nota (longitud n)
            if (--magazineCounts[c] < 0) { // 4. Verificar disponibilidad
                return false;
            }
        }
        return true;
    }
}
