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

    //Constructor con parámetros
    public Alumno(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
        if (fechaNacimiento == null || dni == null || telefono == null || correo == null || nombre == null) {
            throw new NullPointerException("ERROR: La fecha de nacimiento de un alumno no puede ser nula.");
        }
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
    }

    //Constructor copia
    public Alumno(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No es posible copiar un alumnado nulo.");
        }
        this.nombre = alumno.getNombre();
        this.telefono = alumno.getTelefono();
        this.correo = alumno.getCorreo();
        this.dni = alumno.getDni();
        this.fechaNacimiento = alumno.getFechaNacimiento();
        this.nia = alumno.getNia();
    }

    public Alumno(String nombre, String apellidos, String dni, String correo, String telefono, String fechaNacimiento) {
    }

    public Alumno() {
    }

    public static String formateaNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un alumno no puede ser nulo.(27)");
        }
        if (nombre.isBlank() || nombre.isEmpty()) {
            throw new IllegalArgumentException("ERROR: El nombre de un alumno no puede estar vacío.(30)");
        }

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws NullPointerException {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un alumno no puede ser nulo.");
        }
        this.nombre = nombre;
    }

    public String getNia() {
        return nia;
    }

    private void setNia(String nia) throws NullPointerException {
        if (nia == null) {
            throw new NullPointerException("ERROR: No puede establecerse el NIA del alumnado ya que el nombre es nulo.");
        }
        this.nia = nia;
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
        /*if (!correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("ERROR: El correo del alumno no tiene un formato válido.");
        }*/
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
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", nia='" + nia + '\'' +
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
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", nia='" + nia + '\'' +
                '}';
    }

}


/*
import java.util.Scanner;

public class CrearNIA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar el nombre
        System.out.print("Introduce tu nombre: ");
        String nombre = scanner.nextLine();

        // Solicitar el DNI
        System.out.print("Introduce tu DNI: ");
        String dni = scanner.nextLine();

        // Validar que el nombre tenga al menos 4 caracteres
        if (nombre.length() < 4) {
            System.out.println("El nombre debe tener al menos 4 caracteres.");
            return;
        }

        // Validar que el DNI tenga al menos 3 dígitos
        if (dni.length() < 3) {
            System.out.println("El DNI debe tener al menos 3 dígitos.");
            return;
        }

        // Obtener los primeros 4 caracteres del nombre en minúsculas
        String primerosCuatro = nombre.substring(0, 4).toLowerCase();

        // Obtener los últimos 3 dígitos del DNI
        String ultimosTres = dni.substring(dni.length() - 3);

        // Crear el NIA
        String nia = primerosCuatro + ultimosTres;

        // Mostrar el NIA
        System.out.println("El NIA es: " + nia);
    }
}
 */