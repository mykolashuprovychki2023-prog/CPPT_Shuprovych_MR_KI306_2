package KI306.Shuprovych.Lab3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Демонстраційний додаток ЛР3:
 * Створює електричку (ElectricTrain) та демонструє роботу класів.
 * Показує:
 * - Абстрактні класи
 * - Спадкування
 * - Поліморфізм
 * - Інтерфейси
 * - Приведення типів (casting)
 */
public class TrainApp {
    public static void main(String[] args) {
        try (TrainLogger logger = new TrainLogger("train_lab3.log")) {

            // Створюємо двигун і кондуктора
            Engine engine = new Engine("EL-Motor", 3200, 2018);
            Conductor conductor = new Conductor("Petro Shevchenko", "E-2025");

            // Створюємо вагони
            Carriage c1 = new Carriage("C1", Carriage.Type.PASSENGER, 12);
            Carriage c2 = new Carriage("C2", Carriage.Type.PASSENGER, 12);
            List<Carriage> cars = new ArrayList<>();
            cars.add(c1);
            cars.add(c2);

            // Створення електрички через Builder
            ElectricTrain elektrychka = new ElectricTrain.Builder()
                    .name("Elektrychka-101")
                    .engine(engine)
                    .carriages(cars)
                    .conductor(conductor)
                    .logger(logger)
                    .origin("Lviv")
                    .destination("Khmelnytskyi")
                    .voltage(25000)
                    .currentType("AC")
                    .maxSpeed(120)
                    .energyConsumptionPerKm(4.5)
                    .build();

            // Upcasting: підклас → суперклас (Приведення об’єктних типів)
            Train trainReference = elektrychka;
            System.out.println("\nUpcasting виконано: ElectricTrain → Train");

            // Downcasting: суперклас → підклас, з перевіркою instanceof
            if (trainReference instanceof ElectricTrain) {
                ElectricTrain casted = (ElectricTrain) trainReference;
                System.out.println("Downcasting виконано: Train → ElectricTrain");
                casted.connectToCatenary(); // виклик методу специфічного для ElectricTrain
            }

            // Демонстрація операцій
            elektrychka.printManifest();
            elektrychka.reserveSeat("C1", 1, "Oksana");
            elektrychka.startJourney();
            elektrychka.moveToNextStation("Ternopil");
            elektrychka.stopJourney();
            elektrychka.disconnectFromCatenary();
            elektrychka.shutdown();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
