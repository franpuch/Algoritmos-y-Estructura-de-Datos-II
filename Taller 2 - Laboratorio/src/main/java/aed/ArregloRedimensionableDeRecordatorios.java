package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] elementos;
    private int tamaño;

    public ArregloRedimensionableDeRecordatorios(Recordatorio[] recordatorios) {
        this.elementos = recordatorios;
        this.tamaño = recordatorios.length;
    }

    public int longitud() {
        return this.tamaño;
    }

    public void agregarAtras(Recordatorio i) {
        // Creo el nuevo array (donde voy a hacer la copia y agregar el nuevo elemento).
        Recordatorio[] res = new Recordatorio[this.tamaño + 1];

        // Copio los elementos que había.
        for (int j = 0 ; j < this.tamaño ; j++) {
            res[j] = this.elementos[j];
        }

        // Añado el nuevo elemento (en la última posición del array).
        res[this.tamaño] = i;

        // Modifico el puntero de this.elementos (le digo que apunte al nuevo array creado).
        this.elementos = res;
    }

    public Recordatorio obtener(int i) {
        Recordatorio res = this.elementos[i];
        return res; 
    }

    public void quitarAtras() {
        // Creo el nuevo array (donde voy a hacer la copia eliminando el último elemento).
        Recordatorio[] res = new Recordatorio[this.tamaño - 1];

        // Copio los elementos hasta el ante-último (el último no).
        for (int i = 0 ; i < this.tamaño - 1 ; i++) {
            res[i] = this.elementos[i];
        }

        // Modifico el puntero de this.elementos (le digo que apunte al nuevo array creado).
        this.elementos = res;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        // Implementar
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        // Implementar
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        // Implementar
        return null;
    }
}
