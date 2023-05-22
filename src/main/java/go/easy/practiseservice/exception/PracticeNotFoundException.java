package go.easy.practiseservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PracticeNotFoundException extends ResponseStatusException {
	public PracticeNotFoundException(HttpStatus status, String errorMessage) {
		super(status, errorMessage);
	}

}
