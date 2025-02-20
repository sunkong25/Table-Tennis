package com.prography.tabletennis.global.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiException extends RuntimeException {
	private ApiResponseStatus apiResponseStatus;
}
