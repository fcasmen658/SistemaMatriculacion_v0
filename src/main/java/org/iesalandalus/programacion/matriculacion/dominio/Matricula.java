package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;

public class Matricula {

    public static int MAXIMO_MESES_ANTERIOR_ANULACION;
    public static int MAXIMO_DIAS_ANTERIOR_MATRICULA;
    public static int MAXIMO_NUMERO_HORAS_MATRICULA;
    public static int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA;
    private static String ER_CURSO_ACADEMICO = "[0-9]{2}" + "/" + "[0-9]{2}";
    public static String FORMATO_FECHA = "dd/MM/yyyy";

    private final int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;

    public Matricula() {
        this.idMatricula = 0;
    }

    public int getMAXIMO_MESES_ANTERIOR_ANULACION() {
        return MAXIMO_MESES_ANTERIOR_ANULACION;
    }

    public void setMAXIMO_MESES_ANTERIOR_ANULACION(int MAXIMO_MESES_ANTERIOR_ANULACION) {
        this.MAXIMO_MESES_ANTERIOR_ANULACION = MAXIMO_MESES_ANTERIOR_ANULACION;
    }

    public int getMAXIMO_DIAS_ANTERIOR_MATRICULA() {
        return MAXIMO_DIAS_ANTERIOR_MATRICULA;
    }

    public void setMAXIMO_DIAS_ANTERIOR_MATRICULA(int MAXIMO_DIAS_ANTERIOR_MATRICULA) {
        if (MAXIMO_DIAS_ANTERIOR_MATRICULA < 0) {
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser anterior a " + MAXIMO_DIAS_ANTERIOR_MATRICULA + " días.");
        }
        if (MAXIMO_DIAS_ANTERIOR_MATRICULA > 30) {
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser anterior a " + MAXIMO_DIAS_ANTERIOR_MATRICULA + " días.");
        }
        if (MAXIMO_DIAS_ANTERIOR_MATRICULA == 0) {
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser anterior a " + MAXIMO_DIAS_ANTERIOR_MATRICULA + " días.");
        }
        this.MAXIMO_DIAS_ANTERIOR_MATRICULA = MAXIMO_DIAS_ANTERIOR_MATRICULA;
    }

    public int getMAXIMO_NUMERO_HORAS_MATRICULA() {
        return MAXIMO_NUMERO_HORAS_MATRICULA;
    }

    public void setMAXIMO_NUMERO_HORAS_MATRICULA(int MAXIMO_NUMERO_HORAS_MATRICULA) {
        this.MAXIMO_NUMERO_HORAS_MATRICULA = MAXIMO_NUMERO_HORAS_MATRICULA;
    }

    public int getMAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA() {
        return MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA;
    }

    public void setMAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA(int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
        this.MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA = MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA;
    }

    public String getER_CURSO_ACADEMICO() {
        return ER_CURSO_ACADEMICO;
    }

    public void setER_CURSO_ACADEMICO(String ER_CURSO_ACADEMICO) {
        this.ER_CURSO_ACADEMICO = ER_CURSO_ACADEMICO;
    }

    public String getFORMATO_FECHA() {
        return FORMATO_FECHA;
    }

    public void setFORMATO_FECHA(String FORMATO_FECHA) {
        this.FORMATO_FECHA = FORMATO_FECHA;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public String getCursoAcademico() {
        return cursoAcademico;
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
        this.fechaAnulacion = fechaAnulacion;
    }

    public Matricula (int idMatricula, String cursoAcademico, LocalDate fechaMatriculacion, Alumno alumno, Asignatura[] coleccionMatricula) {

        this.idMatricula = idMatricula;
        this.cursoAcademico = cursoAcademico;
        this.fechaMatriculacion = fechaMatriculacion;
        this.fechaAnulacion = fechaAnulacion;
    }

    public Matricula(Matricula matricula, Asignatura asignatura) {
        this(matricula.getIdMatricula(), matricula.getCursoAcademico(), matricula.getFechaMatriculacion(), matricula.getAlumno(), matricula.getColeccionAsignaturas());
    }

    private Asignatura[] getColeccionAsignaturas() {
        return null;
    }

    private Alumno getAlumno() {
        return null;
    }


}
