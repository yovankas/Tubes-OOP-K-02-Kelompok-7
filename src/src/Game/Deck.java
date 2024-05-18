package src.Game;

import src.Plant;
import src.Exception.InvalidIndexException;

import java.util.ArrayList;
// import java.util.List;
// import java.lang.Exception;

public class Deck {
    
    private ArrayList<Plant> deck ;

    public Deck() {
        deck = new ArrayList<>() ;
    }

    public ArrayList<Plant> getDeck() {
        return deck ;
    }

    public boolean isEmpty() {
        return deck.isEmpty() ;
    }

    public boolean isSlotEmpty(int i) {
        return deck.get(i) == null ;
    }

    public void showDeck() {
        int i = 1 ;
        if (! deck.isEmpty()) {
            for (Plant plant : deck) {
                System.out.println(i + " "+ deck.get(i-1).getName());
                i++ ;
            }
        } else {
            System.out.println("Deck kosong");
        }
    }

    public void choosePlant(int i) throws InvalidIndexException {
        if (i < 0 || i >= deck.size()) {
            throw new InvalidIndexException() ;
        } else {
            // belum diisi
        }
    }

    public void tanam () {
        // belum diisi
    }
}
