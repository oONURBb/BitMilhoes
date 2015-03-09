package bitmilhoes.model;

import bitmilhoes.containers.ContainerSet;
import bitmilhoes.containers.IContainerOperations;
import java.time.LocalDate;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void apostaAleatoria(int telefone, short pin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void apostaPersonalizada(int telefone, short pin, ContainerSet<Integer> numeros, ContainerSet<Integer> estrelas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ContainerSet<Apostador> listarApostadoresNome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ContainerSet<Apostador> listarApostadoresDataNascimento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ContainerSet<Apostador> listarApostadoresSaldo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ContainerSet<Aposta> listarPremiosUltimoSorteio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ContainerSet<Movimento> listarMovimentosApostador(int telefone, short pin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    
  
}
