import java.util.HashMap;

public class StepTracker {
    private int goalNumbersOfSteps = 10000;
    private static int dayInMonth = 30;
    HashMap<String, MonthData> monthToData = new HashMap<>();
    private static final String[] months =
            {"Январь", "Февраль", "Март", " Апрель", "Май",
                    "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    public StepTracker() {
        for (String month : months) {
            monthToData.put(month, new MonthData());
        }
    }

    public int getGoalNumbersOfSteps() { //Геттер на всякий случай
        return goalNumbersOfSteps;
    }

    public void setGoalNumbersOfSteps(int goal) { //Сеттер для установки новой цели Обьекта
        if (goal < 0) {
            Printer.sayNegativeValue();
        } else {
            this.goalNumbersOfSteps = goal;
            Printer.saveData();
        }
    }

    public static int getDayInMonth() {
        return dayInMonth;
    }

    public static void setDayInMonth(int day) { //Защищаем сеттером значение дней в месяце
        if (day < 28 || day > 31) {
            Printer.print("В месяце не может быть " + day + " дней");
        } else {
            dayInMonth = day;
        }
    }

    public String[] getMonths() {
        return months;
    }

    void saveSteps(String month, int day, int steps) {
        monthToData.get(month).dataToStep.put(day, steps);
        Printer.saveData();
    }

    void getStatistics(String month) {
        int maxNumberOfSteps = 0;
        int sumOfSteps = 0;
        int dayOfMaxNumberOfSteps = 0; //
        int countSet = 0; // счетчик для подряд идущих дней.
        int maxSet = 0; // лучшая серия идущих дней
        int countDay = 0; // счетчик дней для определения дня, с которого начинается самый результативный сет
        int daySet = 0; //день с которого начинается лучший сет
        Converter converter = new Converter(0.75, 50); // 1 шаг = 0,75 м, 1 шаг = 50 калорий

        Printer.putLine();
        for (Integer day : monthToData.get(month).dataToStep.keySet()) {
            System.out.print(day + " день: " + monthToData.get(month).dataToStep.get(day) + ", ");
            if (monthToData.get(month).dataToStep.get(day) > maxNumberOfSteps) {
                maxNumberOfSteps = monthToData.get(month).dataToStep.get(day); // ищем самое большое кол-во шагов
                dayOfMaxNumberOfSteps = day; //день, в котором пройдено самое большоре кол-во шагов
            }
            sumOfSteps += monthToData.get(month).dataToStep.get(day); // общее кол-во шагов в мес.
        }
        Printer.print("");
        for (Integer steps : monthToData.get(month).dataToStep.values()) {
            countDay++;
            if (steps >= goalNumbersOfSteps) {
                countSet++;
                if (maxSet < countSet) {
                    maxSet = countSet;
                    daySet = countDay;
                }
            } else {
                countSet = 0;
            }
        }
        Printer.showStatistics(sumOfSteps, maxNumberOfSteps, dayOfMaxNumberOfSteps, // за печать всей статистики отвечает данный метод класса Printer
                dayInMonth, converter.lengthOfSteps, converter.caloriesPerStep, // передаем в нее всю необходимую информацию
                goalNumbersOfSteps, maxSet, daySet);
    }
}






