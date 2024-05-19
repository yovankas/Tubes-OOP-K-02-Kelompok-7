package src.Game;

import src.Plant;
import src.Exception.InvalidIndexException;

import java.util.ArrayList;
import java.util.List;
// import java.lang.Exception;

public class Deck {
    
    private static final int DECK_SIZE = 6;
    private Plant[] deck;
    private int plantCount;

    public Deck() {
        deck = new Plant[DECK_SIZE];
        plantCount = 0;
    }

    public Plant[] getDeck() {
        return deck ;
    }

    public boolean isEmpty() {
        return plantCount == 0 ;
    }

    public int getPlantCount() {
        return plantCount;
    }

    public void setPlantCount(int plantCount) {
        this.plantCount = plantCount;
    }

    public boolean isSlotEmpty(int i) {
        if (i < 0 || i >= DECK_SIZE) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return deck[i] == null;
    }

    public void showDeck() {
        if (isEmpty()) {
            System.out.println("Deck kosong");
        } else {
            for (int i = 0; i < DECK_SIZE; i++) {
                if (deck[i] != null) {
                    System.out.println((i + 1) + ". " + deck[i].getName());
                } else {
                    System.out.println((i + 1) + " Slot kosong");
                }
            }
        }
    }

    public void choosePlant(int i) throws InvalidIndexException {
        if (i < 0 || i >= DECK_SIZE || deck[i] == null) {
            throw new InvalidIndexException();
        } else {
            // belum diisi
        }
    }

    public boolean isDeckFull() {
        return plantCount == DECK_SIZE;
    }

    public void tanam () {
        // belum diisi
    }

    public void increasePlantCount() {
        if (plantCount < DECK_SIZE) {
            plantCount++;
        }
    }

    public void decreasePlantCount() {
        if (plantCount > 0) {
            plantCount--;
        }
    }

    public boolean containsPlant(Plant plant) {
        for (Plant p : deck) {
            if (p == plant) {
                return true;
            }
        }
        return false;
    }
}
