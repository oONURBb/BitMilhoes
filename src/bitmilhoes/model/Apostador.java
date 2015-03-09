package bitmilhoes.model;

import bitmilhoes.containers.ContainerSet;
import bitmilhoes.containers.IContainerOperations;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javafx.collections.ObservableSet;


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
    
    private IContainerOperations<Movimento> movimentos;
    private IContainerOperations<Aposta> apostas;

    // NOVO
    public Apostador() {
        this(0, (short)0, "", LocalDate.now(), 0.0f);
        this.movimentos = new ContainerSet<>();
        this.apostas = new ContainerSet<>();
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
        if(valor >= 0 && this.saldo >= valor)
        {
            this.saldo-=valor;
            return true;
        }
        return false;
    }

    @Override
    public boolean alterarPin(short pinNovo, short pinActual) {
        if((pinNovo != pinActual) && (pinActual == this.pin))
        {
            this.pin = pinNovo;
            return true;
        }
        return false;
    }

    @Override
    public Aposta criarAposta(Chave chave) {
        Aposta ap = null;
        if(chave != null && this.debitar(2)){
            ap = new Aposta(this, chave);
            apostas.insert(ap);
        }
        return ap;
    }

    @Override
    public ContainerSet<Aposta> getApostas() {
        ContainerSet<Aposta> apostasAUX = new ContainerSet<>();
        for (Iterator iterator = apostas.getIterador(); iterator.hasNext();) {
            apostasAUX.insert((Aposta)iterator.next());
        }
        return apostasAUX;
    }

    @Override
    public ContainerSet<Movimento> getMovimentos() {
        ContainerSet<Movimento> movimentosAUX = new ContainerSet<>();
        for (Iterator iterator = movimentos.getIterador(); iterator.hasNext();) {
            movimentosAUX.insert((Movimento)iterator.next());
        }
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
