package algorithm;

public class Serialization {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    static String Serialize(TreeNode root) {
        if (root == null) return "";
        return traverseRoot(root) + "#" + traverseLeft(root, 1);
    }

    static TreeNode Deserialize(String str) {
        if (str == null || str.trim().equals("")) return null;
        String[] strs = str.split("#");
        if (strs.length != 2) return null;
        String[] rootStr = strs[0].split("!");
        String[] leftStr = strs[1].split("!");
        if (rootStr.length != leftStr.length) return null;
        if (rootStr.length == 0 || leftStr.length == 0) return null;
        return constructTree(rootStr, 0, rootStr.length - 1, leftStr, 0, leftStr.length - 1, 1);
    }

    static TreeNode constructTree(String[] rootStr, int start1, int end1,
                                  String[] leftStr, int start2, int end2, int index) {
        int val = Integer.valueOf(rootStr[start1]).intValue();
        TreeNode node = new TreeNode(val);
        TreeNode leftNode = null;
        TreeNode rightNode = null;
        int root = 0;
        for (int i = start2; i <= end2; i++) {
            String[] strs = leftStr[i].split("\\+");
            if (Integer.valueOf(strs[0]) == val && Integer.valueOf(strs[1]) == index) {
                break;
            }
            root++;
        }
        if (root > 0) {
            leftNode = constructTree(rootStr, start1 + 1, start1 + root, leftStr, start2, start2 + root - 1, index + 1);
        }
        if (start2 + root < end2) {
            rightNode = constructTree(rootStr, start1 + root + 1, end1, leftStr, start2 + root + 1, end2, index + 1);
        }
        node.left = leftNode;
        node.right = rightNode;
        return node;
    }

    static String traverseRoot(TreeNode root) {
        String result = "";
        if (root == null) return result;
        String str = String.valueOf(root.val) + "!";
        String left = traverseRoot(root.left);
        String right = traverseRoot(root.right);
        result = str + left + right;
        return result;
    }

    static String traverseLeft(TreeNode root, int depth) {
        String result = "";
        if (root == null) return result;
        String str = String.valueOf(root.val) + "+" + String.valueOf(depth) + "!";
        String left = traverseLeft(root.left, depth + 1);
        String right = traverseLeft(root.right, depth + 1);
        result = left + str + right;
        return result;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(8);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(10);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(9);
        TreeNode node7 = new TreeNode(11);
        node.right = node3;
        node3.right = node7;
        node.left = node2;
        node2.left = node4;
        node2.right = node5;
        node5.left = node6;
        String str = Serialize(node);
        TreeNode root = Deserialize(str);
    }
}
