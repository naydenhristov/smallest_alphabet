package smallest_alphabet;

import java.util.Scanner;

public class Smallest_Alphabet {

    private static String smallestSubstringContainingTheAlphabet(String str) {
        int initA = 'a', initZ = 'z';
        int range = (initZ-initA)+1;
        int zeroes = range;
        int checkSymbol;
        int[] alphabet = new int[range];
        int from = 0, to = str.length(), startOfSmallest = 0;
        boolean completeAlphabet = false;
        str = str.replace("\t","");
        str = str.replace(" ","");
        str = str.replace("\r","");
        str = str.toLowerCase();
        for (int i=0; i<str.length(); ++i) {
            checkSymbol = str.charAt(i);
            if (checkSymbol<32 || checkSymbol>127) {
                return "\nWrong input! Out of ASCII!";
            }
        }
        for(int i=0; i<str.length(); ++i) {
            int index = str.charAt(i) - initA;
            if (index>=0 && index<range) {
                if (alphabet[index] == 0) --zeroes;
                ++alphabet[index];
                while (zeroes == 0) {
                    completeAlphabet = true;
                    index = str.charAt(startOfSmallest) - initA;
                    if (index>=0 && index<range) {
                        --alphabet[index];
                        if (alphabet[index] == 0) {
                            ++zeroes;
                            if ((i - startOfSmallest) < (to - from)) {
                                from = startOfSmallest;
                                to = i;
                            }
                        }
                    }
                    ++startOfSmallest;
                }
            }
        }
        if (!completeAlphabet){
            return "The string \"" + str + "\" does not contain complete Alphabet!";
        } else {
            System.out.printf("Minimum length is %d, from symbol %d to %d%n", to-from+1, from+1, to+1);
            return str.substring(from, to+1);
        }
    }
    public static void main(String[] args) {
        System.out.println("Result of some pre-entered strings:");
        System.out.println(smallestSubstringContainingTheAlphabet("awxyz"));
        System.out.println(smallestSubstringContainingTheAlphabet("aaaaaabcdefghijklmnopqrstuvwxyz"));
        System.out.println(smallestSubstringContainingTheAlphabet("abcdefghijklzmnaaaaoabcpqrstuvwxyzabcdefghijklmnopqrstuvwxyz"));
        System.out.println(smallestSubstringContainingTheAlphabet("abcdefghijklmn124345678!@#$%^&*opqrstuvwxyz!*abcdefghijklmn"));
        System.out.print("\nEnter your own string for test: ");
        Scanner input = new Scanner(System.in);
        String someString = input.nextLine();
        System.out.println("\nResult of the entered string:");
        System.out.println(smallestSubstringContainingTheAlphabet(someString));
    }
}