import { render, screen } from '@testing-library/react';
import { MemoryRouter, Route, Routes } from 'react-router-dom';
import { describe, expect, it } from 'vitest';
import ProtectedRoute from './ProtectedRoute';

describe('ProtectedRoute', () => {
  it('redirects unauthenticated users to the login page', () => {
    render(
      <MemoryRouter initialEntries={['/perfil']}>
        <Routes>
          <Route path="/login" element={<div>Login page</div>} />
          <Route
            path="/perfil"
            element={
              <ProtectedRoute user={null}>
                <div>Contenido protegido</div>
              </ProtectedRoute>
            }
          />
        </Routes>
      </MemoryRouter>
    );

    expect(screen.getByText('Login page')).toBeTruthy();
  });

  it('renders protected content for authenticated users', () => {
    render(
      <MemoryRouter initialEntries={['/perfil']}>
        <Routes>
          <Route path="/login" element={<div>Login page</div>} />
          <Route
            path="/perfil"
            element={
              <ProtectedRoute user={{ id: 1, nombre: 'Usuario' }}>
                <div>Contenido protegido</div>
              </ProtectedRoute>
            }
          />
        </Routes>
      </MemoryRouter>
    );

    expect(screen.getByText('Contenido protegido')).toBeTruthy();
  });
});
