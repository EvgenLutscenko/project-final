## [REST API](http://localhost:8080/doc)

## Концепция:

- Spring Modulith
    - [Spring Modulith: достигли ли мы зрелости модульности](https://habr.com/ru/post/701984/)
    - [Introducing Spring Modulith](https://spring.io/blog/2022/10/21/introducing-spring-modulith)
    - [Spring Modulith - Reference documentation](https://docs.spring.io/spring-modulith/docs/current-SNAPSHOT/reference/html/)

```
  url: jdbc:postgresql://localhost:5432/jira
  username: jira
  password: JiraRush
```

- Есть 2 общие таблицы, на которых не fk
    - _Reference_ - справочник. Связь делаем по _code_ (по id нельзя, тк id привязано к окружению-конкретной базе)
    - _UserBelong_ - привязка юзеров с типом (owner, lead, ...) к объекту (таска, проект, спринт, ...). FK вручную будем
      проверять

## Аналоги

- https://java-source.net/open-source/issue-trackers

## Тестирование

- https://habr.com/ru/articles/259055/

## Список выполненных задач:

- 1.Разобраться со структурой проекта (onboarding).
- 2.Удалить социальные сети: vk, yandex.
- 3.Вынести чувствительную информацию в отдельный проперти файл:
  логин
  пароль БД
  идентификаторы для OAuth регистрации/авторизации
  настройки почты
- 4.Переделать тесты так, чтоб во время тестов использовалась in memory БД (H2), а не PostgreSQL. Для этого нужно
  определить 2 бина, и выборка какой из них использовать должно определяться активным профилем Spring. H2 не поддерживает
  все фичи, которые есть у PostgreSQL, поэтому тебе прийдется немного упростить скрипты с тестовыми данными.
- 6.Сделать рефакторинг метода com.javarush.jira.bugtracking.attachment.FileUtil#upload чтоб он использовал
  современный подход для работы с файловой системмой.
- 7.Добавить новый функционал: добавления тегов к задаче (REST API + реализация на сервисе).
  Фронт делать необязательно. Таблица task_tag уже создана.

  в классе: src/main/java/com/javarush/jira/bugtracking/task/TaskController.java
  создан метод:
  @PutMapping("/{id}/tags")
  public void addTags(@Valid @RequestBody TaskToTags taskToTags, @PathVariable long id){
  taskService.addTags(id, taskToTags);
  }

  так же создан метод и в TaskService
  все работает как api, запрос отправлется как json объект

- 9.Написать Dockerfile для основного сервера
- 10.Написать docker-compose файл для запуска контейнера сервера вместе с БД и nginx. Для nginx используй конфиг-файл config/nginx.conf.
  При необходимости файл конфига можно редактировать