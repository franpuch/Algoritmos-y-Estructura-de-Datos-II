package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Atributos privados del Conjunto.
    private Nodo root;
    private int cardinal;

    private class Nodo {
        // Atributos privados del Nodo.
        private T value;
        private Nodo left;
        private Nodo right;
        private Nodo father;

        // Constructores del Nodo.
        public Nodo() {}

        public Nodo(T valor, Nodo izq, Nodo der, Nodo padre) {
            this.value = valor;
            this.left = izq;
            this.right = der;
            this.father = padre;
        }
    }

    public ABB() {
        this.root = null;
        this.cardinal = 0;
    }

    public int cardinal() {
        return this.cardinal;
    }

    public T minimo(){
        // Me paro en la raiz.
        Nodo nodo_actual = this.root;

        // El Nodo mas pequeño es aquel que más a la izquierza está en el árbol (y que no 
        // tiene hijo izquierdo).
        while (nodo_actual.left != null) {
            nodo_actual = nodo_actual.left;
        }

        return nodo_actual.value;
    }

    public T maximo(){
        // Me paro en la raiz.
        Nodo nodo_actual = this.root;

        // El Nodo mas grande es aquel que más a la derecha está en el árbol (y que no 
        // tiene hijo derecho).
        while (nodo_actual.right != null) {
            nodo_actual = nodo_actual.right;
        }
        
        return nodo_actual.value;
    }
    // ? Supongo que la especificación de los método 'maximo' y 'minimo' no admite
    // ? aplicar el método a conjuntos vacíos.

    public void insertar(T elem){
        // Me paro en el primer elemento, también creo otro puntero que le siga por detrás.
        Nodo nodo_actual = this.root;
        Nodo nodo_anterior = null;

        // Verifico que no pertenezca.
        if (!(this.pertenece(elem))) {

            // Atajo el caso donde quiera añadir elemento en un ABB vacío.
            if (this.cardinal == 0) {
                Nodo añadir_raiz = new Nodo(elem, null, null, null);
    
                this.root = añadir_raiz;
                this.cardinal ++;
    
            } else {
                // Busco dónde hay que añadirlo.
                while (nodo_actual != null) {
                    if (nodo_actual.value.compareTo(elem) > 0) {
                        nodo_anterior = nodo_actual;
                        nodo_actual = nodo_actual.left;
                    } else {
                        nodo_anterior = nodo_actual;
                        nodo_actual = nodo_actual.right;
                    }
                }

                // Creo el Nodo a añadir.
                Nodo añadir = new Nodo(elem,null,null,nodo_anterior);

                // Enchufo 'añadir' a derecha/izquierda respectivamente.
                if (nodo_anterior.value.compareTo(elem) > 0) {
                    nodo_anterior.left = añadir; 
                    this.cardinal++;
                } else {   // * OBS -> compareTo() nunca dá cero aquí, porque ese caso ya lo filtré al pedir que no esté 'elem' en el conjunto.
                    nodo_anterior.right = añadir;
                    this.cardinal++;
                }
            }

        }
    }

    public boolean pertenece(T elem){
        Nodo nodo_actual = this.root;

        while (nodo_actual != null) {
            if (nodo_actual.value.compareTo(elem) == 0) {
                return true;
            } else if (nodo_actual.value.compareTo(elem) < 0) {
                nodo_actual = nodo_actual.right;
            } else {
                nodo_actual = nodo_actual.left;
            }
        }

        return false;
    }
    
    public void eliminar(T elem) {
        this.root = eliminarAux(this.root, elem);

        // Sólo decremento 'cardinal' si se encontró y eliminó 'elem'.
        if (this.root != null) { 
            this.cardinal--;
        }

        // Si elimino todos los elementos, la guarda de arriba no lleva cardinal a cero.
        // Me queda 1, entonces lo setteo 'a la fuerza'.
        if (this.root == null) {
            this.cardinal = 0;
        }
    }
    
    // Método auxiliar (para 'eliminar()')
    private Nodo eliminarAux(Nodo nodo, T valor) {
        // Caso Base: el valor no se encontró.
        if (nodo == null) {
            return null;
        }
    
        // Voy a buscar el nodo con el valor que quiero elimina.
        // Si lo encuentro, ejecuto la eliminación.

        int comparacion = valor.compareTo(nodo.value);

        if (comparacion < 0) {
            nodo.left = eliminarAux(nodo.left, valor);
        } 
        else if (comparacion > 0) {
            nodo.right = eliminarAux(nodo.right, valor);
        } 
        else {
            // comparación = 0 -> encontré el nodo.

            // Caso 1: Nodo hoja.
            if (nodo.left == null && nodo.right == null) {
                return null;
            } 
            // Caso 2: Un hijo por derecha.
            else if (nodo.left == null) {
                return nodo.right;
            } 
            // Caso 3: Un hijo por izquierda.
            else if (nodo.right == null) {
                return nodo.left;
            } 
            // Caso 3: Dos hijos.
            else {
                Nodo sucesor = encontrarSucesor(nodo.right);
                nodo.value = sucesor.value;
                nodo.right = eliminarAux(nodo.right, sucesor.value);
            }
        }

        return nodo;
    }
    
    // Método auxiliar (para 'eliminar()')
    // Tomo al sucesor como el más pequeño de la rama derecha.
    private Nodo encontrarSucesor(Nodo nodo) {
        while (nodo.left != null) {
            nodo = nodo.left;
        }

        return nodo;
    }

    // Método auxiliar (para 'toString()' y el iterador).
    // * Requiere -> 'elem' pertenece al ABB.
    private Nodo buscarNodo(T elem){
        Nodo res = this.root;

        while (res.value.compareTo(elem) != 0) {
            if (res.value.compareTo(elem) > 0) {
                res = res.left;
            } else {
                res = res.right;
            }
        }

        return res;
    }

    // Método auxiliar (para 'toString()' y el iterador).
    // * Requiere -> 'nodo' pertenece al ABB y no es el máximo del conjunto.
    private Nodo sucesor(Nodo nodo) {
        Nodo res;

        // Atajo el caso que 'nodo' sea la raíz.
        if (nodo.father == null) {

            if (nodo.right != null) {
                res = nodo.right;

                while (res.left != null) {
                    res = res.left;
                }
            } else {
                res = nodo.left;

                while (res.right != null) {
                    res = res.right;
                }
            }
            return res;
        } 
        // Ahora voy con el caso que no es raíz.
        else {
            if (nodo.right != null) {
                res = nodo.right;

                while (res.left != null) {
                    res = res.left;
                }

                return res;
            } else {
                res = nodo.father;

                while (res.right.value == nodo.value) {
                    res = res.father;
                }

                return res;
            }
        }
    }

    public String toString(){
        String res = "";

        if (this.cardinal == 0) {
            return "{}";
        } else {
            Nodo nodo_actual = this.buscarNodo(this.minimo());

            res = res + "{" + String.valueOf(nodo_actual.value);

            while (nodo_actual.value != this.maximo()) {
                nodo_actual = this.sucesor(nodo_actual);

                res = res + "," + String.valueOf(nodo_actual.value);
            }

            res = res + "}";

            return res;
        }
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo nodo_actual;
        private ABB<T> arbol_iterable;

        public ABB_Iterador(ABB<T> arbol){
            this.arbol_iterable = arbol;
            this.nodo_actual = arbol_iterable.root;
        }

        public boolean haySiguiente() {
            return nodo_actual.right != null;
        }
    
        public T siguiente() {
            T res = nodo_actual.value;

            if (this.haySiguiente()) {
                nodo_actual = nodo_actual.right;
            }

            return res;
        }
    }

    public Iterador<T> iterador() {
        // Voy a crear un ABB copia.
        // Esta copia va a ir copiando elementos en forma sucesiva (es decir, va a ser un
        // ABB escalera, que sólo tiene rama derecha).

        ABB<T> arbol_escalera = new ABB<T>();
        Nodo actual = this.buscarNodo(this.minimo());

        while (actual.value != this.maximo()) {
            arbol_escalera.insertar(actual.value);
            actual = sucesor(actual);
        }

        // Añado el último (que el ciclo no me lo añade).
        arbol_escalera.insertar(this.maximo());

        // Le paso al iterador el nuevo ABB copia.
        return new ABB_Iterador(arbol_escalera);
    }

}
