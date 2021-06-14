import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HardwareFacade {

    private Hardware hardware;

    public HardwareFacade() {
        this.hardware = new Hardware();
    }

    public HashMap<String, Integer> getControlValues() {
        HashMap<String, Integer> controlValues = new HashMap<String, Integer>();
        controlValues.put("Air Pressure", hardware.getCurrentPsi());
        controlValues.put("Electrical Current", hardware.getCurrentAmps());
        controlValues.put("Operation Time", hardware.getOperationTime());
        return controlValues;
    }

    public void setControlValue(String controlValueName, int controlValue) {
        switch (controlValueName) {
            case "Air Pressure" -> hardware.setCurrentPsi(controlValue);
            case "Electrical Current" -> hardware.setCurrentAmps(controlValue);
            case "Operation Time" -> hardware.setOperationTime(controlValue);
            default -> System.err.println("Control value does not exist");
        }
    }

    public void runManual() throws IOException {
        System.out.println("Hardware started (manual)");
        hardware.runManual();
        System.out.println("Hardware stopped (manual)");
    }

    public void runRecipe(String fileName, String recipeName, String recipeType, ArrayList<Integer> airPressures,
                          ArrayList<Integer> electricCurrents, ArrayList<Integer> operationTimes, int partSize) throws IOException {
        System.out.println("Hardware started (recipe)");
        hardware.runRecipe(fileName, recipeName, recipeType, airPressures, electricCurrents, operationTimes, partSize);
        System.out.println("Hardware ended (recipe)");
    }

}
