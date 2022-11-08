package ArrWork;import java.util.*;public class Searches {    public static int fill_possible_options(ArrayList<Integer> pos, int n, int column, int[] already_placed, int queen_number) {        ArrayList<Integer>pos_before_check = new ArrayList<>();        for (int k = 0; k < n; k++) {            if (k != column && k + 1 != column && k - 1 != column && !(ArrWork.check_in_int_arr(already_placed, k))) {                        pos_before_check.add(k);            }        }        for (Integer integer : pos_before_check) {            if (ArrWork.place(queen_number, integer, already_placed))                pos.add(integer);        }        return pos.size();    }    public static void fill_graph(Graph gr, int n, int[] initial_task) {        int[] solution = new int[n];        List<Integer> initials = ArrWork.array_to_list(initial_task);        int queen_number = 0;        int[] already_placed = new int[n];        int graph_count = 0;        int counter_check = 0;        System.out.println("Add initial: ");        for (int k = 0; k < n; k++) {            gr.addEdge(0, initials.get(k));        }        gr.print_graph();        graph_count++;        if (initials.get(0)==7||initials.get(0)==0||initials.get(0)==1) {            gr.get_adj(0).removeFirst();            gr.get_adj(0).addFirst(2);        }        // Stage 1        LinkedList<Integer> clone = gr.get_adj(0);        System.out.println("Stage 1: ");        if (gr.get_adj(0).get(0) == n - 1 || gr.get_adj(0).get(0) == 0) {            gr.addEdge(1, 1);            for (int i = 1; i < n; i++)                gr.addEdge(1, gr.get_adj(0).get(i));            graph_count++;        }        int[] clone_arr = ArrWork.linked_to_array(clone);        int[] clone_arr_copy = clone_arr.clone();        int[] should_place = clone_arr.clone();        counter_check = 1;            should_place = clone_arr.clone();            clone_arr = clone_arr_copy.clone();            queen_number = 0;            int possible_amount = 0;            int possible_position =0;            graph_count = go_through_graph(gr, n, queen_number, graph_count, should_place, 11,possible_position,true );            if (gr.get_adj(graph_count).size() > n - 1 && gr.get_adj(graph_count).getLast() != 11) {                System.out.println("CONGRATS");                gr.get_adj(graph_count).add(9);                return;            }        System.out.println("ANALYZING: ");        analyze_graph(gr,graph_count,n,initial_task);    }    private static int go_through_graph(Graph gr, int n, int queen_number, int graph_count, int[] clone_arr,                                         int forbidden, int current_possibles_pos, boolean expand_with_possibles) {        int[] already_placed;        int pos_amount = 0;        already_placed = new int[n];        int additional_graphs = 0;        for (int i = 0; i < n; i++) {            already_placed = ArrWork.linked_to_array(gr.get_adj(graph_count));            pos_amount = insert_in_graph(gr, clone_arr[i], queen_number, already_placed, graph_count, n, forbidden, current_possibles_pos);            queen_number++;            already_placed = ArrWork.linked_to_array(gr.get_adj(graph_count));            if (gr.get_adj(graph_count).size() > n - 1 && gr.get_adj(graph_count).getLast() != 11) {                System.out.println("CONGRATS");                gr.get_adj(graph_count).add(9);                i = n;                return graph_count+additional_graphs;            }            int additional_pos_amount =0;            if(pos_amount>1&& gr.get_adj(graph_count).getLast() != 11&& expand_with_possibles) {                int[] new_clone = ArrWork.linked_to_array(gr.get_adj(graph_count));                if (gr.get_adj(graph_count).getLast() != 11) {                    for (int k = 1; k < pos_amount; k++) {                        int queen_number_new = 0;                        additional_graphs++;                        for (int j = 0; j < new_clone.length-1; j++) {                            gr.addEdge(graph_count+additional_graphs,new_clone[j]);                            queen_number_new++;                        }                        already_placed = ArrWork.linked_to_array(gr.get_adj(graph_count+additional_graphs));                        insert_in_graph(gr, new_clone[new_clone.length-1], queen_number_new, already_placed, graph_count + additional_graphs, n, new_clone[new_clone.length-1], k);                        if (gr.get_adj(graph_count).size() > n - 1 && gr.get_adj(graph_count).getLast() != 11) {                            System.out.println("CONGRATS");                            gr.get_adj(graph_count).add(9);                            k=pos_amount;                            return graph_count+additional_graphs;                        }                        ArrayList<Integer>add_pos = new ArrayList<>();                        already_placed = ArrWork.linked_to_array(gr.get_adj(graph_count+additional_graphs));                        additional_pos_amount = fill_possible_options(add_pos,n,already_placed[already_placed.length-1],already_placed,queen_number_new);                        gr.print_graph();                        if(additional_pos_amount>0) {                            int[] new_add_clone = ArrWork.linked_to_array(gr.get_adj(graph_count+additional_graphs));                            for (int z = 1; z < additional_pos_amount; z++) {                                int queen_add_number_new = 0;                                additional_graphs++;                                for (int j = 0; j < new_add_clone.length - 1; j++) {                                    gr.addEdge(graph_count + additional_graphs, new_add_clone[j]);                                    queen_add_number_new++;                                }                                gr.addEdge(graph_count+additional_graphs,new_add_clone[new_add_clone.length-1]);                                already_placed = ArrWork.linked_to_array(gr.get_adj(graph_count + additional_graphs));                                insert_in_graph(gr, new_add_clone[new_add_clone.length - 1], queen_add_number_new, already_placed, graph_count + additional_graphs, n, new_add_clone[new_add_clone.length - 1], z);                                if (gr.get_adj(graph_count).size() > n - 1 && gr.get_adj(graph_count).getLast() != 11) {                                    System.out.println("CONGRATS");                                    gr.get_adj(graph_count).add(9);                                    z = additional_pos_amount;                                    k=pos_amount;                                    return graph_count+additional_graphs;                                }                                already_placed = ArrWork.linked_to_array(gr.get_adj(graph_count + additional_graphs));                                gr.print_graph();                            }                        }                    }                }            }            if (gr.get_adj(graph_count).getLast() == 11)                i = n;        }        gr.print_graph();        return graph_count+additional_graphs;    }    private static void analyze_graph(Graph gr, int graph_count, int n, int[]initial_task)    {     for(int i =2; i<graph_count;i++) {            if(gr.get_adj(i).size()<n) {                int queen_number = gr.get_adj(i).size();                int []clone = ArrWork.linked_to_array(gr.get_adj(i));            for(int j =gr.get_adj(i).size()-1; j <n-1;j++) {                clone = ArrWork.addtoArr(1,clone);            }            int possible_amounts = go_through_graph(gr,n,queen_number,i,clone,11,0,false);                if (possible_amounts == 0)                    gr.addEdge(i, 11);            }             if (gr.get_adj(graph_count).size() > n - 1 && gr.get_adj(i).getLast() != 11) {                 System.out.println("CONGRATS");                 gr.get_adj(i).add(9);                 i = graph_count;                 return;             }             if (gr.BFS(9)!=null)                 return;         }     }    static int insert_in_graph(Graph gr, int insert_value, int queen_number, int[] already_placed,                                int variant_start, int n, int forbidden, int possible_cur_position) {        ArrayList<Integer> possible_options = new ArrayList<>();        int possibles_amount=0;        if(already_placed.length>0)            possibles_amount= fill_possible_options(possible_options, n, already_placed[already_placed.length - 1], already_placed, queen_number);;        if (ArrWork.place(queen_number, insert_value, already_placed) && insert_value!=forbidden) {            gr.addEdge(variant_start, insert_value);            already_placed = ArrWork.linked_to_array(gr.get_adj(variant_start));        } else {            possibles_amount = fill_possible_options(possible_options, n, already_placed[already_placed.length - 1], already_placed, queen_number);            if (possible_options.size() > 0) {                if (ArrWork.place(queen_number, possible_options.get(possible_cur_position), already_placed)) {                    gr.addEdge(variant_start, possible_options.get(possible_cur_position));                } else {                    while (possible_options.size() > 0 && !(ArrWork.place(queen_number, possible_options.get(possible_cur_position), already_placed))) {                        possible_options.remove(possible_cur_position);                        if (possible_options.size() > 0) {                            if (ArrWork.place(queen_number, possible_options.get(possible_cur_position), already_placed)) {                                gr.addEdge(variant_start, possible_options.get(possible_cur_position));                                break;                            }                        }                    }                }                if (possible_options.size() == 0)                    gr.addEdge(variant_start, 11);            }            if (possible_options.size() == 0)                gr.addEdge(variant_start, 11);        }        return possibles_amount;    }    public static int[] find_result(Graph gr, int n) {        LinkedList<Integer> solution = gr.BFS(9);        if (solution != null) {            Object[] result = solution.toArray();            int[] result_to_print = new int[n];            for (int i = 0; i < n; i++)                result_to_print[i] = (int) result[i];            return result_to_print;        }        return null;    }}