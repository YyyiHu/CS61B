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


        // Create addLast time table
        AList<Double> times = new AList<>();
        // Create Timing table
        AList<Integer> Ns = new AList<>();
        for(int number = 1000; number <= 128000; number = 2 * number) {
            Ns.addLast(number);
        }
        // Create ops table
        AList<Integer> opCounts = Ns;

        for (int i = 0; i < Ns.size(); i++) {
            // Create an empty AList
            AList<Integer> lst = new AList<>();

            // Using addLast method to create a AList, use a timer to calculate the total time
            // for lst to reach a certain size
            Stopwatch sw = new Stopwatch();
            for(lst.size(); lst.size() < Ns.get(i); lst.addLast(1));
            double time = sw.elapsedTime();
            times.addLast(time);
        }
        printTimingTable(Ns, times, opCounts);
    }

}
