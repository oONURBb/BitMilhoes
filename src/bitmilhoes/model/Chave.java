package bitmilhoes.model;

import java.util.ArrayList;
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

    private List<Integer> numeros;
    private List<Integer> estrelas;

    public Chave(){
        this.numeros = new ArrayList<>();
        this.estrelas = new ArrayList<>();
    }
    
    public Chave(List<Integer> numeros, List<Integer> estrelas) {  
        this.numeros = numeros;
        this.estrelas = estrelas;
    }

    public List<Integer> getNumeros()
    {
        return this.numeros;
    }

    public List<Integer> getEstrelas()
    {
        return this.estrelas;
    }
    
    public void setNumeros(List<Integer> numeros)
    {
        this.numeros = numeros;
    }
    
    public void setEstrelas(List<Integer> estrelas)
    {
        this.estrelas = estrelas;
    }

    public static void gerarChave(List<Integer> nrs, int max, int nElem)
    {
        Random rnd = new Random();
        do
        {
            int randomNum = rnd.nextInt(max) + 1;
            if(!nrs.contains(randomNum))
                nrs.add(randomNum);
        }while(nrs.size() != nElem);
    }
}
