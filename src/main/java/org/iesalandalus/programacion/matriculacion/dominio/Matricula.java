package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;

public class Matricula {

    public static int MAXIMO_MESES_ANTERIOR_ANULACION;
    public static int MAXIMO_DIAS_ANTERIOR_MATRICULA;
    public static int MAXIMO_NUMERO_HORAS_MATRICULA;
    public static int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA;
    private static String ER_CURSO_ACADEMICO = "[0-9]{2}" + "/" + "[0-9]{2}";
    public static String FORMATO_FECHA = "dd/MM/yyyy";
    private Alumno alumno;
    private Asignatura[] coleccionAsignaturas;

    private int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;

    public Matricula(int idMatricula, String cursoAcademico, LocalDate fechaMatriculacion, Alumno alumno, Asignatura[] coleccionMatricula) {
        setIdMatricula(idMatricula);
        setCursoAcademico(cursoAcademico);
        setFechaMatriculacion(fechaMatriculacion);
        setAlumno(alumno);
        setColeccionAsignaturas(coleccionMatricula);
    }

    public Matricula(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No es posible copiar una matricula nula.");
        }
        setIdMatricula(matricula.getIdMatricula());
        setCursoAcademico(matricula.getCursoAcademico());
        setFechaMatriculacion(matricula.getFechaMatriculacion());
        setAlumno(matricula.getAlumno());
        setColeccionAsignaturas(matricula.getColeccionAsignaturas());
    }

    public Matricula() {
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        if (idMatricula <= 0) {
            throw new NullPointerException("ERROR: El identificador de una matrícula no puede ser menor o igual a 0.");
        }
        this.idMatricula = idMatricula;
    }

    public String getCursoAcademico() {
        return cursoAcademico;
    }

    public void setCursoAcademico(String cursoAcademico) {
        if (cursoAcademico == null) {
            throw new NullPointerException("ERROR: El curso académico de una matrícula no puede ser nulo.");
        } else if (cursoAcademico.isBlank()) {
            throw new IllegalArgumentException("ERROR: El curso académico de una matrícula no puede estar vacío.");
        } else if (!cursoAcademico.matches(ER_CURSO_ACADEMICO)) {
            throw new IllegalArgumentException("ERROR: El formato del curso académico no es correcto.");
        }
        this.cursoAcademico = cursoAcademico;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public LocalDate getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(LocalDate fechaAnulacion) {
        if (fechaAnulacion == null) {
            throw new NullPointerException("ERROR: La fecha de anulación de una mátricula no puede ser nula.");
        } else if (fechaAnulacion.isAfter(LocalDate.now())) {
            throw new NullPointerException("ERROR: La fecha de anulación de una mátricula no puede ser posterior a hoy.");
        } else if (fechaAnulacion.isBefore(LocalDate.now().minusMonths(MAXIMO_MESES_ANTERIOR_ANULACION))) {
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser anterior a " + MAXIMO_MESES_ANTERIOR_ANULACION + " meses.");
        }
        this.fechaAnulacion = fechaAnulacion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Asignatura[] getColeccionAsignaturas() {
        return coleccionAsignaturas;
    }

    public void setColeccionAsignaturas(Asignatura[] coleccionAsignaturas) {
        this.coleccionAsignaturas = coleccionAsignaturas;
    }






}
