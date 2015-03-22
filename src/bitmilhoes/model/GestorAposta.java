package bitmilhoes.model;

import bitmilhoes.containers.ContainerSet;
import bitmilhoes.containers.IContainerOperations;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Intermedeia a interface utilizada pelo utilizador e as classes com
 * responsabilidade pelas operaçoes de gestto dos apostadores, o registo de
 * apostas, sorteio e atribuicao de premios.
 * @author poo
 * @version 4.0
 * @updated 18-Jan-2015T17:13:36
 */
public class GestorAposta implements IGestorAposta {

    private Sorteio sorteio;
    private IContainerOperations<Apostador> apostadores;
    
    public GestorAposta() {
        this.apostadores = new ContainerSet<>();
        this.sorteio = new Sorteio();
    }

    public GestorAposta(Sorteio sorteio, IContainerOperations<Apostador> apostadores) {
        this.sorteio = sorteio;
        this.apostadores = apostadores;
    }


    @Override
    public boolean novoApostador(int telefone, short pin, String nome, LocalDate dataNascimento, float saldo) {
        Apostador ap = new Apostador(telefone, pin, nome, dataNascimento, saldo);
        if(apostadores.getElement(ap) == null){
            apostadores.insert(ap);
            return true;
        }
        return false;
    }

    @Override
    public boolean alterarPin(int telefone, short pinActual, short pinNovo) {
        for (Iterator iterator = apostadores.getIterador(); iterator.hasNext();) {
            Apostador ap = (Apostador)iterator.next();
            if(ap.getTelefone() == telefone && ap.alterarPin(pinNovo, pinActual))
                return true;
        }
        return false;
    }

    @Override
    public boolean validaApostador(int telefone, short pinActual) {
        for (Iterator iterator = apostadores.getIterador(); iterator.hasNext();) {
            Apostador ap = (Apostador)iterator.next();
            if(ap.getTelefone() == telefone && ap.getPin() == pinActual)
                return true;
        }
        return false;
    }

    @Override
    public boolean creditarMontante(int telefone, short pin, float montante) {
        for (Iterator iterator = apostadores.getIterador(); iterator.hasNext();) {
            Apostador ap = (Apostador)iterator.next();
            if(ap.getTelefone() == telefone && ap.getPin() == pin)
            {
                ap.creditar(montante);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean levantarMontante(int telefone, short pin, float montante) {
        for (Iterator iterator = apostadores.getIterador(); iterator.hasNext();) {
            Apostador ap = (Apostador)iterator.next();
            if(ap.getTelefone() == telefone && ap.getPin() == pin)
            {
                ap.creditar(montante);
                return true;
            }
        }
        return false;
    }

    @Override
    public Apostador removerApostador(int telefone, short pin) {
        Apostador apostadorAUX = null;
        
        if(validaApostador(telefone, pin))
        {
           apostadorAUX = getApostador(telefone, pin);
           return this.apostadores.remove(apostadorAUX);
        }
        return apostadorAUX;
    }

    @Override
    public void apostaAleatoria(int telefone, short pin) {
        Apostador apostadorAposta = null;
        
        if(validaApostador(telefone, pin))
        {
            apostadorAposta = getApostador(telefone, pin);
            apostadorAposta.criarAposta(new Chave());
        }
    }

    @Override
    public void apostaPersonalizada(int telefone, short pin, ContainerSet<Integer> numeros, ContainerSet<Integer> estrelas) {
        Apostador apostadorAux = null;
        if(validaApostador(telefone, pin))
        {
            apostadorAux = getApostador(telefone, pin);
            apostadorAux.criarAposta(new Chave(numeros, estrelas));
        }
    }

    @Override
    public IContainerOperations<Apostador> listarApostadoresNome() {
        IContainerOperations apostadoresNomes = this.apostadores;
        Collections.sort(apostadoresNomes.getElements(), ApostadorNomeComparator);
        return apostadoresNomes;
    }

    @Override
    public IContainerOperations<Apostador> listarApostadoresDataNascimento() {
        IContainerOperations apostadoresDataNascimento = this.apostadores;
        Collections.sort(apostadoresDataNascimento.getElements(), ApostadorDataNascimentoComparator);
        return apostadoresDataNascimento;
    }

    @Override
    public IContainerOperations<Apostador> listarApostadoresSaldo() {
        IContainerOperations apostadoresSaldo = this.apostadores;
        Collections.sort(apostadoresSaldo.getElements(), ApostadorSaldoComparator);
        return apostadoresSaldo;
    }

    @Override
    public IContainerOperations<Aposta> listarPremiosUltimoSorteio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IContainerOperations<Movimento> listarMovimentosApostador(int telefone, short pin) {
        IContainerOperations apostadoresMovimentos = this.apostadores;
        Collections.sort(apostadoresMovimentos.getElements(), ApostadoresMovimentosComparator);
        return apostadoresMovimentos;
    }

    @Override
    public boolean iniciarCicloApostas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Chave efectuarSorteio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Chave efectuarSorteio(ContainerSet<Integer> nums, ContainerSet<Integer> ests) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inicializaNrApostadores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ContainerSet<Apostador> getApostadores() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
    }
    
    public Apostador getApostador(int telefone, short pinActual) {
        Apostador ap = null;
        
        for (Iterator iterator = apostadores.getIterador(); iterator.hasNext();) {
            Apostador apIt = (Apostador)iterator.next();
            if(apIt.getTelefone() == telefone && apIt.getPin() == pinActual)
            {
                apIt = ap;
                return ap;
            }
        }
        return null;
    }

    /*Comparator para comparar os nomes dos Apostadores*/
    public static Comparator<Apostador> ApostadorNomeComparator = new Comparator<Apostador>(){
        @Override
        public int compare(Apostador ap1, Apostador ap2) {
           return (ap1.getNome().compareToIgnoreCase(ap2.getNome()));
        }      
    };
    
    /*Comparator utilizado para comparar todas as datas de nascimento da Class Apostador*/
    public static Comparator<Apostador> ApostadorDataNascimentoComparator = new Comparator<Apostador>(){
        @Override
        public int compare(Apostador ap1, Apostador ap2) {
            return (ap1.getDataNascimento().compareTo(ap2.getDataNascimento()));
        }
    };
    
    
    /*Comparator utilizado para comparar o saldo de todos os Apostadores*/
    public static Comparator<Apostador> ApostadorSaldoComparator = new Comparator<Apostador>(){

        @Override
        public int compare(Apostador ap1, Apostador ap2) {
            return (ap1.getSaldo() < ap2.getSaldo()) ? 1 : 0;
        }
        
    };
    
    // Comparator usado para comparar movimentos por ordem crescente de data e decrescente de valor
    public static Comparator<Movimento> ApostadoresMovimentosComparator = new Comparator<Movimento>(){
        @Override
        public int compare(Movimento m1, Movimento m2) {
            if(m1.getDataMovimento().toLocalDate().equals(m2.getDataMovimento().toLocalDate()))
                return (m2.getValor() > m1.getValor()) ? 1 : 0;
            else
                return (m1.getDataMovimento().compareTo(m2.getDataMovimento()) != 0) ? 1 : 0; 
        }  
    };
  
}
