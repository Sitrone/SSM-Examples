package com.sununiq.snippet.jianzhioffer;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 两个栈实现一个队列
 * 两个队列实现一个栈
 */
public class Question7 {
    public static void main(String[] args) {
//        testSQueue();

        testQStack();
    }

    private static void testQStack() {
        QStack<Integer> stack = new QStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(stack.pop());
        }
    }

    private static void testSQueue() {
        SQueue<Integer> queue = new SQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(queue.dequeue());
        }
    }

    static class SQueue<T> {
        private Stack<T> stack1 = new Stack<>();
        private Stack<T> stack2 = new Stack<>();

        private int size;

        public void enqueue(T t) {
            stack1.push(t);
            size++;
        }

        public T dequeue() {
            if(stack2.isEmpty() && stack1.isEmpty()) {
                throw new IllegalStateException("Queue is empty, cannot dequeue anymore.");
            }


            if(stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }

            T value = stack2.pop();
            size--;
            return value;
        }

        private boolean isEmpty() {
            return size == 0;
        }
    }


    /**
     * 另一个版本可以参考
     * ref:https://stackoverflow.com/questions/688276/implement-stack-using-two-queues
     * @param <T>
     */
    static class QStack<T> {
        private LinkedList<T> queue1 = new LinkedList<>();
        private LinkedList<T> queue2 = new LinkedList<>();
        private int size;

        private boolean isEmpty() {
            return size == 0;
        }

        public T pop() {
            if(queue2.isEmpty() && queue1.isEmpty()) {
                throw new IllegalStateException("Stack is empty, cannot pop element anymore.");
            }
            if(queue1.isEmpty()) {
                while (queue2.size() != 1) {
                    queue1.offer(queue2.poll());
                }

                return queue2.pop();
            } else {
                while (queue1.size() != 1) {
                    queue2.offer(queue1.pop());
                }
                return queue1.pop();
            }
        }

        public void push(T t) {
            if(!queue1.isEmpty()) {
                queue1.offer(t);
            } else {
                queue2.offer(t);
            }
            size++;
        }
    }

}
