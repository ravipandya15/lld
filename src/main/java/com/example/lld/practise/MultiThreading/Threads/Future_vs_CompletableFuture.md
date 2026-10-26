# Future vs CompletableFuture

- "Future represents the result of an asynchronous computation and allows us to retrieve, check, or cancel that computation, but it has limited support for asynchronous composition. CompletableFuture, introduced in Java 8, extends Future and provides non-blocking callbacks, chaining, combining multiple asynchronous operations, and better exception handling through APIs such as thenApply, thenCompose, thenCombine, and exceptionally."

One important nuance: CompletableFuture is not automatically non-blocking just because it exists. Calling get() or join() still blocks; the non-blocking behavior comes from using its completion-stage methods rather than waiting synchronously.

````
ExecutorService executor = Executors.newFixedThreadPool(2);

Future<Integer> future = executor.submit(() -> {
    Thread.sleep(2000);
    return 10;
});

System.out.println("Doing other work...");

Integer result = future.get();  // BLOCKS until result is available
System.out.println(result);
````

```
Other methods

future.get();       // wait for result
future.isDone();    // check completion
future.cancel(true);
````

## CompletableFuture

- It extends Future and CompletionStage.

````
CompletableFuture<Integer> future =
        CompletableFuture.supplyAsync(() -> {
            return 10;
        });

future.thenApply(result -> result * 2)
      .thenAccept(result -> System.out.println(result));
      
Flow

Async computation
      ↓
     10
      ↓
 result * 2
      ↓
     20
      ↓
 print  
````

````

| Feature                           | `Future`  | `CompletableFuture` |
| --------------------------------- | --------- | ------------------- |
| Introduced                        | Java 5    | Java 8              |
| Async execution                   | ✅         | ✅                   |
| Get result                        | `get()`   | `get()` / `join()`  |
| Non-blocking callbacks            | ❌         | ✅                   |
| Chaining                          | ❌         | ✅                   |
| Combine multiple async operations | Difficult | ✅                   |
| Exception handling                | Limited   | ✅                   |
| Manually complete                 | ❌         | ✅                   |
| Cancellation                      | ✅         | ✅                   |

````