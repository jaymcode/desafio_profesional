import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../api/axiosConfig';
import { handleApiError } from '../utils/errorHandler';
import { DEFAULT_PROFESSIONAL_IMAGE, validateProfessionalForm } from '../utils/professionalFormValidation';
import './AgregarProfesional.css';

const AgregarProfesional = () => {
  const [form, setForm] = useState({
    nombre: '',
    descripcion: '',
    profesion: 'Kinesiología',
    imagenes: []
  });
  const [imagenesInput, setImagenesInput] = useState('');
  const [error, setError] = useState('');
  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const disciplinas = ['Kinesiología', 'Fisiatría', 'Fonoaudiología', 'Psicopedagogía', 'Pediatría'];

  const handleChange = (e) => {
    const { name, value } = e.target;
    const nextForm = { ...form, [name]: value };
    setForm(nextForm);

    if (errors[name]) {
      setErrors((prevErrors) => ({ ...prevErrors, [name]: undefined }));
    }
  };

  const handleBlur = (e) => {
    const { name, value } = e.target;
    const validation = validateProfessionalForm({
      ...form,
      [name]: value,
      imagenesInput
    });

    setErrors((prevErrors) => ({ ...prevErrors, [name]: validation.errors[name] }));
  };

  const handleImagenesChange = (e) => {
    const value = e.target.value;
    setImagenesInput(value);

    if (errors.imagenes) {
      setErrors((prevErrors) => ({ ...prevErrors, imagenes: undefined }));
    }
  };

  const handleImagenesBlur = () => {
    const validation = validateProfessionalForm({
      ...form,
      imagenesInput
    });

    setErrors((prevErrors) => ({ ...prevErrors, imagenes: validation.errors.imagenes }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    const validation = validateProfessionalForm({
      ...form,
      imagenesInput
    });

    if (!validation.isValid) {
      setErrors(validation.errors);
      setError('Revisa los campos marcados para completar el formulario correctamente.');
      setLoading(false);
      return;
    }

    try {
      const dataToSubmit = {
        nombre: validation.values.nombre,
        descripcion: validation.values.descripcion,
        profesion: validation.values.profesion,
        imagenes: validation.values.imagenes.length > 0 ? validation.values.imagenes : [DEFAULT_PROFESSIONAL_IMAGE]
      };

      await axiosInstance.post('/profesionales', dataToSubmit);
      navigate('/administracion/lista');
    } catch (submitError) {
      const errorMsg = handleApiError(submitError);
      setError(errorMsg);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="agregar-profesional">
      <div className="form-container">
        <h1>Agregar Nuevo Profesional</h1>

        {error && <div className="error-message">{error}</div>}

        <form onSubmit={handleSubmit} className="profesional-form">
          <div className="form-group">
            <label htmlFor="nombre">Nombre</label>
            <input
              id="nombre"
              type="text"
              name="nombre"
              placeholder="Ej: Dr. Juan Pérez"
              value={form.nombre}
              onChange={handleChange}
              onBlur={handleBlur}
              maxLength="255"
              className={errors.nombre ? 'input-error' : ''}
              aria-invalid={Boolean(errors.nombre)}
            />
            {errors.nombre && <span className="field-error">{errors.nombre}</span>}
          </div>

          <div className="form-group">
            <label htmlFor="descripcion">Descripción</label>
            <textarea
              id="descripcion"
              name="descripcion"
              placeholder="Describe la experiencia y especialidades del profesional..."
              value={form.descripcion}
              onChange={handleChange}
              onBlur={handleBlur}
              maxLength="5000"
              rows="6"
              className={errors.descripcion ? 'input-error' : ''}
              aria-invalid={Boolean(errors.descripcion)}
            />
            {errors.descripcion && <span className="field-error">{errors.descripcion}</span>}
          </div>

          <div className="form-group">
            <label htmlFor="profesion">Profesión</label>
            <select
              id="profesion"
              name="profesion"
              value={form.profesion}
              onChange={handleChange}
              onBlur={handleBlur}
              className={errors.profesion ? 'input-error' : ''}
              aria-invalid={Boolean(errors.profesion)}
            >
              {disciplinas.map((disc) => (
                <option key={disc} value={disc}>{disc}</option>
              ))}
            </select>
            {errors.profesion && <span className="field-error">{errors.profesion}</span>}
          </div>

          <div className="form-group">
            <label htmlFor="imagenes">URLs de Imágenes (separadas por coma)</label>
            <textarea
              id="imagenes"
              name="imagenes"
              placeholder="Ej: /src/assets/img/img1.jpg, /src/assets/img/img2.jpg"
              value={imagenesInput}
              onChange={handleImagenesChange}
              onBlur={handleImagenesBlur}
              rows="4"
              className={errors.imagenes ? 'input-error' : ''}
              aria-invalid={Boolean(errors.imagenes)}
            />
            {errors.imagenes && <span className="field-error">{errors.imagenes}</span>}
            <small>Si no añades imágenes, se usará una por defecto</small>
          </div>

          <div className="form-actions">
            <button type="submit" className="submit-btn" disabled={loading}>
              {loading ? 'Guardando...' : 'Guardar Profesional'}
            </button>
            <button
              type="button"
              className="cancel-btn"
              onClick={() => navigate('/administracion/lista')}
            >
              Cancelar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default AgregarProfesional;