package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;
import java.util.LinkedList;

public class TestArrayDequeEC {

    StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<>();
    ArrayDequeSolution<Integer> solutionDeque = new ArrayDequeSolution<>();
    LinkedList<String> linkedList = new LinkedList<>();

    private String printMessage(LinkedList linkedList) {
        StringBuilder stringBuilder = new StringBuilder();
        if (linkedList.size() > 0) {
            for (int i = 0; i < linkedList.size() - 1; i++) {
                stringBuilder.append(linkedList.get(i).toString() + "\n");
            }
            stringBuilder.append(linkedList.get(linkedList.size() - 1).toString());
        }
        return stringBuilder.toString();
    }
    @Test
    /**
     * Test an array deque including randoms calls to add and remove methods.
     */
    public void randomizedTest1() {

        int N = 10000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(4);
            if (operationNumber == 0) {
                // addFirst
                int randVal = StdRandom.uniform(100);
                studentDeque.addFirst(randVal);
                solutionDeque.addFirst(randVal);
                linkedList.addLast("addFirst(" + randVal + ")");
            } else if (operationNumber == 1) {
                // addLast
                int randVal = StdRandom.uniform(100);
                studentDeque.addLast(randVal);
                solutionDeque.addLast(randVal);
                linkedList.addLast("addLast(" + randVal + ")");
            } else if (operationNumber == 2) {
                // removeFirst
                int randVal = StdRandom.uniform(100);
                if (!studentDeque.isEmpty() && !solutionDeque.isEmpty()) {
                    Integer removeFirstStudent = studentDeque.removeFirst();
                    Integer removeFirstSolution = solutionDeque.removeFirst();
                    linkedList.addLast("removeFirst()");
                    assertEquals(printMessage(linkedList), removeFirstSolution, removeFirstStudent);
                }
            } else if (operationNumber == 3) {
                // removeLast
                int randVal = StdRandom.uniform(100);
                if (!studentDeque.isEmpty() && !solutionDeque.isEmpty()) {
                    Integer removeLastStudent = studentDeque.removeLast();
                    Integer removeLastSolution = solutionDeque.removeLast();
                    linkedList.addLast("removeLast()");
                    assertEquals(printMessage(linkedList), removeLastSolution, removeLastStudent);
                }
            }
        }
    }
}

