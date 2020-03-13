package com.faq.validators.input;


import com.faq.exceptions.ServiceException;
import com.faq.shared.ErrorMessageList;
import com.faq.shared.Utils;
import com.faq.ui.model.request.UserSignUpRequestModel;

public class UserInputValidator {

	public static void SignUp(UserSignUpRequestModel req) throws Exception {
		validateFirstName(req.getFirstName());
		validateLastName(req.getLastName());
		validateEmail(req.getEmail());
		validatePassword(req.getPassword());
		validateOrgName(req.getOrgName());
	}

	private static void validateFirstName(String fn) throws Exception {
		if (fn == null)
			throw new ServiceException(ErrorMessageList.REQUIRED_FIRST_NAME_FIELD.getErrorMessage());
		if (fn.isEmpty())
			throw new ServiceException(ErrorMessageList.REQUIRED_FIRST_NAME_VALUE.getErrorMessage());
		if (fn.length() < 3 || fn.length() > 20)
			throw new ServiceException(ErrorMessageList.REQUIRED_FIRST_NAME_LENGTH.getErrorMessage());
	}

	private static void validateLastName(String ln) throws Exception {
		if (ln == null)
			throw new ServiceException(ErrorMessageList.REQUIRED_LAST_NAME_FIELD.getErrorMessage());
		if (ln.isEmpty())
			throw new ServiceException(ErrorMessageList.REQUIRED_LAST_NAME_VALUE.getErrorMessage());
		if (ln.length() < 3 || ln.length() > 20)
			throw new ServiceException(ErrorMessageList.REQUIRED_LAST_NAME_LENGTH.getErrorMessage());
	}

	private static void validateEmail(String email) throws Exception {
		if (email == null)
			throw new ServiceException(ErrorMessageList.REQUIRED_EMAIL_FIELD.getErrorMessage());
		if (email.isEmpty())
			throw new ServiceException(ErrorMessageList.REQUIRED_EMAIL_VALUE.getErrorMessage());
		if (!Utils.CheckEmailPattern(email))
			throw new ServiceException(ErrorMessageList.REQUIRED_EMAIL_PATTERN.getErrorMessage());
	}
	
	private static void validatePassword(String pwd) throws Exception {
		if (pwd == null)
			throw new ServiceException(ErrorMessageList.REQUIRED_PASSWORD_FIELD.getErrorMessage());
		if (pwd.isEmpty())
			throw new ServiceException(ErrorMessageList.REQUIRED_PASSWORD_VALUE.getErrorMessage());
		if (!Utils.CheckPasswordPattern(pwd))
			throw new ServiceException(ErrorMessageList.REQUIRED_PASSWORD_PATTERN.getErrorMessage());
	}
	
	private static void validateOrgName(String orgName) throws Exception {
		if (orgName == null)
			throw new ServiceException(ErrorMessageList.REQUIRED_ORG_NAME_FIELD.getErrorMessage());
		if (orgName.isEmpty())
			throw new ServiceException(ErrorMessageList.REQUIRED_ORG_NAME_VALUE.getErrorMessage());
		
	}

	

}
