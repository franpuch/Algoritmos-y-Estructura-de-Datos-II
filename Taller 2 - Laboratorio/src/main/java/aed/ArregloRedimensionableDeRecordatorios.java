package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] elementos;

    public ArregloRedimensionableDeRecordatorios() {
        this.elementos = new Recordatorio[0];
    }

    public ArregloRedimensionableDeRecordatorios(Recordatorio[] recordatorios) {
        this.elementos = recordatorios;
    }

    public int longitud() {
        return this.elementos.length;
    }

    public void agregarAtras(Recordatorio i) {
        // Creo el nuevo array (donde voy a hacer la copia y agregar el nuevo elemento).
        Recordatorio[] res = new Recordatorio[this.elementos.length + 1];

        // Copio los elementos que había.
        if (this.elementos.length != 0) {
            for (int j = 0 ; j < this.elementos.length ; j++) {
                res[j] = this.elementos[j];
            }
        }
       
        // Añado el nuevo elemento (en la última posición del array).
        res[this.elementos.length] = i;

        // Modifico el puntero de this.elementos (le digo que apunte al nuevo array creado).
        this.elementos = res;
    }

    public Recordatorio obtener(int i) {
        Recordatorio res = this.elementos[i];
        return res; 
    }

    public void quitarAtras() {
        // Creo el nuevo array (donde voy a hacer la copia eliminando el último elemento).
        Recordatorio[] res = new Recordatorio[this.elementos.length - 1];

        // Copio los elementos hasta el ante-último (el último no).
        for (int i = 0 ; i < this.elementos.length - 1 ; i++) {
            res[i] = this.elementos[i];
        }

        // Modifico el puntero de this.elementos (le digo que apunte al nuevo array creado).
        this.elementos = res;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        this.elementos[indice] = valor;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        // Creo un nuevo array (donde voy a hacer la copia).
        Recordatorio[] elem = new Recordatorio[vector.elementos.length];

        // Copio los elementos.
        for (int i = 0 ; i < vector.elementos.length ; i++) {
            elem[i] = vector.elementos[i];
        }

        // Defino el puntero de this.elementos (le digo que apunte al nuevo array creado).
        this.elementos = elem;
    }

    // ? No entiendo por qué el método devuelve otra instancia (y no simplemento el array).
    // ? No estoy seguro de si puedo pasar 'this' como parámetro para (de cienta forma) decirle:
    // ?    pasale al constructor el mismo objeto. 
    // ? Pero funciono así que...
    public ArregloRedimensionableDeRecordatorios copiar() {
        ArregloRedimensionableDeRecordatorios nuevo = new ArregloRedimensionableDeRecordatorios(this);
        return nuevo;
    }
}
