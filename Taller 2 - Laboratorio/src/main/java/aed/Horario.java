package aed;

public class Horario {
    private int hora;
    private int minutos;


    public Horario(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }

    public Horario (Horario horario1) {
        this.hora = horario1.hora;
        this.minutos = horario1.minutos;
    }
// Constructor copia.

    public int hora() {
        return this.hora;
    }

    public int minutos() {
        return this.minutos;
    }

    @Override
    public String toString() {
        return String.valueOf(this.hora) + ":" + String.valueOf(this.minutos);
    }

    @Override
    public boolean equals(Object otro) {
        if (this.getClass() != otro.getClass()) {
        return false;
        } else if (otro instanceof String) {
            return (otro.equals(this.toString()));
        } else {
            Horario otro_casteada = (Horario) otro;
            return ((this.hora == otro_casteada.hora) && (this.minutos == otro_casteada.minutos));
        }
    }

}
