package my.denispavlov.robofinance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "customer not found")
public class CustomerNotFoundException extends RuntimeException {
}