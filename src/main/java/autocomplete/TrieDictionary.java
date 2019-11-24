package autocomplete;

import java.util.ArrayList;
import java.util.List;

/**
 * ascii字符集字典树
 */
public class TrieDictionary implements Dictionary {

    /**
     * 字典根节点
     */
    private volatile Trie root;

    public TrieDictionary() {
        root = new Trie();
    }

    @Override
    public void insert(String word) {
        if (word != null) {
            Trie node = root;
            char[] chars = word.toCharArray();
            for (char aChar : chars) {
                int index = aChar - 'a';
                // 判断是否为字母
                if (index >= 0 && index <= 25) {
                    if (node.children[index] == null) {
                        node.children[index] = new Trie();
                    }
                    node = node.children[index];
                }
            }
            // 单词结束符
            node.word = true;
        }
    }

    @Override
    public List<String> searchWords(String prefix) {
        List<String> result = new ArrayList<>();
        if (prefix != null) {
            Trie node = root;
            char[] chars = prefix.toCharArray();
            // 遍历相同前缀
            for (char aChar : chars) {
                int index = aChar - 'a';
                if (index >= 0 && index <= 25) {
                    if (node.children[index] == null) {
                        return result;
                    }
                    node = node.children[index];
                }
            }
            // 查询相似单词，本demo默认为5个
            DFS(result, node, prefix, 5);
        }
        return result;
    }

    @Override
    public void updateFrequency(String word) {
        if (word != null) {
            Trie node = root;
            char[] chars = word.toCharArray();
            // 记录经过的节点
            List<Trie> nodeList = new ArrayList<>();
            for (char aChar : chars) {
                int index = aChar - 'a';
                if (index >= 0 && index <= 25) {
                    // 该单词未插入字典
                    if (node.children[index] == null) {
                        return;
                    }
                    nodeList.add(node);
                    node = node.children[index];
                }
            }
            // 该单词未插入字典
            if (!node.word) return;
            // 对更新某一个节点的数据需要获取该节点的锁
            for (Trie trie : nodeList) {
                synchronized (trie) {
                    // 有效查询次数加一，提高本单词及其前缀的搜索优先度
                    trie.times++;
                }
            }
        }
    }

    /**
     * 依据搜索频率做深度搜索，返回还剩余多少个结果待查询
     *
     * @param list 查询结果集合
     * @param node 字典树节点
     * @param prefix 字符串前缀
     * @param num 查询个数
     * @return 还可以查多少个
     */
    private int DFS(List<String> list, Trie node, String prefix, int num) {
        // 如果num为零或者没有子节点，那么停止搜索
        if (num == 0 || node.children.length == 0) return num;
        // 自身是不是为单词
        if (node.word) {
            list.add(prefix);
            num--;
        }
        // 检索子树，依据子节点的频率决定次序
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                pairs.add(new Pair(i, node.children[i].times));
            }
        }
        pairs.sort(((o1, o2) -> o2.number - o1.number));
        // 深度检索子树
        int residue = num;
        for (Pair pair : pairs) {
            StringBuilder temp = new StringBuilder(prefix).append((char) ('a' + pair.index));
            residue = DFS(list, node.children[pair.index], temp.toString(), residue);
        }
        return residue;
    }

    /**
     * 用于比较各个子树优先度的内部类
     */
    class Pair {

        /**
         * 编号
         */
        private int index;

        /**
         * 比较权重值
         */
        private int number;

        public Pair(int index, int number) {
            this.index = index;
            this.number = number;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}
