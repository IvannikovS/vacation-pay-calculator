# Vacation Pay Calculator

Vacation Pay Calculator - приложение для расчета отпускных.

### Примеры использования
#### Используйте командную строку для выполнения запроса.

Пример 1: Расчёт отпускных без указания даты начала отпуска:
```
curl "http://localhost:8080/calculate?averageSalary=60000&vacationDays=10"
```

Пример 2: Расчёт отпускных с указанием даты начала отпуска:
```
curl "http://localhost:8080/calculate?averageSalary=60000&vacationDays=10&startDate=2024-05-01"
```

Так же в проекте присутствуют тесты.

