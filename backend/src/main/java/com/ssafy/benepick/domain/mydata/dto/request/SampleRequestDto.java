package com.ssafy.benepick.domain.mydata.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "샘플 요청 DTO")
public class SampleRequestDto {

	@Schema(description = "값에 대한 설명", example = "값의 예시")
	private String sample;
}
