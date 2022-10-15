package com.shoping.product.exception;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shoping.product.dto.APIError;

@ControllerAdvice // takes care of global exception handler
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<String> error = ex.getBindingResult().getFieldErrors()
		.stream()
		.map(err -> err.getField() + " : " + err.getDefaultMessage())
		.collect(Collectors.toList());
		
		APIError apiError = new APIError();
		apiError.setStatus(status);
		apiError.setError(error);
		apiError.setTimestamp(LocalDateTime.now());
		apiError.setPathUrl(request.getDescription(true));
		return new ResponseEntity(apiError, headers, apiError.getStatus());
	}

	@ExceptionHandler({OfferNotValidException.class, CurrencyNotValidException.class})
	public ResponseEntity<?> offerNotValidHandler(Exception ex, ServletWebRequest request) {
		APIError apiError = new APIError();
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		apiError.setError(Arrays.asList(ex.getClass() + " : " + ex.getMessage()));
		apiError.setTimestamp(LocalDateTime.now());
		apiError.setPathUrl(request.getDescription(true));
		return new ResponseEntity(apiError, new HttpHeaders(), apiError.getStatus());
	}

}
