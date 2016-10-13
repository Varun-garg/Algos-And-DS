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

    public static void insert(int arr[], int value, int length)
    {
        arr[length+1] = value;
        FixHeapPushUp(arr,length+1);
    }

    public static void delete(int arr[],int position, int length)
    {
        //as of now, works properly on head only

        //we will be replacing it with last element of heap
        //last element will be obviously less than parent
        //so we only need to take care of lower elements

        /*
        int nextPosition;
        for(nextPosition = position; nextPosition <= length; nextPosition *=2 );
        if(nextPosition > length ) nextPosition /= 2;
        if(nextPosition < length) nextPosition++; //right child */

        arr[position] = arr[length];
        FixHeapPushDown(arr,length-1,position);
    }

    public static void modify(int arr[],int position,int value, int length)
    {
        //we will be fixing heap both sides
        arr[position] = value;
        FixHeapPushDown(arr,length,position);
        FixHeapPushUp(arr,position);
    }

    public static void FixHeapPushDown(int arr[], int length, int top)
    //index begins at 1, so length is 1 greater than actual
    {
        int child1Position = top * 2;
        if (child1Position > length)
            return;

        int child2Position = top * 2 + 1;
        int temp;

        //FixHeapPushDown(arr, length, c1);
        if ((child2Position) > length) //only first child exists
        {
            if (arr[child1Position] > arr[top]) {
                temp = arr[child1Position];
                arr[child1Position] = arr[top];
                arr[top] = temp;
                FixHeapPushDown(arr, length, child1Position);
            }
            return;
        }
        //FixHeapPushDown(arr, length, c2);// second child is also there

        if (arr[child1Position] > arr[top] && arr[child1Position] > arr[child2Position]) {
            temp = arr[child1Position];
            arr[child1Position] = arr[top];
            arr[top] = temp;
            FixHeapPushDown(arr, length, child1Position);
        } else if (arr[child2Position] > arr[top]) {
            temp = arr[child2Position];
            arr[child2Position] = arr[top];
            arr[top] = temp;
            FixHeapPushDown(arr, length, child2Position);
        }
    }

    public static void FixHeapPushUp(int arr[], int currentPosition)
    {
        //index begins at 1, so length is 1 greater than actual
        int parentPosition = currentPosition / 2;
        if(parentPosition < 1)
            return;

        if(arr[currentPosition] > arr[parentPosition])
        {
            int temp;
            temp = arr[currentPosition];
            arr[currentPosition] = arr[parentPosition];
            arr[parentPosition] = temp;
            FixHeapPushUp(arr,parentPosition);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.print("Enter no of elements\t:");
        int n,value,pos;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int heap[] = new int[n + 10 + 1]; // 10 extra spaces + 1 for index at 1
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter " + i + "th element\t:");
            heap[i] = Integer.parseInt(br.readLine());
        }

        BuildHeapBottomUp(heap, n);
        System.out.println("Built heap is " + Arrays.toString(heap));

        System.out.println("Enter value you would like to insert");
        value = Integer.parseInt(br.readLine());
        insert(heap,value,n);
        n++;
        System.out.println("Heap after insertion is " + Arrays.toString(heap));


        System.out.println("Enter index you would like to delete (from 1) ");
        pos = Integer.parseInt(br.readLine());
        delete(heap,pos,n);
        heap[n] = 0;
        n--;
        System.out.println("Heap after deletion is " + Arrays.toString(heap));

        System.out.println("Enter index you would like to modify");
        pos = Integer.parseInt(br.readLine());
        System.out.println("Enter new value ");
        value = Integer.parseInt(br.readLine());
        modify(heap,pos,value,n);
        System.out.println("Heap after modification is " + Arrays.toString(heap));

        HeapSort(heap,n);
        System.out.println("Sorted heap is " + Arrays.toString(heap));

    }
}
