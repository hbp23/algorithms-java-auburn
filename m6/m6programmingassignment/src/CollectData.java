
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class CollectData {

    // start times array
    private int[] s;
    // finish times array
    private int[] f;
    // array size is 11

    private boolean[] setA;


    public void recursiveActivitySelector(int k, int n) {
        int m = k + 1; // iterates the activity index
        while (m <= n && s[m] < f[k]) { m++;} // checks if current activity is compatible else iterates activity
        if (m <= n) { // if still in activities, add to optimal solution set
            setA[m] = true;
            recursiveActivitySelector(m, n);
        }
    }

    /*
     * GreedyActivitySelector(n)
     * n = number of activities in the original problem
     */
    public void greedyActivitySelector(int n) {
        List<Integer> solutionSet = new ArrayList<>();
        int k = 0; // index of last selected activity
        for (int m = 1; m <= n; m++) {
            if (s[m] >= f[k]) { // checks if compatible
                setA[m] = true;
                k = m; // updates the last selected activity
            }
        }
    }

    public void initializeArrays(int n) {
        s[0] = 0;
        f[0] = 0;
        for (int i = 1; i <= n - 1; i++) {
            if ((i & 1) == 0) {
                s[i] = f[i - 2];
                f[i] = s[i] + 2;
            } else {
                s[i] = f[i - 1] - 1;
                f[i] = f[i - 1] + 1;
            }
        }
    }

    public void studyOverhead(int numberPoints) {
        // UPDATE CSV FILE PATH
        File csv = new File("/Users/harshpatel/Desktop/AUBURN_UNIVERSITY/CPSC_3280_Fall_2024/m6/m6programmingassignment/Filef.csv");
        try(PrintWriter writer = new PrintWriter(csv)) {
            writer.write("i,M[i]\n");
            s = new int[numberPoints];
            f = new int[numberPoints];
            initializeArrays(numberPoints);

            for (int i = 1; i <= numberPoints; i++) {

                double timeRecursive = 0;
                double timeIterative = 0;
                int numberRuns = 100;

                for (int j = 1; j <= numberRuns; j++) {
                    setA = new boolean[i + 1];
                    double rStart = System.nanoTime();
                    recursiveActivitySelector(0, i - 1);
                    timeRecursive += System.nanoTime() - rStart;
                    double iStart = System.nanoTime();
                    greedyActivitySelector(i - 1);
                    timeIterative += System.nanoTime() - iStart;
                }

                double M = timeIterative / timeRecursive;


                writer.write(i + "," + M + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("CSV File not found");
        }
    }

    public static void main(String[] args) {
        CollectData collectData = new CollectData();
        collectData.studyOverhead(100000);
    }

}
