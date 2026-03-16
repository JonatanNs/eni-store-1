package fr.eni.tpenistore1.exceptions;

/**
 * Classe 'NotFoundException' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026 14:05
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
