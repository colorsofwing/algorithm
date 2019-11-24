package autocomplete;

import java.util.List;

/**
 * 单词自动补全定义接口
 */
public interface AutoComplete {

    /**
     * 输入字符串，依据查询频率从词库中查询一定数量前缀相同的单词集合
     *
     * @param inputText
     * @return 匹配单词集合（不超过5个）
     */
    List<String> obtainMatchedWords(String inputText);

    /**
     * 更新指定单词的查询频率，提高被查询到的几率
     *
     * @param word
     */
    void updateWordWeight(String word);
}
