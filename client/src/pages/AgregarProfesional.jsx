import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../api/axiosConfig';
import { validateImageUrls } from '../utils/imageValidator';
import { handleApiError } from '../utils/errorHandler';
import './AgregarProfesional.css';

const AgregarProfesional = () => {
  const [form, setForm] = useState({
    nombre: '',
    descripcion: '',
    profesion: 'Kinesiología',
    imagenes: []
  });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const disciplinas = ['Kinesiología', 'Fisiatría', 'Fonoaudiología', 'Psicopedagogía', 'Pediatría'];

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleImagenesChange = (e) => {
    const value = e.target.value;
    const rawImages = value ? value.split(',').map(img => img.trim()) : [];
    const validatedImages = validateImageUrls(rawImages);
    setForm({ ...form, imagenes: validatedImages });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    if (!form.nombre.trim()) {
      setError('El nombre es requerido');
      setLoading(false);
      return;
    }

    if (!form.descripcion.trim()) {
      setError('La descripción es requerida');
      setLoading(false);
      return;
    }

    try {
      const dataToSubmit = {
        ...form,
        imagenes: form.imagenes.length > 0 ? form.imagenes : ['/src/assets/img/pexels-pavel-danilyuk-5998448.jpg']
      };

      await axiosInstance.post('/profesionales', dataToSubmit);
      navigate('/administracion/lista');
    } catch (error) {
      const errorMsg = handleApiError(error);
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
              maxLength="255"
              required 
            />
          </div>

          <div className="form-group">
            <label htmlFor="descripcion">Descripción</label>
            <textarea 
              id="descripcion"
              name="descripcion" 
              placeholder="Describe la experiencia y especialidades del profesional..." 
              value={form.descripcion} 
              onChange={handleChange}
              maxLength="5000"
              rows="6"
              required 
            />
          </div>

          <div className="form-group">
            <label htmlFor="profesion">Profesión</label>
            <select 
              id="profesion"
              name="profesion" 
              value={form.profesion}
              onChange={handleChange}
              required
            >
              {disciplinas.map(disc => (
                <option key={disc} value={disc}>{disc}</option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label htmlFor="imagenes">URLs de Imágenes (separadas por coma)</label>
            <textarea 
              id="imagenes"
              name="imagenes" 
              placeholder="Ej: /src/assets/img/img1.jpg, /src/assets/img/img2.jpg" 
              value={form.imagenes.join(', ')}
              onChange={handleImagenesChange}
              rows="4"
            />
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