import java.util.Scanner;
//This class run Dictionary Commandline Application
public class DictionaryCommandline {

    //Property of DictionaryCommandline
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();

    //This method calls method showAllWords from dictionaryManagement
    public void showAllWords() {
        dictionaryManagement.showAllWords();
    }

    //This method contains 2 function: insert  words from commandline and show all words in dictionary
    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandLine();
        showAllWords();
    }

    //This method contains 3 function: insert words from .txt file, show all words and look up exact word in dictionary
    public void dictionaryAdvanced() {
        dictionaryManagement.insertFromFile();
        showAllWords();
        dictionaryManagement.dictionaryLookup();
    }

    //This method call method dictionarySearcher in dictionaryManagement
    public void dictionarySearcher() {
        dictionaryManagement.dictionarySearcher();
    }

    //Main function
    public static void main(String[] args) {
        DictionaryCommandline cmd = new DictionaryCommandline();
        //Insert words from file
        cmd.dictionaryManagement.insertFromFile();
        Scanner scanner = new Scanner(System.in);
        int command = 0;
        //List of functions in this application
        while (true) {
            switch (command) {
                case 0: {
                    //Show functions
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
                    //Lookup an exact word in dicitonary
                    cmd.dictionaryManagement.dictionaryLookup();
                    System.out.println("Press enter to continue");
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 3: {
                    //Search a string
                    cmd.dictionarySearcher();
                    System.out.println("Press enter to continue");
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 4: {
                    //Add a word to dictionary
                    cmd.dictionaryManagement.addWord();
                    System.out.println("Press enter to continue");
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 5: {
                    //Change word's meaning
                    cmd.dictionaryManagement.changeWordMeaning();
                    System.out.println("Press enter to continue");
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 6: {
                    //Delete a word
                    cmd.dictionaryManagement.deleteWord();
                    System.out.println("Press enter to continue");
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 7: {
                    //Export the dictionary to .txt file
                    cmd.dictionaryManagement.dictionaryExportToFile();
                    System.out.println("Press enter to continue");
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
