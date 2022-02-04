import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StepTracker stepTracker = new StepTracker();
        Scanner scanner = new Scanner(System.in);
        printMenu();
        int userInput = scanner.nextInt();

        while (userInput != 4) {
            if (userInput == 1) {
                System.out.println("Введите название месяца с заглавной буквы");
                String month = scanner.next();
                while (!stepTracker.monthToData.containsKey(month)) {
                    System.out.println("Вы ввели некорректное название месяца, пожалуйста повторите");
                    month = scanner.next();
                }
                System.out.println("ОК! Теперь укажите день месяца в диапазоне: от 1 по 30 ");
                int day = scanner.nextInt();
                while (day < 1 || day > 30) {
                    System.out.println("Вы ввели неккоректное значение дня: " +day+", повторите пожалуйста ввод");
                    day = scanner.nextInt();
                    }
                System.out.println("ОК! Теперь введите кол-во шагов");
                int stepsNumber = scanner.nextInt();
                while (stepsNumber < 0) {
                    System.out.println("Вы ввели отрицательное число, пожалуйста повторите ввод");
                    stepsNumber = scanner.nextInt();
                }
                stepTracker.saveSteps(month, day, stepsNumber);
            } else if (userInput == 2) {
                System.out.println("За какой месяц напечатать статистику?");
                String month = scanner.next();
                while (!stepTracker.monthToData.containsKey(month)) {
                    System.out.println("Вы ввели некорректное название месяца, пожалуйста повторите ввод");
                    month = scanner.next();
                }
                stepTracker.printStatistics(month);
            } else if (userInput == 3) {
                System.out.println("Введите новую цель по количеству шагов в день");
                int goal = scanner.nextInt();
                while (goal < 0) {
                    System.out.println("Вы ввели отрицательное число, пожалуйста повторите ввод");
                    goal = scanner.nextInt();
                }
                stepTracker.goalUpdater(goal);
            } else {
                System.out.println("Такой команды нет");
            }
            System.out.println();
            printMenu(); // печатаем меню ещё раз перед завершением предыдущего действия
            userInput = scanner.nextInt(); // повторное считывание данных от пользователя
        }
        System.out.println("Программа завершена");
    }

    private static void printMenu() {
        System.out.println("Введите команду:");
        System.out.println("1 - Ввести количество шагов за определённый день");
        System.out.println("2 - Напечатать статистику за определённый месяц");
        System.out.println("3 - Изменить цель по количеству шагов в день");
        System.out.println("4 - Выйти из приложения");
    }
}