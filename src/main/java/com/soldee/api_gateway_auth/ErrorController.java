package com.soldee.api_gateway_auth;

import com.soldee.api_gateway_auth.exceptions.EmptyRolesException;
import com.soldee.api_gateway_auth.response.ErrorResponse;
import com.soldee.api_gateway_auth.exceptions.InvalidProviderException;
import com.soldee.api_gateway_auth.response.GeneralErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ErrorController {

    Logger log = LoggerFactory.getLogger(ErrorController.class);


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({EmptyRolesException.class, InvalidProviderException.class})
    @ResponseBody
    public ErrorResponse handleBadRequest(HttpServletRequest request, Exception error) {
        log.error(request.getRemoteAddr() + " - " + request.getRequestURI() + " - " + error.getLocalizedMessage());

        return new ErrorResponse(error, request.getRequestURI());
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public GeneralErrorResponse fallbackErrorHandler(HttpServletRequest request, Exception error) {
        log.error(request.getRequestURI() + " - " + error.getLocalizedMessage());
        error.printStackTrace();

        return new GeneralErrorResponse(request.getRequestURI());
    }
}
