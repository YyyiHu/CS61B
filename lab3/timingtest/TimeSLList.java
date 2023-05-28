package timingtest;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE

        // Create the first column N
        AList<Integer> N = new AList<>();

        // Create the times column
        AList<Double> times = new AList<>();

        // Crete the ops column
        AList<Integer> ops = new AList<>();

        for (int start = 1000; start <= 128000; start *= 2) {

            // Create a SLList
            SLList<Integer> lst = new SLList<>();

            // Add N items to the SLList
            while (lst.size() < start) {
                lst.addLast(1);
            }
            // Add the size of SLList to the first column N
            N.addLast(lst.size());

            int M = 10000;

            // Start the timer
            Stopwatch sw = new Stopwatch();

            // Perform M getLast operations
            for (int i = 0; i < M; i++) {
                lst.getLast();
            }

            // Check the timer
            double time = sw.elapsedTime();

            // Add time to the times column
            times.addLast(time);

            // Add the number of calls to the ops column
            ops.addLast(M);
        }
        printTimingTable(N, times, ops);
    }

}
