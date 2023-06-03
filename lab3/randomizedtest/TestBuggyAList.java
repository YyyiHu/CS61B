package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE

    @Test
    /**
     * Test an AList including three addLast and three removeLast calls.
     */
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> noResizingList = new AListNoResizing<>();
        BuggyAList<Integer> buggyList = new BuggyAList<>();
        noResizingList.addLast(4);
        buggyList.addLast(4);
        noResizingList.addLast(5);
        buggyList.addLast(5);
        noResizingList.addLast(6);
        buggyList.addLast(6);
        assertEquals(noResizingList.size(), buggyList.size());
        assertEquals(noResizingList.removeLast(), buggyList.removeLast());
        assertEquals(noResizingList.removeLast(), buggyList.removeLast());
        assertEquals(noResizingList.removeLast(), buggyList.removeLast());
    }

    @Test
    /**
     * Test an AList including random calls to addLast and size
     */
    public void randomizedTest() {
        AListNoResizing<Integer> noResizingList = new AListNoResizing<>();
        BuggyAList<Integer> buggyList = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            int lastNoResizing = 0;
            int lastBuggy = 0;
            int removeNoResizing = 0;
            int removeBuggy = 0;
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                noResizingList.addLast(randVal);
                buggyList.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                assertEquals(noResizingList.size(), buggyList.size());
            } else if (operationNumber == 2) {
                // getLast
                if (noResizingList.size() != 0) {
                    lastNoResizing = noResizingList.getLast();
                }
                if (buggyList.size() != 0) {
                    lastBuggy = buggyList.getLast();
                }
                assertEquals(lastNoResizing, lastBuggy);

            } else if (operationNumber == 3) {
                // removeLast
                if (noResizingList.size() != 0) {
                    removeNoResizing = noResizingList.removeLast();
                }
                if (buggyList.size() != 0) {
                    removeBuggy = buggyList.removeLast();
                }
                assertEquals(removeNoResizing, removeBuggy);
            }
        }
    }
}
