package src.Game;

import java.util.List;
import src.Exception.InvalidIndexException;
import src.Plant;
import src.Plants.Lilypad;
import src.Tile;
import src.Tiles.GroundTile;
import src.Tiles.WaterTile;
// import java.lang.Exception;

public class Deck {
    
    private static final int DECK_SIZE = 6;
    private Plant[] deck;
    private int plantCount;
    private List<List<Tile>> map;

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

    public Plant choosePlant(int i) throws InvalidIndexException {
        if (i < 0 || i >= DECK_SIZE || deck[i] == null) {
            throw new InvalidIndexException();
        } else {
            // belum diisi
            return deck[i] ;
        }
    }

    public boolean isDeckFull() {
        return plantCount == DECK_SIZE;
    }

    public void tanam(Plant plant, int x, int y) {
        // Pastikan koordinat valid
        if (x >= 0 && x < 11 && y >= 0 && y < 6) {
            Tile target = map.get(x).get(y);

            if (target.getPlant() == null) { // Tidak ada tanaman di tile target
                if (!(plant instanceof Lilypad) && target instanceof GroundTile) {
                    // Tanaman biasa di tanah
                    GroundTile ground = (GroundTile) target;
                    ground.addPlant(plant);
               //     System.out.println(plant.getName() + " ditanam di (" + x + ", " + y + ")");
                } else if (plant instanceof Lilypad && target instanceof WaterTile) {
                    // Lilypad di air
                    WaterTile water = (WaterTile) target;
                    Lilypad lily = (Lilypad) plant;
                    water.addLilypad(lily);
                //    System.out.println(plant.getName() + " ditanam di (" + x + ", " + y + ")");
                } else {
                    System.out.println("Tidak bisa tanam di sini.");
                }
            } else if (target.getPlant() instanceof Lilypad && !(plant instanceof Lilypad))  {
                // Tanaman biasa di air yang sudah ada Lilypad
                Lilypad lilypad = (Lilypad) target.getPlant();
                lilypad.addPlant(plant);
           //     System.out.println(plant.getName() + " ditanam di (" + x + ", " + y + ")");
            } else {
                System.out.println("Tile sudah ditempati atau tidak cocok untuk tanaman.");
            }
        } else {
            System.out.println("Koordinat tidak valid.");
        }
    }
    

    public void gali(int x, int y) {
        if (x >= 0 && x < 11 && y >= 0 && y < 6) {
            Tile target = map.get(x).get(y);
            Plant plant = target.getPlant();
            if (plant != null) {
                target.removePlant();
                System.out.println("Tanaman digali dari (" + x + ", " + y + ")");
            } else {
                System.out.println("Tidak ada tanaman yang bisa digali dari (" + x + ", " + y + ")");
            }
        } else {
            System.out.println("Invalid coordinates (" + x + ", " + y + ")");
        }
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
