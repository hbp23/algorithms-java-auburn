import java.util.ArrayList;
import java.util.List;

public class GreedyActivitySelector {

    /*
     * problem to solve
     * i  | 1 2 3 4 5 6  7  8  9 10 11
     * si | 1 3 0 5 3 5  6  8  8  2 12
     * fi | 4 5 6 7 9 9 10 11 12 14 16
     */

    // start times array
    private static final int[] s = {0, 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
    // finish times array
    private static final int[] f = {0, 4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};
    // array size is 11

    /*
     * GreedyActivitySelector(n)
     * n = number of activities in the original problem
     */
    public static List<Integer> greedyActivitySelector(int n) {
        List<Integer> solutionSet = new ArrayList<>();
        int k = 0; // index of last selected activity
        for (int m = 1; m <= n; m++) {
            if (s[m] >= f[k]) { // checks if compatible
                solutionSet.add(m); // adds index of selected activity
                k = m; // updates the last selected activity
            }
        }
        return solutionSet;
    }

    public static void main(String[] args) {
        List<Integer> solutionSet = greedyActivitySelector(s.length - 1);
        System.out.println("maximum-size set of mutually compatible activities:");
        System.out.println(solutionSet.toString());
    }
}
