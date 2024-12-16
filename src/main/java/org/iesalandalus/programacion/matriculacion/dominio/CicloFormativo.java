package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.Objects;

public class CicloFormativo extends Alumno {

    private int codigo;// TODO Número de 4 dígitos
    private String familiaProfesional;
    private Grado grado;
    private String nombre;
    private int horas;

    public static final int MAXIMO_NUMERO_HORAS = 2000;

    public CicloFormativo(int codigo) {
        setCodigo(codigo);
        setFamiliaProfesional(familiaProfesional);
        setGrado(grado);
        setNombre(nombre);
        setHoras(horas);
    }

    public CicloFormativo(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: El ciclo formativo no puede ser nulo.");
        }
        this.codigo = cicloFormativo.getCodigo();
        this.familiaProfesional = cicloFormativo.getFamiliaProfesional();
        this.grado = cicloFormativo.getGrado();
        this.nombre = cicloFormativo.getNombre();
        this.horas = cicloFormativo.getHoras();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) throws IllegalArgumentException{
        if (false) {
            throw new NullPointerException("ERROR: El código de un ciclo formativo no puede ser nulo. Debe ser un número de 4 dígitos.");
        } else if (codigo < 1000 || codigo > 9999) {
            throw new IllegalArgumentException("ERROR: El código de un ciclo formativo debe ser un número de 4 dígitos.");
        }
        this.codigo = codigo;
    }

    public String getFamiliaProfesional() {
        return familiaProfesional;
    }

    public void setFamiliaProfesional(String familiaProfesional) {
        if (familiaProfesional == null) {
            throw new NullPointerException("ERROR: La familia profesional de un ciclo formativo no puede ser nula.");
        }
        this.familiaProfesional = familiaProfesional;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        if (grado == null) {
            throw new NullPointerException("ERROR: El grado de un ciclo formativo no puede ser nulo.");
        }
        this.grado = grado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un ciclo formativo no puede ser nulo.");
        }
        this.nombre = nombre;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) throws IllegalArgumentException {
        if (horas < 0 || horas > MAXIMO_NUMERO_HORAS) {
            throw new IllegalArgumentException("ERROR: El número de horas de un ciclo formativo no puede ser menor o igual a 0 ni mayor a " + MAXIMO_NUMERO_HORAS + ".");
        }
        this.horas = horas;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CicloFormativo that)) return false;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    public String imprimir() {
        return "Código ciclo formativo=" + codigo + ", nombre ciclo formativo=" + nombre;
    }

    @Override
    public String toString() {
        return "Código ciclo formativo=" + codigo +
                ", familia profesional=" + familiaProfesional + ", grado=" + grado +
                ", nombre ciclo formativo=" + nombre +
                ", horas=" + horas;
    }
}
