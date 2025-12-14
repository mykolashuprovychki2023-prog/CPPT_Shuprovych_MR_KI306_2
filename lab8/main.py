import math
import struct

def red(text):
    """Повертає текст, виділений червоним кольором у консолі."""
    return f"\033[31m{text}\033[0m"


class MathException(Exception):
    """Клас власного винятку для математичних помилок."""
    pass


def calculate_y(x):
    """
    Обчислює значення y за формулою:
        y = cos(x) / (x + 2 * ctg(x))

    Параметри:
        x (float): значення аргументу в радіанах.

    Повертає:
        float: результат обчислення y.

    Викидає:
        MathException: якщо ctg(x) не існує (sin(x) = 0) або знаменник дорівнює нулю.
    """
    sinus = math.sin(x)
    cosinus = math.cos(x)

    if abs(sinus) < 1e-12:
        raise MathException("sin(x) = 0, ctg(x) - не визначенний")

    ctg = cosinus / sinus
    denom = x + 2 * ctg

    if abs(denom) < 1e-12:
        raise MathException("Знаменник = 0, y - не визначенний")

    return cosinus / denom


def write_res_txt(filename, y):
    """
    Записує результат y у текстовий файл.

    Параметри:
        filename (str): ім'я текстового файлу.
        y (float): значення, яке потрібно записати.

    Викидає:
        FileNotFoundError: якщо файл неможливо створити.
        OSError: загальні помилки файлової системи.
        Exception: інші неочікувані помилки.
    """
    try:
        with open(filename, 'w', encoding="utf-8") as f:
            f.write(str(y))
    except FileNotFoundError:
        print(f"File {filename} not found. Aborting")
        exit(1)
    except OSError:
        print(f"OS error occurred trying to open {filename}")
        exit(1)
    except Exception as err:
        print(f"Unexpected error opening {filename} is", repr(err))
        exit(1)


def read_res_txt(filename):
    """
    Зчитує текстовий файл і повертає його вміст.

    Параметри:
        filename (str): ім'я текстового файлу.

    Повертає:
        str: вміст файлу.

    Викидає:
        FileNotFoundError, OSError, Exception — при помилках доступу.
    """
    try:
        with open(filename, 'r', encoding="utf-8") as f:
            return f.read()
    except FileNotFoundError:
        print(f"File {filename} not found. Aborting")
        exit(1)
    except OSError:
        print(f"OS error occurred trying to open {filename}")
        exit(1)
    except Exception as err:
        print(f"Unexpected error opening {filename} is", repr(err))
        exit(1)


def write_res_bin(filename, y):
    """
    Записує значення y у бінарний файл як 8-байтове число (double).

    Параметри:
        filename (str): ім'я бінарного файлу.
        y (float): значення для запису.

    Викидає:
        FileNotFoundError, OSError, Exception — при помилках роботи з файлом.
    """
    try:
        with open(filename, 'wb') as f:
            f.write(struct.pack('d', y))
    except FileNotFoundError:
        print(f"File {filename} not found. Aborting")
        exit(1)
    except OSError:
        print(f"OS error occurred trying to open {filename}")
        exit(1)
    except Exception as err:
        print(f"Unexpected error opening {filename} is", repr(err))
        exit(1)


def read_res_bin(filename):
    """
    Зчитує бінарний файл та повертає число типу double.

    Параметри:
        filename (str): ім'я бінарного файлу.

    Повертає:
        float: зчитане значення y.

    Викидає:
        FileNotFoundError, OSError, Exception — при помилках доступу.
    """
    try:
        with open(filename, 'rb') as f:
            data = f.read()
            return struct.unpack('d', data)[0]
    except FileNotFoundError:
        print(f"File {filename} not found. Aborting")
        exit(1)
    except OSError:
        print(f"OS error occurred trying to open {filename}")
        exit(1)
    except Exception as err:
        print(f"Unexpected error opening {filename} is", repr(err))
        exit(1)


# --- Основна частина програми ---
print("\ny = cos(x) / (x + 2 * ctg(x))")   # Формула обчислення y

# --- Ввід x до правильного значення ---
while True:
    try:
        x_degrees = float(input("\nВведіть x(градуси): "))   # Ввід у градусах
    except ValueError:
        print(red("Не коректний ввід"))
        continue
    try:
        x = math.radians(x_degrees)      # Переведення у радіани
        y = calculate_y(x)               # Обчислення y
        print("y =", y)
        break                            # Якщо успіх — виходимо
    except MathException as e:
        print(red(f"Помилка: {e}"))      # Математична помилка


print("\nРобота з файлами:\n")

binary = "binRes.bin"
text = "textRes.txt"

# --- Робота з текстовим файлом ---
write_res_txt(text, y)
print("Вміст текстового файлу:")
print(read_res_txt(text), end="")

print("\n")

# --- Робота з бінарним файлом ---
write_res_bin(binary, y)
print("Вміст бінарного файлу:")
print(read_res_bin(binary))
