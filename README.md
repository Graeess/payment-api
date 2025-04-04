# Payment API

Приложение Spring Boot для аутентификации пользователей и обработки платежей. Этот проект демонстрирует использование JWT для безопасной аутентификации, Spring Data JPA для операций с базой данных и PostgreSQL для хранения данных.

## Модули

### 1. AuthService
Служит для аутентификации пользователей и генерации JWT-токенов.
- Конечная точка: `POST /api/auth/login`.
- Технологии: Spring Security, JWT, PostgreSQL.

### 2. PaymentService
Обрабатывает платежи и обновляет балансы пользователей.
- Конечная точка: `POST /api/payments/process`.
- Технологии: Spring Boot, Spring Data JPA, PostgreSQL.

### 3. Сущности и база данных
Определяет сущности `User` и `Payment` и управляет операциями с базой данных.
- Таблицы: `пользователи`, `платежи`.
- Технологии: Spring Data JPA, Hibernate, PostgreSQL.

## Настройка
1. Клонируйте репозиторий:
   ``bash
   git clone https://github.com/<ваш_пользователь>/payment-api.git

Stack:

Java, Spring Boot, Spring Security, Spring Data JPA
JWT (JSON Web Tokens)
PostgreSQL
Lombok
