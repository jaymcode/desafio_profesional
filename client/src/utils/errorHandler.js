/**
 * Maps API errors to user-friendly messages
 * Prevents exposing internal system details
 */
export const getErrorMessage = (error) => {
  // Handle timeout
  if (error.code === 'ECONNABORTED' || error.message === 'Request timeout - please try again') {
    return 'La solicitud tardó demasiado. Por favor, intenta de nuevo.';
  }

  // Handle network errors
  if (error.message === 'Network Error' || !error.response) {
    return 'Error de conexión. Verifica tu conexión a internet.';
  }

  const status = error.response?.status;
  const data = error.response?.data;

  // Map HTTP status codes to user-friendly messages
  switch (status) {
    case 400:
      return 'Datos inválidos. Verifica el formulario y vuelve a intentar.';

    case 409:
      return 'Este nombre de profesional ya existe. Usa un nombre diferente.';

    case 401:
      return 'No tienes permiso para realizar esta acción.';

    case 403:
      return 'Acceso denegado. No tienes permisos suficientes.';

    case 404:
      return 'El recurso solicitado no fue encontrado.';

    case 500:
    case 502:
    case 503:
    case 504:
      return 'Error en el servidor. Por favor, intenta de nuevo más tarde.';

    default:
      // Generic fallback error message
      return 'Ocurrió un error. Por favor, intenta de nuevo.';
  }
};

/**
 * Helper to handle form submission errors
 */
export const handleApiError = (error, fallbackMessage = 'Ocurrió un error') => {
  console.error('API Error:', error); // Log full error for debugging
  return getErrorMessage(error) || fallbackMessage;
};
