package bitmilhoes.model;

import bitmilhoes.containers.ContainerSet;
import bitmilhoes.containers.IContainerOperations;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Não existem "m�ltiplas", pelo que cada chave ter� exactamente 5 numeros e 2
 * estrelas.
 * @author ipoo
 * @version 1.0
 * @created 11-Nov-2010 01:11:27
 */
public class Chave {

    private IContainerOperations<Integer> numeros;
    private IContainerOperations<Integer> estrelas;

    public Chave(){
        this.numeros = new ContainerSet<>();
        this.estrelas = new ContainerSet<>();
    }
    
    public Chave(ContainerSet<Integer> numeros, ContainerSet<Integer> estrelas) {  
        this.numeros = numeros;
        this.estrelas = estrelas;
    }

    public ContainerSet<Integer> getNumeros()
    {
        ContainerSet<Integer> numerosAUX = new ContainerSet<>();
        for (Iterator iterator = numeros.getIterador(); iterator.hasNext();) {
            numerosAUX.insert((Integer)iterator.next());
        }
        return numerosAUX;
    }

    public ContainerSet<Integer> getEstrelas()
    {
        ContainerSet<Integer> estrelasAUX = new ContainerSet<>();
        for (Iterator iterator = estrelas.getIterador(); iterator.hasNext();) {
            estrelasAUX.insert((Integer)iterator.next());
        }
        return estrelasAUX;
    }
    
    public void setNumeros(ContainerSet<Integer> numeros)
    {
        this.numeros = numeros;
    }
    
    public void setEstrelas(ContainerSet<Integer> estrelas)
    {
        this.estrelas = estrelas;
    }

    public static void gerarChave(ContainerSet<Integer> nrs, int max, int nElem)
    {
        Random rnd = new Random();
        do
        {
            int randomNum = rnd.nextInt(max) + 1;
            if(nrs.getElement(randomNum) == null) {
                nrs.insert(randomNum);
            }
        }while(nrs.size() != nElem);
    }
}
