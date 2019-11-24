package autocomplete;

/**
 * ascii字符集字典树节点结构
 * 利用ascii字符有固定的int，每个字母可以代表数组中固定位置的元素
 */
public class Trie {

    /**
     * 子节点
     */
    Trie[] children;

    /**
     * 根节点至此节点的路径是否为单词
     */
    boolean word = false;

    /**
     * 查询次数(优先级)
     */
    int times;

    public Trie() {
        children = new Trie[26];
    }
}
