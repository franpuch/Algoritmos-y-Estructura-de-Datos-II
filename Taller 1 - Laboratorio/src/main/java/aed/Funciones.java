package aed;

class Funciones {
    int cuadrado (int x) {
        int res = x * x;
        return res;
    }

    double distancia (double x , double y) {
        double res = Math.sqrt (x * x + y * y);
        return res;
    }

    boolean esPar (int n) {
        return (n % 2 == 0);
    }

    boolean esBisiesto (int n) {
        boolean res = false;
        if (n % 400 == 0) {
            res = true;
        } else if ((n % 4 == 0) && (!(n % 100 == 0))) {
            res = true;
        }
        return res;
    }

    int factorialIterativo (int n) {
        int res = 1;
        for (int i = 1 ; i <= n ; i++) {
            res *= i;
        }
        return res;
    }

    int factorialRecursivo (int n) {
        int res = 1;
        if (n == 0) {
            res *= 1;
        } else {
            res = n * factorialRecursivo(n-1);
        }
        return res;
    }

    boolean esPrimo (int n) {
        boolean res = false;
        int cantidad_de_divisores = 0;
        for ( int i = n ; i >= 1 ; i--) {
            if (n % i == 0) {
                cantidad_de_divisores++;
            }
        }
        if (cantidad_de_divisores == 2) {
            res = true;
        }
        return res;
    }

    int sumatoria (int[] numeros) {
        int res = 0;
        for (int i = 0 ; i < numeros.length ; i++) {
            res += numeros[i];
        }
        return res;
    }

    int busqueda (int[] numeros, int buscado) {
        int res = -1;
        for (int i = 0 ; i < numeros.length ; i ++) {
            if (numeros[i] == buscado) {
                res = i;
            }
        }
        return res;
    }
/* La consigna no me dice nada acerca de qué debe devolver si 'buscado' NO ESTÁ EN 'numeros'.
 Chumié los casos de test y ese caso no es un test case.
 De todos modos, defino en mi implementación que, si 'buscado' no es encontrado en 'numeros',
 devuelve '-1'.
*/ 

    boolean tienePrimo (int[] numeros) {
        boolean res = false;
        for (int i : numeros) {
            if (esPrimo(i)) {
                res = true;
            }
        }
        return res;
    }

    boolean todosPares (int[] numeros) {
        boolean res = true;
        for (int i : numeros) {
            if (!(esPar(i))) {
                res = false;
            }
        }
        return res;
    }

    boolean esPrefijo (String s1, String s2) {
        boolean res = true;
        if (s1.length() > s2.length()) {
            res = false;
        } else {
            for (int i = 0 ; i < s1.length() ; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    res = false;
                }
            }
        }
        return res;
    }

    boolean esSufijo(String s1, String s2) {
        boolean res = true;
        if (s1.length() > s2.length()) {
            res = false;
        } else {
            // Quiero aprovechar 'esPrefijo'.
            // En 'candidato' armo un nuevo string donde meto los últimos 'n' elementos de 's2'.
            // 'n' es la longitud de 's1'.
            // Tengo que tener cuidado de meter los elementos (dentro de 'candidato') en el orden en el que aparecen en 's2'.
            String candidato = "";
            int i = (s2.length() - s1.length());
            while (i < s2.length()) {
                candidato = candidato + s2.charAt(i);
                i ++;
            }
            // Ahora utilizo 'esPrefijo' pasandole 's1' y 'candidato'.
            res = esPrefijo (s1,candidato);
        }
        return res;
    }
}