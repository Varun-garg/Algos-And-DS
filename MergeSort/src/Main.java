import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void MergeSort(int arr[], int begin, int end) {
        //both inclusive
        if (begin == end)
            return;
        int mid = (begin + end) / 2;
        MergeSort(arr, begin, mid);
        MergeSort(arr, mid + 1, end);

        int tempArr[] = new int[end - begin + 1];  //maybe we can use global array for temporary array instead
        int i = begin, j = mid + 1, k = 0;
        while (k < (end - begin + 1))
            tempArr[k++] = (j > end || ((i <= mid) && arr[i] <= arr[j])) ? arr[i++] : arr[j++];

        for (i = begin, k = 0; i <= end; i++, k++)
            arr[i] = tempArr[k];
    }

    public static void main(String[] args) throws IOException {
        int arr[] = new int[20];
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            arr[i] = Integer.parseInt(bufferedReader.readLine());
        }
        MergeSort(arr, 0, 9);
        System.out.println("Sorted array is " + Arrays.toString(arr));

    }
}
