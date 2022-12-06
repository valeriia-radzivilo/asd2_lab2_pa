package ArrWork;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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
//        ArrayList<Integer> answer = BFS(tree);
        ArrayList<Node> min_conflicts = new ArrayList<>();
        ArrayList<Tree> trees_in_mem = new ArrayList<>();
        ArrayList<Integer> answer = RBFS(tree,min_conflicts,trees_in_mem,0);

        return ArrWork.arrlist_to_arr(answer);
    }


    static ArrayList<Integer> BFS(Tree tree)
    {
        ArrayList<Integer> answer = new ArrayList<>();
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
            check = check_children(children_to_check);
            if (check != null) return check;
        return check;
    }

    static ArrayList<Integer> check_children (ArrayList<Node>children)
    {
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


    static ArrayList<Integer> RBFS(Tree tree, ArrayList<Node>min_conflicts, ArrayList<Tree> trees_in_mem, int iter) {
//        if(lastNode!=null) {
//            ArrayList<Integer> answer = new ArrayList<>();
//            ArrayList<Node> lessConflicts = new ArrayList<>();
//            ArrayList<Node> lessers = new ArrayList<>(choose_less_conflict_in_list(lastNode.getChildren()));
//            if (!lessers.contains(null)) {
//                for (Node l : lessers) {
//                    rbfs_res.write(l.get_col_list().toString());
//                    rbfs_res.write(Integer.toString(l.getConflicts()) + "\n");
//                    if (l.getConflicts() == 0)
//                    {
//                        System.out.println("FOUND");
//                        return l.get_col_list();
//                    }
//                    else {
//                        ArrayList<Node> new_lessers = new ArrayList<>(choose_less_conflict_in_list(l.getChildren()));
//                        for (Node n_l : new_lessers) {
//                            answer = RBFS(tree,n_l, rbfs_res);
//                            if(answer!=null)
//                                return answer;
//                        }
//
//                    }
//                }
//            }
//            return answer;
//        }

        ArrayList<Integer>answer =new ArrayList<>();
        Node start = choose_less_conflict_in_list(tree.getRoot().getChildren());
        if(iter==0)
        {
            if(start.getConflicts()==0)
                return start.get_col_list();
            min_conflicts.add(start);
            Tree new_tree = new Tree(start);
            trees_in_mem.add(new_tree);
            RBFS(new_tree,min_conflicts,trees_in_mem,iter++);
        }
        else {
            int min_start = start.getConflicts();
            if(min_start ==0)
                return start.get_col_list();
            if (find_min_coflicts(min_conflicts).getConflicts() >= min_start) {
                min_conflicts.add(start);
                Tree new_tree = new Tree(start);
                trees_in_mem.add(new_tree);
                RBFS(new_tree,min_conflicts,trees_in_mem,iter++);
            }
            else {
                Tree new_tree = trees_in_mem.get(trees_in_mem.size()-1);
                RBFS(new_tree,min_conflicts,trees_in_mem,iter++);

            }
        }

        return answer;
    }


    static Node find_min_coflicts(ArrayList<Node> nodes)
    {
        Node min_node = null;
        int minimum = 100000000;
        for(Node n: nodes)
        {
            if(n.getConflicts()<minimum)
            {
                min_node = n;
                minimum = n.getConflicts();
            }
        }
        return  min_node;
    }


    static Node choose_less_conflict_in_list(ArrayList<Node> nodes)
    {
        int min = 10000;
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
        return f_ans;
    }

}
