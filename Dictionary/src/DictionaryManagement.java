import java.io.*;
import java.util.*;
import java.util.Scanner;

public class DictionaryManagement {

    private Dictionary dictionary = new Dictionary();
    private Scanner scanner = new Scanner(System.in);

    public void insertFromCommandLine() {
        int numWords = scanner.nextInt();
        scanner.skip("\n");
        while (numWords-- > 0) {
            String[] words = scanner.nextLine().split("\t");
            dictionary.add(words[0], words[1]);
        }
        scanner.close();
        dictionary.sort();
    }

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

    public void showAllWords() {
        dictionary.showAllWords();
    }

    public void dictionaryLookup() {
        System.out.print("Input english word: ");
        String str = scanner.nextLine();
        Word temp = new Word(str, "");
        System.out.println(dictionary.getWord(Collections.binarySearch(dictionary.getWords(), temp, dictionary.greater)));
    }

    public void dictionarySearcher() {
        System.out.print("Input: ");
        dictionary.dictionarySearcher(scanner.nextLine());
    }

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

    public void changeWordMeaning() {
        System.out.print("Input word: ");
        String enWord = scanner.nextLine();
        int index = Collections.binarySearch(dictionary.getWords(), new Word(enWord, ""), dictionary.greater);
        if (index < 0) {
            System.out.println("Word not found");
        } else {
            dictionary.getWord(index);
            System.out.print("Change meaning to: ");
            String newWord = scanner.nextLine();
            dictionary.getWords().get(index).setWordExplain(newWord);
        }
    }

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
