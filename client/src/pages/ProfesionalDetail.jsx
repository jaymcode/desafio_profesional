import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import DOMPurify from 'dompurify';
import axiosInstance from '../api/axiosConfig';
import './ProfesionalDetail.css';

const ProfesionalDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [profesional, setProfesional] = useState(null);
  const [selectedImage, setSelectedImage] = useState(0);
  const [showAllImages, setShowAllImages] = useState(false);
  const [lightboxOpen, setLightboxOpen] = useState(false);
  const [lightboxImage, setLightboxImage] = useState(0);

  useEffect(() => {
    fetchProfesional();
  }, [id]);

  useEffect(() => {
    if (lightboxOpen) {
      document.body.style.overflow = 'hidden';
    } else {
      document.body.style.overflow = 'unset';
    }
  }, [lightboxOpen]);

  const fetchProfesional = async () => {
    try {
      const response = await axiosInstance.get(`/profesionales/${id}`);
      setProfesional(response.data);
    } catch (error) {
      console.error('Error fetching profesional:', error);
    }
  };

  if (!profesional) return <div className="loading">Cargando...</div>;

  const images = profesional.imagenes && profesional.imagenes.length > 0 
    ? profesional.imagenes 
    : ['/src/assets/img/pexels-pavel-danilyuk-5998448.jpg'];

  const openLightbox = (index) => {
    setLightboxImage(index);
    setLightboxOpen(true);
  };

  const nextImage = () => {
    setLightboxImage((prev) => (prev + 1) % images.length);
  };

  const previousImage = () => {
    setLightboxImage((prev) => (prev - 1 + images.length) % images.length);
  };

  return (
    <div className="profesional-detail">
      <div className="detail-body">
        <button className="back-button" onClick={() => navigate(-1)}>
          ← Volver
        </button>
        <div className="detail-container">
          <section className="gallery-section">
            <div className="gallery-container">
              <div className="main-image-wrapper">  
                <img 
                    src={images[selectedImage]} 
                    alt={`Imagen ${selectedImage + 1}`}
                    className="main-image"
                />
              </div>
              
              {images.length > 1 && (
                <div className="thumbnails">
                  {images.slice(0, 4).map((img, index) => (
                    <button
                      key={index}
                      className={`thumbnail ${selectedImage === index ? 'active' : ''}`}
                      onClick={() => setSelectedImage(index)}
                      aria-label={`View image ${index + 1}`}
                    >
                      <img src={img} alt={`Thumbnail ${index + 1}`} />
                    </button>
                  ))}
                </div>
              )}
            </div>
            
            {images.length > 1 && (
              <button className="view-more-button" onClick={() => openLightbox(selectedImage)}
                  aria-label="Open image in lightbox"
              >
                Ver más
              </button>
            )}
          </section>

          <section className="description-section">
            <h1 className="detail-title">{profesional.nombre}</h1>
            <div className="profession-badge">{profesional.profesion}</div>
            <p className="detail-description">{DOMPurify.sanitize(profesional.descripcion)}</p>
            <button className="book-button">Reservar turno</button>
          </section>
        </div>
      </div>

      {lightboxOpen && (
        <div className="lightbox-backdrop" onClick={() => setLightboxOpen(false)}>
          <div className="lightbox-container" onClick={(e) => e.stopPropagation()}>
            <div className="lightbox-content">
              <div className="lightbox-view">
                <div className="lightbox-frame">
                  <figure className="lightbox-figure">
                    <img 
                      className="lightbox-image" 
                      src={images[lightboxImage]} 
                      alt={`Imagen ${lightboxImage + 1}`}
                    />
                  </figure>
                </div>
              </div>

              {images.length > 1 && (
                <>
                  <button 
                    className="lightbox-control lightbox-left" 
                    onClick={previousImage}
                    aria-label="Previous image"
                  />
                  <button 
                    className="lightbox-control lightbox-right" 
                    onClick={nextImage}
                    aria-label="Next image"
                  />
                </>
              )}

              <button 
                className="lightbox-control lightbox-close" 
                onClick={() => setLightboxOpen(false)}
                aria-label="Close lightbox"
              />

              {images.length > 1 && (
                <div className="lightbox-strip">
                  {images.map((img, index) => (
                    <button
                      key={index}
                      className={`lightbox-thumbnail ${lightboxImage === index ? 'active' : ''}`}
                      onClick={() => setLightboxImage(index)}
                      aria-label={`View image ${index + 1}`}
                      aria-current={lightboxImage === index ? 'true' : 'false'}
                    >
                      <div className="lightbox-thumbnail-wrapper">
                        <img src={img} alt={`Thumbnail ${index + 1}`} />
                      </div>
                    </button>
                  ))}
                </div>
              )}
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default ProfesionalDetail;