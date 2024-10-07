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
        int indice_actual = 0;
        Nodo nodo_actual = lista.primer_elemento;

        while (indice_actual != lista.nro_elementos) {
            this.agregarAtras(nodo_actual.valor);

            nodo_actual = nodo_actual.siguiente;

            indice_actual += 1;
        }
    }
    
    @Override
    public String toString() {
        String res = "[";
        int indice = 0;
        Nodo elem_actual = this.primer_elemento;

        while (indice != this.nro_elementos - 1) {
            // Copio el valor que quiero añadir.
            T valor_de_nodo = elem_actual.valor;
            // Lo casteo a String.
            String.valueOf(valor_de_nodo);
            // Lo añado a res.
            res = res + valor_de_nodo + ", ";
            // Cambio a 'elem_actual' por el siguiente.
            elem_actual = elem_actual.siguiente;
            // Avanzo el índice (recién me olvidé y se me colgó el ciclo jejeje).
            indice += 1;
        }

        // Ahora añado el último elemento (que cierra el corchete)
        T ultimo_elemento = this.ultimo_elemento.valor;
        String.valueOf(ultimo_elemento);
        res = res + ultimo_elemento + "]";

        // Finalmente, devuelvo el String completo.
        return res;
    }

// ------------------------------------------------------------------------------------------ //

    private class ListaIterador implements Iterador<T> {
    	// Atributos privados.
        private Nodo nodo_apuntado;

        public boolean haySiguiente() {
	        if (nodo_apuntado.siguiente != null) {
                return true;
            } else {
                return false;
            }
        }
        
        public boolean hayAnterior() {
	        return nodo_apuntado.anterior != null;
        }
        // Basicamente mismo fin que el método 'haySiguiente()', pero en una sola linea.

        public T siguiente() {
	        if (nodo_apuntado.siguiente != null) {
                nodo_apuntado = nodo_apuntado.siguiente;
                T res = nodo_apuntado.valor;
                return res;
            } else {
                throw new NoSuchElementException("No hay más elementos");
            }
        }
        // Si se intenta avanzar desde el último elemento de la lista, arrojo una excepción.

        public T anterior() {
	        if (nodo_apuntado.anterior != null) {
                T res = nodo_apuntado.valor;
                nodo_apuntado = nodo_apuntado.anterior;
                return res;
            } else {
                throw new NoSuchElementException("No hay más elementos anteriores");
            }
        }
        // Si se intenta retroceder desde el primer elemento de la lista, arrojo una excepción.
    }

    public Iterador<T> iterador() {
	    ListaIterador iterador = new ListaIterador();

        // Primero atajo el caso de la lista vacía. Luego en el 'else' atajo lo demás.
        if (this.nro_elementos == 0) {
            // Creo un nodo vacío.
            Nodo nodo_vacio = new Nodo();
            nodo_vacio.anterior = null;
            nodo_vacio.siguiente = null;

            // El atributo 'nodo_actual' del iterador apunta al nodo vacío.
            iterador.nodo_apuntado = nodo_vacio;

            // Devuelvo el iterador.
            return iterador;
        } else {
            // Creo una copia de la lista.
            ListaEnlazada<T> lista_iterable = new ListaEnlazada<>(this);

            // A la copia creada (que es la lista sobre la que va a iterar el iterador) le agrego
            // al principio un 'nodo ubicador'. Este nuevo nodo, es un nodo cuyo atributo
            // 'anterior' apunta a 'null'; pero el atributo 'siguiente' apunta el ex primer elemento.
            // ! OBS -> El 'valor' de este 'nodo_ubicador' no me importa, nunca voy a acceder a él.
            // !        Para que no se me rompan los tipos, le pongo un valor repetido.
            lista_iterable.agregarAdelante(this.obtener(0));

            // El atributo 'nodo_apuntado' (del iterador) apunta al primer elemento de esta copia.
            iterador.nodo_apuntado = lista_iterable.primer_elemento;

            // Devuelvo el iterador.
            return iterador;
        }
    }

}
