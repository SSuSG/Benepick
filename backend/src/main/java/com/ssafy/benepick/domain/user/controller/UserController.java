package com.ssafy.benepick.domain.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.benepick.domain.user.dto.request.CreateUserAccountRequestDto;
import com.ssafy.benepick.domain.user.service.UserService;
import com.ssafy.benepick.global.response.ResponseResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApiResponses({
	@ApiResponse(responseCode = "200", description = "응답이 성공적으로 반환되었습니다."),
	@ApiResponse(responseCode = "400", description = "응답이 실패하였습니다.",
		content = @Content(schema = @Schema(implementation = ResponseResult.class)))})
@Tag(name = "User Controller", description = "유저 컨트롤러")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserController {

	private final UserService userService;

	@Operation(summary = "회원가입", description = "사용자가 회원가입 합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "회원가입 성공"),
		@ApiResponse(responseCode = "470", description = "이미 존재하는 사용자")
	})
	@PostMapping("/signup")
	public ResponseResult createUserAccount(
		@Valid @RequestBody CreateUserAccountRequestDto createUserAccountRequestDto , HttpServletResponse response) {
		log.info("UserController_createUserAccount | 사용자의 회원가입");
		userService.createUserAccount(createUserAccountRequestDto , response);
		return ResponseResult.successResponse;
	}
}
