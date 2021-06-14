import java.util.ArrayList;

public class Ramp implements RecipeStrategy {

    public void generateValues(ArrayList<Integer> airPressures, ArrayList<Integer> electricCurrents,
                               ArrayList<Integer> operationTimes, int partSize) {

        if (partSize < 50) {
            System.err.println("Error: Cannot proceed with Ramp because part size is less than 50");
        } else {
            for (int t = 0; t < 31; t++) {
                operationTimes.add(t);
                int airPressure = t * 10;
                airPressures.add(Math.min(airPressure, 100));
                electricCurrents.add(Math.min(partSize + t * 20, 200));
            }
        }
    }

}
