import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import logo from '../assets/cimlogosinfo2.png';
import './Header.css';

const Header = () => {
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

  const toggleMobileMenu = () => {
    setMobileMenuOpen(!mobileMenuOpen);
  };

  const closeMobileMenu = () => {
    setMobileMenuOpen(false);
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
        <button className="btn btn-secondary">Crear cuenta</button>
        <button className="btn btn-primary">Iniciar sesión</button>
      </div>
    </header>
  );
};

export default Header;