package ArrWork;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class make_n_queens {
    public static int[] make_n_queens(int n, boolean isRand, int[] notrand) throws IOException {
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
//        FileWriter rbfs_res = new FileWriter("rbfs_res.txt");
//        ArrayList<Integer>answer = RBFS(tree, tree.getRoot(), rbfs_res);
//        if (answer==null) answer = RBFS(tree, tree.getRoot(), rbfs_res);
//        rbfs_res.close();

        return ArrWork.arrlist_to_arr(answer);
    }


    static ArrayList<Integer> BFS(Tree tree)
    {
        ArrayList<Integer> answer = new ArrayList<>();
//        answer = check_every_child(tree.getRoot().getChildren());
//        if(answer!=null) return answer;
//        for(int i =0; i<8;i++) {
//            answer =check_every_child(tree.getRoot().getChildren().get(i).getChildren());
//            if(answer!=null) return answer;
//            for(int j =0; j<8;j++) {
//                answer =check_every_child(tree.getRoot().getChildren().get(i).getChildren().get(j).getChildren());
//                if(answer!=null) return answer;
//                for(int a =0; a<8;a++) {
//                    answer =check_every_child(tree.getRoot().getChildren().get(i).getChildren().get(j).getChildren().get(a).getChildren());
//                    if(answer!=null) return answer;
//                    for(int b =0; b<8;b++) {
//                        answer =check_every_child(tree.getRoot().getChildren().get(i).getChildren().get(j).getChildren().get(a).getChildren().get(b).getChildren());
//                        if(answer!=null) return answer;
//                        for(int c =0; c<8;c++) {
//                            answer =check_every_child(tree.getRoot().getChildren().get(i).getChildren().get(j).getChildren().get(a).getChildren().get(b).getChildren().get(c).getChildren());
//                            if(answer!=null) return answer;
//                            for(int d =0; d<8;d++) {
//                                answer =check_every_child(tree.getRoot().getChildren().get(i).getChildren().get(j).getChildren().get(a).getChildren().get(b).getChildren().get(c).getChildren().get(d).getChildren());
//                                if(answer!=null) return answer;
//                            }
//                        }
//                    }
//                }
//            }
//        }

//        answer = check_every_child(tree.getRoot().getChildren());
//       ArrayList<ArrayList<Node>> checked_children = new ArrayList<>();
//       checked_children.add(tree.getRoot().getChildren());
//       for(int i =0; i <8;i++)
//       {
//           ArrayList<Node> checked_child = checked_children.get(checked_children.size()-1);
//           for (int s =0; s<8;s++)
//           {
//
//               checked_children.add(checked_child.get(i).getChildren());
//               answer = check_every_child(checked_child);
//               if(answer!=null) return answer;
//           }
//           i=0;
//       }

        answer = check_every_child(tree.getRoot().getChildren());
        if(answer==null) {

            ArrayList<Node> children_to_check = new ArrayList<>();
            children_to_check = tree.getRoot().getChildren();
            int iterator = 0;
            while(iterator<100) {
                ArrayList<Node> get_children = new ArrayList<>();
                for (Node child : children_to_check) {
                    answer = check_every_child(child.getChildren());
                    if(answer!=null)
                    {
                        return answer;
                    }
                    for(Node c: child.getChildren())
                        get_children.add(c);
                }
                children_to_check = get_children;
                iterator++;

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


    static ArrayList<Integer> RBFS(Tree tree, Node lastNode, FileWriter rbfs_res) throws IOException {
        if(lastNode!=null) {
            ArrayList<Integer> answer = new ArrayList<>();
            ArrayList<Node> lessConflicts = new ArrayList<>();
            ArrayList<Node> lessers = new ArrayList<>(choose_less_conflict_in_list(lastNode.getChildren()));
            if (!lessers.contains(null)) {
                for (Node l : lessers) {
                    rbfs_res.write(l.get_col_list().toString());
                    rbfs_res.write(Integer.toString(l.getConflicts()) + "\n");
                    if (l.getConflicts() == 0)
                    {
                        System.out.println("FOUND");
                        return l.get_col_list();
                    }
                    else {
                        ArrayList<Node> new_lessers = new ArrayList<>(choose_less_conflict_in_list(l.getChildren()));
                        for (Node n_l : new_lessers) {
                            answer = RBFS(tree,n_l, rbfs_res);
                            if(answer!=null)
                                return answer;
                        }

                    }
                }
            }
            return answer;
        }



        return null;
    }


    static ArrayList<Node> choose_less_conflict_in_list(ArrayList<Node> nodes)
    {
        int min = 10000;
        ArrayList<Node> answer = new ArrayList<>();
        Node f_ans = null;
        for(Node n: nodes)
        {
            if(n.getConflicts()<min&&!n.visited)
            {
                min = n.getConflicts();
                n.setVisited(true);
                f_ans=n;

            }
        }
        answer.add(f_ans);
        for(Node n: nodes)
        {
            if(n.getConflicts()==min&&n!=f_ans)
            {
                n.setVisited(true);
                answer.add(n);

            }
        }

        return answer;
    }

}
