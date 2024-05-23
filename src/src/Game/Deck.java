package src.Game;

import java.util.List;
import src.Exception.InvalidIndexException;
import src.Plant;
import src.Plants.Lilypad;
import src.Tile;
import src.Tiles.GroundTile;
import src.Tiles.WaterTile;

public class Deck {
    
    private static final int DECK_SIZE = 6;
    private Plant[] deck;
    private int plantCount;
    private List<List<Tile>> map;

    public Deck(Map gamemap) {
        deck = new Plant[DECK_SIZE];
        plantCount = 0;
        map = gamemap.getMap() ;
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
            // Menampilkan header tabel dengan pembatas antar kolom
            System.out.printf("| %-5s | %-15s | %-10s |%n", "No", "Nama Tanaman", "Status");
            System.out.println("--------------------------------------");
            
            for (int i = 0; i < DECK_SIZE; i++) {
                if (deck[i] != null) {
                    deck[i].isReady();
                    System.out.printf("| %-5d | %-15s | %-10s |%n", (i + 1), deck[i].getName(), deck[i].isReady());
                } else {
                    System.out.printf("| %-5d | %-15s |%n", (i + 1), "Slot kosong");
                }
                // Menambahkan pembatas antar baris
                
            }
            System.out.println("-------------------------------------");
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

    public void tanam(Plant plant, int x, int y, Sun sun) throws Exception{
        
        // Pastikan koordinat valid
        if (x >= 0 && x < 11 && y >= 0 && y < 6 && plant.isReady()) {
            Tile target = map.get(y).get(x);
            if (!(plant instanceof Lilypad) && target instanceof GroundTile) {            // Tanaman biasa di tanah
                GroundTile ground = (GroundTile) target;
                if (ground.getPlant() == null && sun.getSun() >= plant.getCost()){
                    ground.addPlant(plant);
                    sun.decreaseSunAmount(plant.getCost());
                    plant.setLastPlantedTime();
                } else if (ground.getPlant() == null && sun.getSun() < plant.getCost()) {
                    throw new Exception ("Sun tidak mencukupi") ;
                }                    
                    else {
                    throw new Exception("Tile sudah ditempati.");
                }
            }   
            else if (target instanceof WaterTile) {
                // Lilypad di air
                WaterTile water = (WaterTile) target;
                if (water.getLilypad() == null && plant instanceof Lilypad){ 
                // Tidak ada lilypad di tile target
                    if (sun.getSun() >= plant.getCost()) {
                        Lilypad lily = (Lilypad) plant;
                        water.addLilypad(lily);
                        sun.decreaseSunAmount(plant.getCost());
                        plant.setLastPlantedTime();
                    }
                    else {
                        throw new Exception("Sun tidak mencukupi") ;
                    }
                } else if (water.getLilypad() != null && water.getLilypad().getPlant() == null) { // Sudah ada lilypad di tile target dan tanaman di lilypad kosong
                        Lilypad lilypad = (Lilypad) water.getLilypad();
                        if (sun.getSun() >= lilypad.getPlant().getCost()) {
                            lilypad.addPlant(plant);
                            sun.decreaseSunAmount(lilypad.getPlant().getCost());
                            plant.setLastPlantedTime();
                        } else {
                            throw new Exception("Sun tidak mencukupi") ;
                        }
                }   else if (water.getLilypad() != null && water.getLilypad().getPlant() != null ){ // Sudah ada tanaman di lilypad
                        throw new Exception("Sudah ada tanaman di Lilypad");
                }
                    else if (water.getLilypad() == null && !(plant instanceof Lilypad)) { // Ga ada Lilypad jadi ga bisa tanam 
                        throw new Exception("Tidak bisa tanam, tidak ada Lilypad");
                    }
                }
            //    System.out.println(plant.getName() + " ditanam di (" + x + ", " + y + ")");
             else {
                throw new Exception("Tidak bisa tanam di sini.");
            }
        } else if (! plant.isReady()) {
            throw new Exception("Waktu cooldown belum selesai.");
        } 
         else {
            throw new Exception("Koordinat tidak valid.");
        }
    }
    

    public void gali(int x, int y) throws Exception{
        if (x >= 0 && x < 11 && y >= 0 && y < 6) {
            Tile target = map.get(x).get(y);
            Plant plant;
            if (target instanceof  GroundTile) {
                GroundTile ground = (GroundTile) target;
                plant = ground.getPlant();
                if (plant != null) {
                    ground.removePlant();
                    System.out.println("Tanaman digali dari (" + x + ", " + y + ")");
                } else {
                    throw new Exception("Tidak ada tanaman yang bisa digali dari (" + x + ", " + y + ")");
                }                
            } else if (target instanceof WaterTile) {
                WaterTile water = (WaterTile) target;
                plant = (Plant) water.getLilypad();
                if (plant != null) {
                    water.removeLilypad();
                    System.out.println("Tanaman digali dari (" + x + ", " + y + ")");
                } else {
                    throw new Exception("Tidak ada tanaman yang bisa digali dari (" + x + ", " + y + ")");
                }                
            }
        } else {
            throw new Exception("Invalid coordinates (" + x + ", " + y + ")");
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
