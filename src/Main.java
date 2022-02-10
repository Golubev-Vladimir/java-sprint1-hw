import java.util.Scanner;

import static utils.Printer.*;

public class Main {

    public static void main(String[] args) {
        StepTracker stepTracker = new StepTracker();
        Scanner scanner = new Scanner(System.in);
        showMenu();
        int userInput = scanner.nextInt();
        Menu userCommand = Menu.values()[userInput - 1];
        while (userCommand != Menu.EXIT) {
            switch (userCommand) {
                case INPUT:
                    print("Введите название месяца с заглавной буквы.\n" +
                            "Например: Март");
                    String month = scanner.next();
                    while (!StepTracker.getMonthToData().containsKey(month)) {
                        sayErrorValue();
                        month = scanner.next();
                    }
                    print("ОК! Теперь укажите день месяца в диапазоне:" +
                            " от 1 по 30 ");
                    int day = scanner.nextInt();
                    while (day < 1 || day > 30) {
                        sayErrorValue();
                        day = scanner.nextInt();
                    }
                    print("ОК! Теперь введите кол-во шагов");
                    int stepsNumber = scanner.nextInt();
                    while (stepsNumber < 0) {
                        sayNegativeValue();
                        stepsNumber = scanner.nextInt();
                    }
                    stepTracker.saveSteps(month, day, stepsNumber);
                    break;
                case STAT:
                    print("Укажите месяц, за который напечатать статистику?\n" +
                            "Например: Январь");
                    String monthStat = scanner.next();
                    while (!StepTracker.getMonthToData().containsKey(monthStat)) {
                        sayErrorValue();
                        monthStat = scanner.next();
                    }
                    stepTracker.getStatistics(monthStat);
                    break;
                case CHANGE:
                    print("Введите новую цель по количеству шагов в день");
                    int goal = scanner.nextInt();
                    stepTracker.setGoalNumbersOfSteps(goal);
                    break;
                default:
                    sayErrorCommand();
                    break;
            }
            showMenu(); // после отработки команды пользователя, повторно запускаем всю конструкцию: меню, ввод пользователя
            userInput = scanner.nextInt();
            userCommand = Menu.values()[userInput - 1];
        }
        exit();
    }
}