import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analysis {

    private static StackOfString stack = new StackOfString();

    public static double sum =0;
    public static char func = ' ';

    public double calculate(String input) {
        Pattern pattern = null;
        int dol = input.indexOf("$");
        int rub = input.indexOf("р");

         if(dol >-1 && rub >-1) {
            System.out.println("Неверный ввод!");
            return 0;
        }
        else if(dol>-1)  pattern = Pattern.compile("\\$(\\d+(?:\\.\\d+)?)|([-+])\\s\\$(\\d+(?:\\.\\d+)?)");
         else if(rub>-1) pattern = Pattern.compile("(\\d+(?:\\.\\d+)?)(р)\\s?([-+])?");

        Matcher matcher = pattern.matcher(input);
        double total = 0.0;
        while (matcher.find()) {
            String match = matcher.group(1);
            String operator = matcher.group(2);
            String match2 = matcher.group(3);
            double amount;
            if (match != null) {
                amount = Double.parseDouble(match);
            } else if (match2 != null) {
                amount = Double.parseDouble(match2);
            } else {
                continue; // Пропустить итерацию, если оба значения null
            }
            if ("-".equals(operator)) {
                total -= amount;
            } else {
                total += amount;
            }
        }
        //System.out.println(total);
        return total;
        }

    public String simplifyString(String input){
       // StackOfString stack = new StackOfString();
        input = " + " + input;
        int start = input.indexOf("toDollars(");
        int start1 = input.indexOf("toRubles(");   //если ничего нет
        while ((start != -1) || (start1 != -1)){

        if(start1< start && start1 != -1) start = start1;
        else if(start == -1) start = start1;

        if (start != -1) {
            int openBrackets = 0;
            int end = -1;

            for (int i = start + 10; i < input.length(); i++) {
                char c = input.charAt(i);

                if (c == '(') {
                    openBrackets++;
                } else if (c == ')') {
                    if (openBrackets == 0) {
                        end = i;
                        break;
                    } else {
                        openBrackets--;
                    }
                }
            }

            if (end != -1) {

                String substring = input.substring(start -3, end +1 );
                stack.push(substring);
                System.out.println("Подстрока найдена: " + substring);
                input = input.substring(0, start - 3) + input.substring(end +1);
                start = input.indexOf("toDollars(");
                start1 = input.indexOf("toRubles(");              //добавить проверку
            } else {
                System.out.println("Неправильный формат строки.");
            }
        } else {
            System.out.println("Подстрока не найдена.");
        }
        }
        return input;
    }
  public double calculateStringsInStack(){

        double amount = 0;

        while (!stack.isEmpty()) {
            String str = stack.pop();
            char sign = str.charAt(1);   //в самом конце применить
            int start = findInnerFunction(str);
            char sign1 = ' ';

            while(start != -1) {
                int end = str.indexOf(')');   //получили индексы самой внутренней функции


                String substring = "";
                if(func == 'd') substring = str.substring(start + 10, end );
                else if(func == 'r') substring = str.substring(start + 9, end );

                substring = " + " + substring;
                 substring = substring.replace(",", ".");
                amount = calculate(substring);
                if (func == 'd') {
                    String newstr = toDollar(amount);
                    str = str.substring(0, start ) + newstr + str.substring(end + 1);
                } else if (func == 'r') {
                    String newstr = toRubles(amount);
                    str = str.substring(0, start )  + newstr + str.substring(end + 1);
                }
                start = findInnerFunction(str);
            }
            sum+=calculate(str);
            amount=0;
        }

        return sum;
  }

    public static int findInnerFunction(String input) {
        int toRublesIndex = input.lastIndexOf("toRubles(");
        int toDollarsIndex = input.lastIndexOf("toDollars(");

        if (toRublesIndex == -1 && toDollarsIndex == -1) {
            return -1; // В выражении нет функций toRubles() и toDollars()
        } else if (toRublesIndex == -1) {
            func ='d';
            return toDollarsIndex; // В выражении нет функции toRubles(), но есть toDollars()
        } else if (toDollarsIndex == -1) {
            func ='r';
            return toRublesIndex; // В выражении нет функции toDollars(), но есть toRubles()
        } else {
            if(toRublesIndex > toDollarsIndex) {
                func ='r';
                return toRublesIndex;
            }

            else{
                func ='d';
                return toDollarsIndex;
            }
        }
    }
    public String toDollar(double amount){
        return  "$" + (amount/60);
    }
    public String toRubles(double amount){
        return   (amount*60) +"р";
    }
}
