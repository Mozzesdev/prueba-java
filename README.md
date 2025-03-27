# Magazine Note Checker - Reto 1

## Descripción
Este proyecto contiene una solución en Java para determinar si un informe (nota) puede escribirse utilizando recortes de una revista dada. El algoritmo verifica si todos los caracteres de la nota están presentes en la revista con la frecuencia requerida.

## Características
- **Entrada**: Dos cadenas (`note` y `magazine`).
- **Salida**: `true` si la nota puede formarse, `false` en caso contrario.
- **Eficiencia**: 
  - Tiempo: **O(n + m)** (donde `n` es la longitud de la nota y `m` la de la revista).
  - Espacio: **O(1)** (usa un arreglo de tamaño fijo).

## Requisitos
- Java 17+
- Maven 3.8+
- JUnit 5 (ya incluido en el `pom.xml`)

## Instalación
1. Clona el repositorio:
   ```bash
   git clone [URL_DEL_REPOSITORIO]