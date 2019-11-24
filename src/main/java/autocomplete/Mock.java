package autocomplete;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 模拟100个线程的并发请求，输入是一个字符串，输出是一个List，list最多为5个
 * 单词补全服务在程序启动时，自动读取词库信息，组成前缀树，即字典树
 * 如果每个请求都会选择一个有效单词，那么该单词的搜索频率增加，这将影响搜索优先度
 */
public class Mock {

    /**
     * 单词自动补全服务
     */
    private static AutoComplete autoComplete;

    public static void main(String[] args) {
        Random random = new Random();
        // 字符集
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // 获取服务
        autoComplete = new AutoCompleteService();
        // 100个线程并发查询
        int index = 100;
        CountDownLatch countDownLatch = new CountDownLatch(index);
        for (int i = 0; i < index; i++) {
            new Thread(() -> {
                // 生成随机一位输入字符串
                StringBuilder inputText = new StringBuilder();
                for (int j = 0; j < 1; j++) {
                    int number = random.nextInt(1);
                    inputText.append(str.charAt(number));
                }
                System.out.println(Thread.currentThread().getName() + "输入字符串:" + inputText);
                // 查询
                List<String> resultList = autoComplete.obtainMatchedWords(inputText.toString());
                if (resultList != null && resultList.size() == 0) {
                    System.out.println(Thread.currentThread().getName() + "没有补全单词");
                }
                for (String result : resultList) {
                    System.out.println(Thread.currentThread().getName() + "补全单词:" + result);
                }
                // 随机选择一个结果更新频率
                if (resultList != null && resultList.size() > 0) {
                    String word = resultList.get(random.nextInt(resultList.size()));
                    System.out.println(Thread.currentThread().getName() + "更新单词:" + word);
                    autoComplete.updateWordWeight(word);
                }
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LexiconFactory.getDictionary("ascii");
        System.out.println("执行完毕！");
    }
}
