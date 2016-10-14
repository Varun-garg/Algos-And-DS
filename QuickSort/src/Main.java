import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void qsort(int arr[], int begIndex, int endIndex) {
        if (begIndex == endIndex)
            return;
        int pivot = arr[begIndex];
        int i = begIndex, j = endIndex + 1, temp;
        while (true) {
            do {
                i++;
            } while (i < endIndex && arr[i] < pivot);

            do {
                j--;
            } while (j > begIndex && arr[j] > pivot);


            if (i >= j)
                break;

            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        i--;
        temp = arr[begIndex];
        arr[begIndex] = arr[i];
        arr[i] = temp;

        qsort(arr, begIndex, i);
        qsort(arr, i + 1, endIndex);
    }


    public static void main(String[] args) throws IOException {
        int arr[] = new int[20];
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            arr[i] = Integer.parseInt(bufferedReader.readLine());
        }
        qsort(arr, 0, 9);
        System.out.println("Sorted array is " + Arrays.toString(arr));

    }
}
