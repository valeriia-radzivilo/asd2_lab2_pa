import ArrWork.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Main {
       public static void main(String args[]) {
           int n =8;
            int [] result = make_n_queens.make_n_queens(n,true, new int[]{});
           System.out.println("Result: ");
           ArrWork.create_matr(result,n);
       }


       @Test
        void testValueFromMethod()
       {
           int []expected = {3,6,4,1,5,0,2,7};
           assertArrayEquals(expected, make_n_queens.make_n_queens(8,false, new int[]{3,6,2,3,0,5,6,7}),"Test Check");
       }

}
