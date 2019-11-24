package autocomplete;

import java.util.List;

/**
 * 单词自动补全服务
 */
public class AutoCompleteService implements AutoComplete {

    /**
     * 字典
     */
    private Dictionary dictionary;

    @Override
    public List<String> obtainMatchedWords(String inputText) {
        // 从工厂获取字典
        dictionary = LexiconFactory.getDictionary("ascii");
        // 获取符合要求的单词集合
        List<String> matchers = dictionary.searchWords(inputText.toLowerCase());
        return matchers;
    }

    @Override
    public void updateWordWeight(String word) {
        if (dictionary != null) {
            dictionary.updateFrequency(word);
        }
    }
}
