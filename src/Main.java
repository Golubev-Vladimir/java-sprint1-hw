import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        StepTracker stepTracker = new StepTracker();
        Scanner scanner = new Scanner(System.in);
        Printer.showMenu();
        int userInput = scanner.nextInt();

        while (userInput != 4) {
            switch (userInput) {
                case 1:
                    Printer.print("Введите название месяца с заглавной буквы.\n" +
                            "Например: Март");
                    String month = scanner.next();
                    while (!stepTracker.monthToData.containsKey(month)) {
                        Printer.sayErrorValue();
                        month = scanner.next();
                    }
                    Printer.print("ОК! Теперь укажите день месяца в диапазоне:" +
                            " от 1 по 30 ");
                    int day = scanner.nextInt();
                    while (day < 1 || day > 30) {
                        Printer.sayErrorValue();
                        day = scanner.nextInt();
                    }
                    Printer.print("ОК! Теперь введите кол-во шагов");
                    int stepsNumber = scanner.nextInt();
                    while (stepsNumber < 0) {
                        Printer.sayNegativeValue();
                        stepsNumber = scanner.nextInt();
                    }
                    stepTracker.saveSteps(month, day, stepsNumber);
                    break;
                case 2:
                    Printer.print("Укажите месяц, за который напечатать статистику?\n" +
                            "Например: Январь");
                    String monthStat = scanner.next();
                    while (!stepTracker.monthToData.containsKey(monthStat)) {
                        Printer.sayErrorValue();
                        monthStat = scanner.next();
                    }
                    stepTracker.getStatistics(monthStat);
                    break;
                case 3:
                    Printer.print("Введите новую цель по количеству шагов в день");
                    int goal = scanner.nextInt();
                    stepTracker.setGoalNumbersOfSteps(goal);
                    break;
                default:
                    Printer.sayErrorCommand();
                    break;
            }
            Printer.showMenu(); // печатаем меню ещё раз перед завершением предыдущего действия
            userInput = scanner.nextInt(); // повторное считывание данных от пользователя
        }
        Printer.exit();
    }
}