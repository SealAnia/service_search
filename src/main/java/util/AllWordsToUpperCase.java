package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AllWordsToUpperCase {
	
	public static String allWordsToUpperCase(String s) {
		s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1);
		Pattern pattern = Pattern.compile("[ -][a-z]{1}");
		Matcher matcher = pattern.matcher(s);
		while(matcher.find()) {
			s = s.replaceAll(s.substring(matcher.start(), matcher.end()), s.substring(matcher.start(), matcher.end()).toUpperCase());
		}
		return s;
	}

}
