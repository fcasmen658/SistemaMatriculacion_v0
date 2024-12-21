package org.iesalandalus.programacion.matriculacion.dominio;

// import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumno extends Matricula {

    private static final String ER_TELEFONO = "\\d{9}";
    private static final String ER_CORREO = "\\w+@\\w+\\.\\w+";
    private static final String ER_DNI = "[0-9]{8}[A-Z]";
    public static final String FORMATO_FECHA = "dd/MM/yyyy";
    private static final String ER_NIA = "^[a-z]{4}\\d{3}$";
    private static final int MIN_EDAD_ALUMNADO = 16;

    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;
    private String nia;

    //todo revisar constructor sin parámetros en pdf de la tarea 4
    public Alumno() {

    }

    //Constructor con parámetros
    public Alumno(String nombre, String correo, String dni,String telefono, LocalDate fechaNacimiento, String nia) {
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
        setNia();
    }

    //Constructor copia
    public Alumno(Alumno alumno) {
        this.nombre = alumno.getNombre();
        this.telefono = alumno.getTelefono();
        this.correo = alumno.getCorreo();
        this.dni = alumno.getDni();
        this.fechaNacimiento = alumno.getFechaNacimiento();
        this.nia = alumno.getNia();
    }

    public String getNia() {
        return nia;
    }

    private void setNia() throws IllegalArgumentException{
        if (nombre == null) {
            throw new NullPointerException("ERROR: No puede establecerse el NIA del alumnado ya que el nombre es nulo.");
        }
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("ERROR: No puede establecerse el NIA del alumnado ya que el nombre es vacío.");
        }
        if (nombre.length() < 4) {
            throw new IllegalArgumentException("ERROR: El NIA del alumno/a no puede calcularse porque el nombre del alumno/a tiene menos de 4 caracteres.");
        }
        this.nia = nombre.toLowerCase().substring(0, 4) + dni.substring(dni.length() - 3);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws IllegalArgumentException {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un alumno no puede ser nulo.");
        }
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("ERROR: El nombre de un alumno no puede estar vacío.");
        }
        this.nombre = formateaNombre(nombre);
    }

    private String formateaNombre(String nombre) {

        // Eliminar espacios en blanco al principio y al final
        nombre = nombre.trim();

        // Dividir el nombre en palabras
        String[] palabras = nombre.split("\\s+"); // Utiliza una expresión regular para dividir por uno o más espacios

        StringBuilder nombreFormateado = new StringBuilder();

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) { // Asegurarse de que la palabra no esté vacía
                // Capitalizar la primera letra y poner el resto en minúsculas
                String palabraFormateada = palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();
                nombreFormateado.append(palabraFormateada).append(" "); // Agregar la palabra formateada y un espacio
            }
        }

        // Convertir a cadena y eliminar el último espacio
        return nombreFormateado.toString().trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) throws IllegalArgumentException {
        if (telefono == null) {
            throw new NullPointerException("ERROR: El teléfono de un alumno no puede ser nulo.");
        }
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("ERROR: El teléfono del alumno no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) throws IllegalArgumentException {
        if (correo == null) {
            throw new NullPointerException("ERROR: El correo de un alumno no puede ser nulo.");
        }
        if (!correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("ERROR: El correo del alumno no tiene un formato válido.");
        }
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) throws IllegalArgumentException {
        if (dni == null) {
            throw new NullPointerException("ERROR: El dni del alumno no puede ser nulo.");
        }
        if (dni.isBlank()) {
            throw new IllegalArgumentException("ERROR: El dni del alumno no tiene un formato válido.");
        }
        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("ERROR: El dni del alumno no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("ERROR: La letra del dni del alumno no es correcta.");
        }
        this.dni = dni;
    }

    private boolean comprobarLetraDni(String dni) throws IllegalArgumentException {
        if (dni == null)
            throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");
        if ((dni.isBlank()))
            throw new IllegalArgumentException("ERROR: El dni del alumno no tiene un formato válido.");
        if (!dni.matches(ER_DNI))
            throw new IllegalArgumentException("ERROR: El dni del alumno no tiene un formato válido.");
        Pattern pDNI = Pattern.compile(ER_DNI);
        Matcher mDNI;
        mDNI = pDNI.matcher(dni);
        int numeroDni = Integer.parseInt(mDNI.group(1));
        int resultadodivision = numeroDni % 23;
        String[] letrasTabla = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        boolean dniValido;
        dniValido = mDNI.group(2).equals(letrasTabla[resultadodivision]);
        return dniValido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    private void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new NullPointerException("ERROR: La fecha de nacimiento de un alumno no puede ser nula.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    private String getIniciales() {
        StringBuilder iniciales = new StringBuilder();
        for (int i = 0; i < nombre.length(); i++) {
            if (Character.isLetter(nombre.charAt(i))) {
                iniciales.append(Character.toUpperCase(nombre.charAt(i)));
            }
        }
        return iniciales.toString().toUpperCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno alumno)) return false;
        return Objects.equals(getDni(), alumno.getDni());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDni());
    }

    public String imprimir() {
        return "Alumno{" +
                "ER_TELEFONO='" + ER_TELEFONO + '\'' +
                ", ER_CORREO='" + ER_CORREO + '\'' +
                ", ER_DNI='" + ER_DNI + '\'' +
                ", FORMATO_FECHA='" + FORMATO_FECHA + '\'' +
                ", ER_NIA='" + ER_NIA + '\'' +
                ", MIN_EDAD_ALUMNADO=" + MIN_EDAD_ALUMNADO +
                ", nombre='" + nombre + '\'' +
                ", iniciales='" + getIniciales() + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", nia='" + getNia() + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "ER_TELEFONO='" + ER_TELEFONO + '\'' +
                ", ER_CORREO='" + ER_CORREO + '\'' +
                ", ER_DNI='" + ER_DNI + '\'' +
                ", FORMATO_FECHA='" + FORMATO_FECHA + '\'' +
                ", ER_NIA='" + ER_NIA + '\'' +
                ", MIN_EDAD_ALUMNADO=" + MIN_EDAD_ALUMNADO +
                ", nombre='" + nombre + '\'' +
                ", iniciales='" + getIniciales() + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", nia='" + getNia() + '\'' +
                '}';
    }

}
