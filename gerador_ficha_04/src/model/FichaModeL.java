package model;

import controller.FichaController;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

// Falta as checagens de acoes com o controller, nao posso fazer DIRETO

public class FichaModeL implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String FILENAME = "FICHA_DE_PERSONAGEM.txt";
    

    public void saveUserData(Ficha NovaFicha) {
        try {
            FileWriter writer = new FileWriter(FILENAME, true);
            writer.write(NovaFicha.getNomePersonagem());
            writer.write(NovaFicha.getNomeJogador());
            writer.write(NovaFicha.getRaca());
            writer.write(NovaFicha.getClasse());
            writer.write(NovaFicha.getAntecedentes());

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }

}


