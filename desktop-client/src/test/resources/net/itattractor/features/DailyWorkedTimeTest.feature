#language: ru
Функционал: проверка итога рабочего времени затраченного пользователем за день на странице ежедневного отчета

  Предыстория:
    Допустим я создал и принял задачу на себя
    И через клиент сделал рабочую запись "Task1" в "14.09.2013 15:29:00"
    И работал над ней до "14.09.2013 15:39:00" с активностью мыши "21" и клавиатуры "30"
    И переключился на задачу "Task2" в "14.09.2013 15:45:00"
    И работал над ней до "14.09.2013 15:55:00" с активностью мыши "15" и клавиатуры "36"

  Сценарий: : Пользователь видит сумму затраченного времени на основе двух скриншотов на странице ежедневного отчета
    Допустим открываю главную страницу тракера
    И перехожу во вкладку "Tracker"
    И открываю отчет с ссылкой "Ежедневный отчет" за "09/14/2013" пользователя "tester"
    Если вижу скриншот с количеством кликаний мышью "21" и нажатий клавиатуры "30" раз
    И вижу скриншот с количеством кликаний мышью "15" и нажатий клавиатуры "36" раз
    То в окне "Total Time" вижу затраченное время в часах "0" в минутах "20"
