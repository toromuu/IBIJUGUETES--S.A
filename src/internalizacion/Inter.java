package internalizacion;
/*
 * Patron singleton para acceder a ella desde cualquer sitio
 */
import java.util.Locale;
import java.util.ResourceBundle;


public class Inter {

	public  String idiom;

	private static ResourceBundle messages;
	private static Inter instance=null;
	
	private Inter(){
		Locale currentLocale;
		currentLocale = new Locale(System.getProperty("user.language"),System.getProperty("user.country"));
		Inter.messages=ResourceBundle.getBundle("idioma",currentLocale);
	}
	
	public static Inter getInstance(){
		if(instance==null)instance= new Inter();
		return instance;
	}

	public String getString(String key){
		String sol=null;
		try {
			sol=messages.getString(key);
		} catch (Exception e) {
			sol=key;
		}
		return sol;
	}
	
	public void setIdioma(String idiom) {
		
		Locale currentLocale;
		
		if (idiom.equals("En") ) {
			currentLocale = new Locale("en","US");
		}
		else  {
			currentLocale = new Locale(System.getProperty("user.language"),System.getProperty("user.country"));
		}
		Inter.messages=ResourceBundle.getBundle("idioma",currentLocale);
		
	}

		
}
