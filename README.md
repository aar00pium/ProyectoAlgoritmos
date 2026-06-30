# QuickLibrary

Sistema de gestión de préstamos de libros para una biblioteca universitaria. Desarrollado en Java usando estructuras de datos propias (árbol BST y cola genérica con nodos enlazados).

---

## Requisitos

- Java 11 o superior
- Terminal o consola

Para verificar que tienes Java instalado:
```
java -version
```

---

## Cómo ejecutar

**1. Clonar el repositorio**
```
git clone https://github.com/aar00pium/ProyectoAlgoritmos.git
cd ProyectoAlgoritmos
```

**2. Compilar**
```
javac -d out $(find src -name "*.java")
```

**3. Ejecutar**
```
java -cp out quicklibrary.Main
```

---

## Opciones del menú

```
========== QUICKLIBRARY ==========
 1. Registrar libro
 2. Mostrar libros
 3. Buscar libro
 4. Buscar libros por categoria
 5. Modificar libro
 6. Eliminar libro
 7. Registrar solicitud de prestamo
 8. Mostrar cola de solicitudes
 9. Atender siguiente solicitud
10. Registrar devolucion
11. Mostrar reporte
12. Salir
```

- **Opción 2** te pregunta si quieres ver todos, solo disponibles o solo prestados.
- **Opción 3** te deja buscar por código, título o autor.
- **Opción 9** atiende la siguiente solicitud en orden de llegada y marca el libro como prestado automáticamente.

El sistema ya carga 30 libros al iniciar, no hace falta agregarlos a mano.

---

## Ejemplos de uso

**Registrar un libro:**
```
Opcion: 1
Codigo    : P006
Titulo    : Head First Java
Autor     : Kathy Sierra
Categoria : Programacion
Anio      : 2022
Libro registrado correctamente.
```

**Registrar una solicitud y atenderla:**
```
Opcion: 7
Codigo estudiante : 20210045
Nombre estudiante : Ana Flores
Codigo libro      : P006
Solicitud registrada correctamente.

Opcion: 9
Solicitud atendida:
  Estudiante : Ana Flores (20210045)
  Libro      : P006
  Fecha      : 2026-06-30
Libro marcado como PRESTADO.
```

**Ver reporte:**
```
Opcion: 11
======== REPORTE GENERAL ========
Total de libros        : 31
Libros disponibles     : 30
Libros prestados       : 1
Solicitudes pendientes : 0
=================================
```

---

## Estructura del proyecto

```
src/quicklibrary/
  estructuras/   → Nodo, Cola, NodoArbol, ArbolBST
  excepciones/   → ColaVacia, ElementoNoEncontrado, CodigoDuplicado, DatoInvalido
  modelos/       → Libro, SolicitudPrestamo, EstadoLibro
  servicios/     → GestorLibros, GestorSolicitudes
  validacion/    → Validador
  Main.java
```

---

## Integrantes

- aar00pium
- samuelSC97
- FrankHUN233
