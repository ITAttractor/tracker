#language: ru
Функционал: проверка функционала для полосы активности в ежедневном отчете пользователя

  Предыстория:
    Допустим я создал и принял задачу на себя
    И запустил клиент
    И выбрал последнюю задачу

  Структура сценария: Пользователь видит номер тикета и название задачи в полосе активности
    Допустим сделал рабочую запись "<work_log>" в "<start_time>"
    И активно работал над ней до "<end_time>"
    И открываю вкладку Tracker
    Если открываю отчет с ссылкой "Ежедневный отчет" за "<date>" пользователя "tester"
    То вижу на странице количество полос активности "<feeds>"
    И внутри полосы активности вижу что номер тикета равен последнему созданному тикету
    И внутри полосы активности вижу рабочую запись "<work_log>"

  Примеры:
  | work_log             | start_time          | end_time            |   date     | feeds |
  | полоса активности №1 | 01.07.2014 15:01:00 | 01.07.2014 15:11:00 | 07/01/2014 |   1   |
    Допустим сделал рабочую запись "<work_log>" в "<start_time>"
    И активно работал над ней до "<end_time>"
    И открываю вкладку Tracker
    И открываю отчет с ссылкой "Ежедневный отчет" за "<date>" пользователя "tester"
    Если в полосе активности кликаю по номеру тикета
    То вижу рабочий журнал "Work Log" на открывшейся странице кликаемого тикета
    И вижу в рабочем журнале запись "<work_log>"

  Примеры:
    | work_log             | start_time          | end_time            |   date     |
    | полоса активности №3 | 03.07.2014 15:01:00 | 03.07.2014 15:21:00 | 07/03/2014 |