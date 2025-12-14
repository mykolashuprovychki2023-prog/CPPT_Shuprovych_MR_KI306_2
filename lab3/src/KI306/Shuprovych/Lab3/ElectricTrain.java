package KI306.Shuprovych.Lab3;

import java.util.ArrayList;
import java.util.List;

/**
 * Електричка: підклас Train з електричною тягою.
 * Реалізує інтерфейс Electric.
 */
public class ElectricTrain extends Train implements Electric {

    private final int voltage;
    private final String currentType;
    private final int maxSpeed;
    private boolean pantographUp;
    private final double energyConsumptionPerKm;

    /** Конструктор для Builder */
    public ElectricTrain(String name, Engine engine, List<Carriage> carriages, Conductor conductor,
                         TrainLogger logger, String origin, String destination,
                         int voltage, String currentType, int maxSpeed, double energyConsumptionPerKm) {
        super(name, engine, carriages, conductor, logger, origin, destination);
        this.voltage = voltage;
        this.currentType = currentType;
        this.maxSpeed = maxSpeed;
        this.energyConsumptionPerKm = energyConsumptionPerKm;
        this.pantographUp = false;
        log("ElectricTrain created: " + getName() + ", voltage=" + voltage + "V");
    }

    /** Копіювальний конструктор */
    public ElectricTrain(ElectricTrain other) {
        super(other);
        this.voltage = other.voltage;
        this.currentType = other.currentType;
        this.maxSpeed = other.maxSpeed;
        this.energyConsumptionPerKm = other.energyConsumptionPerKm;
        this.pantographUp = other.pantographUp;
        log("ElectricTrain copied: " + getName());
    }

    /** Підключення до контактної мережі */
    @Override
    public void connectToCatenary() {
        if (!pantographUp) {
            pantographUp = true;
            log("Pantograph raised.");
        } else {
            log("Pantograph already up.");
        }
    }

    /** Відключення від контактної мережі */
    @Override
    public void disconnectFromCatenary() {
        if (pantographUp) {
            pantographUp = false;
            log("Pantograph lowered.");
        } else {
            log("Pantograph already down.");
        }
    }

    /** @return напруга контактної мережі */
    @Override
    public int getVoltage() { return voltage; }

    /** @return споживання енергії на км */
    @Override
    public double getPowerConsumption() { return energyConsumptionPerKm; }

    /** @return тип поїзда */
    @Override
    public String getTrainType() { return "Electric"; }

    /** Оцінка енергії для заданої відстані */
    public double estimateEnergyForDistance(double km) { return km * energyConsumptionPerKm; }

    public boolean isPantographUp() { return pantographUp; }
    public int getMaxSpeed() { return maxSpeed; }
    public String getCurrentType() { return currentType; }

    // -------------------- Builder --------------------
    public static class Builder {
        private String name;
        private Engine engine;
        private List<Carriage> carriages = new ArrayList<>();
        private Conductor conductor;
        private TrainLogger logger;
        private String origin;
        private String destination;
        private int voltage = 25000;
        private String currentType = "AC";
        private int maxSpeed = 120;
        private double energyConsumptionPerKm = 4.5;

        public Builder name(String name) { this.name = name; return this; }
        public Builder engine(Engine engine) { this.engine = engine; return this; }
        public Builder carriages(List<Carriage> carriages) { this.carriages = carriages; return this; }
        public Builder conductor(Conductor conductor) { this.conductor = conductor; return this; }
        public Builder logger(TrainLogger logger) { this.logger = logger; return this; }
        public Builder origin(String origin) { this.origin = origin; return this; }
        public Builder destination(String destination) { this.destination = destination; return this; }
        public Builder voltage(int voltage) { this.voltage = voltage; return this; }
        public Builder currentType(String currentType) { this.currentType = currentType; return this; }
        public Builder maxSpeed(int maxSpeed) { this.maxSpeed = maxSpeed; return this; }
        public Builder energyConsumptionPerKm(double e) { this.energyConsumptionPerKm = e; return this; }

        /** Створення об'єкта ElectricTrain */
        public ElectricTrain build() {
            return new ElectricTrain(name, engine, carriages, conductor, logger, origin, destination,
                    voltage, currentType, maxSpeed, energyConsumptionPerKm);
        }
    }
}
