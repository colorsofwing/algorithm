package algorithm;

import java.util.ArrayList;
import java.util.Comparator;

public class TreePathNumber {

    public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if(root == null) return null;
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> result = calculate(root, target, list);
        Comparator<ArrayList<Integer>>  comparator = ((o1, o2) -> o1.size() - o2.size());
        result.sort(comparator);
        return result;
    }

    public static ArrayList<ArrayList<Integer>> calculate(TreeNode node, int number, ArrayList<Integer> list){
        list.add(node.val);
        ArrayList<Integer> list1 = (ArrayList<Integer>)list.clone();
        ArrayList<Integer> list2 = (ArrayList<Integer>)list.clone();
        ArrayList<ArrayList<Integer>> result1 = null;
        ArrayList<ArrayList<Integer>> result2 = null;
        if (node.left != null){
            result1 = calculate(node.left, number - node.val, list1);
        }
        if (node.right != null){
            result2 = calculate(node.right, number - node.val, list2);
        }
        ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
        if(node.left == null && node.right == null && number - node.val == 0){
            temp.add(list1);
            return temp;
        }
        if(result1 != null){
            temp.addAll(result1);
        }
        if(result2 != null){
            temp.addAll(result2);
        }
        return temp;
    }

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(-5);
        node.left = node2;
        node.right = node3;
        node3.left = node4;
        FindPath(node,0);
    }
}
