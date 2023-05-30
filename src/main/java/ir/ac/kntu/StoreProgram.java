package ir.ac.kntu;


import java.util.ArrayList;

import static ir.ac.kntu.Get.getString;
import static ir.ac.kntu.UserMainPage.allUsers;


public class StoreProgram {

    public static void makeHashie() {
        System.out.println("._._._._._.");
    }

    public static void displayMenu() {
        makeHashie();
        System.out.println("1.Admin");
        System.out.println("2.User");
        makeHashie();
        String ans = getString();
        switch (ans) {
            case "1": {
                adminLogIn();
                break;
            }
            case "2": {
                userChoices();
                break;
            }
            case "3": {
                UserLoggedInPage.showUserLoggedInMenu(allUsers.get(0));
                break;
            }
            case "4": {
                UserLoggedInPage.showUserLoggedInMenu(allUsers.get(1));
                break;
            }
            default: {
                System.out.println("Wrong input, redirecting to start of page.");
                displayMenu();
                break;
            }
        }
    }

    public static void userChoices() {
        System.out.println("1.Log in");
        System.out.println("2.Sign up");
        System.out.println("3.Return");
        String ans = getString();
        switch (ans) {
            case "1": {
                UserGetIn.userLogin();
                break;
            }
            case "2": {
                UserGetIn.userSignUp();
                break;
            }
            case "3": {
                displayMenu();
                break;
            }
            //cheat_code
            default: {
                System.out.println("Wrong input, redirecting to start of menu.");
                userChoices();
                break;
            }
        }
    }

    private static void tryInputAgain() {
        System.out.println("Wrong Input!");
        System.out.println("1.Retry");
        System.out.println("2.Return");
    }

    private static void adminLogIn() {
        Admin admin = new Admin("aaa", "123");
        System.out.println("Enter username:");
        String enteredUser = getString();
        if (admin.username.equals(enteredUser)) {
            System.out.println("Enter password");
            String enteredPass = getString();
            while (!admin.password.equals(enteredPass)) {
                tryInputAgain();
                String ans = getString();
                switch (ans) {
                    case "1": {
                        System.out.println("Enter password");
                        enteredPass = getString();
                        break;
                    }
                    case "2": {
                        displayMenu();
                        break;
                    }
                    default: {
                        System.out.println("Wrong input, redirecting to start of page");
                        adminLogIn();
                    }
                }
            }
            System.out.println("Welcome to Admin's main page.");
            AdminMainPage.displayAdminPage();
        } else {
            tryInputAgain();
            String ans = getString();
            switch (ans) {
                case "1": {
                    adminLogIn();
                    break;
                }
                case "2": {
                    displayMenu();
                    break;
                }
                default: {
                    adminLogIn();
                }
            }
        }
    }

    public static void main(String[] args) {
        Game game1 = new Game("CoD: Advanced Warfare",
                "Call of Duty: Advanced Warfare is a 2014 first-person shooter video game published by Activision.",
                "Action fps-shooter",
                59.99);
        Game game2 = new Game("Dota2",
                "Dota 2 is a 2013 multiplayer online battle arena video game by Valve. The game is a sequel to Defense of the Ancients," +
                        "a community-created mod for Blizzard Entertainment's Warcraft III: Reign of Chaos.",
                "Strategy Moba",
                0.0);
        Controller device1 = new Controller("keyboard","damn",15,1,"mmd",true);
        Controller device2 = new Controller("daste","khar",150,20,"mmk",false);

        ArrayList<Game> user1Games = new ArrayList<>();
        ArrayList<Controller> user1Controllers = new ArrayList<>();
        ArrayList<Monitor> user1Monitors = new ArrayList<>();
        ArrayList<Item> user1Items = new ArrayList<>();

        user1Items.add(device2);

        User user1 = new User("mmd", "12345678Aa", "mmd@gmail.com", "09363340618");
        User user2 = new User("eli", "12345678Aa", "mmd@gmail.com", "09363340618");
        user1.setWallet(150);
        user2.setWallet(1000);
        allUsers.add(user1);
        allUsers.add(user2);
        displayMenu();
    }
}