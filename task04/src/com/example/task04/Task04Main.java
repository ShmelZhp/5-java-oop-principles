package com.example.task04;
import java.time.temporal.ChronoUnit;
import java.io.IOException;

public class Task04Main {
    public static void main(String[] args) {
        // 1. Получаем логгер
        Logger logger = Logger.getLogger("MyApp");

        // 2. Устанавливаем уровень логирования
        logger.setLevel(Logger.Level.INFO);

        // 3. Пример с ConsoleHandler (вывод в консоль)
        System.out.println("=== ЛОГ В КОНСОЛЬ ===");
        logger.setHandler(new ConsoleHandler());
        logger.info("Это сообщение INFO в консоль");
        logger.debug("Это DEBUG — не появится, т.к. уровень INFO");
        logger.error("Ошибка в консоли!");

        // 4. Пример с FileHandler (запись в файл)
        try {
            System.out.println("\n=== ЛОГ В ФАЙЛ (log.txt) ===");
            logger.setHandler(new FileHandler("log.txt"));
            logger.info("Запись в файл: приложение стартовало");
            logger.warning("Предупреждение: мало памяти");
            logger.error("Критическая ошибка: %s", "не удалось сохранить данные");

            // Обязательно закрываем обработчик
            ((FileHandler) logger.getHandler()).close();

        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }

        // 5. Пример с RotationFileHandler (ротация файлов)
        try {
            System.out.println("\n=== РОТИРУЕМЫЙ ЛОГ (каждую минуту) ===");
            // Ротация каждую минуту (для демонстрации)
            logger.setHandler(new RotationFileHandler("rotated-log", ChronoUnit.MINUTES));

            logger.info("Сообщение 1 — должно создать новый файл");
            // Если запустить несколько раз в течение минуты — попадёт в тот же файл
            logger.info("Сообщение 2 — в тот же файл (если <1 мин)");

            // Закрываем обработчик
            ((RotationFileHandler) logger.getHandler()).close();

        } catch (IOException e) {
            System.err.println("Ошибка при ротации файла: " + e.getMessage());
        }

        // 6. Комбинированный пример: несколько обработчиков
        System.out.println("\n=== КОМБИНИРОВАННЫЙ ЛОГ (консоль + файл) ===");
        try {
            FileHandler fileHandler = new FileHandler("combined.log");
            ConsoleHandler consoleHandler = new ConsoleHandler();

            // Логируем одновременно в файл и консоль
            logger.setHandler(fileHandler);
            logger.info("Это пойдёт в файл combined.log");

            logger.setHandler(consoleHandler);
            logger.info("Это появится в консоли");

            // Закрываем оба обработчика
            fileHandler.close();
            consoleHandler.close();

        } catch (IOException e) {
            System.err.println("Ошибка в комбинированном логе: " + e.getMessage());
        }
    }
}
