import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MachineControlFacade {

    private MachineControl machineControl;

    public MachineControlFacade() {
        this.machineControl = new MachineControl();
    }

    public HashMap<String, Integer> getControlValues() {
        return machineControl.getControlValues();
    }

    public void setControlValue(String controlValueName, int controlValue) {
        machineControl.setControlValue(controlValueName, controlValue);
    }

    public void runManual() throws IOException {
        machineControl.runManual();
    }

    public void runRecipe(String fileName) throws Exception {
        String[] recipeDetails = machineControl.readRecipe(fileName);
        String recipeName = recipeDetails[0];
        String recipeType = recipeDetails[1];
        int partSize = Integer.parseInt(recipeDetails[2]);
        machineControl.runRecipe(fileName, recipeName, recipeType, partSize);
        machineControl.validateRecipe(fileName);
    }

}
