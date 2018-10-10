import java.util.*;

public class Dictionary {
    private List<Word> words = new ArrayList<>();
    public Comparator<Word> greater = new Comparator<Word>() {
        public int compare(Word o1, Word o2) {
            return o1.getWordTarget().compareTo(o2.getWordTarget());
        }
    };

    public void add(String wordTarget, String wordExplain) {
        words.add(new Word(wordTarget, wordExplain));
    }

    public void showAllWords() {
        if (words.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        System.out.println("NO    | English         | Vietnamese");
        for (int i = 0; i < words.size(); i++) {
            System.out.printf("%-5d | %-15s | %s\n", i + 1, words.get(i).getWordTarget(), words.get(i).getWordExplain());
        }
    }

    public void sort() {
        Collections.sort(words, greater);
    }

    public List<Word> getWords() {
        return words;
    }

    public String getWord(int index) {
        if (index < 0)
            return "Not found";
        return words.get(index).getWordTarget() + "\t" + words.get(index).getWordExplain();
    }

    public void dictionarySearcher(String str) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWordTarget().contains(str))
                System.out.println(getWord(i));
        }
    }
}
