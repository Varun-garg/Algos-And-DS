import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void BuildHeapBottomUp(int arr[], int length)
    //index begins at 1, so length is 1 greater than actual
    {
        for (int i = length; i >= 1; i--)
            FixHeapPushDown(arr, length, i);
    }

    public static void HeapSort(int arr[], int length)
    // index begins at 1 (top) so length is 1 greater than actual
    {
        if (length == 1)
            return;

        int temp;
        temp = arr[1]; //swap Heap top (which is always at 1) and last elements
        arr[1] = arr[length];
        arr[length] = temp;

        FixHeapPushDown(arr, length - 1, 1);
        HeapSort(arr, length - 1);
    }

    public static void FixHeapPushDown(int arr[], int length, int top)
    //index begins at 1, so length is 1 greater than actual
    {
        int c1 = top * 2;
        if (c1 > length)
            return;

        int c2 = top * 2 + 1;
        int temp;

        //FixHeapPushDown(arr, length, c1);
        if ((c2) > length) //only first child exists
        {
            if (arr[c1] > arr[top]) {
                temp = arr[c1];
                arr[c1] = arr[top];
                arr[top] = temp;
                FixHeapPushDown(arr, length, c1);
            }
            return;
        }

        //FixHeapPushDown(arr, length, c2);// second child is also there

        if (arr[c1] > arr[top] && arr[c1] > arr[c2]) {
            temp = arr[c1];
            arr[c1] = arr[top];
            arr[top] = temp;
            FixHeapPushDown(arr, length, c1);
        } else if (arr[c2] > arr[top]) {
            temp = arr[c2];
            arr[c2] = arr[top];
            arr[top] = temp;
            FixHeapPushDown(arr, length, c2);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.print("Enter no of elements\t:");
        int n;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int heap[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter " + i + "th element\t:");
            heap[i] = Integer.parseInt(br.readLine());
        }

        BuildHeapBottomUp(heap, n);

        System.out.println("Built heap is " + Arrays.toString(heap));
        HeapSort(heap,n);
        System.out.println("Sorted heap is " + Arrays.toString(heap));

    }
}
