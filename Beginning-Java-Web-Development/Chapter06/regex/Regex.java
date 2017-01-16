import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
 
public class Regex {
    private static final String PwordPattern = "^((?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$!%]).{7,21})$";
    /*
      Explanation: The hat '^' on the left is the 'left-anchor', and the dollar-sign '$' on the right is
      the 'right anchor': the anchors mark the start and end of the pattern. Within the anchors are
      five patterns ('constraints') that must be matched, each of which is clarified below:
      
      (?=.*[0-9])    ## at least one decimal digit 
      (?=.*[A-Z])    ## at least one uppercase letter: a letter from the set [A-Z]
      (?=.*[a-z])    ## at least one lowercase letter: a letter from the set [a-z]
      (?=.*[@#$!%])  ## at least one character from the set [@#$!%]
      {7,21}         ## 7 to 21 characters in all

      The '?=' are 'positive lookaheads', which say in effect: the expression ahead must contain a ...
      In our case, there are 'positive lookaheads' for a decimal digit, a lowecase character, an
      uppercase character, and a special character. 

      For much, much more on regexes, let me recommend strongly:

      Mastering Regular Expressions, 3rd edition, by Jeffrey E. F. Friedl
      http://shop.oreilly.com/product/9780596528126.do
    */
    public static void main(String[ ] args) {
	Pattern pattern = Pattern.compile(PwordPattern); // for efficiency
	String password = "quit";
	Scanner scanner = new Scanner(System.in);

	// Test until user tires.
	do {
	    System.out.print("Password ('quit' to quit): ");
	    password = scanner.nextLine();
	    if (password.equalsIgnoreCase("quit"))
		break;

	    Matcher matcher = pattern.matcher(password);
	    if (matcher.matches())
		System.out.println(password + " is sufficiently strong.");
	    else
		System.out.println(password + " is not sufficiently strong.");
	} while (true);
    }
}
