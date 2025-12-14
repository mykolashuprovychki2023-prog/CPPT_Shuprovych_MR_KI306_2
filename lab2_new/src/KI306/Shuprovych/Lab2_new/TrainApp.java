package KI306.Shuprovych.Lab2_new;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас-драйвер TrainApp для демонстрації роботи лабораторної роботи №2.
 * Створює об'єкти двигуна, кондуктора та вагонів, формує поїзд через Builder,
 * бронює місця, рухає поїзд по маршруту та показує логування у файл.
 */
public class TrainApp {
    public static void main(String[] args) {

        // Використовуємо try-with-resources для автоматичного закриття логера
        try (TrainLogger logger = new TrainLogger("train.log")) {

            // Створюємо компоненти поїзда
            Engine engine = new Engine("Turbine-3000", 5000, 2015);
            Conductor conductor = new Conductor("Ivan Petrov", "AB-1234");

            // Створюємо вагони
            Carriage c1 = new Carriage("C1", Carriage.Type.PASSENGER, 10);
            Carriage c2 = new Carriage("C2", Carriage.Type.SLEEPING, 8);
            Carriage c3 = new Carriage("C3", Carriage.Type.PASSENGER, 12);

            // Додаємо вагони у список
            List<Carriage> cars = new ArrayList<>();
            cars.add(c1);
            cars.add(c2);

            // Створюємо поїзд через Builder
            Train train = new Train.Builder()
                    .name("InterCity-1")
                    .engine(engine)
                    .conductor(conductor)
                    .carriages(cars)
                    .origin("Kyiv")
                    .destination("Lviv")
                    .logger(logger)
                    .build();

            // Друкуємо початковий маніфест поїзда
            train.printManifest();

            // Додаємо ще один вагон (копію)
            Carriage c3copy = new Carriage(c3); // copy constructor
            train.addCarriage(c3copy);

            // Бронюємо місця
            train.reserveSeat("C1", 3, "Alice");
            train.reserveSeat("C3", 1, "Bob");

            // Розрахунок загальної кількості місць
            train.getTotalSeats();

            // Початок руху поїзда та зупинка на проміжній станції
            train.startJourney();
            train.moveToNextStation("Zhytomyr");
            train.stopJourney();

            // Друк маніфесту після бронювання та руху
            train.printManifest();

            // Створюємо deep copy поїзда
            Train trainCopy = new Train(train);
            trainCopy.printManifest();

            // Завершення роботи поїзда
            train.shutdown();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
