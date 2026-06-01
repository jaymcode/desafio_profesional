package com.centrointegral.backend.util;

import org.owasp.encoder.Encode;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Utility class for input validation and sanitization
 * Prevents XSS, SQL injection, and malicious image URLs
 */
public class ValidationUtil {

    private static final Pattern VALID_IMAGE_URL_PATTERN = Pattern.compile("^/src/assets/[a-zA-Z0-9._\\-/]+$");
    private static final Pattern VALID_DATA_URI_PATTERN = Pattern.compile("^data:image/(jpg|jpeg|png|gif|webp|svg\\+xml);base64,[A-Za-z0-9+/=]+$");
    private static final Pattern VALID_PROFESSION_PATTERN = Pattern.compile("^(Kinesiología|Fisiatría|Fonoaudiología|Psicopedagogía|Pediatría)$");
    private static final int MAX_NOMBRE_LENGTH = 255;
    private static final int MAX_DESCRIPCION_LENGTH = 5000;

    /**
     * Validates an image URL - only allows local assets or base64 data URIs
     */
    public static boolean isValidImageUrl(String url) {
        if (url == null || url.isBlank()) {
            return false;
        }

        String trimmed = url.trim();

        // Allow local asset paths
        if (VALID_IMAGE_URL_PATTERN.matcher(trimmed).matches()) {
            return true;
        }

        // Allow base64 data URIs
        if (VALID_DATA_URI_PATTERN.matcher(trimmed).matches()) {
            return true;
        }

        // Reject everything else (javascript:, http://, external URLs, etc.)
        return false;
    }

    /**
     * Filters and validates an array of image URLs
     */
    public static List<String> validateImageUrls(List<String> urls) {
        if (urls == null) {
            return List.of();
        }

        return urls.stream()
                .filter(url -> url != null && !url.isBlank())
                .map(String::trim)
                .filter(ValidationUtil::isValidImageUrl)
                .collect(Collectors.toList());
    }

    /**
     * Sanitizes HTML content to prevent XSS attacks
     * Removes script tags and event handlers
     */
    public static String sanitizeHtml(String input) {
        if (input == null) {
            return "";
        }

        // HTML encode the input to escape special characters
        String encoded = Encode.forHtml(input);

        // Remove any remaining script tags or dangerous patterns
        encoded = encoded.replaceAll("(?i)<script[^>]*>.*?</script>", "");
        encoded = encoded.replaceAll("(?i)javascript:", "");
        encoded = encoded.replaceAll("(?i)on\\w+\\s*=", "");

        return encoded.trim();
    }

    /**
     * Validates profession field
     */
    public static boolean isValidProfession(String profession) {
        if (profession == null || profession.isBlank()) {
            return false;
        }

        return VALID_PROFESSION_PATTERN.matcher(profession.trim()).matches();
    }

    /**
     * Validates nombre field
     */
    public static boolean isValidNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return false;
        }

        String trimmed = nombre.trim();
        return trimmed.length() > 0 && trimmed.length() <= MAX_NOMBRE_LENGTH;
    }

    /**
     * Validates descripcion field
     */
    public static boolean isValidDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            return false;
        }

        String trimmed = descripcion.trim();
        return trimmed.length() > 0 && trimmed.length() <= MAX_DESCRIPCION_LENGTH;
    }

    /**
     * Sanitizes user input for safe storage
     */
    public static String sanitizeInput(String input) {
        if (input == null) {
            return "";
        }

        return sanitizeHtml(input).trim();
    }
}
