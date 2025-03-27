# 🚀 Java Coding Challenges - Dual Retos

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![Maven](https://img.shields.io/badge/Maven-3.8%2B-orange)
![JUnit5](https://img.shields.io/badge/JUnit-5.13.0_M2-success)

Proyecto con dos retos de programación en Java que demuestran:
- Manejo eficiente de estructuras de datos
- Diseño de algoritmos óptimos
- Pruebas unitarias profesionales

## 📚 Tabla de Contenidos
1. [Reto 1: Validación de Revista](#reto-1---validación-de-revista)
2. [Reto 2: Sistema de Notificaciones](#reto-2---sistema-de-notificaciones)
3. [Instalación](#-instalación)
4. [Ejecución de Pruebas](#-ejecución-de-pruebas)

---

## 🔍 Reto 1 - Validación de Revista
### Problema
Determinar si una nota puede formarse usando los caracteres de una revista.

### 🛠️ Solución
```java
boolean canWrite = Magazine.canWrite("nota", "revista");
```java

---

## 📚 Reto 2 - Sistema de Notificaciones Académicas

### 🎯 Problema
Sistema para notificar estudiantes afectados por cancelación de clases en Málaga, considerando:
- Dos asignaturas afectadas (Matemáticas y Francés)
- Estudiantes matriculados en una o ambas asignaturas
- Evitar notificaciones duplicadas

## 🧠 Solución Implementada

### 🔍 Diagrama Conceptual
```mermaid
graph LR
    A[Matemáticas] --> C[Filtrar Málaga]
    B[Francés] --> C
    D[Ambas] --> C
    C --> E[Eliminar Duplicados]
    E --> F[Grupo MATHS]
    E --> G[Grupo FRENCH]
    E --> H[Grupo BOTH]