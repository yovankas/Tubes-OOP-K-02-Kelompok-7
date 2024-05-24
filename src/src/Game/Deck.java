package src.Game;

import java.util.List;
import src.Exception.CantPlantException;
import src.Exception.CantPlantLilypadException;
import src.Exception.CooldownNotDoneException;
import src.Exception.InvalidCoordinateException;
import src.Exception.InvalidIndexException;
import src.Exception.NoLilypadException;
import src.Exception.NoPlantToDigException;
import src.Exception.NotEnoughSunException;
import src.Exception.PlantAlreadyInLilypadException;
import src.Exception.TileFullException;
import src.Plant;
import src.Plants.*;
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

    public Plant getNewPlant(int i) {
        String name = deck[i].getName();
        if (name.equals("Lilypad")) {
            return new Lilypad();
        } else if (name.equals("Magnetshroom")) {
            return new Magnetshroom();
        } else if (name.equals("Peashooter")) {
            return new Peashooter();
        } else if (name.equals("Repeater")) {
            return new Repeater();
        } else if (name.equals("Snowpea")) {
            return new Snowpea();
        } else if (name.equals("Squash")) {
            return new Squash();
        } else if (name.equals("Sunflower")) {
            return new Sunflower();
        } else if (name.equals("TwinSunflower")) {
            return new TwinSunflower();
        } else if (name.equals("Tallnut")) {
            return new Tallnut();
        } else if (name.equals("Wallnut")) {
            return new Wallnut();
        } else {
            throw new IllegalArgumentException("Invalid plant name: " + name);
        }
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
            System.out.printf("| %-5s | %-15s | %-10s | %-5s |%n", "No", "Nama Tanaman", "Status", "Cost");
            System.out.println("-----------------------------------------------");

            for (int i = 0; i < DECK_SIZE; i++) {
                if (deck[i] != null) {
                    deck[i].isReady();
                    System.out.printf("| %-5d | %-15s | %-10s | %-5d |%n", (i + 1), deck[i].getName(), deck[i].isReady(), deck[i].getCost());
                } else {
                    System.out.printf("| %-5d | %-15s | %-10s |%n", (i + 1), "Slot kosong", "");
                }
                // Menambahkan pembatas antar baris
            }
            System.out.println("-----------------------------------------------");
        }
    }

    public Plant choosePlant(int i) throws InvalidIndexException {
        if (i < 0 || i >= DECK_SIZE || deck[i] == null) {
            throw new InvalidIndexException();
        } else {
            return deck[i] ;
        }
    }

    public boolean isDeckFull() {
        return plantCount == DECK_SIZE;
    }

    public void tanam(Plant plant, int x, int y, Sun sun) throws Exception{
        if (plant == null) {
            return;
        }
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
                    throw new NotEnoughSunException();
                } else {
                    throw new TileFullException();
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
                        throw new NotEnoughSunException();
                    }
                } else if (water.getLilypad() != null && water.getLilypad().getPlant() == null) { // Sudah ada lilypad di tile target dan tanaman di lilypad kosong
                        Lilypad lilypad = (Lilypad) water.getLilypad();
                        if (plant instanceof Lilypad) {
                            throw new CantPlantLilypadException();
                        }
                        if (sun.getSun() >= plant.getCost()) {
                            lilypad.addPlant(plant);
                            sun.decreaseSunAmount(lilypad.getPlant().getCost());
                            plant.setLastPlantedTime();
                        } else {
                            throw new NotEnoughSunException();
                        }
                }   else if (water.getLilypad() != null && water.getLilypad().getPlant() != null ){ // Sudah ada tanaman di lilypad
                        throw new PlantAlreadyInLilypadException();
                }
                    else if (water.getLilypad() == null && !(plant instanceof Lilypad)) { // Ga ada Lilypad jadi ga bisa tanam 
                        throw new NoLilypadException();
                    }
                }
            //    System.out.println(plant.getName() + " ditanam di (" + x + ", " + y + ")");
             else {
                throw new CantPlantException();
            }
        } else if (!plant.isReady()) {
            throw new CooldownNotDoneException();
        } 
         else {
            throw new InvalidCoordinateException();
        }
    }
    

    public void gali(int x, int y) throws Exception{
        if (x >= 0 && x < 11 && y >= 0 && y < 6) {
            Tile target = map.get(y).get(x);
            Plant plant;
            if (target instanceof  GroundTile) {
                GroundTile ground = (GroundTile) target;
                plant = ground.getPlant();
                if (plant != null) {
                    ground.removePlant();
                    System.out.println("Tanaman digali dari (" + x + ", " + y + ")");
                } else {
                    throw new NoPlantToDigException(x, y);
                }                
            } else if (target instanceof WaterTile) {
                WaterTile water = (WaterTile) target;
                plant = (Plant) water.getLilypad();
                if (plant != null) {
                    water.removeLilypad();
                    System.out.println("Tanaman digali dari (" + x + ", " + y + ")");
                } else {
                    throw new NoPlantToDigException(x, y);
                }                
            }
        } else {
            throw new InvalidCoordinateException();
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
