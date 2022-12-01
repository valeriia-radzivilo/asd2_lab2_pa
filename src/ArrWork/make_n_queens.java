package ArrWork;

import java.util.ArrayList;
import java.util.List;

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

        System.out.println("F2: "+F2.F2(initial_placement,8));

        ArrayList<Integer> init_list = ArrWork.array_to_list(initial_placement);
        Tree tree = new Tree(new Node(init_list,0));
        tree.make_tree(tree);
        ArrayList<Integer> answer = BFS(tree);

        return ArrWork.arrlist_to_arr(answer);
    }


    static ArrayList<Integer> BFS(Tree tree)
    {
        ArrayList<Integer> answer = new ArrayList<>();
        answer = check_every_child(tree.getRoot().getChildren());
        if(answer!=null) return answer;
        for(int i =0; i<8;i++) {
            answer =check_every_child(tree.getRoot().getChildren().get(i).getChildren());
            if(answer!=null) return answer;
            for(int j =0; j<8;j++) {
                answer =check_every_child(tree.getRoot().getChildren().get(i).getChildren().get(j).getChildren());
                if(answer!=null) return answer;
                for(int a =0; a<8;a++) {
                    answer =check_every_child(tree.getRoot().getChildren().get(i).getChildren().get(j).getChildren().get(a).getChildren());
                    if(answer!=null) return answer;
                    for(int b =0; b<8;b++) {
                        answer =check_every_child(tree.getRoot().getChildren().get(i).getChildren().get(j).getChildren().get(a).getChildren().get(b).getChildren());
                        if(answer!=null) return answer;
                        for(int c =0; c<8;c++) {
                            answer =check_every_child(tree.getRoot().getChildren().get(i).getChildren().get(j).getChildren().get(a).getChildren().get(b).getChildren().get(c).getChildren());
                            if(answer!=null) return answer;
                            for(int d =0; d<8;d++) {
                                answer =check_every_child(tree.getRoot().getChildren().get(i).getChildren().get(j).getChildren().get(a).getChildren().get(b).getChildren().get(c).getChildren().get(d).getChildren());
                                if(answer!=null) return answer;
                            }
                        }
                    }
                }
            }
        }


        return answer;
    }

    static ArrayList<Integer> check_every_child(ArrayList<Node> children_to_check)
    {
        ArrayList<Integer> check = null;
        for( Node ch : children_to_check){
            check = check_children(children_to_check);
            if (check != null) return check;
        }
        return check;
    }

    static ArrayList<Integer> check_children (ArrayList<Node>children)
    {
//        System.out.println(children.get(0).get_col_list());
        for(Node child:children)
        {
            if(!child.visited) {
                if (check_placement(child.get_col_list())) {
                    System.out.println("FOUND");
                    return child.get_col_list();
                }
                child.setVisited(true);
            }
        }
        return null;
    }


    static boolean check_placement(ArrayList<Integer>list)
    {
        int[]already_placed = new int[8];
        int iter =0;
        for(int i:list)
        {
            if(ArrWork.place(iter,i,already_placed)) {
                already_placed[iter] = i;
                iter++;
            }
            else break;
        }
        return iter == 8;
    }


}
