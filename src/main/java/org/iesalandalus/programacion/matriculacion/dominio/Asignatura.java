package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.Objects;

public class Asignatura {

    private String codigo;
    private String nombre;
    private int horasAnuales;
    private Curso curso;
    private int horasDesdoble;
    private EspecialidadProfesorado especialidadProfesorado;
    private CicloFormativo cicloFormativo;

    public static final int MAX_NUM_HORAS_ANUALES = 300;
    public static final int MAX_NUM_HORAS_DESDOBLES = 6;
    private static final String ER_CODIGO = "[A-Z]{4}\\d{3}";

    public String getCodigo() {
        return codigo;
    }

    private void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHorasAnuales() {
        return horasAnuales;
    }

    public void setHorasAnuales(int horasAnuales) {
        this.horasAnuales = horasAnuales;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getHorasDesdoble() {
        return horasDesdoble;
    }

    public void setHorasDesdoble(int horasDesdoble) {
        this.horasDesdoble = horasDesdoble;
    }

    public EspecialidadProfesorado getEspecialidadProfesorado() {
        return especialidadProfesorado;
    }

    public void setEspecialidadProfesorado(EspecialidadProfesorado especialidadProfesorado) {
        this.especialidadProfesorado = especialidadProfesorado;
    }

    public CicloFormativo getCicloFormativo() {
        return cicloFormativo;
    }

    public void setCicloFormativo(CicloFormativo cicloFormativo) {
        this.cicloFormativo = cicloFormativo;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Asignatura that)) return false;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", horasAnuales=" + horasAnuales +
                ", curso=" + curso +
                ", horasDesdoble=" + horasDesdoble +
                ", especialidadProfesorado=" + especialidadProfesorado +
                ", cicloFormativo=" + cicloFormativo +
                '}';
    }

    public String imprimir() {
        return "Asignatura{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", horasAnuales=" + horasAnuales +
                ", curso=" + curso +
                ", horasDesdoble=" + horasDesdoble +
                ", especialidadProfesorado=" + especialidadProfesorado +
                ", cicloFormativo=" + cicloFormativo +
                '}';
    }

    public Asignatura(String codigo) {
        setCodigo(codigo);
        setNombre(nombre);
        setHorasAnuales(horasAnuales);
        //setCurso(curso);
        setHorasDesdoble(horasDesdoble);
        //setEspecialidadProfesorado(especialidadProfesorado);
        setCicloFormativo(cicloFormativo);
    }

    public Asignatura(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No es posible copiar una asignatura nula.");
        }
        setCodigo(asignatura.getCodigo());
        setNombre(asignatura.getNombre());
        setHorasAnuales(asignatura.getHorasAnuales());
        setCurso(asignatura.getCurso());
        setHorasDesdoble(asignatura.getHorasDesdoble());
        setEspecialidadProfesorado(asignatura.getEspecialidadProfesorado());
        setCicloFormativo(asignatura.getCicloFormativo());
    }



}
