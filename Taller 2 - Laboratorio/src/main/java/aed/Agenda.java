package aed;

public class Agenda {
    private Fecha fecha_actual;
    private ArregloRedimensionableDeRecordatorios recordatorios_hoy;

    public Agenda(Fecha fechaActual) {
        this.fecha_actual = fechaActual;
        this.recordatorios_hoy = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
            this.recordatorios_hoy.agregarAtras(recordatorio);
    }

    @Override
    public String toString() {
        String res = "";
        res = res + this.fecha_actual.toString() + "\n=====\n";
        for (int i = 0 ; i < this.recordatorios_hoy.longitud() ; i++) {

            // Solamente imprimo aquellos 'recordatorios' que tienen fecha igual a 'this.fecha_actual'
            if (this.fecha_actual.equals(this.recordatorios_hoy.obtener(i).fecha())) {
                res = res + this.recordatorios_hoy.obtener(i).toString() + "\n" ;
            }
        }
        return res;
    }

    public void incrementarDia() {
        // Cambio la fecha actual (incrementando en uno).
        this.fecha_actual.incrementarDia();

        // Creo un nuevo array de recordatorios (donde voy a guardar los recordatorios que tiene fecha de mañana).
        ArregloRedimensionableDeRecordatorios nuevosRecordatorios = new ArregloRedimensionableDeRecordatorios();

        // Recorro el array de recordatorios y voy añadiendo a 'nuevosRecordatorios' aquellos que tienen fecha de mañana.
        for (int i = 0 ; i < this.recordatorios_hoy.longitud() ; i++) {
            if (this.recordatorios_hoy.obtener(i).fecha().equals(this.fecha_actual)) {
                nuevosRecordatorios.agregarAtras(this.recordatorios_hoy.obtener(i));
            }
        }

        // Cambio el puntero de this.recordatorios_hoy
        this.recordatorios_hoy = nuevosRecordatorios;
    }

    public Fecha fechaActual() {
        Fecha res = new Fecha(fecha_actual);
        return res;
    }

}
