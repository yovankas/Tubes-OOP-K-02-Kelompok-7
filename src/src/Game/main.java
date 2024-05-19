package src.Game;

public class main {
    //main
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            mainMenu.printWelcomeMessage();
            String input = scanner.nextLine();
            
            if(input.equals("Start")) {
                start();
                break;
            }
            else if(input.equals("Help")) {
                //help
            }
            else if(input.equals("Plants List")) {
                mainMenu.printListOfPlants();
            }
            else if(input.equals("Zombies List")) {
                mainMenu.printListOfZombies();
            }
            else if(input.equals("Exit")) {
                mainMenu.printExit();
                break;
            }
        }
        
        scanner.close();
    }
}
