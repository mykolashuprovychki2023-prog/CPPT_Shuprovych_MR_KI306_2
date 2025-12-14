from .Locomotive import Locomotive
from .LoggerMixin import LoggerMixin

class ElectricTrain(Locomotive, LoggerMixin):
    """Похідний клас електрички, який розширює базовий локомотив."""

    def __init__(self, model, power, max_speed, weight, num_cars, passenger_capacity, route):
        super().__init__(model, power, max_speed, weight)
        # Використовуємо сеттери для валідації
        self.num_cars = num_cars
        self.passenger_capacity = passenger_capacity
        self.route = route

    @property
    def num_cars(self):
        return self._num_cars

    @property
    def passenger_capacity(self):
        return self._passenger_capacity

    @property
    def route(self):
        return self._route

    # -------- SETTERS ----------
    @num_cars.setter
    def num_cars(self, value):
        if not isinstance(value, int) or value <= 0 or value > 50:
            raise ValueError("Кількість вагонів має бути в межах 1–50")
        self._num_cars = value

    @passenger_capacity.setter
    def passenger_capacity(self, value):
        if not isinstance(value, int) or value <= 0:
            raise ValueError("Вмістимість пасажирів має бути додатним числом")
        self._passenger_capacity = value

    @route.setter
    def route(self, value):
        if not isinstance(value, str) or not value.strip():
            raise ValueError("Маршрут має бути непорожнім рядком")
        self._route = value.strip()

    # -------- METHODS ----------
    def get_info(self):
        """Повертає детальну інформацію про електричку."""
        base_info = super().get_info()
        self.log(f"к-сть вагонів - {self._num_cars}, "
                f"вмістимість пасажирів - {self._passenger_capacity}, "
                f"маршрут - {self._route}")
        return (base_info +
                f", к-сть вагонів - {self._num_cars}, "
                f"вмістимість пасажирів - {self._passenger_capacity}, "
                f"маршрут - {self._route}")

    def open_doors(self):
        """Відчиняє двері електрички (симуляція)."""
        print(f"Електричка {self.model} відчинила двері")
        self.log("Двері відкрито")

    def close_doors(self):
        """Зачиняє двері електрички (симуляція)."""
        print(f"Електричка {self.model} закрила двері")
        self.log("Двері закрито")
