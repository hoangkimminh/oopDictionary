import java.io.*;
import java.util.*;
import java.util.Scanner;

/*
    This is class DictionaryManagement
    includes of a dictionary and tool'methods to shows and edit them
 */
public class DictionaryManagement {

    //Property of class DictionaryManagement
    private Dictionary dictionary = new Dictionary();
    private Scanner scanner = new Scanner(System.in);

    //This medthod inserts some words from commandline by keyboard inputting
    public void insertFromCommandLine() {
        int numWords = scanner.nextInt();
        scanner.skip("\n");
        //repeat inserting a new word until numWords = 0
        while (numWords-- > 0) {
            String[] words = scanner.nextLine().split("\t");
            dictionary.add(words[0], words[1]);
        }
        scanner.close();
        dictionary.sort();      //Sort the dictionary
    }

    //This method inserts words into dictionary from .txt file
    public void insertFromFile() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("dictionaries.txt"));
            String line = null;
            while ((line = in.readLine()) != null) {
                String[] words = line.split("\t");
                dictionary.add(words[0], words[1]);
            }
            in.close();
            dictionary.sort();
        } catch (IOException ex) {
            System.out.println("Unable to open file");
        }
    }

    //This method shows all words in the dictionary by calling "showAllWords method" in dictionary
    public void showAllWords() {
        dictionary.showAllWords();
    }

    //This method looks up the exact string entered from the keyboard in the dictionary
    public void dictionaryLookup() {
        System.out.print("Input english word: ");
        String str = scanner.nextLine();
        Word temp = new Word(str, "");
        System.out.println(dictionary.getWord(Collections.binarySearch(dictionary.getWords(), temp, dictionary.greater)));
    }

    //This method searchs all words contain string named str by calling similar method in dictonary
    public void dictionarySearcher() {
        System.out.print("Input: ");
        dictionary.dictionarySearcher(scanner.nextLine());
    }

    //This method adds a word from keyboard
    public void addWord() {
        System.out.print("Input english word: ");
        String enWord = scanner.nextLine();
        int index = Collections.binarySearch(dictionary.getWords(), new Word(enWord, ""), dictionary.greater);
        if (index >= 0) {
            System.out.println("Already in the dictionary\n" + dictionary.getWord(index));
        } else {
            System.out.print("Input new meaning: ");
            String vnWord = scanner.nextLine();
            index = -(index + 1);
            dictionary.getWords().add(index, new Word(enWord, vnWord));
        }
    }

    //This method deletes a word inputed from keyboard if it is in the dictionary
    public void deleteWord() {
        System.out.print("Input english word: ");
        String enWord = scanner.nextLine();
        int index = Collections.binarySearch(dictionary.getWords(), new Word(enWord, ""), dictionary.greater);
        if (index < 0) {
            System.out.println("Word not found");
        } else {
            dictionary.getWords().remove(index);
        }
    }

    //This method changes a word's meaning
    public void changeWordMeaning() {
        System.out.print("Input word: ");
        String enWord = scanner.nextLine();
        int index = Collections.binarySearch(dictionary.getWords(), new Word(enWord, ""), dictionary.greater);

        //firstly, look up it in the dictionary
        if (index < 0) {
            System.out.println("Word not found");
        } else {
            // if this word is in the dictionary, remove it from dictionary
            dictionary.getWord(index);
            System.out.print("Change meaning to: ");
            String newWord = scanner.nextLine();
            dictionary.getWords().get(index).setWordExplain(newWord);
        }
    }

    //This metod exports all words in the dictionary to a .txt file
    public void dictionaryExportToFile() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));
            int size = dictionary.getWords().size();
            for (int i = 0; i < size; i++) {
                String str = dictionary.getWord(i);
                bufferedWriter.write(str);
                bufferedWriter.newLine();
            }
            System.out.println("Completed\n");
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Failed to export to file");
        }
    }
}
