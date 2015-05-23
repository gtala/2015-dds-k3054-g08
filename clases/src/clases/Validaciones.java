package clases;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;

public class Validaciones {
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
    /**
     * Validate given email with regular expression.
     * 
     * @param email
     *            email for validation
     * @return true valid email, otherwise false
     */
    public static boolean validateEmail(String email) {
 
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
 
    }
    public static boolean validateDate(Date unaFecha) {
    	 
    	Date today = new Date();
    	int  diff  = today.compareTo(unaFecha);
    	
    	if (diff > 7){
    		return false;
    	}
    	else if (diff < -7){
    		return false;
    	}
    	else{
    		return true;
    	}
    }
    
    public static boolean validateStrings (String primerString, String segundoString){
    	return primerString.equals(segundoString);
    	
    }
}
