import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axiosInstance from '../api/axiosConfig';
import './Register.css';

const Login = ({ onLogin }) => {
  const [form, setForm] = useState({ email: '', password: '' });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
    setError('');
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');

    try {
      const response = await axiosInstance.post('/usuarios/login', form);

      if (response.status >= 400) {
        const backendMessage = response.data?.message || response.data?.error || 'No se pudo iniciar sesión. Verifica tus datos.';
        setError(backendMessage);
        return;
      }

      const user = response.data;
      onLogin(user);
      navigate('/');
    } catch (err) {
      const backendMessage = err.response?.data?.message || err.response?.data?.error || err.message;
      const message = backendMessage || 'No se pudo iniciar sesión. Verifica tus datos.';
      setError(message);
      console.error('Login failed:', err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="register-page">
      <div className="register-card">
        <h1>Iniciar sesión</h1>
        <p>Ingresa tus credenciales para acceder a tu cuenta.</p>

        <form onSubmit={handleSubmit} className="register-form">
          <label>
            Correo electrónico
            <input type="email" name="email" value={form.email} onChange={handleChange} required />
          </label>
          <label>
            Contraseña
            <input type="password" name="password" value={form.password} onChange={handleChange} required />
          </label>

          {error && <p className="register-error">{error}</p>}

          <button type="submit" className="btn btn-primary" disabled={loading}>
            {loading ? 'Ingresando...' : 'Ingresar'}
          </button>
        </form>

        <p className="register-link">
          ¿No tienes cuenta? <Link to="/registro">Crear cuenta</Link>
        </p>
      </div>
    </div>
  );
};

export default Login;
