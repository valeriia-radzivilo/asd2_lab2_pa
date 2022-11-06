package ArrWork;

public class make_n_queens {
    public static int[] make_n_queens(int n, boolean isRand, int[] notrand)
    {
        int [ ] initial_placement = new int[n];
        if(isRand)
            initial_placement = ArrWork.make_rand_int_arr(n);
        else
            initial_placement =notrand.clone();

        System.out.println("Initial task: ");
        ArrWork.create_matr(initial_placement,n);
        Graph gr = new Graph((int) Math.pow(n,n));
        Searches.fill_graph(gr,n,initial_placement);
        int[] result = Searches.find_result(gr,n);
        return result;
    }
}
