package com.centrointegral.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.http.HttpHeaders;

/**
 * Security configuration for HTTP response headers
 * Adds security headers to prevent XSS, clickjacking, and other attacks
 */
@Configuration
public class SecurityHeaderConfig {

    /**
     * Add security headers to all responses
     */
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(false); // Don't log payloads (may contain sensitive data)
        loggingFilter.setMaxPayloadLength(10000);
        loggingFilter.setIncludeHeaders(false);
        loggingFilter.setAfterMessagePrefix("REQUEST DATA : ");
        return loggingFilter;
    }
}
