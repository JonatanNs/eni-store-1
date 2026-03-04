package fr.eni.tpenistore1.record;

import java.time.LocalDateTime;

/**
 * Classe 'ApiResponse' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 11:18
 */
public record ApiResponse<T>( String code, LocalDateTime timestamp , String message, T data) {
}
