import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("Добро пожаловать в настоящий калькулятор!");
        System.out.println("Команды: 'C' — сброс/обнуление результата, 'S' — выключение.");

        while (isRunning) {
            // Шаг 1. Ввод самого первого числа в начале сессии
            System.out.print("Введите первое число (или 'S' для выключения): ");
            String firstInput = scanner.next();

            // Проверка на команду выключения
            if (firstInput.equalsIgnoreCase("s")) {
                isRunning = false;
                break;
            }

            // Проверка, что введено именно число
            double currentResult;
            try {
                currentResult = Double.parseDouble(firstInput);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введено не число. Попробуйте сначала.");
                continue;
            }

            // Внутренний цикл для непрерывной цепочки вычислений
            while (true) {
                // Шаг 2. Считывание операции
                System.out.print("Текущий результат [" + currentResult + "]. Введите операцию (+, -, *, /), 'C' для сброса или 'S' для выключения: ");
                String operation = scanner.next();

                // Проверка команд управления без учета регистра (C и S)
                if (operation.equalsIgnoreCase("s")) {
                    isRunning = false;
                    break;
                }

                if (operation.equalsIgnoreCase("c")) {
                    System.out.println("Результат сброшен!\n");
                    break; // Возвращает к вводу нового первого числа
                }

                // Проверка на неподдерживаемые операции
                if (!operation.equals("+") && !operation.equals("-") && !operation.equals("*") && !operation.equals("/")) {
                    System.out.println("Ошибка: неподдерживаемая операция '" + operation + "'. Попробуйте еще раз.");
                    continue;
                }

                // Шаг 3. Считывание второго числа
                System.out.print("Введите следующее число (или 'C'/'S'): ");
                String secondInput = scanner.next();

                if (secondInput.equalsIgnoreCase("s")) {
                    isRunning = false;
                    break;
                }

                if (secondInput.equalsIgnoreCase("c")) {
                    System.out.println("Результат сброшен!\n");
                    break;
                }

                // Явная инициализация нулем убирает ошибку компиляции
                double secondNumber = 0;
                try {
                    secondNumber = Double.parseDouble(secondInput);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введено не число. Начните операцию заново.");
                    continue;
                }

                // Проверка на деление на ноль
                if (operation.equals("/") && secondNumber == 0) {
                    System.out.println("Ошибка: деление на ноль невозможно! Попробуйте другую операцию.");
                    continue;
                }

                // Шаг 4. Вычисление и вывод результата на экран
                switch (operation) {
                    case "+":
                        currentResult = add(currentResult, secondNumber);
                        break;
                    case "-":
                        currentResult = subtract(currentResult, secondNumber);
                        break;
                    case "*":
                        currentResult = multiply(currentResult, secondNumber);
                        break;
                    case "/":
                        currentResult = divide(currentResult, secondNumber);
                        break;
                }

                System.out.println("Результат: " + currentResult);
                System.out.println("--------------------------------");
                // Полученный результат автоматически становится базой для следующего шага
            }
        }

        System.out.println("Калькулятор выключен. До свидания!");
        scanner.close();
    }

    // --- Выделенные методы для математических операций ---

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        return a / b;
    }
}
