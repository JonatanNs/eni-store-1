package fr.eni.tpenistore1.advice;

import fr.eni.tpenistore1.exceptions.AlreadyExistsException;
import fr.eni.tpenistore1.exceptions.InvalidException;
import fr.eni.tpenistore1.exceptions.NotFoundException;
import fr.eni.tpenistore1.exceptions.UnauthorizedAccessException;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.util.List;

/**
 * Contrôleur global pour gérer les réponses et exceptions REST de manière centralisée.
 * Toutes les classes annotées @RestController peuvent bénéficier de ces méthodes.
 * Fournit notamment :
 * - des réponses standardisées via ApiResponse,
 * - la gestion centralisée des exceptions.
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026
 */
@RestControllerAdvice
public class GlobalRestController {

    /**
     * Méthode générique pour construire la réponse d'erreur.
     */
    private ResponseEntity<ApiResponse<?>> buildResponse(Exception ex, HttpStatus status, int code) {
        return ResponseEntity.status(status)
                .body(ApiResponse.of(code, ex.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleAllExceptions(Exception ex) {
        return buildResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleExists(AlreadyExistsException ex) {
        return buildResponse(ex, HttpStatus.CONFLICT, HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalid(InvalidException ex) {
        return buildResponse(ex, HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleElementNotFound(NotFoundException ex) {
        return buildResponse(ex, HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ApiResponse<?>> handleNotUnauthorizedAccess(UnauthorizedAccessException ex){
        return buildResponse(ex, HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(
            MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

              return ResponseEntity.badRequest()
                .body(ApiResponse.of(
                        HttpStatus.BAD_REQUEST.value(),
                        errors.toString(),
                        null));
    }

    // URL invalide / endpoint non trouvé
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<?>> handlePathNotFound(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.of(HttpStatus.NOT_FOUND.value(), "Endpoint introuvable", null));
    }

    // Type de paramètre invalide
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<?>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = "Paramètre invalide : " + ex.getName();
        return ResponseEntity.badRequest()
                .body(ApiResponse.of(HttpStatus.BAD_REQUEST.value(), message, null));
    }
}
