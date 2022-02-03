import java.util.HashMap;

public class StepTracker {
    int goalNumbersOfSteps = 10000;
    int dayInMonth = 30;
    HashMap<String, MonthData> monthToData = new HashMap<>();
    String[] months = {"Январь", "Февраль", "Март", " Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    public StepTracker() {
        for (String month : months) {
            monthToData.put(month, new MonthData());
        }
    }

    void saveSteps(String month, int day, int steps) {
        monthToData.get(month).dataToStep.put(day, steps);
        System.out.println("Данные сохранены");
    }

    void printStatistics(String month) {
        int maxNumberOfSteps = 0;
        int sumOfSteps = 0;
        int dayOfmaxNumberOfSteps = 0; //
        int countSet = 0; // счетчик для подряд идущих дней.
        int maxset = 0; // лучшая серия идущих дней
        int countDay = 0; // счетчик дней для определения дня, с которого начинается самый результативный сет
        int daySet = 0; //день с которого начинается лучший сет
        Converter converter = new Converter(0.75, 50); // 1 шаг = 0,75 м, 1 шаг = 50 калорий
        for (Integer day : monthToData.get(month).dataToStep.keySet()) {
            System.out.print(day + " день: " + monthToData.get(month).dataToStep.get(day) + ", ");
            if (monthToData.get(month).dataToStep.get(day) > maxNumberOfSteps) {
                maxNumberOfSteps = monthToData.get(month).dataToStep.get(day); // ищем самое большое кол-во шагов
                dayOfmaxNumberOfSteps = day; //день, в котором пройдено самое большоре кол-во шагов
            }
            sumOfSteps += monthToData.get(month).dataToStep.get(day); // общее кол-во шагов в мес.
        }
        for (Integer steps : monthToData.get(month).dataToStep.values()) {
            countDay++;
            if (steps >= goalNumbersOfSteps) {
                countSet++;
                if (maxset < countSet) {
                    maxset = countSet;
                    daySet = countDay;
                }
            } else {
                countSet = 0;
            }
        }

        System.out.println("Общее количество шагов за месяц: " + sumOfSteps);
        System.out.println("Максимальное пройденное количество шагов в месяце: " + maxNumberOfSteps + " (" + dayOfmaxNumberOfSteps + "-го числа)");
        System.out.println("Среднее количество шагов в месяц: " + sumOfSteps / dayInMonth);
        converter.Statistics(sumOfSteps);
        System.out.println("Лучшая серия: максимальное количество подряд идущих дней, в течение которых количество шагов за день было равно или выше целевого (" + goalNumbersOfSteps + "): " +maxset--+" (начиная с "+(daySet-maxset)+"-го числа)");
    }

    void goalUpdater(int goal) {
        goalNumbersOfSteps = goal;
        System.out.println("Новая цель " + goal + " установлена");
    }

    class MonthData {
        HashMap<Integer, Integer> dataToStep = new HashMap<>();

        public MonthData() {
            for (int i = 1; i <= dayInMonth; i++) {
                dataToStep.put(i, 0);
            }
        }
    }

    static class Converter {
        double lengthOfSteps;
        double caloriesPerStep;

        Converter(double length, double calories) {
            lengthOfSteps = length / 1000; //переводим в км
            caloriesPerStep = calories / 1000; //переводим в Килокалории
        }

        void Statistics(int sumSteps) {
            System.out.println("Пройденная дистанция (в км): " + sumSteps * lengthOfSteps);
            System.out.println("Количество сожжённых килокалорий: " + sumSteps * caloriesPerStep);
        }
    }
}






