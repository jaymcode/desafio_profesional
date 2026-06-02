/**
 * Validates image URLs to prevent XSS and malicious image injection
 * Only allows:
 * - Relative paths starting with /src/assets/
 * - Data URIs starting with data:image/
 */
export const isValidImageUrl = (url) => {
  if (!url || typeof url !== 'string') {
    return false;
  }

  const trimmedUrl = url.trim();

  // Allow local asset paths
  if (trimmedUrl.startsWith('/src/assets/')) {
    // Validate path format (no special characters that could break HTML)
    return /^\/src\/assets\/[a-zA-Z0-9._\-/]+$/.test(trimmedUrl);
  }

  // Allow data URIs for images only
  if (trimmedUrl.startsWith('data:image/')) {
    // Basic validation for data URI format
    return /^data:image\/(jpg|jpeg|png|gif|webp|svg\+xml);base64,[A-Za-z0-9+/=]+$/.test(trimmedUrl);
  }

  // Reject everything else including: javascript:, data:text/, external URLs, etc.
  return false;
};

/**
 * Filter and validate an array of image URLs
 */
export const validateImageUrls = (urls) => {
  if (!Array.isArray(urls)) {
    return [];
  }

  return urls
    .map((url) => (typeof url === 'string' ? url.trim() : ''))
    .filter((url) => url.length > 0 && isValidImageUrl(url));
};
