import ArrWork.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Main {
       public static void main(String args[]) {
           int n =8;
            int [] result = make_n_queens.make_n_queens(n,true , new int[]{6, 4, 5, 7, 7, 3, 7, 5});
           System.out.println("Result: ");
           ArrWork.create_matr(result,n);
       }


       @Test
        void testValueFromMethod()
       {
           int n=8;
           int []expected = {3,6,4,1,5,0,2,7};

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
