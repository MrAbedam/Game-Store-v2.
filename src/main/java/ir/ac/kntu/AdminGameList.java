package ir.ac.kntu;

import java.util.ArrayList;

import static ir.ac.kntu.Get.*;
import static ir.ac.kntu.StoreProgram.makeHashie;

public class AdminGameList {


    static ArrayList<Game> listOfGames = new ArrayList<>();

    static ArrayList<Controller> listOfControllers = new ArrayList<>();

    static ArrayList<Monitor> listOfMonitors = new ArrayList<>();

    static ArrayList<Item> listOfItems = new ArrayList<>();

    public static ArrayList<Game> findGameByName(String gameName) {
        ArrayList<Game> filteredGames = new ArrayList<>();
        for (Game foundGame : listOfGames) {
            if (foundGame.getName().equals(gameName)) {
                filteredGames.add(foundGame);
            }
        }
        return filteredGames;
    }

    public static ArrayList<Device> findDeviceByName(String deviceName) {
        ArrayList<Device> filteredDevice = new ArrayList<>();
        for (Device foundDevice : listOfMonitors) {
            if (foundDevice.getName().equals(deviceName)) {
                filteredDevice.add(foundDevice);
            }
        }
        return filteredDevice;
    }

    public AdminGameList(ArrayList<Game> listOfGames, ArrayList<Monitor> listOfMonitors, ArrayList<Controller> listOfControllers) {
        this.listOfGames = listOfGames;
        this.listOfControllers = listOfControllers;
        this.listOfMonitors = listOfMonitors;
    }


    public static void removeItem(Item item) {

        for (User testUser : UserMainPage.allUsers) {
            if (item instanceof Game && !testUser.getOwnedGames().isEmpty()) {
                for (Game testGame : testUser.getOwnedGames()) {
                    if (testGame == item) {
                        testUser.getOwnedGames().remove(item);
                        listOfGames.remove(item);
                        break;
                    }
                }
            } else if (item instanceof Monitor && !testUser.getOwnedMonitors().isEmpty()) {
                for (Monitor testMonitor : testUser.getOwnedMonitors()) {
                    if (testMonitor == item) {
                        testUser.getOwnedMonitors().remove(item);
                        listOfMonitors.remove(item);
                        break;
                    }
                }
            } else if (item instanceof Controller && !testUser.getOwnedControllers().isEmpty()) {
                for (Controller testController : testUser.getOwnedControllers()) {
                    if (testController == item) {
                        testUser.getOwnedControllers().remove(item);
                        listOfControllers.remove(item);
                        break;
                    }
                }
            }
        }
    }

    public static void adminGameChangeDetails(Admin admin) {
        System.out.println("Enter game's name.");
        String filterName = getString();
        ArrayList<Game> filteredList = findGameByName(filterName);
        if (filteredList.isEmpty()) {
            System.out.println("No Games Matched. Enter anything to return to Admins gameList.");
            String tmp = getString();
            makeHashie();
            adminGameListMenu(admin);
        } else {
            System.out.println("Choose a game between the filtered games:");
            showGivenListOfGames(filteredList);
            int gameChoice = getInt();
            while (gameChoice > filteredList.size() || gameChoice < 1) {
                System.out.println("Wrong input, try again:");
                gameChoice = getInt();
            }
            Game chosenGame = filteredList.get(gameChoice - 1);
            changeGameDetail(chosenGame,admin);
        }
    }

    public static void adminGameListMenuOptions() {
        System.out.println("Admins gameList page.");
        System.out.println("1.Add a game.");
        System.out.println("2.Change a game's details.");
        System.out.println("3.Remove a game.");
        System.out.println("4.Show all of games.");
        System.out.println("5.Return.");
    }

    public static void adminGameListMenu(Admin admin) {
        adminGameListMenuOptions();
        String ans = getString();
        switch (ans) {
            case "1": {
                makeGame();
                adminGameListMenu(admin);
                break;
            }
            case "2": {
                adminGameChangeDetails(admin);
                break;
            }
            case "3": {
                System.out.println("Enter game's name.");
                String filterName = getString();
                ArrayList<Game> filteredList = findGameByName(filterName);
                if (filteredList.isEmpty()) {
                    System.out.println("No Games Matched. Enter anything to return to Admins gameList.");
                    getString();
                    adminGameListMenu(admin);
                } else {
                    System.out.println("Choose a game between the filtered games:");
                    showGivenListOfGames(filteredList);
                    int gameChoice = getInt();
                    Game chosenGame = filteredList.get((gameChoice - 1) % filteredList.size());
                    removeItem(chosenGame);
                    adminGameListMenu(admin);
                }
                break;
            }
            case "4": {
                showGivenListOfGames(listOfGames);
                System.out.println("Enter anything to return to Admins gameList.");
                String tmp = getString();
                makeHashie();
                adminGameListMenu(admin);
                break;
            }
            case "5": {
                AdminMainPage.displayAdminPage(admin);
                break;
            }
            default: {
                System.out.println("Wrong input, redirecting to start of page.");
                adminGameListMenu(admin);
                break;
            }
        }
    }

    public static void changeGameDetailOptions() {
        System.out.println("Which detail do you want to change?");
        System.out.println("1.Name");
        System.out.println("2.Description");
        System.out.println("3.Genre");
        System.out.println("4.Price");
        System.out.println("5.Return");
        makeHashie();
    }

    public static void changeGameDetailName(Game game) {
        System.out.println("Current name: " + game.getName());
        System.out.println("Enter new name:");
        String newName = getString();
        game.setName(newName);
        System.out.println("Name changed!");
        makeHashie();
    }

    public static void changeGameDetail(Game game,Admin admin) {
        changeGameDetailOptions();
        int detailNumber = getInt();
        switch (detailNumber) {
            case 1: {
                changeGameDetailName(game);
                changeGameDetail(game,admin);
                break;
            }
            case 2: {
                System.out.println("Current description: " + game.getDescription());
                System.out.println("Enter new description:");
                String newDescription = getString();
                game.setDescription(newDescription);
                System.out.println("Description changed!");
                makeHashie();
                changeGameDetail(game,admin);
                break;
            }
            case 3: {
                System.out.println("Current genre: " + game.getGenre());
                System.out.println("Enter new genre:");
                String newGenre = getString();
                game.setGenre(newGenre);
                System.out.println("Genre changed!");
                makeHashie();
                changeGameDetail(game,admin);
                break;
            }
            case 4: {
                System.out.println("Current price: " + game.getPrice());
                System.out.println("Enter new price:");
                double newPrice = getDouble();
                game.setPrice(newPrice);
                System.out.println("Price changed!");
                makeHashie();
                changeGameDetail(game,admin);
                break;
            }
            case 5: {
                adminGameListMenu(admin);
                break;
            }
            default: {
                System.out.println("Wrong input, redirecting to start of page.");
                changeGameDetail(game,admin);
                break;
            }
        }
    }

    public static void makeGame() {
        System.out.println("Enter game's name:");
        String gameName = getString();
        System.out.println("Enter game's description");
        String gameDescription = getString();
        System.out.println("Enter game's genre");
        String gameGenre = getString();
        System.out.println("Enter game's price");
        double gamePrice = getDouble();
        System.out.println("Enter game's level");
        int gameLevel = getInt();
        boolean isGameBeta;
        System.out.println("Is game in beta mode? Enter y for yes, or anything else for no");
        String ans = getString();
        if (ans.equals("y") || ans.equals("Y")) {
            isGameBeta = true;
        } else {
            isGameBeta = false;
        }
        Game newGame = new Game(gameName, gameDescription, gameGenre, gamePrice, gameLevel, isGameBeta);
        System.out.println("Game added!");
        makeHashie();
    }

    public static void addItem(Item item) {
        if (item instanceof Game) {
            listOfGames.add((Game) item);
            return;
        }
        if (item instanceof Monitor) {
            listOfMonitors.add((Monitor) item);
            return;
        }
        if (item instanceof Controller) {
            listOfControllers.add((Controller) item);
            return;
        }
    }

    public void addMontior(Monitor monitor) {
        listOfMonitors.add(monitor);
    }

    public static void addGame(Game game) {
        listOfGames.add(game);
    }

    public void addController(Controller controller) {
        listOfControllers.add(controller);
    }


    /// change to list of devices =>
    public static void showGivenListOfGames(ArrayList<Game> listOfGivenGames) {
        int gameCounter = 1;
        for (Game game : listOfGivenGames) {
            System.out.println(gameCounter + ". " + game.getName() + " => " + game.getPrice() + "$ " + "Level:" + game.getLevel());
            gameCounter++;
        }
    }


}
