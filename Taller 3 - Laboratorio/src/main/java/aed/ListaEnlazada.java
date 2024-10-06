package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    // Atributos (privados)
    private Nodo primer_elemento;
    private Nodo ultimo_elemento;
    private int nro_elementos;

    // Defino una clase privada (clase con la que sólo se puede trabajar dentro de la clase
    // ListaEnlazada) para trabajar cada Nodo como un objeto (de esta nueva clase).
    private class Nodo {
        T valor;
        Nodo siguiente;
        Nodo anterior;

    }

    // Constructor.
    public ListaEnlazada() {
        // Cuando inicializo una instancia, ambos atributos (que son instancias de otra clase)
        // apuntan a 'null' ("nada").
        this.primer_elemento = null;
        this.ultimo_elemento = null;
        this.nro_elementos = 0;
    }

    public int longitud() {
        return this.nro_elementos;
    }

    public void agregarAdelante(T elem) {
        if (this.nro_elementos == 0) {
            // Creo el Nodo para ese 1er elemento,
            Nodo primero = new Nodo();
            primero.valor = elem;
            primero.siguiente = null;
            primero.anterior = null;

            // Asigno el puntero de 'primer_elemento' y 'ultimo_elemento' a ese Nodo.
            this.primer_elemento = primero;
            this.ultimo_elemento = primero;
            this.nro_elementos += 1;
        } else {
            // Creo el Nodo para el nuevo elemento.
            Nodo nuevo_elemento = new Nodo();
            nuevo_elemento.valor = elem;
            nuevo_elemento.siguiente = this.primer_elemento;
            nuevo_elemento.anterior = null;

            // A el viejo primer elemento le defino su puntero 'anterior' a este nuevo elemento.
            this.primer_elemento.anterior = nuevo_elemento;

            // Asigno el puntero de 'primer_elemento' a ese Nodo.
            this.primer_elemento = nuevo_elemento;
            this.nro_elementos += 1;
        }
    }

    public void agregarAtras(T elem) {
        if (this.nro_elementos == 0) {
            // Creo el Nodo para ese 1er elemento,
            Nodo primero = new Nodo();
            primero.valor = elem;
            primero.siguiente = null;
            primero.anterior = null;

            // Asigno el puntero de 'primer_elemento' y 'ultimo_elemento' a ese Nodo.
            this.primer_elemento = primero;
            this.ultimo_elemento = primero;
            this.nro_elementos += 1;
        } else {
            // Creo el Nodo para el nuevo elemento.
            Nodo nuevo_elemento = new Nodo();
            nuevo_elemento.valor = elem;
            nuevo_elemento.siguiente = null;
            nuevo_elemento.anterior = this.ultimo_elemento;

            // A el último elemento (hasta el momento), asigno su puntero de 'siguiente' a este
            // nuevo Nodo.
            this.ultimo_elemento.siguiente = nuevo_elemento;

            // Asigno el puntero de 'ultimo_elemento' a ese Nodo.
            this.ultimo_elemento = nuevo_elemento;
            this.nro_elementos += 1;
        }
    }

    public T obtener(int i) {
        int indice_actual = 0;
        Nodo nodo_actual = this.primer_elemento;
        T res = nodo_actual.valor;

        if (i == 0) {
            return res;
        } else {
            while (indice_actual != i) {
                indice_actual += 1;
                nodo_actual = nodo_actual.siguiente;
                res = nodo_actual.valor;
            }
            return res;
        }
    }

    private Nodo obtenerNodoCompleto (int i) {
        int indice_actual = 0;
        Nodo res = this.primer_elemento;

        if (i == 0) {
            return res;
        } else {
            while (indice_actual != i) {
                indice_actual += 1;
                res = res.siguiente;
            }
            return res;
        }
    }

    public void eliminar(int i) {
        // Agarro aquellos Nodos que voy a modificar: el que quiero eliminar (para poder definir
        // el anterior y el siguiente), el anterior al que quiero eliminar (para cambiar el puntero
        // de su atributo 'siguiente') y el siguiente al que quiero eliminar (para cambiar el 
        // puntero de su atributo 'anterior').
        Nodo elem_a_eliminar = this.obtenerNodoCompleto(i);
        Nodo elem_anterior = elem_a_eliminar.anterior;
        Nodo elem_siguiente = elem_a_eliminar.siguiente;

        if (this.longitud() == 1) {
            this.primer_elemento = null;
            this.ultimo_elemento = null;
            this.nro_elementos = 0;
        } else if (i == 0) {
            this.primer_elemento = elem_siguiente;
            this.primer_elemento.anterior = null;
            this.nro_elementos -= 1;
        } else if (i == this.longitud() - 1) {
            this.ultimo_elemento = elem_anterior;
            this.ultimo_elemento.siguiente = null;
            this.nro_elementos -= 1;
        } else {
            elem_anterior.siguiente = elem_siguiente;
            elem_siguiente.anterior = elem_anterior;
            this.nro_elementos -= 1;
        }
    }
    // ! No puedo utilizar el método 'obtener()' (el que es público) porque ese método devuelve
    // ! el elemento (del Nodo de posición 'i')y no el Nodo (que es en realidad lo que voy a 
    // ! eliminar). Entonces, implementé uno auxiliar (en forma privada).

    public void modificarPosicion(int indice, T elem) {
        // Agarro el Nodo que quiero cambiar.
        Nodo elem_a_modificar = this.obtenerNodoCompleto(indice);

        // Cambio su atributo 'valor' por el pasado como parámetro.
        elem_a_modificar.valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        // Para evita aliasing, creo nuevos objetos (en el heap) que copien a los atributos
        // que luego voy a redefinir.
        Nodo nuevo_primer_elemento = new Nodo();
        nuevo_primer_elemento.valor = lista.primer_elemento.valor;
        nuevo_primer_elemento.anterior = lista.primer_elemento.anterior;
        nuevo_primer_elemento.siguiente = lista.primer_elemento.siguiente;

        Nodo nuevo_ultimo_elemento = new Nodo();
        nuevo_ultimo_elemento.valor = lista.ultimo_elemento.valor;
        nuevo_ultimo_elemento.anterior = lista.ultimo_elemento.anterior;
        nuevo_ultimo_elemento.siguiente = lista.ultimo_elemento.siguiente;

        // ! El atributo 'nro_elementos', como es de tipo primitivo (int) si o si se pasa por copia.

        this.nro_elementos = lista.nro_elementos;
        this.primer_elemento = nuevo_primer_elemento;
        this.ultimo_elemento = nuevo_ultimo_elemento;
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ListaIterador implements Iterador<T> {
    	// Completar atributos privados

        public boolean haySiguiente() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
        
        public boolean hayAnterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }

        public T siguiente() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
        

        public T anterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
	    throw new UnsupportedOperationException("No implementada aun");
    }

}
