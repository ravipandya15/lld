package com.example.lld.practise.MultiThreading.Custom_BlockingQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue implements Runnable {

    //  Normal way to create blokingQueue in java.
    //  BlockingQueue blockingQueue = new LinkedBlockingDeque(); or BlockingQueue blockingQueue = new LinkedBlockingQueue();
    // BlockingQueue is thread safe.

    //  Creates a Blocking Queue with capacity 5
    //  BlockingQueue blockingQueue = new LinkedBlockingDeque(5);
    int capacity;
    Queue<Integer> queue;
    Lock lock;
    Condition queueEmptyCondition;
    Condition queueFullCondition;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
        this.lock = new ReentrantLock();
        queueEmptyCondition = lock.newCondition();
        queueFullCondition = lock.newCondition();
    }

    public void push(int value) {
        try {
            lock.lock();
            while (queue.size() == capacity) {
                System.out.println(Thread.currentThread().getName() + ": queue is full, waiting tp push: " + value);
                queueFullCondition.await();
            }
            queue.add(value);
            System.out.println(Thread.currentThread().getName() + " : Push value to queue: " + value);
            queueEmptyCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int pop() {
        int value = -1;
        try {
            lock.lock();
            while (queue.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + ": queue is empty, waiting to pop: ");
                queueEmptyCondition.await();
            }
            value = queue.poll();
            System.out.println(Thread.currentThread().getName() + ": Pop from queue value: " + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueFullCondition.signalAll();
            lock.unlock();
        }
        return value;
    }

    @Override
    public void run() {

    }
}
