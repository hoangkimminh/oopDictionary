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
                    System.out.print("\nDictionary: English - Vietnamese" +
                                        "\n1. Show all words" +
                                        "\n2. Find word" +
                                        "\n3. Search" +
                                        "\n4. Add word" +
                                        "\n5. Change word meaning" +
                                        "\n6. Delete word" +
                                        "\n7. Export to file" +
                                        "\nInput letter to exit program" +
                                        "\nYour command: ");
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
                    System.out.print("Press enter to continue");
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 2: {
                    //Look up a word
                    cmd.dictionaryManagement.dictionaryLookup();
                    System.out.print("Press enter to continue");
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 3: {
                    //Search list of words contains a string
                    cmd.dictionarySearcher();
                    System.out.print("Press enter to continue");
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 4: {
                    //Add a word to the dictionary
                    cmd.dictionaryManagement.addWord();
                    System.out.print("Press enter to continue");
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 5: {
                    //Change a word'meaning
                    cmd.dictionaryManagement.changeWordMeaning();
                    System.out.print("Press enter to continue");
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 6: {
                    //Delete a word from dictionary
                    cmd.dictionaryManagement.deleteWord();
                    System.out.print("Press enter to continue");
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 7: {
                    //Export the dictionary to a .txt file
                    cmd.dictionaryManagement.dictionaryExportToFile();
                    System.out.print("Press enter to continue");
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
