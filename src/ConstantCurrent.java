import java.util.ArrayList;

public class ConstantCurrent implements RecipeStrategy {

    public void generateValues(ArrayList<Integer> airPressures, ArrayList<Integer> electricCurrents,
                               ArrayList<Integer> operationTimes, int partSize) {
        for (int t = 0; t < 21; t++) {
            operationTimes.add(t);
            int airPressure = 50 - (t * 2);
            airPressures.add(Math.max(airPressure, 10));
            electricCurrents.add(partSize + 50);
        }
    }

}
