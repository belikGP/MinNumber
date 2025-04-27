# MinNumber
Тестовое задание для Удобный Софт

1. Требования
Java 17+ (для Spring Boot 3.x)
Maven 3.8+
Любая IDE (IntelliJ IDEA, Eclipse, VS Code)

2. Сборка проекта
Клонируйте репозиторий или скопируйте код в папку проекта.
Откройте терминал в корне проекта и выполните:

bash
mvn clean package
Собранный JAR-файл появится в папке target.

3. Запуск сервиса
bash
java -jar target/min-number-service.jar
Сервис запустится на порту 8080.

4. Проверка через Swagger
Откройте в браузере:
http://localhost:8080/swagger-ui.html
Найдите единственный метод POST /findNthMin.
Нажмите Try it out → заполните параметры:
filePath: Полный путь к XLSX-файлу (например, C:/data/numbers.xlsx).
n: Порядковый номер минимального числа (N ≥ 1).
Нажмите Execute.

