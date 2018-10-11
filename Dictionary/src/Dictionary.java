import java.util.*;


/*
    This is class Dictionary
    includes of list word and tool'methods to showing and searching them
 */
public class Dictionary {
    //Property of Dictionary
    private List<Word> words = new ArrayList<>();

    //This method defines a Comparator for compare two Word
    public Comparator<Word> greater = new Comparator<Word>() {
        public int compare(Word o1, Word o2) {
            return o1.getWordTarget().compareTo(o2.getWordTarget());
        }
    };

    //This method adds a new Word to dictionary
    public void add(String wordTarget, String wordExplain) {
        words.add(new Word(wordTarget, wordExplain));
    }

    //This method shows all words in the dictionay
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

    //This method sorts the list of words in the dictionary ascending A - Z
    public void sort() {
        Collections.sort(words, greater);
    }

    //This method  returns the list of words
    public List<Word> getWords() {
        return words;
    }

    //This method returns a word with input index
    public String getWord(int index) {
        if (index < 0)
            return "Not found";
        return words.get(index).getWordTarget() + "\t" + words.get(index).getWordExplain();
    }

    //This method searchs all words contain string named str
    public void dictionarySearcher(String str) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWordTarget().contains(str))
                System.out.println(getWord(i));
        }
    }
}
