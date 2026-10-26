package com.example.lld.practise.MultiThreading.Custom_BlockingStack;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingStack implements Runnable {

    int capacity;
    Stack<Integer> stack;
    Lock lock;
    Condition stackEmptyCondition;
    Condition stackFullCondition;

    public BlockingStack(int capacity) {
        this.capacity = capacity;
        this.stack = new Stack<>();
        this.lock = new ReentrantLock();
        stackEmptyCondition = lock.newCondition();
        stackFullCondition = lock.newCondition();
    }

    public void push(int value) {
        try {
            lock.lock();
            while (stack.size() == capacity) {
                System.out.println(Thread.currentThread().getName() + ": stack is full, waiting tp push: " + value);
                stackFullCondition.await();
            }
            stack.push(value);
            System.out.println(Thread.currentThread().getName() + ": Push value to stack: " + value);
            stackEmptyCondition.signalAll();
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
            while (stack.size() == 0) {
                System.out.println(Thread.currentThread().getName() + ": stack is empty, waiting to pop: ");
                stackEmptyCondition.await();
            }
            value = stack.pop();
            System.out.println(Thread.currentThread().getName() + ": Pop from stack value: " + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            stackFullCondition.signalAll();
            lock.unlock();
        }
        return value;
    }

    @Override
    public void run() {

    }
}
