package model;

//import java.util.Random;

public class Ficha {
    private String nomeDoJogador, nomeDoPersonagem, classe, raca, antecedentes;
    private int nivel, FOR, DES, CON, INT, SAB, CAR;
    private boolean[] pericias = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    // 0 - Acrobacia(DES)
    // 1 - Arcanismo(INT)
    // 2 - Atletismo(FOR)
    // 3 - Atuacão(CAR)
    // 4 - Blefar(CAR)
    // 5 - Furtividade(DES)
    // 6 - História(INT)
    // 7 - Intimidação(CAR)
    // 8 - Intuicão(SAB)
    // 9 - Investigação(INT)
    // 10 - Lidar com os animais(SAB)
    // 11 - Medicina(SAB)
    // 12 - Natureza(INT)
    // 13 - Percepção(SAB)
    // 14 - Persuasão(CAR)
    // 15 - Prestidigitação(DES)
    // 16 - Religião(INT)
    // 17 - Sobrevivência(SAB)
    private int pontosdevida, dadoVida, modAtributos;
    private boolean profArmasSimples;
    private boolean profArmasMarciais;
    private boolean profArmaduras;
    private boolean profEscudos;
    private boolean profFerramentas;
    private boolean tstFOR, tstCON;
    private double deslocamento;
    private String[] magicas;
    private String[] truques;
    private String[] habilidades;


    
    
    public Ficha(){ // Construtor padrão, com todas as variaveis.
        
    }

    public void setInfoDoPersonagem(String classe, String raca, String antecedentes){
        this.classe = classe;
        this.raca = raca;
        this.antecedentes = antecedentes;
    }
    public void setNomeJogador(String nomeDoJogador){
        this.nomeDoJogador = nomeDoJogador;
    }
    public void setNomePersonagem(String nomeDoPersonagem){
        this.nomeDoPersonagem = nomeDoPersonagem;
    }
    public void setAtributosPadrao(int FOR, int DES, int CON, int INT, int SAB, int CAR){
        this.FOR = FOR;
        this.DES = DES;
        this.CON = CON;
        this.INT = INT; 
        this.SAB = SAB;
        this.CAR = CAR;
    }


    public Ficha getAtributos(){
        return this; // na teoria eu so preciso mandar os valores dos atributos em vez do objeto inteiro, mas eu nao sei como...
    }
    public String getRaca(){
        return this.raca;
    }
    public String getClasse(){
        return this.classe;
    }
    public String getAntecedentes(){
        return this.antecedentes;
    }
    public String getNomePersonagem(){
        return this.nomeDoPersonagem;
    }
    public String getNomeJogador(){
        return this.nomeDoJogador;
    }
  
    // Metodos Auxiliares para testes temporarios (Ddeseletar antes da entrega final)
    public void diplayFicha(){
        System.out.print("\nNome do Personagem: "+ nomeDoPersonagem); System.out.print("\tNome do Jogador: "+ nomeDoJogador);
        System.out.print("Classe & Nivel: "+ classe); System.out.print(", "+ nivel); System.out.print("\tAntecedentes: "+ antecedentes+"\n");
        System.out.print("Raca: "+ raca);
    }  
}