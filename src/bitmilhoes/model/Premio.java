package bitmilhoes.model;

/**
 * @author ipoo
 * @version 1.0
 * @created 11-Nov-2010 01:11:27
 */
public class Premio {

    private int ordinal; // 1º Premio, 2º Premio, 3º Premio, ...,etc
    private int numero; // Numero de numeros certos
    private int estrela; // Numero de estrelas certas
    private float percentagem; // Percentagem atribuida

    public Premio() {
        this(0, 0, 0, 0.0f);
    }
    
    public Premio(int ordinal,int numero,int estrela,float percentagem) {
       this.ordinal = ordinal;
       this.numero = numero;
       this.estrela = estrela;
       this.percentagem = percentagem;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getEstrela() {
        return estrela;
    }

    public void setEstrela(int estrela) {
        this.estrela = estrela;
    }

    public float getPercentagem() {
        return percentagem;
    }

    public void setPercentagem(float percentagem) {
        this.percentagem = percentagem;
    }

    
}
