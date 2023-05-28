package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE

        // Create an AList to store the addLast time data
        AList<Double> times = new AList<>();
        // Create the Timing table
        AList<Integer> Ns = new AList<>();
        int start = 1000;
        int end = 128000;
        while (start <= end) {
            Ns.addLast(start);
            start *= 2;
        }
        // Create ops table
        AList<Integer> opCounts = Ns;

        for (int i = 0; i < Ns.size(); i++) {
            // Create an empty AList
            AList<Integer> lst = new AList<>();

            // Using addLast method to create a AList, use a timer to calculate the total time
            // for lst to reach a certain size
            Stopwatch sw = new Stopwatch();
            while (lst.size() < Ns.get(i)) {
                lst.addLast(1);
            }
            double time = sw.elapsedTime();
            times.addLast(time);
        }
        printTimingTable(Ns, times, opCounts);
    }

}
