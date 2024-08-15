import queue.ArrayQueue;

import java.util.NoSuchElementException;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        // ArrayQueue 인스턴스 생성
        Queue<Integer> queue = new ArrayQueue<>();

        // 큐에 요소 추가 (enqueue)
        System.out.println("Adding elements to the queue:");
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        // 큐의 현재 상태 확인
        System.out.println("Queue after adding elements: ");
        printQueue(queue);

        // 첫 번째 요소 확인 (peek)
        System.out.println("Peek: " + queue.peek()); // Should print 1

        // 큐에서 요소 제거 (dequeue)
        System.out.println("Polling elements from the queue:");
        System.out.println(queue.poll()); // Should print 1
        System.out.println(queue.poll()); // Should print 2

        // 큐의 현재 상태 확인
        System.out.println("Queue after polling elements: ");
        printQueue(queue);

        // 큐에 추가적인 요소 추가
        System.out.println("Adding more elements to the queue:");
        queue.add(6);
        queue.add(7);

        // 큐의 현재 상태 확인
        System.out.println("Queue after adding more elements: ");
        printQueue(queue);

        // 큐에서 모든 요소 제거 (clear)
        queue.clear();
        System.out.println("Queue after clearing: ");
        System.out.println("Is queue empty? " + queue.isEmpty()); // Should print true

        // 빈 큐에서 poll 및 remove 시도
        try {
            queue.remove(); // Should throw NoSuchElementException
        } catch (NoSuchElementException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        System.out.println("Polling from empty queue: " + queue.poll()); // Should print null
    }

    // 큐의 요소를 출력하는 헬퍼 메서드
    private static void printQueue(Queue<?> queue) {
        for (Object element : queue) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
