import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyCalculator {

    private static double amount =0;

     public static void main(String[] args) {
        // System.out.print("Enter: ");
        //Scanner input = new Scanner(System.in);
        //String str = input.nextLine();
        //String input = "$10 - $2 + toDollars(737р + toRubles($85,4) + 100р) + $18 + toDollars(200р) + $70";
         //String input = "10р - 2р + toDollars(737р + toRubles($85,4) + 100р) + 18р + toDollars(200р) + 70р ";
         String input = "$10 - $2 + toDollars(737р + 10,3333р + 2,501р + toRubles($85,4 + toDollars(200р + 10р)) + 100,44р) + $18 + $70";
         Analysis s = new Analysis();
         String str = s.simplifyString(input);
         System.out.println(str);
         amount = s.calculate(str);
         System.out.println("  " + amount);
         amount += s.calculateStringsInStack();
         System.out.println(" S: " + amount);


    }


}
