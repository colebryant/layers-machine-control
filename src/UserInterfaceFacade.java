import java.util.Scanner;

public class UserInterfaceFacade {

    public static void main(String[] args) throws Exception {
        UserInterface userInterface = new UserInterface();
        int choice = 0;
        while (choice != 3) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to the PhFMM System");
            System.out.print("Enter 1 for manual, 2 for recipe, or 3 to exit: ");
            choice = scanner.nextInt();
            if (choice == 1) {
                userInterface.manualUI(scanner);
            } else if (choice == 2) {
                userInterface.recipeUI(scanner);
            }
        }
    }

}
