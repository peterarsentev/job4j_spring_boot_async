# Пример использования Spring Async TaskExecutor

Этот проект демонстрирует интеграцию Spring с механизмами пулов потоков для выполнения асинхронных задач. Основное внимание уделено настройке собственных пулов потоков для обеспечения оптимальной производительности и управления ресурсами в приложении Spring Boot.

## Особенности

- **Выполнение асинхронных задач**: Использование аннотации `@Async` для выполнения методов асинхронно.
- **Настройка собственного пула потоков**: Избегайте проблем с производительностью, явно настраивая `ThreadPoolTaskExecutor`.
- **Несколько пулов потоков**: Определение и использование нескольких пулов потоков для различных типов задач.
- **Автонастройка с помощью Spring Boot**: Упрощение настройки пулов потоков с использованием `application.properties`.

## Начало работы

1. **Включение поддержки асинхронности**: Используйте аннотацию `@EnableAsync` в вашем основном классе приложения.
2. **Настройка пулов потоков**: Определите бины `ThreadPoolTaskExecutor` или используйте `application.properties` для автонастройки.
3. **Отметка методов аннотацией `@Async`**: Укажите, какие методы должны выполняться асинхронно, и при необходимости задайте имя пула потоков.

## Пример настройки

```java
@EnableAsync
@SpringBootApplication
public class Main {
    @Bean("customExecutor")
    public ThreadPoolTaskExecutor initCustomExecutor() {
        var pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(1);
        pool.setMaxPoolSize(5);
        pool.setQueueCapacity(10);
        return pool;
    }
}
```

**application.properties**:
```properties
spring.task.execution.pool.core-size=1
spring.task.execution.pool.max-size=5
spring.task.execution.pool.queue-capacity=10
```

## Использование

- Аннотируйте методы вашего сервиса аннотацией `@Async`.
- Укажите имя пула потоков, если у вас несколько пулов:
  ```java
  @Async("customExecutor")
  public void asyncOperation() {
      System.out.println("Выполнение асинхронной операции: " + Thread.currentThread().getName());
  }
  ```

## Лицензия

Этот проект лицензирован по лицензии MIT. Подробнее см. файл [LICENSE](LICENSE).

---

Этот README предоставляет обзор проекта, выделяя его ключевые особенности, инструкции по настройке и примеры использования. Для получения более детальной информации обратитесь к документации проекта.