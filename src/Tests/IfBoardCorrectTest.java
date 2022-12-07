package Tests;

import ArrWork.ArrWork;
import ArrWork.make_n_queens;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class IfBoardCorrectTest {
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


}
