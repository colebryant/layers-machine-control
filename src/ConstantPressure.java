import java.util.ArrayList;

public class ConstantPressure implements RecipeStrategy {

    public void generateValues(ArrayList<Integer> airPressures, ArrayList<Integer> electricCurrents,
                                ArrayList<Integer> operationTimes, int partSize) {
        for (int t = 0; t < 11; t++) {
            operationTimes.add(t);
            airPressures.add(partSize + 100);
            electricCurrents.add(t * 2);
        }
    }

}
