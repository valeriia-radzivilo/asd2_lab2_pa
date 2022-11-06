package ArrWork;

import ArrWork.*;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.*;

public class Searches {

    public static void fill_possible_options(ArrayList<Integer> pos, int n, int column, int[] already_placed, int forbidden_possible)
    {
        for(int k=0; k<n;k++)
            if(k!=column && k+1!=column && k-1!=column && !(ArrWork.check_in_int_arr(already_placed,k)) && k!=forbidden_possible)
                pos.add(k);

    }

    public static void fill_graph(Graph gr, int n, int [] initial_task) {
        int[] solution = new int[n];
        List<Integer> initials = ArrWork.array_to_list(initial_task);
        int queen_number = 0;
        int[] already_placed = new int[n];
        int graph_count = 0;
        int counter_check = 0;
        System.out.println("Add initial: ");
        for (int k = 0; k < n; k++) {
            gr.addEdge(0, initials.get(k));
        }
        graph_count++;
        LinkedList<Integer> clone = gr.get_adj(0);
        int[] clone_arr = ArrWork.linked_to_array(clone);
        int[] clone_arr_copy = clone_arr.clone();
        int[] should_place = clone_arr.clone();
        System.out.println("Stage 1: ");
        for (int j = 0; j < n; j++) {
            should_place = clone_arr.clone();
            clone_arr = clone_arr_copy.clone();
            queen_number = 0;
            go_through_graph(gr, n, queen_number, graph_count, should_place, 11);
            if (gr.get_adj(graph_count).size() > 7 && gr.get_adj(graph_count).getLast() != 11) {
                System.out.println("CONGRATS");
                gr.get_adj(graph_count).add(9);
                j = n;

            } else {
                clone_arr = clone_arr_copy.clone();
                if (j > 0) {
                    if (clone_arr[j - 1] > 6)
                        clone_arr[j - 1] = 0;
                    else
                        clone_arr[j - 1] += 1;
                }
                graph_count++;
                counter_check++;
            }

        }
        clone_arr = ArrWork.linked_to_array(clone);
        clone_arr_copy = clone_arr.clone();
        should_place = clone_arr.clone();

        if (counter_check == n) {
            counter_check = 0;
            System.out.println("Stage 2: ");
            for (int k = 0; k < n / 2; k++) {
                for (int j = 0; j < n; j++) {
                    queen_number = 0;
                    if (clone_arr[k] > 6)
                        clone_arr[k] = 0;
                    else
                        clone_arr[k] += 1;
                    go_through_graph(gr, n, queen_number, graph_count, clone_arr, 11);
                    if (gr.get_adj(graph_count).size() > 7 && gr.get_adj(graph_count).getLast() != 11) {
                        System.out.println("CONGRATS");
                        j = n;
                        k = n;
                        gr.get_adj(graph_count).add(9);
                        break;

                    }
                    graph_count++;
                }
                counter_check++;
            }
        }
            if (counter_check == n/2) {
                counter_check = 0;
                System.out.println("Stage 3: ");
                for (int j = 1; j < n; j++) {
                    for (int i = 0; i < n; i++) {
                        if (initial_task[i] + j < 8)
                            initial_task[i] += j;
                        else if (initial_task[i] - j >= 0)
                            initial_task[i] -= j;
                        else
                            initial_task[i] = 0;
                    }
                    queen_number = 0;
                    go_through_graph(gr, n, queen_number, graph_count, initial_task, 11);

                    if (gr.get_adj(graph_count).size() > 7 && gr.get_adj(graph_count).getLast() != 11) {
                        System.out.println("CONGRATS");
                        j = n;
                        gr.get_adj(graph_count).add(9);
                        break;
                    }
                    graph_count++;
                    counter_check++;
                }
            }
                if (counter_check == n-1) {
                    System.out.println("Stage 4: ");
                    for (int j = 0; j < initials.size(); j++) {
                        for (int i = 0; i < n; i++) {
                            initial_task[i] = i+j;
                            if (initial_task[i]>n-1)
                                initial_task[i] = i-j+1;
                        }
                        queen_number = 0;
                        go_through_graph(gr, n, queen_number, graph_count, initial_task, 11);

                        if (gr.get_adj(graph_count).size() > 7 && gr.get_adj(graph_count).getLast() != 11) {
                            System.out.println("CONGRATS");
                            gr.get_adj(graph_count).add(9);
                            j=n;
                            break;

                        }
                        graph_count++;
                    }
                }
            }





    private static void go_through_graph(Graph gr, int n, int queen_number, int graph_count, int[] clone_arr, int forbidden) {
        int[] already_placed;
        already_placed = new int[n];
        for (int i = 0; i < n; i++) {
            insert_in_graph(gr, clone_arr[i], queen_number, already_placed, graph_count, n, forbidden);
            queen_number++;
            already_placed = ArrWork.linked_to_array(gr.get_adj(graph_count));

            if (gr.get_adj(graph_count).getLast() == 11)
                i = n;

        }
        gr.print_graph();
    }


    static void insert_in_graph(Graph gr, int insert_value, int queen_number, int[]already_placed,int variant_start, int n, int forbidden) {
        ArrayList<Integer> possible_options = new ArrayList<>();
        if (ArrWork.place(queen_number, insert_value, already_placed)) {
            gr.addEdge(variant_start, insert_value);

            already_placed = ArrWork.linked_to_array(gr.get_adj(variant_start));
        } else {
            fill_possible_options(possible_options, n, already_placed[already_placed.length - 1], already_placed,forbidden);


//                    if(possible_options.size()+queen_number<n-3)
//                        gr.addEdge(variant_start,11);
            if (possible_options.size()>0&&ArrWork.place(queen_number, possible_options.get(0), already_placed)) {
                gr.addEdge(variant_start, possible_options.get(0));
            } else  {
                while(possible_options.size()>0&&!(ArrWork.place(queen_number, possible_options.get(0), already_placed)))
                {
                    possible_options.remove(0);
                    if (possible_options.size()>0&&ArrWork.place(queen_number, possible_options.get(0), already_placed)) {
                        gr.addEdge(variant_start, possible_options.get(0));
                        break;
                    }
                }
                if(possible_options.size()==0)
                    gr.addEdge(variant_start, 11);


            }

        }
    }

    public static int[] find_result(Graph gr, int n) {
        LinkedList<Integer> solution = gr.BFS(n + 1);
        if (solution != null) {
            Object[] result = solution.toArray();
            int[] result_to_print = new int[n];
            for (int i = 0; i < n; i++)
                result_to_print[i] = (int) result[i];
            return result_to_print;
        }
        return null;
    }



}
