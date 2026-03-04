package fr.eni.tpenistore1.exceptions;

/**
 * Exception levée lorsqu'un utilisateur tente de s'inscrire avec un email déjà existant.
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026 12:05
 */
public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
