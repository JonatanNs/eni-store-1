package fr.eni.tpenistore1.exceptions;

/**
 * Classe 'AuthentificationInvalidException' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026 14:34
 */
public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
