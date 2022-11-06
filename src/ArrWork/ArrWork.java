package ArrWork;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class ArrWork {
    public static void printArr(String[][] arr, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j]==null)
                    arr[i][j]="#";
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        System.out.println("____________________________");
    }

    public static String[][] create_matr(int[] arr, int n)
    {
        String[][] matr = new String[n][n];
        for(int i =0; i <n;i++)
        {
            for (int j = 0; j <n; j++)
            {
                if(arr[i]==j)
                    System.out.print(" Q ");
                else{
                    System.out.print(" # ");
                }

            }
            System.out.println();
        }
        return matr;
    }


    public static boolean place(int queen_number, int column_number, int[] columns_placed)
    {
        for(int j =0; j<queen_number;j++)
        {
            if(columns_placed[j]==column_number || Math.abs(columns_placed[j]-column_number)==Math.abs(j-queen_number))

            {
                return false;
            }
        }
        return true;
    }

    public static boolean check_in_arr(LinkedList<Integer> arr, int toCheckValue)
    {
        return arr.contains(toCheckValue);
    }


    public static boolean check_in_int_arr(int[] arr, int toCheckValue)
    {
        boolean test
                = IntStream.of(arr)
                .anyMatch(x -> x == toCheckValue);
        return test;
    }
    public static int[] linked_to_array(LinkedList<Integer> solution)
    {
        Object[] result = solution.toArray();
        int[] result_to_print = new int[solution.size()];
        for (int i = 0; i < solution.size(); i++)
            result_to_print[i] = (int) result[i];

        return result_to_print;
    }

    public static List<Integer> array_to_list(int []arr)
    {
        List<Integer> answer = new ArrayList<>();
        for (int j : arr) {
            answer.add(j);
        }

        return answer;
    }

    public static int find_closest_in_list(ArrayList<Integer>list, int value)
    {
        int min_dist =  value*value;
        for (Integer integer : list) {
            if (Math.abs(integer - value) < min_dist) {
                return integer;
            }
        }
        return 0;
    }

    public static int[] make_rand_int_arr (int n)
    {
        Random random = new Random();
        int[]result = new int[n];
        for(int i = 0; i <n; i++)
            result[i]=random.nextInt(n);
        return result;
    }

    public static int[] addtoArr(int n, int arr[], int x)
    {
        int i;

        // create a new array of size n+1
        int newarr[] = new int[n + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < n; i++)
            newarr[i] = arr[i];

        newarr[n] = x;

        return newarr;
    }

    public static boolean check_if_linked_are_the_same(LinkedList<Integer>a, LinkedList<Integer>b)
    {
        int counter_check =0;
        if(a.size()==b.size())
        {
            for(int i =0; i< a.size();i++)
            {
                if(a.get(i)==b.get(i))
                    counter_check++;
            }
            if(counter_check==a.size()-1)
                return true;

        }
        else
            return false;
        return false;
    }

}
