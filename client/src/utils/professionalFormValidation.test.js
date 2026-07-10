import { describe, expect, it } from 'vitest';
import { getImageUrlsFromInput, validateProfessionalForm } from './professionalFormValidation';

describe('validateProfessionalForm', () => {
  it('returns clear messages when required fields are missing', () => {
    const result = validateProfessionalForm({
      nombre: '   ',
      descripcion: 'Muy corto',
      profesion: '',
      imagenes: [],
      imagenesInput: ''
    });

    expect(result.isValid).toBe(false);
    expect(result.errors.nombre).toBe('El nombre es obligatorio.');
    expect(result.errors.descripcion).toBe('La descripción debe tener al menos 20 caracteres.');
    expect(result.errors.profesion).toBe('Debes seleccionar una profesión.');
  });

  it('accepts a valid list of image URLs and trims whitespace', () => {
    const result = validateProfessionalForm({
      nombre: 'Dra. Ana',
      descripcion: 'Profesional con más de 10 años de experiencia.',
      profesion: 'Kinesiología',
      imagenes: [],
      imagenesInput: ' /src/assets/img/uno.jpg , /src/assets/img/dos.png '
    });

    expect(result.isValid).toBe(true);
    expect(result.errors.imagenes).toBeUndefined();
    expect(result.values.imagenes).toEqual(['/src/assets/img/uno.jpg', '/src/assets/img/dos.png']);
  });
});

describe('getImageUrlsFromInput', () => {
  it('splits and trims pasted image URLs', () => {
    expect(getImageUrlsFromInput('/src/assets/img/one.jpg, /src/assets/img/two.png')).toEqual([
      '/src/assets/img/one.jpg',
      '/src/assets/img/two.png'
    ]);
  });
});
