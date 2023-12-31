package com.ssafy.benepick.global.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.ssafy.benepick.global.response.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "com.ssafy.benepick")
public class ExceptionController {

	@ExceptionHandler(ExistUserException.class)
	public ResponseResult ExistUserException(ExistUserException err) {
		log.info("Error : {}", err.getClass());
		log.info("Error Message : {}", err.getMessage());
		return ResponseResult.exceptionResponse(ExceptionCode.EXIST_USER_EXCEPTION);
	}

	@ExceptionHandler(NotExistUserCiException.class)
	public ResponseResult NotExistUserCiException(NotExistUserCiException err) {
		log.info("Error : {}", err.getClass());
		log.info("Error Message : {}", err.getMessage());
		return ResponseResult.exceptionResponse(ExceptionCode.NOT_EXIST_USER_CI_EXCEPTION);
	}

	@ExceptionHandler(NotExistAccessTokenException.class)
	public ResponseResult NotExistAccessTokenException(NotExistAccessTokenException err) {
		log.info("Error : {}", err.getClass());
		log.info("Error Message : {}", err.getMessage());
		return ResponseResult.exceptionResponse(ExceptionCode.NOT_EXIST_ACCESS_TOKEN_EXCEPTION);
	}

	@ExceptionHandler(NotExistCardCompanyException.class)
	public ResponseResult NotExistCardCompanyException(NotExistCardCompanyException err) {
		log.info("Error : {}", err.getClass());
		log.info("Error Message : {}", err.getMessage());
		return ResponseResult.exceptionResponse(ExceptionCode.NOT_EXIST_CARD_COMPANY_EXCEPTION);
	}

	@ExceptionHandler(NotExistLinkCardCompanyException.class)
	public ResponseResult NotExistLinkCardCompanyException(NotExistLinkCardCompanyException err) {
		log.info("Error : {}", err.getClass());
		log.info("Error Message : {}", err.getMessage());
		return ResponseResult.exceptionResponse(ExceptionCode.NOT_EXIST_LINK_CARD_COMPANY_EXCEPTION);
	}

	@ExceptionHandler(BankServerException.class)
	public ResponseResult BankServerException(BankServerException err) {
		log.info("Error : {}", err.getClass());
		log.info("Error Message : {}", err.getMessage());
		return ResponseResult.exceptionResponse(ExceptionCode.BANK_SERVER_EXCEPTION);
	}

	@ExceptionHandler(BankServerTimeException.class)
	public ResponseResult BankServerTimeException(BankServerTimeException err) {
		log.info("Error : {}", err.getClass());
		log.info("Error Message : {}", err.getMessage());
		return ResponseResult.exceptionResponse(ExceptionCode.BANK_SERVER_TIME_EXCEPTION);
	}

	@ExceptionHandler(BankServerClientException.class)
	public ResponseResult BankServerClientException(BankServerClientException err) {
		log.info("Error : {}", err.getClass());
		log.info("Error Message : {}", err.getMessage());
		return ResponseResult.exceptionResponse(ExceptionCode.BANK_SERVER_CLIENT_EXCEPTION);
	}
}
