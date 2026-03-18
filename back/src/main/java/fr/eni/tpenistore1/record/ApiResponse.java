package fr.eni.tpenistore1.record;

import java.time.Instant;

/**
 * Classe 'ApiResponse' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 11:18
 */
public record ApiResponse<T>(int code, Instant timestamp , String message, T data) {
    public static <T> ApiResponse<T> of(int code, String message, T data) {
        return new ApiResponse<>(code, Instant.now(), message, data);
    }
}
