package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;

import javax.naming.OperationNotSupportedException;

public class Alumnos {
    private final Alumno[] coleccionAlumnos;
    private final int capacidad;
    private int tamano;

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public Alumnos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.coleccionAlumnos = new Alumno[getCapacidad()];
        this.capacidad = capacidad;
        this.tamano = 0;
    }

    public Alumno[] get() {
        return copiaProfundaAlumnos();
    }

    private Alumno[] copiaProfundaAlumnos() {
        Alumno[] copiaAlumnos = new Alumno[getCapacidad()];

        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaAlumnos[i] = new Alumno(coleccionAlumnos[i]);
        }
        return copiaAlumnos;
    }

    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }
        if (tamanoSuperado(getTamano())) {
            throw new OperationNotSupportedException("ERROR: La colección de alumnos ha alcanzado su capacidad.");
        }
        coleccionAlumnos[tamano] = new Alumno(alumno);
        tamano++;
    }

    private int buscarIndice(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }
        for (int i = 0; !tamanoSuperado(i); i++) {
            if (alumno.equals(coleccionAlumnos[i])) {
                return i;
            }
        }
        return -1;
    }

    private boolean tamanoSuperado(int i) {
        return i == getTamano();
    }

    private boolean capacidadSuperada(int i) {
        return i == getCapacidad();
    }

    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }
        int indice = buscarIndice(alumno);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: El alumno no se encuentra en la colección de alumnos.");
        }
        for (int i = indice; !tamanoSuperado(i); i++) {
            coleccionAlumnos[i] = coleccionAlumnos[i + 1];
        }
        tamano--;
    }

    public Alumno buscar(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }
        return coleccionAlumnos[buscarIndice(alumno)];
    }

    private void desplazarIzquierda(int i) throws OperationNotSupportedException {
        if (!tamanoSuperado(i))
            throw new OperationNotSupportedException("ERROR: El alumno no se encuentra en la colección de alumnos.(96)");
        for (int j = i; !capacidadSuperada(j); j++) {
            coleccionAlumnos[j] = coleccionAlumnos[j + 1];
        }
    }

}
