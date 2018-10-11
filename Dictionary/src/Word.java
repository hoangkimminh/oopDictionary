/*
This is class Word includes of word's properties and methods getter and setter
 */
public class Word {

    private String wordTarget;
    private String wordExplain;

    //Constructor
    public Word() {
        wordTarget = "";
        wordExplain = "";
    }

    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }


    //getter
    public String getWordTarget() {
        return wordTarget;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    //setter
    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }
}
