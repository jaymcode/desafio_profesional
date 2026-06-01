import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import DOMPurify from 'dompurify';
import axiosInstance from '../api/axiosConfig';
import './Home.css';

const Home = () => {
  const [allProfesionales, setAllProfesionales] = useState([]);
  const [displayedProfesionales, setDisplayedProfesionales] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedDisciplina, setSelectedDisciplina] = useState('');
  const [isInitialLoad, setIsInitialLoad] = useState(true);

  const disciplinas = ['Kinesiología', 'Fisiatría', 'Fonoaudiología', 'Psicopedagogía', 'Pediatría'];
  const RESULTS_PER_PAGE = 4;

  // Initial load with random professionals
  useEffect(() => {
    fetchInitialRandomProfesionales();
  }, []);

  // Fetch all professionals for filtering and calculate pagination
  useEffect(() => {
    if (!isInitialLoad) {
      fetchAllProfesionales();
    }
  }, [searchTerm, selectedDisciplina]);

  // Update displayed professionals and pagination based on filters
  useEffect(() => {
    updatePaginatedResults();
  }, [allProfesionales, searchTerm, selectedDisciplina, currentPage]);

  const fetchInitialRandomProfesionales = async () => {
    try {
      // Fetch ALL professionals for random shuffling
      const response = await axiosInstance.get('/profesionales');
      const allProfesionales = response.data;
      
      // Shuffle array for random display
      const shuffled = [...allProfesionales].sort(() => Math.random() - 0.5);
      
      setAllProfesionales(shuffled);
      setCurrentPage(0);
      setIsInitialLoad(false);
      // Effect 3 will calculate pagination and display first 4 items
    } catch (error) {
      console.error('Error fetching initial random profesionales:', error);
      setIsInitialLoad(false);
    }
  };

  const fetchAllProfesionales = async () => {
    try {
      const response = await axiosInstance.get('/profesionales');
      setAllProfesionales(response.data);
      setCurrentPage(0);
    } catch (error) {
      console.error('Error fetching all profesionales:', error);
    }
  };

  const updatePaginatedResults = () => {
    // Filter based on search and discipline
    const filtered = allProfesionales.filter(prof => {
      const matchesSearch = prof.nombre.toLowerCase().includes(searchTerm.toLowerCase());
      const matchesDisciplina = !selectedDisciplina || prof.profesion === selectedDisciplina;
      return matchesSearch && matchesDisciplina;
    });

    // Calculate pagination
    const totalFilteredPages = Math.ceil(filtered.length / RESULTS_PER_PAGE);
    const startIndex = currentPage * RESULTS_PER_PAGE;
    const endIndex = startIndex + RESULTS_PER_PAGE;
    const paginatedResults = filtered.slice(startIndex, endIndex);

    setDisplayedProfesionales(paginatedResults);
    setTotalPages(Math.max(1, totalFilteredPages));
  };

  const handlePageChange = (newPage) => {
    if (newPage >= 0 && newPage < totalPages) {
      setCurrentPage(newPage);
    }
  };

  const handleDisciplinaChange = (disciplina) => {
    setSelectedDisciplina(disciplina === selectedDisciplina ? '' : disciplina);
    setCurrentPage(0);
  };

  const filteredProfesionales = displayedProfesionales;

  return (
    <div className="home">
      <section className="hero">
        <div className="hero-content">
          <h1 className="hero-title">Descubre los beneficios de nuestros servicios</h1>
          <p className="hero-subtitle">Profesionales especializados en Kinesiología, Fisiatría, Fonoaudiología, Psicopedagogía y Pediatría</p>
        </div>
      </section>

      <section className="search-section">
        <div className="search-container">
          <div className="buscador">
            <input 
              type="text" 
              placeholder="Buscar profesional..." 
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              className="search-input"
            />
          </div>
        </div>
      </section>

      <section className="disciplina-section">
        <div className="disciplina-container">
          <div className="disciplina-menu">
            <button
              className={`disciplina-btn ${selectedDisciplina === '' ? 'active' : ''}`}
              onClick={() => setSelectedDisciplina('')}
            >
              <div>Todas las disciplinas</div>
            </button>
            {disciplinas.map(disc => (
              <button
                key={disc}
                className={`disciplina-btn ${selectedDisciplina === disc ? 'active' : ''}`}
                onClick={() => handleDisciplinaChange(disc)}
              >
                <div>{disc}</div>
              </button>
            ))}
          </div>
        </div>
      </section>

      <section className="recomendaciones">
        <h2 className="section-title">Profesionales Recomendados</h2>
        <div className="profesionales-grid">
          {filteredProfesionales.length > 0 ? (
            filteredProfesionales.map(prof => (
              <Link key={prof.id} to={`/profesional/${prof.id}`} className="profesional-card">
                <div className="card-image-wrapper">
                  <img 
                    src={prof.imagenes && prof.imagenes.length > 0 ? prof.imagenes[0] : '/src/assets/img/pexels-pavel-danilyuk-5998448.jpg'} 
                    alt={prof.nombre}
                    className="card-image"
                  />
                </div>
                <div className="card-content">
                  <h3 className="card-title">{prof.nombre}</h3>
                  <p className="card-profession">{prof.profesion}</p>
                  <div className="card-footer">
                    <span className="view-detail">Ver detalle →</span>
                  </div>
                </div>
              </Link>
            ))
          ) : (
            <div className="no-results">
              <p>No se encontraron profesionales con los criterios especificados.</p>
            </div>
          )}
        </div>

        <div className="pagination">
          <button 
            onClick={() => handlePageChange(0)} 
            disabled={currentPage === 0}
            className="pagination-btn"
          >
            Inicio
          </button>
          <button 
            onClick={() => handlePageChange(currentPage - 1)} 
            disabled={currentPage === 0}
            className="pagination-btn"
          >
            ← Anterior
          </button>
          <span className="page-info">Página {currentPage + 1} de {totalPages}</span>
          <button 
            onClick={() => handlePageChange(currentPage + 1)} 
            disabled={currentPage === totalPages - 1 || totalPages === 0}
            className="pagination-btn"
          >
            Siguiente →
          </button>
          <button 
            onClick={() => handlePageChange(totalPages - 1)} 
            disabled={currentPage === totalPages - 1 || totalPages === 0}
            className="pagination-btn"
          >
            Final
          </button>
        </div>
      </section>
    </div>
  );
};

export default Home;