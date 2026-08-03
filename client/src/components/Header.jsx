import React, { useEffect, useRef, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import logo from '../assets/cimlogosinfo2.png';
import './Header.css';

const Header = ({ user, onLogout }) => {
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);
  const [profileOpen, setProfileOpen] = useState(false);
  const profileMenuRef = useRef(null);
  const navigate = useNavigate();

  useEffect(() => {
    const handleClickOutside = (event) => {
      if (profileMenuRef.current && !profileMenuRef.current.contains(event.target)) {
        setProfileOpen(false);
      }
    };

    document.addEventListener('mousedown', handleClickOutside);
    return () => document.removeEventListener('mousedown', handleClickOutside);
  }, []);

  const toggleMobileMenu = () => {
    setMobileMenuOpen(!mobileMenuOpen);
  };

  const closeMobileMenu = () => {
    setMobileMenuOpen(false);
  };

  const handleLogout = () => {
    onLogout();
    setProfileOpen(false);
    closeMobileMenu();
    navigate('/');
  };

  const getInitials = (name = '') => {
    const cleanedName = String(name || '').trim();
    if (!cleanedName) return 'U';

    const parts = cleanedName.split(/\s+/).filter(Boolean);
    if (parts.length === 1) {
      return parts[0].slice(0, 2).toUpperCase();
    }

    return `${parts[0][0]}${parts[parts.length - 1][0]}`.toUpperCase();
  };

  return (
    <header className="header">
      <div className="header-left">
        <Link to="/" className="logo-link" onClick={closeMobileMenu}>
          <img src={logo} alt="Centro Integral Multidisciplinario" className="logo" />
          <span className="lema">Centro Integral Multidisciplinario</span>
        </Link>
      </div>

      <button className="mobile-menu-toggle" onClick={toggleMobileMenu}>
        <span></span>
        <span></span>
        <span></span>
      </button>

      <div className={`header-right ${mobileMenuOpen ? 'mobile-open' : ''}`}>
        {user ? (
          <div className="profile-menu-wrapper" ref={profileMenuRef}>
            <button
              className="user-pill"
              onClick={() => setProfileOpen((prev) => !prev)}
              type="button"
            >
              <span className="avatar">{getInitials(user.nombre || user.email)}</span>
              <span className="user-name">{user.nombre || user.email}</span>
            </button>

            {profileOpen && (
              <div className="profile-menu" role="menu">
                <div className="profile-menu-header">
                  <span className="avatar large">{getInitials(user.nombre || user.email)}</span>
                  <div>
                    <strong>{user.nombre || user.email}</strong>
                    <p>{user.email}</p>
                  </div>
                </div>
                <Link to="/perfil" className="profile-menu-item" onClick={() => { setProfileOpen(false); closeMobileMenu(); }}>Perfil</Link>
                <Link to="/configuracion" className="profile-menu-item" onClick={() => { setProfileOpen(false); closeMobileMenu(); }}>Configuración</Link>
                <Link to="/mis-turnos" className="profile-menu-item" onClick={() => { setProfileOpen(false); closeMobileMenu(); }}>Mis turnos reservados</Link>
                <Link to="/notificaciones" className="profile-menu-item" onClick={() => { setProfileOpen(false); closeMobileMenu(); }}>Notificaciones</Link>
                <Link to="/ayuda" className="profile-menu-item" onClick={() => { setProfileOpen(false); closeMobileMenu(); }}>Ayuda</Link>
                <button className="profile-menu-item danger" type="button" onClick={handleLogout}>Cerrar sesión</button>
              </div>
            )}
          </div>
        ) : (
          <>
            <Link to="/registro" className="btn btn-secondary" onClick={closeMobileMenu}>Crear cuenta</Link>
            <Link to="/login" className="btn btn-primary" onClick={closeMobileMenu}>Iniciar sesión</Link>
          </>
        )}
      </div>
    </header>
  );
};

export default Header;
