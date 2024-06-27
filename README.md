# Создание Spring Boot Starter для логирования HTTP запросов

## Описание:

Разработать Spring Boot Starter, который предоставит возможность логировать HTTP запросы в вашем приложении на базе Spring Boot.

## Требования:

Функциональность:
Ваш Spring Boot Starter должен предоставлять возможность логировать все входящие и исходящие HTTP запросы и ответы вашего приложения.
Логирование должно включать в себя метод запроса, URL, заголовки запроса и ответа, код ответа, время обработки запроса и т.д.

Реализация:
Создайте проект Maven для вашего Spring Boot Starter.
Используйте Spring Boot для автоконфигурации вашего Starter.
Реализуйте механизм перехвата и логирования HTTP запросов с помощью фильтров или интерцепторов Spring, или Spring AOP.
Обеспечьте настройку уровня логирования и формата вывода логов.

## Стек
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white "Java 17")
![Maven](https://img.shields.io/badge/Maven-green.svg?style=for-the-badge&logo=mockito&logoColor=white "Maven")
![Spring](https://img.shields.io/badge/Spring-blueviolet.svg?style=for-the-badge&logo=spring&logoColor=white "Spring")
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![GitHub](https://img.shields.io/badge/git-%23121011.svg?style=for-the-badge&logo=github&logoColor=white "Git")

* Язык: *Java 17*
* Автоматизация сборки: *Maven*
* Фреймворк: *Spring*
* База данных: *PostgreSQL*
* Контроль версий: *Git*

## Взаимодействие с REST-сервисом

- Доступен Swagger-UI во время работы приложения:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)