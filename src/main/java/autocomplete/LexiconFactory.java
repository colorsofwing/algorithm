package autocomplete;

/**
 * 字典树工厂类(目前支持ascii字符集，可以生成其他字符集的扩展)
 */
public class LexiconFactory {

    /**
     * ascii字符集，a->z
     */
    public static final String ASCII = "ascii";

    /**
     * 字典
     */
    private static Dictionary dictionary;

    /**
     * java关键字
     */
    private static final String[] javaKeywords = new String[]{
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue",
            "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "goto", "if",
            "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private",
            "protected", "public", "return", "strictfp","sharp", "short", "static", "super", "switch", "synchronized",
            "this", "throw", "throws", "transient", "try", "void", "volatile", "while"
    };

    /**
     * 获取字典树
     *
     * @return
     */
    public static Dictionary getDictionary(String charset) {
        if (charset != null) {
            switch (charset) {
                case ASCII:
                    synchronized (LexiconFactory.class) {
                        if (dictionary == null) {
                            dictionary = new TrieDictionary();
                        }
                    }
                    break;
                default:
                    break;
            }
            // 字典初始化
            init(dictionary);
        }
        return dictionary;
    }

    /**
     * 初始化字典，可以从词库中加载单词，本例只加载java关键字
     *
     * @param dictionary
     */
    public static void init(Dictionary dictionary) {
        for (String word : javaKeywords) {
            dictionary.insert(word.toLowerCase());
        }
    }
}
