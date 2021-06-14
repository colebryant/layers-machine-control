import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Hardware {
// If no control values supplied for time interval, machine can be thought to be in idle
// state until control values are received for time value. Pressure = 0 and Current = 0
    private int currentPsi;
    private int currentAmps;
    private int operationTime;

    public Hardware() {
        // Set default values = 0 when instantiated
        this.currentPsi = 0;
        this.currentAmps = 0;
        this.operationTime = 0;
    }

    public int getCurrentPsi() {
        return currentPsi;
    }

    public void setCurrentPsi(int currentPsi) {
        if (currentPsi > 200) {
            this.currentPsi = 200;
        } else this.currentPsi = Math.max(currentPsi, 0);
    }

    public int getCurrentAmps() {
        return currentAmps;
    }

    public void setCurrentAmps(int currentAmps) {
        if (currentAmps > 200) {
            this.currentAmps = 200;
        } else this.currentAmps = Math.max(currentAmps, 0);
    }

    public int getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(int operationTime) {
        if (operationTime > 100) {
            this.operationTime = 100;
        } else this.operationTime = Math.max(operationTime, 0);
    }

    public void runManual() throws IOException
    {
        File file = new File("Manual.DAS.csv");
        try {
            // Create csv writer
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);

            // add header
            String[] header = { "Manual" };
            writer.writeNext(header);

            // add data
            for (int t = 0; t < operationTime + 1; t++) {
                String[] data = { String.valueOf(t), String.valueOf(currentPsi), String.valueOf(currentAmps) };
                writer.writeNext(data);
            }
            // close writer connection
            writer.close();
        }
        catch (IOException e) {
            System.err.println("Cannot make a new file");
        }
    }

    public void runRecipe(String fileName, String recipeName, String recipeType, ArrayList<Integer> airPressures,
                          ArrayList<Integer> electricCurrents, ArrayList<Integer> operationTimes, int partSize)
    throws IOException {
        File file = new File(fileName + ".DAS.csv");
        try {
            // Create csv writer
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);

            // add header
            String[] header = { recipeName, recipeType, String.valueOf(partSize) };
            writer.writeNext(header);

            // add data
            for (int i = 0; i < operationTimes.size(); i++) {
                String[] data = { String.valueOf(operationTimes.get(i)), String.valueOf(airPressures.get(i)),
                        String.valueOf(electricCurrents.get(i)) };
                writer.writeNext(data);
            }
            // close writer connection
            writer.close();
        }
        catch (IOException e) {
            System.err.println("Cannot make a new file");
            throw e;
        }
    }
}
