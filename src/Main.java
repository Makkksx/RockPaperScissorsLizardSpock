import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int count_args;
    public static void main(String[] args) {
        if (!checkArgs(args)) return;
        Rules.setRules(args);
        getMove(args);
    }
    private static String getMove(String[] args){
        System.out.println("New HMAC: " + Generation.getHMAC(args));
        System.out.println("Available moves:");
        for (int i=0; i<count_args; i++) System.out.println(i+1 + " " + args[i]);
        System.out.println("0 exit");
        System.out.println("? help");
        String inputScanner  = new Scanner(System.in).nextLine();
        if (inputScanner.length() == 1 && inputScanner.charAt(0) =='?') {
            Help_table.printHelp(Rules.getRules(), args);
            return getMove(args);
        }
        int c = -1;
        try {
            c = Integer.parseInt(inputScanner);
            if (c == 0) return "";
            else if (0 < c && c <= count_args) {
                System.out.println("Your move: " + args[c-1]);
                System.out.println("Computer move: " + Generation.getMessage());
                System.out.println("Result: " + Rules.getWinner(c-1,Generation.geCompMove()));
                System.out.println("HMAC key: " + Generation.getKey());
                System.out.println("-----------------------------------------");
            }
            else printError();
        }catch (NumberFormatException e) {
            printError();
        }
        return getMove(args);
    }
    private static boolean checkArgs(String[] args){
        count_args = args.length;
        if (count_args % 2 == 0 || count_args < 3){
            System.out.println("Please use an odd number of arguments >=3");
            return false;
        }
        if (Arrays.stream(args).distinct().count() != count_args){
            System.out.println("Please use unique arguments");
            return false;
        }
        return true;
    }
    private static void printError(){
        System.out.println("Please use the available options");
        System.out.println("-----------------------------------------");
    }
}
