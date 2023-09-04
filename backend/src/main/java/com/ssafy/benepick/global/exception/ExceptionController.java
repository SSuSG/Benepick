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
}
