import java.util.HashMap;

class MonthData {
    HashMap<Integer, Integer> dataToStep = new HashMap<>();

    public MonthData() {
        for (int i = 1; i <= StepTracker.getDayInMonth(); i++) {
            dataToStep.put(i, 0);
        }
    }
}