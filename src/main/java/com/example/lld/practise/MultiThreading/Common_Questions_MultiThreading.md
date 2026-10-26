*** Scenarios

** How to debug/test concurrency in production application?

- Use MDC -> Mapped Diagnostic Context
- Also configure the log format in application.properties.
  - logging.pattern.console=%d{HH:mm:ss} [%thread] %-5level orderId=%X{orderId} - %msg%n

  - 21:10:01 [http-nio-8080-exec-1] INFO  orderId=ORD-101 - Starting order processing
  - 21:10:01 [http-nio-8080-exec-1] INFO  orderId=ORD-101 - Validating order
  - 21:10:02 [http-nio-8080-exec-1] INFO  orderId=ORD-101 - Order processed successfully
- MDC will capture requestId, traceId and ThreadNumber, ThreadName as well
- Use Thread dump - jcmd <PID> Thread.print
- Java flight recorder - tool
- JFR Record runtime activity and investigate thread contention, monitor waits, CPU usage, and garbage collection. Useful for diagnosing issues in a running service, including production when used carefully.