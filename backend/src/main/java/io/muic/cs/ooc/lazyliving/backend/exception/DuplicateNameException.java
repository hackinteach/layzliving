package io.muic.cs.ooc.lazyliving.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST,reason="Some input already exists in database.")
public class DuplicateNameException extends RuntimeException{

}
