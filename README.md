Мікросервіси для нового бізнес-процесу
Цей проект складається з набору мікросервісів, які реалізують бізнес-процес обробки даних клієнта. Взаємодія між сервісами відбувається через ActiveMQ, а дані зберігаються в SQLite за допомогою JPA.

Зміст
-Опис системи
-Мікросервіси
-Запуск проекту
-Тестування
-Авторизація

Опис системи
Система складається з п'яти мікросервісів, які послідовно обробляють дані клієнта:

Client Service: Приймає HTTP запити з id клієнта та перевіряє авторизацію.

Name Service: Отримує id клієнта та імітує отримання ПІБ.

Address Service: Отримує ПІБ та імітує отримання адреси.

Contact Service: Отримує адресу та імітує отримання контактів.

Database Service: Зберігає всі дані клієнта в SQLite.

Взаємодія між сервісами відбувається через ActiveMQ.

Мікросервіси
1. Client Service
Порт: 8080
API:
POST /client/data: Приймає JSON з id клієнта та sid у заголовку.

2. Name Service
Черга ActiveMQ: nameServiceQueue (вхідна), addressServiceQueue (вихідна).

Функція: Імітує отримання ПІБ клієнта.

3. Address Service
Черга ActiveMQ: addressServiceQueue (вхідна), contactServiceQueue (вихідна).

Функція: Імітує отримання адреси клієнта.

4. Contact Service
Черга ActiveMQ: contactServiceQueue (вхідна), databaseServiceQueue (вихідна).

Функція: Імітує отримання контактів клієнта.

5. Database Service
Черга ActiveMQ: databaseServiceQueue.

Функція: Зберігає дані клієнта в SQLite.

Запуск проекту/Збірка проекту
git clone https://github.com/AdamenkoVlad/microservice-project.git
docker-compose up --build

Доступ до сервісів
Client Service: http://localhost:8080
ActiveMQ Console: http://localhost:8161 (логін: admin, пароль: admin)

Тестування
Для кожного мікросервісу написані юніт-тести. Приклади тестів можна знайти у папці src/test/java кожного сервісу.

Авторизація
Авторизація реалізована через перевірку sid у заголовку HTTP запиту.



