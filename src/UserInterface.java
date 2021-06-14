import java.io.IOException;
import java.util.Scanner;

public class UserInterface {

    private MachineControlFacade machineControlFacade;

    public UserInterface() {
        this.machineControlFacade = new MachineControlFacade();
    }

    public void manualUI(Scanner scanner) throws IOException {
        System.out.println("Manual Run");
        System.out.print("Please enter integer for air pressure (Psi): ");
        int airPressure = scanner.nextInt();
        machineControlFacade.setControlValue("Air Pressure", airPressure);
        System.out.print("Please enter integer for electrical current (Amps): ");
        int electricalCurrent = scanner.nextInt();
        machineControlFacade.setControlValue("Electrical Current", electricalCurrent);
        System.out.print("Please enter integer for hardware operation time (seconds): ");
        int operationTime = scanner.nextInt();
        machineControlFacade.setControlValue("Operation Time", operationTime);
        machineControlFacade.runManual();
    }

    public void recipeUI(Scanner scanner) throws Exception {
        System.out.println("Recipe Run");
        System.out.print("Please enter csv recipe file name: ");
        String fileName = scanner.next();
        machineControlFacade.runRecipe(fileName);
    }

}
