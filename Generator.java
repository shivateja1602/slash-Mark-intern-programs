import java.util.Scanner;

public class Generator {
    Alphabet alphabet;
    public static Scanner keyboard;

    public Generator(Scanner scanner) {
        keyboard = scanner;
    }

    public Generator(boolean IncludeUpper, boolean IncludeLower, boolean IncludeNum, boolean IncludeSym) {
        alphabet = new Alphabet(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
    }

    public void mainLoop() {
        System.out.println("Welcome to Ziz Password Services :)");
        printMenu();

        String userOption = "-1";

        while (!userOption.equals("4")) {
            userOption = keyboard.next();

            switch (userOption) {
                case "1": requestPassword(); break;
                case "2": checkPassword(); break;
                case "3": printUsefulInfo(); break;
                case "4": printQuitMessage(); break;
                default: System.out.println("\nKindly select one of the available commands"); break;
            }
            printMenu();
        }
    }

    private Password GeneratePassword(int length) {
        StringBuilder pass = new StringBuilder();
        int alphabetLength = alphabet.getAlphabet().length();

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * alphabetLength);
            pass.append(alphabet.getAlphabet().charAt(index));
        }

        return new Password(pass.toString());
    }

    private void printUsefulInfo() {
        System.out.println("\nUse a minimum password length of 8 or more characters if permitted");
        System.out.println("Include lowercase and uppercase alphabetic characters, numbers and symbols if permitted");
        System.out.println("Generate passwords randomly where feasible");
        System.out.println("Avoid using the same password twice across multiple accounts and/or systems");
        System.out.println("Avoid character repetition, keyboard patterns, dictionary words, letter or number sequences, "
                + "usernames, relative or pet names, romantic links, and biographical information.");
        System.out.println("Avoid using information that colleagues and/or acquaintances might know to be associated with you");
        System.out.println("Do not use passwords which consist wholly of any simple combination of the aforementioned weak components");
    }

    private void requestPassword() {
        boolean IncludeUpper = false, IncludeLower = false, IncludeNum = false, IncludeSym = false;
        boolean correctParams;

        System.out.println("\nHello, welcome to the Password Generator :) Answer the following questions by Yes or No \n");

        do {
            correctParams = false;

            IncludeLower = getUserChoice("Do you want Lowercase letters \"abcd...\" to be used?");
            IncludeUpper = getUserChoice("Do you want Uppercase letters \"ABCD...\" to be used?");
            IncludeNum = getUserChoice("Do you want Numbers \"1234...\" to be used?");
            IncludeSym = getUserChoice("Do you want Symbols \"!@#$...\" to be used?");

            if (!IncludeUpper && !IncludeLower && !IncludeNum && !IncludeSym) {
                System.out.println("You have selected no characters to generate your password, at least one of your answers should be Yes\n");
                correctParams = true;
            }
        } while (correctParams);

        System.out.println("Great! Now enter the length of the password");
        int length = keyboard.nextInt();

        Generator generator = new Generator(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
        Password password = generator.GeneratePassword(length);

        System.err.println("Your generated password: " + password);
    }

    private boolean getUserChoice(String prompt) {
        String input;
        do {
            System.out.println(prompt);
            input = keyboard.next();
        } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

        return input.equalsIgnoreCase("yes");
    }

    private void checkPassword() {
        System.out.print("\nEnter your password:");
        String input = keyboard.next();
        Password p = new Password(input);
        System.out.println(p.calculateScore());
    }

    private void printMenu() {
        System.out.println("\nEnter 1 - Password Generator");
        System.out.println("Enter 2 - Password Strength Check");
        System.out.println("Enter 3 - Useful Information");
        System.out.println("Enter 4 - Quit");
        System.out.print("Choice:");
    }

    private void printQuitMessage() {
        System.out.println("Closing the program bye bye!");
    }
}
