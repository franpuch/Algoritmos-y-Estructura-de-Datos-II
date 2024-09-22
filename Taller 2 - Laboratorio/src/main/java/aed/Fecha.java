package aed;

public class Fecha {
    private int dia;
    private int mes;


    public Fecha(int dia, int mes) {
        this.dia = dia;
        this.mes = mes;
    }

    public Fecha(Fecha fecha) {
        // Implementar
    }

    public Integer dia() {
        return this.dia;
    }

    public Integer mes() {
        return this.mes;
    }

    public String toString() {
        String dia_string = String.valueOf(this.dia);
        String mes_string = String.valueOf(this.mes);
        return dia_string + "/" + mes_string;
    }

    @Override
    public boolean equals(Object otra) {
        if (this.getClass() != otra.getClass()) {
        return false;
        } else if (otra instanceof String){
            return otra.equals(this.toString());
        } else {
            Fecha otra_casteada = (Fecha) otra;
            return ((this.dia == otra_casteada.dia) && (this.mes == otra_casteada.mes));
        }
    }

    public void incrementarDia() {
        if (this.dia == diasEnMes(this.mes)) {
            this.dia = 1;
            this.mes += 1;
        } else {
            this.dia += 1;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
