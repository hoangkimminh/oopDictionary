import java.util.Scanner;

//This class run the commandline application
public class DictionaryCommandline {

    //Property of Dictionary of DictionaryCommandline
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();

    //This method calls showAllWords method in dictionaryManagement
    public void showAllWords() {
        dictionaryManagement.showAllWords();
    }

    //This method contains two function: insert some new words and show all words in the dictionary
    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandLine();
        showAllWords();
    }

    //This method contains 3 function: insert some new words from .txt file, show all words in the dictionary and look up a word.
    public void dictionaryAdvanced() {
        dictionaryManagement.insertFromFile();
        showAllWords();
        dictionaryManagement.dictionaryLookup();
    }

    //This method calls dictionarySearcher in dictionaryManagement
    public void dictionarySearcher() {
        dictionaryManagement.dictionarySearcher();
    }

    //This is the main method runs commandline application
    public static void main(String[] args) {
        DictionaryCommandline cmd = new DictionaryCommandline();
        cmd.dictionaryManagement.insertFromFile();
        Scanner scanner = new Scanner(System.in);
        int command = 0;        //input a command and do similar function
        while (true) {
            switch (command) {
                case 0: {
                    System.out.println(new String(new char[50]).replace("\0", "\n\r"));
                    System.out.println("\nDictionary: English - Vietnamese");
                    System.out.println("1. Show all words");
                    System.out.println("2. Find word");
                    System.out.println("3. Search");
                    System.out.println("4. Add word");
                    System.out.println("5. Change word meaning");
                    System.out.println("6. Delete word");
                    System.out.println("7. Export to file");
                    System.out.println("Input letter to exit program");
                    System.out.print("Your command: ");
                    if (scanner.hasNextInt()) {
                        command = scanner.nextInt();
                        System.out.println();
                    } else {
                        System.out.println("\nExit dictionary");
                        return;
                    }
                    break;
                }
                case 1: {
                    //Show all words
                    cmd.showAllWords();
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 2: {
                    //Look up a word
                    cmd.dictionaryManagement.dictionaryLookup();
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 3: {
                    //Search list of words contains a string
                    cmd.dictionarySearcher();
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 4: {
                    //Add a word to the dictionary
                    cmd.dictionaryManagement.addWord();
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 5: {
                    //Change a word'meaning
                    cmd.dictionaryManagement.changeWordMeaning();
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 6: {
                    //Delete a word from dictionary
                    cmd.dictionaryManagement.deleteWord();
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 7: {
                    //Export the dictionary to a .txt file
                    cmd.dictionaryManagement.dictionaryExportToFile();
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                default: {
                    command = 0;
                    break;
                }
            }
        }
    }
}
