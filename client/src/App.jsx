import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './pages/Home';
import ProfesionalDetail from './pages/ProfesionalDetail';
import AdminPanel from './pages/AdminPanel';
import ListaProfesionales from './pages/ListaProfesionales';
import AgregarProfesional from './pages/AgregarProfesional';
import Register from './pages/Register';
import Login from './pages/Login';
import ProtectedRoute from './components/ProtectedRoute';
import './App.css';

function App() {
  const [user, setUser] = useState(() => {
    try {
      const storedUser = localStorage.getItem('currentUser');
      return storedUser ? JSON.parse(storedUser) : null;
    } catch (error) {
      localStorage.removeItem('currentUser');
      return null;
    }
  });

  useEffect(() => {
    if (user) {
      localStorage.setItem('currentUser', JSON.stringify(user));
    } else {
      localStorage.removeItem('currentUser');
    }
  }, [user]);

  return (
    <Router>
      <div className="App">
        <Header user={user} onLogout={() => setUser(null)} />
        <main>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/profesional/:id" element={<ProfesionalDetail />} />
            <Route path="/administracion" element={<ProtectedRoute user={user}><AdminPanel /></ProtectedRoute>} />
            <Route path="/administracion/lista" element={<ProtectedRoute user={user}><ListaProfesionales /></ProtectedRoute>} />
            <Route path="/administracion/agregar" element={<ProtectedRoute user={user}><AgregarProfesional /></ProtectedRoute>} />
            <Route path="/registro" element={<Register />} />
            <Route path="/login" element={<Login onLogin={setUser} />} />
            <Route path="/perfil" element={<ProtectedRoute user={user}><div className="page-placeholder">Perfil de usuario</div></ProtectedRoute>} />
            <Route path="/configuracion" element={<ProtectedRoute user={user}><div className="page-placeholder">Configuración</div></ProtectedRoute>} />
            <Route path="/mis-turnos" element={<ProtectedRoute user={user}><div className="page-placeholder">Mis turnos reservados</div></ProtectedRoute>} />
            <Route path="/notificaciones" element={<ProtectedRoute user={user}><div className="page-placeholder">Notificaciones</div></ProtectedRoute>} />
            <Route path="/ayuda" element={<ProtectedRoute user={user}><div className="page-placeholder">Ayuda</div></ProtectedRoute>} />
          </Routes>
        </main>
        <Footer />
      </div>
    </Router>
  );
}

export default App;