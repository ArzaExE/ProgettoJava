package PratoFioritoGame;
/**
 * Classe che gestisce la tabella dei fiori e la tabella di gioco,
 * dentro la classe sono presenti metodi che permettono di controllare se l'utente capita su un fiore,
 * controlla se aggiungere il punti e imposta la difficoltà
 * 
 * @version 19.02.24
 * @author christian.arzani
 */
public class Table {
    private int row;
    private int column;
    private int dimension;
    private String[][] table;  // Matrice per la tabella di gioco
    private String[][] flowers;  // Matrice per la tablella che contiene i fiori
    private int number_flowers;  // Numero di fiori presenti nella tabella

    // Metodi getter e setter per row, column e dimension
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getDimension() {
        return dimension;
    }

    // Imposta la dimensione della tabella, con una dimensione minima di 3 e massima di 20
    public void setDimension(int dimension) {
        if ((dimension < 3) || (dimension > 20)){
            this.dimension = 3;
        }
        else{
            this.dimension = dimension;
        }
    }
    
    /**
     * Costruttore che inizializza la dimensione, la matrice di gioco e la matrice dei fiori
     * 
     * @param dimension è la dimensione impostata dall'utente
     */
    
    // Costruttore che inizializza la tabella di gioco e la tabella dei fiori con la dimensione specificata
    public Table(int dimension) {
        setDimension(dimension);
        table = new String[this.dimension][this.dimension];
        flowers = new String[this.dimension][this.dimension];
    }
    
    /**
     * Metodo che crea la tabella di gioco e la riempe con #
     */
    
    // Crea la tabella di gioco e la riempie con #
    public void createTable(){
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = " # ";
            }
        }
    }
    
    /**
     * Metodo che genera i fiori nella tabella dei fiori in posizioni casuali
     * 
     * @param number_flowers è il numero di fiori che è calcolato tramite il metodo setDifficulty()
     */

    // Genera i fiori nella tabella dei fiori in posizioni casuali
    public void generateFlowers(int number_flowers){
        int randomRow;
        int randomCol;
        for (int i = 0; i < flowers.length; i++) {
            for (int j = 0; j < flowers[i].length; j++) {
                flowers[i][j] = "   ";
            }
        }
        for (int i = 0; i <= number_flowers; i++){
            randomRow = (int)(Math.random() * dimension);
            randomCol = (int)(Math.random() * dimension);
            flowers[randomRow][randomCol] = " * ";
        }
    }
    
    /**
     * Metodo che controlla se c'è un fiore nella posizione specificata
     * 
     * 
     * @param row è la riga inserita dall'utente
     * @param column è la colonna inserita dall'utente
     * @return game è un boolean che ritorna true se il gioco deve continuare,
     * ovvero quando l'utente prende un campo vuoto, invece se l'utente prende
     * un campo vuoto ritorna false e il gioco finisce
     */

    // Controlla se c'è un fiore nella posizione specificata
    public Boolean checkFlowers(int row, int column){
        row -= 1;
        column -= 1;
        Boolean game = true;
        if (flowers[row][column].equals(" * ")) {
            table[row][column] = " * ";
            game = false;
        }
        else{
            table[row][column] = "   ";
            game = true;
        }
        return game;
    }
    
    /**
     * 
     * Metodo che controlla se aggiungere punti a dipendenza della posizione indicata dall'utente
     * 
     * @param row è la riga inserita dall'utente
     * @param column è la colonna inserita dall'utente
     * @return score è un boolean che restituisce false se l'utente prende un campo
     * vuoto o un campo con un fiore e quindi non aggiunge punti, altrimenti restituisce
     * true e quindi aggiunge punti
     */
    
    // Controlla se aggiungere punti a dipendenza della posizione indicata dall'utente
    public Boolean checkScore(int row, int column){
        row -= 1;
        column -= 1;
        Boolean score = true;
            if (table[row][column].equals("   ") || (flowers[row][column].equals(" * "))) {
                score = false;
            }
            else{
                score = true;
            }
        return score;
    }
    
    /**
     * Metodo che imposta il numero di fiori in base alla difficoltà selezionata dall'utente.
     * 
     * Per la dimension essendo una variabile di tipo int viene fatto un casting
     * in double per fare il calcolo. Il risultato del calcolo viene approssimato
     * tramite il metodo Math.round. Siccome il risultato della variabile è di tipo 
     * double dobbiamo fare un casting in int perché il metodo dovrà restituire 
     * un valore di tipo int. 
     * 
     * @param difficulty è la difficoltà selezionata dall'utente
     * @param dimension è la dimensione impostata dall'utente
     * @return number_flowers è il numero di fiori che saranno impostati
     * dentro la tabella dei fiori
     */
    
    // Imposta il numero di fiori in base alla difficoltà
    public int setDifficulty(String difficulty, int dimension){
        if (difficulty.equals("easy")) {
            double temp = Math.round((double)dimension*(double)dimension/100*10);
            number_flowers = (int)temp;
        }
        else if (difficulty.equals("medium")) {
            double temp = Math.round((double)dimension*(double)dimension/100*20);
            number_flowers = (int)temp;
        }
        else if (difficulty.equals("hard")){
            double temp = Math.round((double)dimension*(double)dimension/100*30);
            number_flowers = (int)temp;
        }
        return number_flowers;
    }

    /**
     * Metodo che stampa la tabella di gioco
     * 
     * @return risultato che contiene la tabella di gioco
     */
    
    // Metodo che stampa la tabella di gioco
    @Override
    public String toString() {
        String risultato = "";
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                risultato += table[i][j];
            }
            risultato += "\n";
        }
        return risultato;
    }
}
