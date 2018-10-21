package com;

import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.*;

public class Dictionary {

    private List<Word> words = new ArrayList<>();

    //getter
    public List<Word> getWords() {
        return words;
    }

    //function
    public Comparator<Word> greater = new Comparator<Word>() {
        public int compare(Word o1, Word o2) {
            return o1.getWordTarget().compareToIgnoreCase(o2.getWordTarget());
        }
    };

    public int search(Word searchWord) {
        return Collections.binarySearch(words, searchWord, greater);
    }

    public void sort() {
        Collections.sort(words, greater);
    }

    public void insertFromFile() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new UTF8Reader(new FileInputStream("dictionaries.txt")));
            in.readLine();
            String line = null;
            while ((line = in.readLine()) != null) {
                String[] arr = line.split("\t");
                if (arr.length < 2) {
                    continue;
                } else
                    words.add(new Word(arr[0], arr[1].replace("<>", "\n")));
            }
            in.close();
            sort();
        } catch (IOException ex) {
            System.out.println("Unable to open file");
        }
    }

    public boolean add(String wordTarget, String type, String pronounce, String wordExplain) {
        Word newWord = new Word(wordTarget, wordExplain);
        int index = search(newWord);
        if (index >= 0) {
            return false;
        }
        index = -(index + 1);
        words.add(index, newWord);
        return true;
    }

    public boolean delete(String wordTarget) {
        Word newWord = new Word(wordTarget);
        int index = search(newWord);
        if (index < 0) {
            return false;
        }
        words.remove(index);
        return true;
    }

    public boolean change(String wordTarget, String type, String pronounce, String wordExplain) {
        if (!pronounce.isEmpty()) {
            pronounce = '/' + pronounce + '/';
        }
        Word newWord = new Word(wordTarget, pronounce + '\n' + type + '\n' + wordExplain);
        int index = search(newWord);
        if (index < 0) {
            return false;
        }
        words.set(index, newWord);
        return true;
    }
}
