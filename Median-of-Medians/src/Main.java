import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

//implementation using https://stackoverflow.com/questions/9489061/understanding-median-of-medians-algorithm
/**
 * Created by varun on 16/10/2016.
 */
public class Main {

    public static int select(int arr[], int k) {
        if (arr.length <= 5) {
            Arrays.sort(arr);
            if (arr.length > k)
                return arr[k];
            return arr[arr.length - 1];
        }

        System.out.println("solving for " + Arrays.toString(arr));

        int extra_row = 0;

        if ((arr.length % 5) > 0)
            extra_row = 1;

        int table[][] = new int[arr.length / 5 + extra_row][5];
        int medians[] = new int[table.length];

        for (int i = 0; i < arr.length; i++) {
            table[i / 5][i % 5] = arr[i];
        }

//        System.out.println(Arrays.deepToString(table));
//        System.out.println(table.length);
        for (int i = 0; i < table.length; i++) {
            medians[i] = select(table[i], 2);
            System.out.println("Median of " + Arrays.toString(table[i]) + " is " + medians[i]);
        }
        int medianOfMedians = select(medians, medians.length / 2);
        System.out.println("Median of medians: " + Arrays.toString(medians) + " is " + medianOfMedians);
        ArrayList<Integer> L1 = new ArrayList<>(); // < medianOfMedians
        ArrayList<Integer> L2 = new ArrayList<>();// = medianOfMedians
        ArrayList<Integer> L3 = new ArrayList<>();// > medianOfMedians

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > medianOfMedians)
                L3.add(arr[i]);
            else if (arr[i] < medianOfMedians)
                L1.add(arr[i]);
            else
                L2.add(arr[i]);
        }

        System.out.println("L1 " + L1.toString());
        System.out.println("L2 " + L2.toString());
        System.out.println("L3 " + L3.toString());

        System.out.println("were supposed to look at element at " + k);

        if (k < L1.size())
            return select(L1.stream().mapToInt(i -> i).toArray(), k);
        else if (k < (L1.size() + L2.size()))
            return medianOfMedians;
        else
            return select(L3.stream().mapToInt(i -> i).toArray(), k - L1.size() - L2.size());
    }

    public static void main(String[] args) {
        int list[] = new int[ThreadLocalRandom.current().nextInt(1, 40 + 1)];

        for (int i = 0; i < list.length; i++)
            list[i] = ThreadLocalRandom.current().nextInt(-250, 2322 + 1);

        int median = select(list, list.length / 2);
        System.out.println("Median Of Medians " + median);

        Arrays.sort(list);
        System.out.println("sorted array " + Arrays.toString(list));
        System.out.println("length of  array " + list.length);
        System.out.println("by sorting it is " + list[list.length / 2]);
    }
}
