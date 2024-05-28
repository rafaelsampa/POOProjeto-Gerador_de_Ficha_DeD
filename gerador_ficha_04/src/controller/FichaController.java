package controller;

import view.FichaGUI;
import model.Ficha;
import model.FichaModeL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;

import entities.Antecedentes;
import entities.Classes;
import entities.Racas;

public class FichaController {


    private FichaGUI NovaFichaGUI; // View Object
    private Ficha NovaFicha; // Model object
    private Racas RacaEscolhida;
    private Classes ClasseEscolhida;
    private Antecedentes AntecedentesEscolhido;
    
    private JComboBox combo;
    private String itemSelected;
    private String itemDeselected;
    private ArrayList<Integer> deselecteds = new ArrayList<>();
    private JComboBox comboIntermediaria;

    public FichaController(Ficha NovaFicha, FichaGUI NovaFichaGUI){
        this.NovaFicha = NovaFicha;
        this.NovaFichaGUI = NovaFichaGUI;
    }

    public JComboBox setCombo(JComboBox inputCombo){
        combo = inputCombo;
        return combo;
    }

    public JComboBox setComboInter(JComboBox inputCombo){

        comboIntermediaria = inputCombo;

        return comboIntermediaria;
    }

    public void ideiaDeMartins ( ItemEvent oEvento, JComboBox novaCombo){

        ArrayList<String> vetorSelect = new ArrayList<>();
        ArrayList<String> vetorDeselect = new ArrayList<>();
        boolean verify;
        itemSelected = (String) oEvento.getItem();

        if(oEvento.getStateChange() == ItemEvent.SELECTED){

            verify = vetorSelect.contains(itemSelected);
            if(verify == false){
                vetorSelect.add(itemSelected);
                
            }

        }else if (oEvento.getStateChange() == ItemEvent.DESELECTED){

            verify = vetorDeselect.contains(itemSelected);
            if(verify == false){
                vetorDeselect.add(itemSelected);
            }
            

        }

    }

 

    public void itemSelecionado_setCaracteristicas_dePersonagem(ItemEvent x, String[] vetor, JComboBox comb) {
        if (x.getStateChange() == ItemEvent.SELECTED) {
            itemSelected = (String) x.getItem();

            System.out.println("\nFoi SElecionado " + itemSelected);
            System.out.println( "\n__________________\n");

        }else if(x.getStateChange() == ItemEvent.DESELECTED){
            itemDeselected = (String) x.getItem();
            System.out.println("\nFoi DEselecionado " + itemSelected);
            System.out.println( "\n__________________\n");

        }else{
            System.out.println("\n\n\nSei la\n\n\n");
        }
    }

    public void lisenerRaca(ItemEvent local){
        if(local.getStateChange() == ItemEvent.SELECTED){
            askToSetClasse((String) local.getItem());
        }
    }
    public void lisenerClasse(ItemEvent local){
        if(local.getStateChange() == ItemEvent.SELECTED){
            askToSetClasse((String) local.getItem());
        }
    }
    public void lisenerAntecedentes(ItemEvent local){
        if(local.getStateChange() == ItemEvent.SELECTED){
            //askToSetAtencedentes((String) local.getItem());
        }
    }

    public void askToSetClasse(String classe){

        switch (classe){
            case "Barbaro":
                ClasseEscolhida.setBarbaro();
                break;
            case "Bardo":
                ClasseEscolhida.setBardo();
                break;
            case "Bruxo":
                ClasseEscolhida.setBruxo();
                break;
            case "Clerigo":
                ClasseEscolhida.setClerigo();
                break;
            case "Druida":
                ClasseEscolhida.setDruida();
                break;
            case "Feiticeiro":
                ClasseEscolhida.setFeiticeiro();
                break;
            case "Guerreiro":
                ClasseEscolhida.setGuerreiro();
                break;
            case "Ladino":
                ClasseEscolhida.setLadino();
                break;
            case "Mago":
                ClasseEscolhida.setMago();
                break;
            case "Monge":
                ClasseEscolhida.setMonge();
                break;
            case "Paladino":
                ClasseEscolhida.setPaladino();
                break;
            case "Patrulheiro":
                ClasseEscolhida.setPatrulheiro();
                break;
        }
    }


    public void askToSetRaca(String raca){
        switch (raca) {         
            case "Anão":
                RacaEscolhida.setAnao();
                break;
            case "Elfo":
                RacaEscolhida.setElfo();
                break;
            case "Halfling":
                RacaEscolhida.setHalfling();
                break;
            case "Humano":
                RacaEscolhida.setHumano();
                break;
            case "Draconato":
                RacaEscolhida.setDraconato();
                break;
            case "Gnomo":
                RacaEscolhida.setGnomo();
                break;
            case "Meio-Elfo":
                RacaEscolhida.setMeioElfo();
                break;  
            case "Meio-Orc":
                RacaEscolhida.setMeioOrc();
                break;
            case "Tiefling":
                RacaEscolhida.setTiefling();
                break;
            default: // tratamento de Exception

                break;
        }


    }
}