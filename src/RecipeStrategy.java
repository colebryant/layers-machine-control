import java.util.ArrayList;

public interface RecipeStrategy {

    void generateValues(ArrayList<Integer> airPressures, ArrayList<Integer> electricCurrents,
                        ArrayList<Integer> operationTimes, int partSize);
}
