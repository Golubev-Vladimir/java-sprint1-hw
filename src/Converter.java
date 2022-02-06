class Converter {
    double lengthOfSteps; //длина шага
    double caloriesPerStep; //кол-во сожженых каллорий за 1 шаг

    Converter(double length, double calories) {
        lengthOfSteps = length / 1000; //переводим в км
        caloriesPerStep = calories / 1000; //переводим в Килокалории
    }
}