package service;

public class Converter {
    double lengthSteps; //длина шага
    double caloriesPerStep; //кол-во сожженых каллорий за 1 шаг

    public Converter(double length, double calories) {
        lengthSteps = length / 1000; //переводим в км
        caloriesPerStep = calories / 1000; //переводим в Килокалории
    }
}