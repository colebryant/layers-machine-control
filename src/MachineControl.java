import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MachineControl {

    private HardwareFacade hardwareFacade;
    private ArrayList<Integer> airPressures;
    private ArrayList<Integer> electricCurrents;
    private ArrayList<Integer> operationTimes;
    private RecipeStrategy recipeStrategy;


    public MachineControl () {
        this.hardwareFacade = new HardwareFacade();
        this.airPressures = new ArrayList<Integer>();
        this.electricCurrents = new ArrayList<Integer>();
        this.operationTimes = new ArrayList<Integer>();
    }

    public HashMap<String, Integer> getControlValues() {
        return hardwareFacade.getControlValues();
    }

    public void setControlValue(String controlValueName, int controlValue) {
        hardwareFacade.setControlValue(controlValueName, controlValue);
    }

    public void runManual() throws IOException {
        hardwareFacade.runManual();
    }

    public void setRecipeStrategy(String recipeType) {
        if (recipeType.equals("ConstantPressure")) {
            recipeStrategy = new ConstantPressure();
        } else if (recipeType.equals("ConstantCurrent")) {
            recipeStrategy = new ConstantCurrent();
        } else {
            recipeStrategy = new Ramp();
        }
    }

    public void runRecipe(String fileName, String recipeName, String recipeType, int partSize) throws IOException {
        // Set recipe strategy based on type given
        this.setRecipeStrategy(recipeType);
        // Clear values before setting
        airPressures.clear();
        electricCurrents.clear();
        operationTimes.clear();
        // Generate relevant values for hardware given strategy
        this.recipeStrategy.generateValues(airPressures, electricCurrents, operationTimes, partSize);
        // Run recipe in hardware
        hardwareFacade.runRecipe(fileName, recipeName, recipeType, airPressures, electricCurrents, operationTimes,
                partSize);

    }

    public String[] readRecipe(String fileName) throws Exception{
        String[] recipeValues = null;
        try {
            // Create csv reader
            FileReader filereader = new FileReader(fileName);
            System.out.println(filereader);
            CSVReader csvReader = new CSVReader(filereader);
            // Read first line of input file and return to caller
            recipeValues = csvReader.readNext();
        }
        catch (Exception e) {
            System.err.println("Cannot read file");
            throw e;
        }
        return recipeValues;
    }

    public void validateRecipe(String fileName) throws FileNotFoundException {
        // Create scanner for output file
        Scanner outputScanner = new Scanner(new File(fileName + ".DAS.csv"));
        // Create scanner for reference file
        Scanner referenceScanner = new Scanner(new File(fileName + ".reference.csv"));
        // Check if reference and output files are the same
        boolean isValid = true;
        while (outputScanner.hasNext()) {
            String outputLine = outputScanner.nextLine();
            // Remove double quotes since they are appearing in output csv string for some reason
            outputLine = outputLine.replace("\"", "");
            String referenceLine = referenceScanner.nextLine();
            if (!outputLine.equals(referenceLine)) {
                isValid = false;
            }
        }
        // Output whether part is good
        if (isValid) {
            System.out.println("Part is good");
        } else {
            System.out.println("Part is bad");
        }

    }
}
