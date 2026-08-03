import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axiosInstance from '../api/axiosConfig';
import './Register.css';

const Register = () => {
  const [form, setForm] = useState({
    nombre: '',
    apellido: '',
    email: '',
    password: '',
    confirmPassword: ''
  });
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
    setError('');
    setSuccess('');
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    setSuccess('');

    if (form.password !== form.confirmPassword) {
      setError('Las contraseñas no coinciden.');
      setLoading(false);
      return;
    }

    try {
      await axiosInstance.post('/usuarios/registrar', {
        nombre: form.nombre,
        apellido: form.apellido,
        email: form.email,
        password: form.password
      });

      setSuccess('Cuenta creada correctamente. Ahora puedes iniciar sesión.');
      setTimeout(() => navigate('/login'), 800);
    } catch (err) {
      const message = err.response?.data?.message || 'No se pudo crear la cuenta. Inténtalo nuevamente.';
      setError(message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="register-page">
      <div className="register-card">
        <h1>Crear cuenta</h1>
        <p>Regístrate para reservar turnos y gestionar tus datos.</p>

        <form onSubmit={handleSubmit} className="register-form">
          <label>
            Nombre
            <input type="text" name="nombre" value={form.nombre} onChange={handleChange} required />
          </label>
          <label>
            Apellido
            <input type="text" name="apellido" value={form.apellido} onChange={handleChange} required />
          </label>
          <label>
            Correo electrónico
            <input type="email" name="email" value={form.email} onChange={handleChange} required />
          </label>
          <label>
            Contraseña
            <input type="password" name="password" value={form.password} onChange={handleChange} required />
          </label>
          <label>
            Confirmar contraseña
            <input type="password" name="confirmPassword" value={form.confirmPassword} onChange={handleChange} required />
          </label>

          {error && <p className="register-error">{error}</p>}
          {success && <p className="register-success">{success}</p>}

          <button type="submit" className="btn btn-primary" disabled={loading}>
            {loading ? 'Creando cuenta...' : 'Registrarme'}
          </button>
        </form>

        <p className="register-link">
          ¿Ya tienes cuenta? <Link to="/login">Iniciar sesión</Link>
        </p>
      </div>
    </div>
  );
};

export default Register;
