package autocomplete;

import java.util.List;

/**
 * 字典接口，定义字典的功能
 */
public interface Dictionary {

    /**
     * 向字典中插入一个单词
     *
     * @param word
     */
    public void insert(String word);

    /**
     * 查询具有共同前缀的单词集合
     *
     * @param prefix
     * @return
     */
    public List<String> searchWords(String prefix);

    /**
     * 更新字典中相应单词及其前缀词根的查询频率
     *
     * @param word
     */
    public void updateFrequency(String word);
}
