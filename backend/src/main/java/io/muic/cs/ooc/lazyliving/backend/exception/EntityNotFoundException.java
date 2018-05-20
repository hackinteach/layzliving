package io.muic.cs.ooc.lazyliving.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND,reason="Entity by the specified Id is not found in the system")
public class EntityNotFoundException extends RuntimeException{
}
