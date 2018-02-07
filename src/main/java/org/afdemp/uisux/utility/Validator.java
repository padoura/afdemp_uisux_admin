package org.afdemp.uisux.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Validator {
	
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	private static final Pattern VALID_TELEPHONE_REGEX = 
            Pattern.compile("[0-9]{8}");
	
	private static boolean validateEmail(String email) 
	{
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
	}
	
	private static boolean validateTel(String phone) 
	{
        Matcher matcher = VALID_TELEPHONE_REGEX .matcher(phone);
        return matcher.find();
	}
	
	
	
}
