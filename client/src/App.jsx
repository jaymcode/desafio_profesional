import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './pages/Home';
import ProfesionalDetail from './pages/ProfesionalDetail';
import AdminPanel from './pages/AdminPanel';
import ListaProfesionales from './pages/ListaProfesionales';
import AgregarProfesional from './pages/AgregarProfesional';
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        <Header />
        <main>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/profesional/:id" element={<ProfesionalDetail />} />
            <Route path="/administracion" element={<AdminPanel />} />
            <Route path="/administracion/lista" element={<ListaProfesionales />} />
            <Route path="/administracion/agregar" element={<AgregarProfesional />} />
          </Routes>
        </main>
        <Footer />
      </div>
    </Router>
  );
}

export default App;