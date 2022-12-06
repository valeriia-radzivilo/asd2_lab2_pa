package ArrWork;

import java.util.*;
import java.util.stream.IntStream;

public class ArrWork {

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

    static int check_placed_not_right(ArrayList<Integer> arr)
    {
        int checker =0;
        int[] already_placed = new int[8];
        int i =0;
        for(int a : arr)
        {
            if(place(a,i,already_placed)&&a!=0) {
                checker++;
            }
            already_placed[i] = a;
            i++;
        }
        return checker;
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

    public static ArrayList<Integer> array_to_list(int []arr)
    {
        ArrayList<Integer> answer = new ArrayList<>();
        for (int j : arr) {
            answer.add(j);
        }

        return answer;
    }



    public static int[] make_rand_int_arr (int n)
    {
        Random random = new Random();
        int[]result = new int[n];
        for(int i = 0; i <n; i++)
            result[i]=random.nextInt(n);
        return result;
    }

    public static int[] arrlist_to_arr(ArrayList<Integer>arrlist)
    {
        int[] answer = new int[arrlist.size()];
        int iter =0;
        for(int i: arrlist) {
            answer[iter] = i;
            iter++;
        }
        return answer;

    }

    public static int[] addtoArr(int amount_to_add, int arr[])
    {
        int[] new_arr = new int[arr.length+amount_to_add];
        for(int i =0; i< new_arr.length; i++)
        {
            if(i<arr.length)
                new_arr[i]=arr[i];
            else
                new_arr[i] = 1;
        }
        return new_arr;
    }

    static int amount_of_duplicates_in_arrlist(ArrayList<Integer> arr, int depth)
    {
        ArrayList<Integer> checked = new ArrayList<>();
        ArrayList<Integer>duplicates = new ArrayList<>();
        for(int i =0; i<depth;i++)
        {
            int a = arr.get(i);
            if(!checked.contains(a))
                checked.add(a);
            else
                duplicates.add(a);
        }
        return duplicates.size();
    }

}
