package Tests;

import org.junit.Test;

import java.io.IOException;
import ArrWork.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ValueFromMethodTest {
    @Test
    public void testValueFromMethodBFS() throws IOException {
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
    public void testValueFromMethodRBFS() throws IOException {
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
