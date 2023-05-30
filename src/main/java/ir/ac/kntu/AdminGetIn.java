package ir.ac.kntu;

import java.time.Instant;

import static ir.ac.kntu.AdminMainPage.allAdmins;
import static ir.ac.kntu.AdminMainPage.displayAdminPage;
import static ir.ac.kntu.Get.getString;
import static ir.ac.kntu.UserMainPage.findUser;

public class AdminGetIn {

    public static Admin findAdmin(String userName) {
        for (Admin newAdmin : allAdmins) {
            if (newAdmin.getUsername().equals(userName)) {
                return newAdmin;
            }
        }
        return null;
    }

    public static boolean checkAdminPass(Admin admin, String passWord) {
        if (admin.getPassword().equals(passWord)) {
            return true;
        }
        return false;
    }

    public static void adminLogin() {
        System.out.println("Enter username:");
        String newUserName = getString();
        System.out.println("Enter password:");
        String newPassword = getString();
        Admin testAdmin = findAdmin(newUserName);
        if (testAdmin == null) {
            System.out.println("No admins matched try again.");
            StoreProgram.displayMenu();
        } else if (!checkAdminPass(testAdmin, newPassword)) {
            System.out.println("Wrong password or username try again");
            StoreProgram.displayMenu();
        } else {
            System.out.println("Logged in successfully!");
            displayAdminPage(testAdmin);
        }
    }
}
