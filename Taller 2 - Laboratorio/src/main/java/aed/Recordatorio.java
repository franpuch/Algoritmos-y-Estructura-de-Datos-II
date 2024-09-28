package aed;

public class Recordatorio {
    private String mensaje;
    private Fecha fecha;
    private Horario horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.mensaje = mensaje;
        this.fecha = new Fecha(fecha); // Para evitar aliasing, asigno una copia del parámetro de entrada.
        this.horario = new Horario(horario);
    }

    public Horario horario() {
        Horario h1 = new Horario(this.horario); // Nuevamente, para evitar aliasing, devuelvo una copia del atributo original.
                                                // Si guardo el resultado en una nueva variable y le hago cambios, salvo los atributos del objeto.
        return h1;
    }

    public Fecha fecha() {
        Fecha f1 = new Fecha(this.fecha); // Nuevamente, para evitar aliasing, devuelvo una copia del atributo original.
                                          // Si guardo el resultado en una nueva variable y le hago cambios, salvo los atributos del objeto.
        return f1;
    }

    public String mensaje() {
        return this.mensaje;
    }

    @Override
    public String toString() {
        String mensaje1 = this.mensaje;
        Fecha fecha1 = this.fecha;
        Horario horario1 = this.horario;
        return mensaje1 + " @ " + fecha1.toString() + " " + horario1.toString();
    }

    @Override
    public boolean equals(Object otro) {
        if (this.getClass() != otro.getClass()) {
            return false;
        } else {
            Recordatorio recordatorio1 = (Recordatorio) otro;
            return ((this.mensaje == recordatorio1.mensaje) && (this.fecha.equals(recordatorio1.fecha)) && (this.horario.equals(recordatorio1.horario)));
        }
    }

}
