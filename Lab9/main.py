from railway import Locomotive, ElectricTrain

def red(text):
    """Повертає текст, виділений червоним кольором у консолі."""
    return f"\033[31m{text}\033[0m"

if __name__ == "__main__":
    # ----- Створення локомотива -----
    try:
        print("Створення локомотива...\n")
        loco = Locomotive("ЧС8", 3000, 160, 120)
        print(loco.get_info())
    except ValueError as e:
        print(red(f"Помилка: {e}"))
    else:
        print("\nСимуляція роботи локомотива:\n")
        loco.start()
        loco.stop()

    # ----- Створення електрички -----
    try:
        print("\nСтворення електрички...\n")
        train = ElectricTrain("T2000", 1000, 100, 100, 20, 1000, "Київ-Львів")
        print(train.get_info())
    except ValueError as e:
        print(red(f"Помилка: {e}"))
    else:
        print("\nСимуляція роботи електрички:\n")
        train.open_doors()
        train.close_doors()
        train.start()
        train.stop()
