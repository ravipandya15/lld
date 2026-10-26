# Virtual Thread

- Virtual Threads are a Java feature designed to make it much easier to build applications that handle many concurrent tasks, especially tasks that spend a lot of time waiting for I/O.
- Introduced in Java 21

## First: Platform Thread

Traditionally, when you create a Java thread:

```
Thread thread = new Thread(() -> {
System.out.println("Hello");
});

thread.start();
```

- Java creates a platform thread, which is closely associated with an OS thread.

Conceptually:

````
Java Thread
     ↓
   OS Thread
     ↓
    CPU
````

OS threads are relatively expensive.
## Virtual Thread

A virtual thread is a Java-managed thread that is much cheaper to create.

````
Thread.startVirtualThread(() -> {
    System.out.println("Hello");
});
````

## Better way: Virtual Thread Executor

````
try (ExecutorService executor =
Executors.newVirtualThreadPerTaskExecutor()) {

    for (int i = 0; i < 10_000; i++) {

        executor.submit(() -> {
            callExternalAPI();
        });
    }
}
````

## Virtual thread vs Platform thread
````
|                              | Platform Thread     | Virtual Thread                         |
| ---------------------------- | ------------------- | -------------------------------------- |
| Managed mainly by            | OS/JVM              | JVM                                    |
| Creation cost                | Higher              | Very low                               |
| Memory usage                 | Higher              | Much lower                             |
| Can create huge numbers      | ❌                   | ✅                                      |
| Good for I/O                 | ✅                   | ⭐ Excellent                            |
| Good for blocking operations | Limited scalability | ✅                                      |
| CPU-heavy work               | ✅                   | Doesn't magically improve CPU capacity |
| Java 21                      | ✅                   | ✅                                      |


Platform Thread:

Task → OS Thread
       expensive
       ↓
      wait
       ↓
   resource tied up


Virtual Thread:

Task → Virtual Thread
             ↓
           wait
             ↓
          parked
             ↓
Carrier Thread runs another task
````

## Spring Boot example

This is especially relevant to the Spring Boot applications you've been working with.

In modern Spring Boot, you can enable virtual threads with:

````
spring:
    threads:
        virtual:
            enabled: true
````