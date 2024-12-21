package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.*;
import org.iesalandalus.programacion.utilidades.Entrada;
import org.iesalandalus.programacion.matriculacion.dominio.Grado;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.dominio.EspecialidadProfesorado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {

    public static final CiclosFormativos ciclosFormativos = new CiclosFormativos(5);
    public static final Asignaturas asignaturas = new Asignaturas(5);
    public static final Alumnos alumnos = new Alumnos(5);
    public final Matriculas matriculas = new Matriculas(5);

    private Consola() {}

    public static void mostrarMenu() {
        System.out.println("GESTOR DE MATRICULAS");
        System.out.println("- Menú de opciones -");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.ordinal() + 1 + ". " + opcion.getCadenaAMostrar());
        }
    }

    public static Opcion elegirOpcion() {
        mostrarMenu();
        int opcion = Entrada.entero();
        while (opcion < 0 || opcion > Opcion.values().length) {
            System.out.println("ERROR: Opcion no valida.");
        }
        return Opcion.values()[Entrada.entero()];
    }

    public static Alumno leerAlumno() {
        System.out.println("Introduce los datos del alumno:");
        System.out.print("Nombre y apellidos: ");
        String nombre = Entrada.cadena();
        System.out.println("DNI: ");
        String dni = Entrada.cadena();
        System.out.println("Correo electrónico: ");
        String correo = Entrada.cadena();
        System.out.println("Teléfono: ");
        String telefono = Entrada.cadena();
        System.out.println("Introduce la fecha nacimiento (dd/mm/aaaa): ");
        LocalDate fechaNacimiento = LocalDate.parse(Entrada.cadena());

        return new Alumno(nombre, dni, correo, telefono, fechaNacimiento);
    }

    public static Alumno getAlumnoPorDni() {
        System.out.println("Introduce el DNI del alumno: ");
        String dni = Entrada.cadena();
        return new Alumno("Pepe Apellido1 Apellido2", dni, "pepe@wanadoo.com", "123456789", LocalDate.of(2000, 1, 1));    }

    public LocalDate leerFecha(String mensaje) {
        LocalDate fechaNacimiento = null;
        do {
            System.out.print(mensaje);
            try {
                fechaNacimiento = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));
            } catch (DateTimeParseException e) {
                System.out.println("ERROR: Formato de fecha no válido.");
            }
        } while (fechaNacimiento == null);
        return fechaNacimiento;
    }

    public static Grado leerGrado() {
        System.out.println("Introduce el grado: ");
        for (Grado grado : Grado.values()) {
            System.out.println(grado.ordinal() + 1 + ".-" + grado.imprimir());
        }
        System.out.println("Selecciona un grado: ");
        int opcion = Entrada.entero();
        while (opcion < 0 || opcion > Grado.values().length) {
            System.out.println("ERROR: Opción no valida.");
        }return Grado.values()[Entrada.entero()];
    }

    public static CicloFormativo leerCicloFormativo() {
        System.out.println("Introduce los datos del ciclo formativo: ");
        System.out.print("Código: ");
        int codigo = Entrada.entero();
        System.out.print("Familia profesional: ");
        String familiaProfesional = Entrada.cadena();
        System.out.print("Grado: ");
        Grado grado = leerGrado();
        System.out.print("Nombre: ");
        String nombre = Entrada.cadena();
        System.out.print("Horas: ");
        int horas = Entrada.entero();

        return new CicloFormativo(codigo);
    }

    public static void mostrarCiclosFormativos() {
        if (ciclosFormativos.get().length == 0) {
            System.out.println("No hay ciclos formativos registrados.");
        }else {
            for (CicloFormativo cicloFormativo : ciclosFormativos.get()) {
                System.out.println(cicloFormativo);
            }
        }
    }

    public static CicloFormativo getCicloFormativoPorCodigo() {
        System.out.print("Introduce el código del ciclo formativo: ");
        int codigo = Entrada.entero();
        return new CicloFormativo(codigo);
    }

    public static Curso leerCurso() {
        System.out.print("Introduce el curso: ");
        for (Curso curso : Curso.values()) {
            System.out.println(curso.ordinal() + 1 + ".-" + curso.imprimir());
        }
        if (Curso.values()[Entrada.entero()] == null) {
            do {
                System.out.println("ERROR: Opción no valida.");
            } while (Curso.values()[Entrada.entero()] == null);
        }
        return Curso.values()[Entrada.entero()];
    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado() {
        System.out.print("Introduce la especialidad: ");
        for (EspecialidadProfesorado especialidadProfesorado : EspecialidadProfesorado.values()) {
            System.out.println(especialidadProfesorado.ordinal() + 1 + ".-" + especialidadProfesorado.imprimir());
        }
        if (EspecialidadProfesorado.values()[Entrada.entero()] == null) {
            do {
                System.out.println("ERROR: Opción no valida.");
            } while (EspecialidadProfesorado.values()[Entrada.entero()] == null);
        }
        return EspecialidadProfesorado.values()[Entrada.entero()];
    }

    public static int leerAsignatura(CicloFormativo cicloFormativo) {
        System.out.println("Introduce los datos de la asignatura: ");
        System.out.print("Código: ");
        String codigo = Entrada.cadena();
        System.out.print("Nombre: ");
        String nombre = Entrada.cadena();
        System.out.print("Horas anuales: ");
        int horasAnuales = Entrada.entero();
        System.out.print("Horas desdoble: ");
        int horasDesdoble = Entrada.entero();
        System.out.print("Curso: ");
        Curso curso = leerCurso();
        System.out.print("Especialidad: ");
        EspecialidadProfesorado especialidadProfesorado = leerEspecialidadProfesorado();
        return new Asignatura(codigo);
    }

    public static Asignatura getAsignaturaPorCodigo() {
        System.out.print("Introduce el codigo de la asignatura: ");
        String codigo = Entrada.cadena();
        return new Asignatura(codigo);
    }

    private static void mostrarAsignaturas(Asignaturas[] asignaturas) {
        for (Asignaturas asignatura : asignaturas) {
            if (asignatura == null) {
                System.out.println("No hay asignaturas registradas.");
            }
            System.out.println(asignatura);
        }
    }
    private static boolean asignaturaYaMatriculada(Asignaturas asignaturas) {
        Asignatura asignatura = getAsignaturaPorCodigo();
        asignatura = asignaturas.buscar(asignatura);
        return asignatura != null;
    }

    public static Matricula leerMatricula() {
        Alumno alumno = getAlumnoPorDni();
        alumno = alumnos.buscar(alumno);
        Asignatura asignatura = getAsignaturaPorCodigo();
        asignatura = asignaturas.buscar(asignatura);
        while (asignaturaYaMatriculada(asignaturas)) {
            System.out.println("ERROR: La asignatura ya se encuentra matriculada.");
            asignatura = getAsignaturaPorCodigo();
            asignatura = asignaturas.buscar(asignatura);
        }
        return new Matricula(alumno, asignatura);
    }

    public Matricula getMatriculaPorAlumno(Asignatura asignatura) {
        Alumno alumno = getAlumnoPorDni();
        return new Matricula(alumno, asignatura);
    }

}
