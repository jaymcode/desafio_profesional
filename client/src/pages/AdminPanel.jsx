import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import './AdminPanel.css';

const AdminPanel = () => {
  const [isMobile, setIsMobile] = useState(false);

  useEffect(() => {
    const checkMobile = () => {
      setIsMobile(window.innerWidth <= 768);
    };
    checkMobile();
    window.addEventListener('resize', checkMobile);
    return () => window.removeEventListener('resize', checkMobile);
  }, []);

  if (isMobile) {
    return (
      <div className="admin-panel">
        <div className="mobile-warning">
          <h2>Acceso no disponible</h2>
          <p>El panel de administración no está disponible en dispositivos móviles. Por favor, accede desde una computadora de escritorio.</p>
        </div>
      </div>
    );
  }

  return (
    <div className="admin-panel">
      <h1>Panel de Administración</h1>
      <nav>
        <Link to="/administracion/lista">Lista de Profesionales</Link>
        <Link to="/administracion/agregar">Agregar Profesional</Link>
      </nav>
    </div>
  );
};

export default AdminPanel;