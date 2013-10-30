Файлы содержат решение для workflow обработки запроса на смену тарифного плана исходя из ряда упрощений, связанных с отсутствием документации. Обработка запроса разбита на фазы обработки с сохранением состояния и результатов промежуточной обработки в БД свзанной с сохраняемой сущностью запроса конвейра (WorkflowRequest), для тестирования служит сервлет.

Запросы поступают в JMS очередь и далее на конвейер V2WorkflowForis. Внешние интерфейсы заменены заглушками вызовов соответствующих вебсервисов и клиентов вебсервисов.


glasfish settings

http://nikcode.blogspot.ru/2012/06/jms-mdb-glassfish-20.html

http://stackoverflow.com/questions/8270772/not-able-to-connect-to-embedded-derby-database-in-glassfish-with-hibernate

start glasfish with database generation
http://stackoverflow.com/questions/3061444/how-to-automatically-drop-create-tables-with-glassfish-v3-postgresql-8-4
../../bin/asadmin redeploy --name=miniejb --force=true --dropandcreatetables=true ~/projects/billing/target/billing-1.0-SNAPSHOT.war
