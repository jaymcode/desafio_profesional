import { isValidImageUrl, validateImageUrls } from './imageValidator';

export const DEFAULT_PROFESSIONAL_IMAGE = '/src/assets/img/pexels-pavel-danilyuk-5998448.jpg';

export const getImageUrlsFromInput = (value) => {
  if (typeof value !== 'string') {
    return [];
  }

  return value
    .split(',')
    .map((img) => img.trim())
    .filter(Boolean);
};

export const validateProfessionalForm = ({ nombre, descripcion, profesion, imagenes = [], imagenesInput = '' }) => {
  const errors = {};
  const trimmedName = typeof nombre === 'string' ? nombre.trim() : '';
  const trimmedDescription = typeof descripcion === 'string' ? descripcion.trim() : '';
  const trimmedProfession = typeof profesion === 'string' ? profesion.trim() : '';

  if (!trimmedName) {
    errors.nombre = 'El nombre es obligatorio.';
  } else if (trimmedName.length < 2) {
    errors.nombre = 'El nombre debe tener al menos 2 caracteres.';
  } else if (trimmedName.length > 255) {
    errors.nombre = 'El nombre no puede superar 255 caracteres.';
  }

  if (!trimmedDescription) {
    errors.descripcion = 'La descripción es obligatoria.';
  } else if (trimmedDescription.length < 20) {
    errors.descripcion = 'La descripción debe tener al menos 20 caracteres.';
  } else if (trimmedDescription.length > 5000) {
    errors.descripcion = 'La descripción no puede superar 5000 caracteres.';
  }

  if (!trimmedProfession) {
    errors.profesion = 'Debes seleccionar una profesión.';
  }

  const rawImages = getImageUrlsFromInput(imagenesInput);
  const imageUrls = rawImages.length > 0 ? validateImageUrls(rawImages) : validateImageUrls(imagenes || []);

  if (rawImages.length > 0) {
    const invalidImages = rawImages.filter((image) => !isValidImageUrl(image));
    if (invalidImages.length > 0) {
      errors.imagenes = 'Alguna URL de imagen no es válida. Usa rutas como /src/assets/... o data:image/...';
    }
  } else if (Array.isArray(imagenes) && imagenes.length > 0) {
    const invalidImages = imagenes.filter((image) => !isValidImageUrl(image));
    if (invalidImages.length > 0) {
      errors.imagenes = 'Alguna URL de imagen no es válida. Usa rutas como /src/assets/... o data:image/...';
    }
  }

  return {
    isValid: Object.keys(errors).length === 0,
    errors,
    values: {
      nombre: trimmedName,
      descripcion: trimmedDescription,
      profesion: trimmedProfession,
      imagenes: imageUrls
    }
  };
};
