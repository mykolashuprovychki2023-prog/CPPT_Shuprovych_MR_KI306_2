from .LoggerMixin import LoggerMixin

class Locomotive(LoggerMixin):
    def __init__(self, model, power, max_speed, weight):
        # Використовуємо сеттери — автоматична валідація
        self.model = model
        self.power = power
        self.max_speed = max_speed
        self.weight = weight

    # --------- GETTERS ----------
    @property
    def model(self):
        return self._model

    @property
    def power(self):
        return self._power

    @property
    def max_speed(self):
        return self._max_speed

    @property
    def weight(self):
        return self._weight

    # --------- SETTERS ----------
    @model.setter
    def model(self, value):
        if not isinstance(value, str) or not value.strip():
            raise ValueError("Некоректна назва моделі")
        self._model = value.strip()

    @power.setter
    def power(self, value):
        if value <= 0:
            raise ValueError("Потужність має бути більшою за 0")
        self._power = value

    @max_speed.setter
    def max_speed(self, value):
        if value <= 0 or value > 500:
            raise ValueError("Максимальна швидкість має бути в межах 1–500 км/год")
        self._max_speed = value

    @weight.setter
    def weight(self, value):
        if value <= 0:
            raise ValueError("Вага має бути більшою за 0")
        self._weight = value

    # --------- METHODS ----------
    def start(self):
        print(f"Локомотив {self.model} рушив")
        self.log("Локомотив рушив")

    def stop(self):
        print(f"Локомотив {self.model} зупинився")
        self.log("Локомотив зупинився")

    def get_info(self):
        self.log(f"Локомотив - {self.model}, потужність - {self.power} кВТ, "
                f"макс швидкість - {self.max_speed} км/год, вага - {self.weight} т")
        return (f"Локомотив - {self.model}, потужність - {self.power} кВТ, "
                f"макс швидкість - {self.max_speed} км/год, вага - {self.weight} т")
