package PratoFioritoGame;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * Classe che gestisce il flusso del programma
 * 
 * 
 * @version 19.02.24
 * @author christian.arzani
 */

public class Program {
    public static void main(String[] args) throws Exception{
        // Definizione dei codici di colore per il testo
        String RESET = "\u001B[0m";
        String RED_TEXT = "\u001B[31m";
        String GREEN_TEXT = "\u001B[32m";
        String YELLOW_TEXT = "\u001B[33m";
        boolean winGame = false; 
        int dimension; 
        String difficulty = ""; 
        String musicFile = "src/PratoFioritoGame/music.wav";
        int flowers; 
        int row; 
        int col; 
        Scanner scan = new Scanner(System.in); // Oggetto Scanner per l'input dell'utente

        
        // Stampa le regole del gioco
        Sound.play(musicFile);
        System.out.println(GREEN_TEXT + "Rules: \n"
                + GREEN_TEXT + "Descrizione del gioco: L'obbiettivo del gioco e' semplicemente evitare i fiori\n"
                + GREEN_TEXT + "Inserisci il tuo nome e inserisci la dimensione della tabella che dovra' essere un numero compreso "
                + GREEN_TEXT + "tra 3 a 20, se questa regola non viene rispettata verra' impostato il valore di default ovvero 3\n"
                + GREEN_TEXT + "Inserisci la difficolta' mettendo le parole chiavi 'easy', 'medium', 'hard', "
                + GREEN_TEXT + "se inserirai qualcosa di diverso verra' impostata la difficolta' 'easy' di default"
                + "\n" + GREEN_TEXT + "Quando dovrai inserire il numero della colonna e della riga dovrai inserire un numero compreso da 1 al numero inserito come dimensione\n"
                + GREEN_TEXT + "Se inserirai piu' volte la stessa posizione i punti non verranno conteggiati piu' volte ma solo una");
        System.out.println("");
        System.out.print(RESET + "Enter your name: " + GREEN_TEXT);
        String name = scan.nextLine();        
        Player p = new Player(name); // Crea un nuovo giocatore con il nome inserito

        // Condizione per chiedere all'utente di inserire la dimensione della tabella
        System.out.print(RESET + "Enter dimension: " + GREEN_TEXT);
        try {
            dimension = scan.nextInt();
            if ((dimension < 3) || (dimension > 20)){
                throw new InputMismatchException (RESET + YELLOW_TEXT + "\nYou must enter a valid integer greater than 2\nThe default value has been set (3)\n"+ RESET);
            }
        } catch (InputMismatchException e) {
            System.out.println(RESET + YELLOW_TEXT + "\nYou must enter a valid integer greater than 2\nThe default value has been set (3)\n"+ RESET);
            dimension = 3;
            scan.nextLine();
        }

        // Condizione per chiedere all'utente di inserire la difficoltà del gioco
        System.out.print(RESET + "Enter difficulty: " + GREEN_TEXT);
        try {
            difficulty = scan.next();
            if (difficulty.equals("easy") || difficulty.equals("medium") || difficulty.equals("hard")){
                System.out.println(RESET + GREEN_TEXT + "\nThe " + difficulty + " mode has been set" + RESET);
            }
            else{
                throw new InputMismatchException (RESET + YELLOW_TEXT + "\nYou must enter a valid parameter: 'easy' for easy mode, 'medium' for medium mode, 'hard' for hard mode\n\nThe default value has been set (easy)"+ RESET);
            }
        } catch (InputMismatchException e) {
            System.out.println(RESET + YELLOW_TEXT + "\nYou must enter a valid parameter: 'easy' for easy mode, 'medium' for medium mode, 'hard' for hard mode\n\nThe default value has been set (easy)"+ RESET);
            difficulty = "easy";
            scan.nextLine();
        }

        // Creazione della tabella di gioco e generazione dei fiori in base alla dimensione e alla difficoltà
        Table t = new Table(dimension);
        System.out.println(RESET + "\nPRATO FIORITO GAME\n");
        t.createTable();
        flowers = t.setDifficulty(difficulty, dimension);
        t.generateFlowers(flowers);

        // Ciclo principale del gioco
        while (true) {
            System.out.println(RESET + "\n" +t.toString());
            // Ciclo per chiedere all'utente di inserire la riga e la colonna finchè l'utente non inserirà valori validi
            while (true){
                System.out.print(RESET + "Enter Row: " + GREEN_TEXT);
                try {
                    row = scan.nextInt();
                    if ((row <= dimension) && (row > 0)) {
                        break;
                    }
                    else{
                        throw new InputMismatchException (RESET + RED_TEXT + "\nYou must enter a valid integer greater than 0 and minor than" + (dimension+1) +"\n" + RESET);
                    }
                } catch (InputMismatchException e) {
                    System.out.println(RESET + RED_TEXT + "\nYou must enter a valid integer greater than 0 and minor than " + (dimension+1) +"\n" + RESET);
                    scan.nextLine();
                }
            }

            while (true){
                System.out.print(RESET + "Enter Column: " + GREEN_TEXT);
                try {
                    col = scan.nextInt();
                    if ((col <= dimension) && (col > 0)) {
                        break;
                    }
                    else{
                        throw new InputMismatchException (RESET + RED_TEXT + "\nYou must enter a valid integer greater than 0 and minor than" + (dimension+1) +"\n" + RESET);
                    }
                } catch (InputMismatchException e) {
                    System.out.println(RESET + RED_TEXT + "\nYou must enter a valid integer greater than and minor than " + (dimension+1) +"\n" + RESET);
                    scan.nextLine();
                }
            }

            // Controllo se il giocatore ha preso un fiore o un campo vuoto
            // viene assegnato a una variabile in modo da avere il risultato prima che si faccia il controllo
            boolean score = t.checkScore(row, col);
            if (t.checkFlowers(row, col) == false) {
                break;
            }
            else{
                // Se il giocatore ha cliccato su un campo vuoto e non ha cliccato su un fiore, aggiungi un punto al punteggio del giocatore
                if (score) {
                    p.addScore();
                    // Verifica se il giocatore ha vinto la partita
                    winGame = p.winGame(dimension);
                    if (winGame){
                        break; 
                    }
                }
            }
        }
        // Stampa del risultato finale del gioco
        if (winGame){
            System.out.println("YOU WON!!");
        }
        else{
            System.out.println(RESET + t.toString());
            System.out.println("GAME OVER");
        }
        System.out.println("Player: " + p.getName());
        System.out.println("Score: " + p.getScore());
    }
}
