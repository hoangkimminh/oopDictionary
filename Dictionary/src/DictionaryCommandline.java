import java.util.Scanner;

public class DictionaryCommandline {

    private DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public void showAllWords() {
        dictionaryManagement.showAllWords();
    }

    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandLine();
        showAllWords();
    }

    public void dictionaryAdvanced() {
        dictionaryManagement.insertFromFile();
        showAllWords();
        dictionaryManagement.dictionaryLookup();
    }

    public void dictionarySearcher() {
        dictionaryManagement.dictionarySearcher();
    }

    public static void main(String[] args) {
        DictionaryCommandline cmd = new DictionaryCommandline();
        cmd.dictionaryManagement.insertFromFile();
        Scanner scanner = new Scanner(System.in);
        int command = 0;
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
                    cmd.showAllWords();
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 2: {
                    cmd.dictionaryManagement.dictionaryLookup();
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 3: {
                    cmd.dictionarySearcher();
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 4: {
                    cmd.dictionaryManagement.addWord();
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 5: {
                    cmd.dictionaryManagement.changeWordMeaning();
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 6: {
                    cmd.dictionaryManagement.deleteWord();
                    scanner.nextLine();
                    scanner.nextLine();
                    command = 0;
                    break;
                }
                case 7: {
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
