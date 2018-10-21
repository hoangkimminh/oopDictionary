package com;

public class Word {

    private String wordTarget;
    private String wordExplain;


    //constructor
    public Word(String wordTarget) {
        this.wordTarget = wordTarget;
        this.wordExplain = "";
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

    @Override
    public String toString() {
        return wordTarget;
    }

    //setter
    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }
}
