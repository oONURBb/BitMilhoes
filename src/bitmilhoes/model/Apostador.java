package bitmilhoes.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Conta do apostador cujo saldo pode ser utilizado para pagar o registo de
 * apostas. O valor dos premios são creditados nesta conta.
 * @author ipoo
 * @version 1.0
 * @updated 17-Nov-2010 12:54:02
 */
public class Apostador implements IApostador{

    /**
     * O numero de telefone identifica univocamente um apostador. Não pode haver
     * numeros de telefone repetidos, pelo que devera sempre ser validardo este facto
     * no registo de novos apostadores.
     */
    private int telefone;
    /**
     * Nome completo do apostador.
     */
    private String nome;
    /**
     * O PIN do apostador deve ter exactamente 4 digitos. O PIN Não devera ser
     * mostrado em qualquer detalhe/listagem de apostadores.
     */
    private short pin;
    /**
     * A data de nascimento devera aparecer no formato dd-mm-aaaa (e.g. 03-02-2008).
     */
    private LocalDate dataNascimento;
    /**
     * Deve ser inserida automaticamente sempre que o saldo for actualizado.
     */
    private LocalDateTime dataSaldo;
    /**
     * Os valores monetarios devem ser sempre apresentados com duas casas decimais, 
     * e o simbolo do euro(e.g. 99,99 €)
     */
    private float saldo;
    
    private List<Movimento> movimentos;
    private List<Aposta> apostas;

    // NOVO
    public Apostador() {
        this(0, (short)0, "", LocalDate.now(), 0.0f);
        this.movimentos = new ArrayList<>();
        this.apostas = new ArrayList<>();
    }

    // ALTERADO
    public Apostador(int telefone, short pin, String nome, LocalDate dataNascimento, float saldo) {
        this.telefone = telefone;
        this.pin = pin;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.saldo = saldo;
    }

    @Override
    public boolean creditar(float valor) {
        if(valor >= 0)
        {
            this.saldo += valor;
            return true;
        }
        return false;
    }

    @Override
    public boolean debitar(float valor) {
        if(valor >= 0 && valor > saldo)
        {
            saldo-=valor;
            return true;
        }
        return false;
    }

    @Override
    public boolean alterarPin(short pinNovo, short pinActual) {
        if(pinNovo == pinActual)
        {
            pinActual = pinNovo;
            return true;
        }
        
        return false;
    }

    @Override
    public Aposta criarAposta(Chave chave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Aposta> getApostas() {
        List<Aposta> apostasAUX = new LinkedList<>();
        apostasAUX.addAll(apostas);
        return apostasAUX;
    }

    @Override
    public List<Movimento> getMovimentos() {
        List<Movimento> movimentosAUX = new LinkedList<>();
        movimentosAUX.addAll(movimentos);
        return movimentosAUX;
    }

    @Override
    public LocalDateTime getDataSaldo() {
        return this.dataSaldo;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public int getNrApostas() {
        return this.apostas.size();
    }

    @Override
    public int getNrMovimentos() {
        return this.movimentos.size();
    }

    @Override
    public short getPin() {
        return pin;
    }

    @Override
    public float getSaldo() {
        return this.saldo;
    }

    @Override
    public int getTelefone() {
        return this.telefone;
    }


}
