import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../api/axiosConfig';
import { handleApiError } from '../utils/errorHandler';
import './ListaProfesionales.css';

const ListaProfesionales = () => {
  const [profesionales, setProfesionales] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchProfesionales();
  }, []);

  const fetchProfesionales = async () => {
    try {
      const response = await axiosInstance.get('/profesionales');
      setProfesionales(response.data);
    } catch (error) {
      console.error('Error fetching profesionales:', error);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('¿Estás seguro de eliminar este profesional?')) {
      try {
        await axiosInstance.delete(`/profesionales/${id}`);
        fetchProfesionales();
      } catch (error) {
        console.error('Error deleting profesional:', error);
        const errorMsg = handleApiError(error);
        alert(errorMsg);
      }
    }
  };

  return (
    <div className="lista-profesionales">
      <div className="admin-content">
        <h1>Lista de Profesionales</h1>
        {profesionales.length > 0 ? (
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Nombre</th>
                  <th>Profesión</th>
                  <th>Acciones</th>
                </tr>
              </thead>
              <tbody>
                {profesionales.map(prof => (
                  <tr key={prof.id}>
                    <td>{prof.id}</td>
                    <td>{prof.nombre}</td>
                    <td>{prof.profesion}</td>
                    <td>
                      <button 
                        className="delete-btn"
                        onClick={() => handleDelete(prof.id)}
                      >
                        Eliminar
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        ) : (
          <p className="no-data">No hay profesionales registrados</p>
        )}
      </div>
    </div>
  );
};

export default ListaProfesionales;