import ArrWork.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Main {
       public static void main(String args[]) {
           int n =8;
            int [] result = make_n_queens.make_n_queens(n,true , new int[]{0, 3, 2, 1, 3, 0, 1, 2});
           System.out.println("Result: ");
           ArrWork.create_matr(result,n);
       }


       @Test
        void testValueFromMethod()
       {
           int n=8;
           int []expected = {4, 7, 3, 0, 2, 5, 1, 6};

           int []result = make_n_queens.make_n_queens(n,false, new int[]{3,6,2,3,0,5,6,7});
           assertArrayEquals(expected,result ,"Test Check");
           System.out.println("Expected: ");
           ArrWork.create_matr(expected,n);
           System.out.println("Result: ");
           ArrWork.create_matr(result,n);
       }

       @Test
        void ifBoardCorrect()
       {
           int n=8;
           int []expected = {2,5,7,0,3,6,4,1};

           int []result = make_n_queens.make_n_queens(n,false, new int[]{2,5,7,0,3,6,4,1});
           assertArrayEquals(expected,result ,"Test Check");
           System.out.println("Expected: ");
           ArrWork.create_matr(expected,n);
           System.out.println("Result: ");
           ArrWork.create_matr(result,n);
       }

}
