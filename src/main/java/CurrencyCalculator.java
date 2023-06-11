import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyCalculator {

     public static void main(String[] args) {
        // System.out.print("Enter: ");
        //Scanner input = new Scanner(System.in);
        //String str = input.nextLine();
        String input = "$10 - $2 + toDollars(737р + toRubles($85,4) + 100р) + $18 + toDollars(200р) + $70";
         //String input = "10р - 2р + toDollars(737р + toRubles($85,4) + 100р) + 18р + toDollars(200р) + 70р ";
Analysis s = new Analysis();
String str = s.simplifyString(input);
System.out.println(str);
System.out.println("  " + s.calculate(str));


    }
    public void toDollars(String str){


    }

}
