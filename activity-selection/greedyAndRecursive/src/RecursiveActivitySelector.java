import java.util.ArrayList;
import java.util.List;

public class RecursiveActivitySelector {

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
     * RecursiveActivitySelector(k,n)
     * k = index of the sub problem Sk to solve
     * n = number of activities in the original problem
     */
    public static List<Integer> recursiveActivitySelector(int k, int n) {
        int m = k + 1; // iterates the activity index
        while (m <= n && s[m] < f[k]) { m++;} // checks if current activity is compatible else iterates activity
        if (m <= n) { // if still in activities, add to optimal solution set
            List<Integer> solutionSet = recursiveActivitySelector(m, n);
            solutionSet.addFirst(m); // adds index of selected activity
            return solutionSet;
        } else { // if no available activity, return empty set
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        List<Integer> solutionSet = recursiveActivitySelector(0, s.length - 1);
        System.out.println("maximum-size set of mutually compatible activities:");
        System.out.println(solutionSet.toString());
    }
}
