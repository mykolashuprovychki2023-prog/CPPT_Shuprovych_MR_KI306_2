def red(text): return f"\033[31m{text}\033[0m"

# --- Ввід символу-заповнювача з перевіркою коректності ---
symbol = input("Введіть символ-заповнювач: ")
while symbol.isdigit() or len(symbol) != 1 or symbol == " ": symbol = input(red("ПОМИЛКА. Символ має бути один(не цифра): "))
print("Ваш символ -", symbol)

# --- Ввід розміру квадратної матриці з перевіркою ---
size = input("Введіть розмір квадратної матриці(від 1 до 25): ")
while not size.isdigit() or not (1 <= int(size) <= 25): size = input(red("ПОМИЛКА. Занодто ввеликий\малий розмір або не число. Введіть ще раз: "))
size = int(size)
print("\nВаша матриця -", size, "x", size)

# --- Створення квадратної матриці зі символів ---
matrix = [[symbol for j in range(size)] for i in range(size)]

# --- Виведення матриці ---
for row in matrix:
    print(" ".join(row))

# --- Побудова правого нижнього трикутника ---
print("\nТрикутник")
for i in range(size):
    for j in range(size):
        if j >= size - i - 1:
            print(symbol, end=" ")
        else:
            print(" ", end=" ")
    print()

# --- Побудова зубчастого списку ---
print("\nЗубчастий")
jagged = []
for i in range(0, size):
    row = [symbol] * (i+1)
    jagged.append(row)

# --- Виведення зубчастого списку ---
for i in jagged:
    print(i)

print()
for i, row in enumerate(jagged):
    space = [" "] * (size - i - 1)
    print(" ".join(space + row))
