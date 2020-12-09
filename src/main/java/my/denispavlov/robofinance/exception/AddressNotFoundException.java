package my.denispavlov.robofinance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "address not found")
public class AddressNotFoundException extends RuntimeException {
}