import ArrWork.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Main {
       public static void main(String args[]) throws IOException {
           int n =8;
           int [] not_rand_arr = new int[]{3,6,2,3,0,5,6,7};

           int [] initial_placement = ArrWork.make_rand_int_arr(n);
           System.out.println("Initial task: ");
           ArrWork.create_matr(initial_placement,n);

           Scanner myObj = new Scanner(System.in);
           System.out.println("Write nuber of type BFS(0)/RBFS(1): ");
           int method = myObj.nextInt();
           while (method!=0 && method!=1){
               System.out.println("YOU MUST WRITE 0 OR 1, NOTHING ELSE!");
               System.out.println("Write nuber of type BFS(0)/RBFS(1): ");
               method = myObj.nextInt();
           }
           if(method == 0) {
               int[] result = make_n_queens.make_n_queens(n, false, initial_placement, method);

               System.out.println("Result BFS: ");
               ArrWork.create_matr(result, n);
               System.out.println("F2 for BFS: " + F2.F2(result, 8));
           }
           else  if (method==1){
               int[] result = make_n_queens.make_n_queens(n, false, initial_placement, 1);

               System.out.println("Result RBFS: ");
               ArrWork.create_matr(result, n);
               System.out.println("F2 for RBFS: " + F2.F2(result, 8));
           }

           System.out.println("Do you want to see 2 solution?('yes'/'no'): ");
           myObj =new Scanner(System.in);
           String answer = myObj.nextLine();
           if(Objects.equals(answer, "yes"))
           {
               method = Math.abs(method -1);
               System.out.println(method);
               if(method == 0) {
                   int[] result = make_n_queens.make_n_queens(n, false, initial_placement, method);

                   System.out.println("Result BFS: ");
                   ArrWork.create_matr(result, n);
                   System.out.println("F2 for BFS: " + F2.F2(result, 8));
               }
               else  if (method==1){
                   int[] result = make_n_queens.make_n_queens(n, false, initial_placement, 1);

                   System.out.println("Result RBFS: ");
                   ArrWork.create_matr(result, n);
                   System.out.println("F2 for RBFS: " + F2.F2(result, 8));
               }
           }

       }




       @Test
        void ifBoardCorrect() throws IOException {
           int n=8;
           int []expected = {2,5,7,0,3,6,4,1};

           int[] result_bfs = make_n_queens.make_n_queens(n,false, new int[]{2,5,7,0,3,6,4,1},0);
           int[] result_rbfs = make_n_queens.make_n_queens(n,false, new int[]{2,5,7,0,3,6,4,1},1);

           assertArrayEquals(expected,result_bfs ,"Test Check for BFS");
           System.out.println("Expected: ");
           ArrWork.create_matr(expected,n);
           System.out.println("Result: ");
           ArrWork.create_matr(result_bfs,n);

           assertArrayEquals(expected,result_rbfs ,"Test Check for RBFS");
           System.out.println("Expected: ");
           ArrWork.create_matr(expected,n);
           System.out.println("Result: ");
           ArrWork.create_matr(result_rbfs,n);
       }

       @Test
        void testValueFromMethodBFS() throws IOException {
           int n=8;
           int []expected = {4,6,1,5,2,0,3,7};
           int[] result_bfs = make_n_queens.make_n_queens(n,false, new int[]{3,6,2,3,0,5,6,7},0);

           assertArrayEquals(expected,result_bfs ,"Test Check for BFS");
           System.out.println("Expected: ");
           ArrWork.create_matr(expected,n);
           System.out.println("Result: ");
           ArrWork.create_matr(result_bfs,n);

       }

    @Test
    void testValueFromMethodRBFS() throws IOException {
        int n=8;
        int[] expected_for_rbfs = {1, 4, 6, 0, 2, 7, 5, 3};
        int[] result_rbfs = make_n_queens.make_n_queens(n,false, new int[]{3,6,2,3,0,5,6,7},1);

        assertArrayEquals(expected_for_rbfs,result_rbfs ,"Test Check for RBFS");
        System.out.println("Expected: ");
        ArrWork.create_matr(expected_for_rbfs,n);
        System.out.println("Result: ");
        ArrWork.create_matr(result_rbfs,n);
    }





}
