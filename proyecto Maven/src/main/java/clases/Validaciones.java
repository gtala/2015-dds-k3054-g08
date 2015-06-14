package clases;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static boolean validateDate(LocalDate unaFecha) {
    	 
    	LocalDate today = LocalDate.now();
    	long diff = ((today.compareTo(unaFecha)) / (1000 * 60 * 60 * 24));
    	
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