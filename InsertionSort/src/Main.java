import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void InsertionSort(int arr[], int length)
    {
        int i,j,temp,k;
        for(i = 1; i < length; i ++)
        {
            for(j = 0; j < i; j ++)
            {
                if(arr[j] > arr[i])
                    break;
            }
            temp = arr[i];
            k = i;
            while (k > j)
            {
                arr[k] = arr[k-1];
                k--;
            }
            arr[k] = temp;
        }
    }


    public static void main(String[] args) throws IOException{

        int arr[]= new int[20];
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i<10;i++)
        {
            arr[i] = Integer.parseInt(bufferedReader.readLine());
        }

        InsertionSort(arr,10);
        System.out.println("Sorted array is " + Arrays.toString(arr));

    }
}
