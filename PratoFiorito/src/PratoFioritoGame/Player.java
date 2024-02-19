package PratoFioritoGame;

/**
 * Classe che gestisce il nome del giocatore e l'incremento dei punti.
 * 
 * 
 * @version 19.02.24
 * @author christian.arzani
 */


public class Player {
    
    private String name;
    private int score;
    
    // Metodi getter e setter per name e score
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        // Se il nome è vuoto imposta il nome "Guest"
        if (name.isBlank()) {
            name = "Guest";
        }
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        score = 0;
        this.score = score;
    }
    
    /**
     * 
     * Costruttore che inizializza il giocatore
     * 
     * @param name è il nome del giocatore inserito dall'utente
     */
    
    // Costruttore che inizializza il nome del giocatore
    public Player(String name) {
        setName(name);
    }
    
    /**
     * Metodo che incrementa i punti del giocatore
     * 
     */
    
    // Metodo per incrementare il punteggio del giocatore
    public void addScore(){
        score += 1;
    }
    
    /**
     * 
     * Metodo che verifica se il giocatore ha vinto la partita
     * 
     * Se i punti del giocatore corrispondono alla dimensione * dimensione - 1, 
     * il giocatore avrà vinto la partita, altrimenti il giocatore a perso.
     * 
     * @param dimension
     * @return 
     */
    
    // Metodo che verifica se il giocatore ha vinto la partita
    public boolean winGame(int dimension){
        boolean win = false;
        // Verifica se il punteggio del giocatore è uguale al numero di caselle nella tabella meno 1
        if (getScore() == (dimension * dimension) - 1){
            win = true;
        }
        return win;
    }
}

