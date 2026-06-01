# Centro Integral Multidisciplinario - Aplicación Web de Reserva de Turnos

Aplicación web completa y moderna para la gestión y reserva de turnos en un Centro Integral Multidisciplinario con profesionales en Kinesiología, Fisiatría, Fonoaudiología, Psicopedagogía y Pediatría.

## 🎯 Características Principales

- **Home**: Hero section, buscador, filtros por disciplina (Kinesiología, Fisiatría, Fonoaudiología, Psicopedagogía, Pediatría), grid responsivo
- **Detalle de Profesionales**: Galería interactiva de imágenes, descripciones, botón de reserva
- **Panel de Administración**: CRUD completo de profesionales (no disponible en móviles)
- **Diseño Responsive**: Optimizado para desktop, tablet y mobile
- **Validaciones**: Nombres únicos, campos requeridos, mensajes de error

## ✅ Requisitos Previos

Antes de comenzar, asegurate de tener instalado:

- **Node.js** 16+ (incluye npm)
- **Java JDK** 17 o superior
- **Maven** 3.6+
- **Git** (opcional, para clonar el repositorio)
- Un navegador moderno (Chrome, Firefox, Edge, Safari)

## 🛠️ Tecnologías

### Frontend
- **React 19**: Framework moderno con hooks
- **Vite**: Build tool rápido y eficiente
- **React Router v7**: Navegación SPA
- **Axios**: Cliente HTTP para API
- **CSS3**: Responsive design, flexbox, grid
- **Fuentes**: Saira (titulos), DM Sans (body)

### Backend
- **Java 17**: Lenguaje de programación
- **Spring Boot 3.2**: Framework web robusto
- **Spring Data JPA**: ORM para base de datos
- **H2 Database**: Base de datos en memoria (desarrollo local)
- **Maven**: Gestor de dependencias
- **Nota**: Backend se ejecuta en repositorio separado en puerto 8080

## 🎨 Paleta de Colores

```
Primario:    #68e9ba (Verde/Turquesa)
Secundario:  #00313e (Azul Oscuro)
Fondo:       #f8f8f8 (Gris Claro)
Texto:       #333 (Gris Oscuro)
Blanco:      #ffffff
```

## 📁 Estructura del Proyecto

```
Frontend (React):
├── src/
│   ├── components/
│   │   ├── Header.jsx / Header.css
│   │   └── Footer.jsx / Footer.css
│   ├── pages/
│   │   ├── Home.jsx / Home.css
│   │   ├── ProfesionalDetail.jsx / ProfesionalDetail.css
│   │   ├── AdminPanel.jsx / AdminPanel.css
│   │   ├── ListaProfesionales.jsx / ListaProfesionales.css
│   │   └── AgregarProfesional.jsx / AgregarProfesional.css
│   ├── App.jsx / App.css
│   └── main.jsx
├── index.html
├── package.json
└── vite.config.js

Backend (Java):
├── src/main/java/com/centrointegral/backend/
│   ├── entity/Profesional.java
│   ├── repository/ProfesionalRepository.java
│   ├── service/ProfesionalService.java
│   ├── controller/ProfesionalController.java
│   ├── DataLoader.java
│   └── CentroIntegralBackendApplication.java
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

## 🚀 Instalación y Ejecución

#### Backend (Spring Boot)

```bash
# Navegar al directorio del backend
cd <ruta-del-backend>/centro-integral-backend

# Compilar e instalar dependencias
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```

**Resultado:**
- Backend accesible en: http://localhost:8080
- H2 Console disponible en: http://localhost:8080/h2-console (usuario: sa, sin contraseña)

#### Frontend (React + Vite)

```bash
# En otra terminal, navegar al directorio del frontend
cd <ruta-del-frontend>/centro-integral-multidisciplinario

# Instalar dependencias (solo en primera ejecución)
npm install

# Ejecutar servidor de desarrollo
npm run dev
```

**Resultado:**
- Frontend accesible en: http://localhost:5173

### ⚙️ Configuración del Entorno

**Backend (application.properties):**
- Spring escucha en puerto `8080`
- CORS habilitado para desarrollo local
- H2 reinicia con datos de ejemplo en cada ejecución

**Frontend (.env opcional):**
```
VITE_API_URL=http://localhost:8080/api
```

## 📡 API REST - Endpoints Disponibles

**Base URL:** `http://localhost:8080/api/profesionales`

### Listado de Endpoints Implementados

| Método | Endpoint | Descripción | Ejemplo |
|--------|----------|-------------|---------|
| GET | `/` | Obtener todos los profesionales | `GET /api/profesionales` |
| GET | `/{id}` | Obtener detalle de profesional por ID | `GET /api/profesionales/1` |
| POST | `/` | Crear nuevo profesional | `POST /api/profesionales` |
| DELETE | `/{id}` | Eliminar profesional por ID | `DELETE /api/profesionales/1` |

**Nota sobre Paginación:** La aplicación frontend implementa **paginación del lado del cliente** (4 profesionales por página). Todos los profesionales se obtienen con un único GET `/api/profesionales` y se paginan en el navegador.

### Profesiones Válidas

La aplicación acepta solo estas disciplinas (profesiones):
- **Kinesiología**
- **Fisiatría**
- **Fonoaudiología**
- **Psicopedagogía**
- **Pediatría**

### Estructura de Datos - Profesional

```json
{
  "id": 1,
  "nombre": "Dra. Rosa Martínez",
  "descripcion": "Especialista en Kinesiología con 10 años de experiencia en rehabilitación motora.",
  "profesion": "Kinesiología",
  "imagenes": [
    "/src/assets/img/image1.jpg",
    "/src/assets/img/image2.jpg",
    "/src/assets/img/image3.jpg"
  ]
}
```

### Ejemplos de Uso con Axios

```javascript
// Obtener todos los profesionales (usado en Home, ProfesionalDetail, ListaProfesionales)
axios.get('http://localhost:8080/api/profesionales')
  .then(response => {
    console.log('Profesionales:', response.data);
    // El frontend luego filtra, busca y pagina estos datos localmente
  })

// Obtener profesional específico por ID (usado en ProfesionalDetail)
axios.get('http://localhost:8080/api/profesionales/1')
  .then(response => {
    console.log('Detalle del profesional:', response.data);
  })

// Crear nuevo profesional (usado en AgregarProfesional)
axios.post('http://localhost:8080/api/profesionales', {
  nombre: "Dra. María García",
  descripcion: "Especialista en Fisiatría y rehabilitación deportiva",
  profesion: "Fisiatría",
  imagenes: ["/src/assets/img/new1.jpg"]
})
  .then(response => {
    console.log('Profesional creado:', response.data);
  })
  .catch(error => {
    console.error('Error:', error.response?.data?.message);
  })

// Eliminar profesional (usado en ListaProfesionales)
axios.delete('http://localhost:8080/api/profesionales/1')
  .then(response => {
    console.log('Profesional eliminado');
  })
  .catch(error => {
    console.error('Error al eliminar:', error.response?.data?.message);
  })
```

## 📱 Responsive Design

- **Desktop (1024px+)**: Diseño completo con sidebar, grid de 4 columnas
- **Tablet (768px - 1023px)**: Grid de 2-3 columnas, menús adaptados
- **Mobile (<768px)**: Single column, menú mobile, panel admin deshabilitado

## ✨ Features Implementadas

### Páginas Principales

#### Home (User Stories #2, #4, #8)
- ✅ Buscador de profesionales funcional (búsqueda en tiempo real)
- ✅ Filtro por disciplina: Kinesiología, Fisiatría, Fonoaudiología, Psicopedagogía, Pediatría
- ✅ Grid de 4 profesionales aleatorios
- ✅ Paginación completa (Inicio, Anterior, Siguiente, Final)
- ✅ Contador de páginas en tiempo real

#### Detalle de Profesional (User Stories #5, #6)
- ✅ Header con nombre y botón de vuelta
- ✅ Descripción detallada del profesional
- ✅ Galería interactiva con imagen principal + 4 thumbnails
- ✅ Galería completa expandible
- ✅ Botón "Reservar turno"
- ✅ Diseño responsive adaptado a todos los dispositivos

#### Panel de Administración (User Story #9)
- ✅ URL dedicada `/administracion`
- ✅ Menú con opciones principales
- ✅ Mensaje de no disponibilidad en móviles
- ✅ Navegación clara y accesible

#### CRUD de Profesionales (User Stories #3, #10, #11)
- ✅ **Agregar Profesional**: Formulario con validación en tiempo real
  - Validación de nombres únicos
  - Campos requeridos verificados
  - Manejo de errores descriptivos
- ✅ **Listar Profesionales**: Tabla dinámica con ID, Nombre, Profesión
  - Ordenamiento por columnas
  - Visualización paginada
- ✅ **Eliminar Profesional**: Confirmación de eliminación, actualización en DB
  - Feedback visual inmediato

#### Componentes Globales (User Stories #1, #7)
- ✅ **Header**: 
  - Fijo al 100% del ancho
  - Logo y lema clickeables (redirigen a home)
  - Botones de "Crear cuenta" e "Iniciar sesión"
  - Responsive con menú mobile "hamburguesa"
- ✅ **Footer**:
  - Copyright dinámico con año actual
  - Enlaces a redes sociales
  - Responsive en todos los dispositivos

### Validaciones y Seguridad

- ✅ Nombres únicos de profesionales (nivel DB)
- ✅ Campos requeridos en formularios (cliente y servidor)
- ✅ Confirmación antes de eliminar profesionales
- ✅ Mensajes de error descriptivos y útiles
- ✅ Estados disabled en botones durante procesamiento
- ✅ CORS habilitado solo para desarrollo local

## 🎯 User Stories Implementadas

| # | Historia | Estado |
|---|----------|--------|
| 1 | Colocar encabezado | ✅ Completado |
| 2 | Definir cuerpo del sitio | ✅ Completado |
| 3 | Registrar profesional | ✅ Completado |
| 4 | Visualizar profesionales en home | ✅ Completado |
| 5 | Detalle de profesional | ✅ Completado |
| 6 | Galería de imágenes | ✅ Completado |
| 7 | Pie de página | ✅ Completado |
| 8 | Paginar profesionales | ✅ Completado |
| 9 | Panel de administración | ✅ Completado |
| 10 | Listar profesionales | ✅ Completado |
| 11 | Eliminar profesional | ✅ Completado |

## 📸 Imágenes

Las imágenes se encuentran en: `/src/assets/img/`

**Total: 41 imágenes**
Fuente: https://www.pexels.com

## 🔄 Flujo de Datos

```
Usuario Accede a http://localhost:5173
   ↓
Frontend (React) Renderiza UI
   ↓
Usuario Interactúa (Busca, Filtra, CRUD)
   ↓
Axios Realiza HTTP Request
   ↓
Backend Spring Boot Recibe en /api/profesionales
   ↓
Spring Data JPA Consulta H2 Database
   ↓
JSON Response ← Frontend Procesa y Actualiza UI
```

## 📝 Notas de Desarrollo

### Base de Datos
- **Motor**: H2 (en memoria)
- **Reinicio**: Cada vez que ejecuta el backend (configuración: `spring.jpa.hibernate.ddl-auto=create-drop`)
- **Datos iniciales**: Se cargan automáticamente desde `DataLoader.java`
- **Consola**: Accesible en http://localhost:8080/h2-console

### Configuración CORS
- CORS habilitado solo para `http://localhost:5173` (desarrollo)
- Método: `@CrossOrigin` en `ProfesionalController`
- Para producción: Configurar en `SecurityConfig` o `WebConfig`

### Variables de Entorno
- No se utilizan actualmente (URLs hardcodeadas en código)
- Se recomienda usar archivo `.env` en frontend para URLs de API

## 🐛 Guía de Desarrollo

### Estructura del Proyecto Frontend

```javascript
// components/Header.jsx - Navegación principal
// components/Footer.jsx - Pie de página

// pages/Home.jsx - Página de inicio con buscador y filtros
// pages/ProfesionalDetail.jsx - Detalle de profesional con galería
// pages/AdminPanel.jsx - Panel de administración
// pages/ListaProfesionales.jsx - Listado en tabla
// pages/AgregarProfesional.jsx - Formulario de creación

// App.jsx - Router y composición de páginas
```

### Estructura del Proyecto Backend

```java
// entity/Profesional.java - Modelo de datos
// repository/ProfesionalRepository.java - Acceso a DB
// service/ProfesionalService.java - Lógica de negocio
// controller/ProfesionalController.java - Endpoints REST
// DataLoader.java - Carga de datos iniciales
```

## 🚦 Solución de Problemas

### Error: "Cannot GET http://localhost:8080/api/profesionales"

**Causa**: Backend no está ejecutándose  
**Solución**:
```bash
# Asegurate que estés en el directorio del backend
cd <ruta-del-backend>/centro-integral-backend
# Ejecutá el backend
mvn spring-boot:run
```

### Error: "Connection refused" o "Network error"

**Causa**: Frontend no puede conectarse al backend  
**Solución**:
1. Verificá que el backend está en http://localhost:8080
2. Abrí F12 → Network → Buscá request fallido → Verificá URL
3. Verificá que no hay firewall bloqueando puerto 8080

### Error: "Port 5173 already in use"

**Causa**: Otro proceso usa puerto 5173  
**Solución**:
```bash
# En Windows, encontrar proceso en puerto 5173
netstat -ano | findstr :5173
# Matar proceso (reemplaza PID con el número obtenido)
taskkill /PID <PID> /F
```

### Error: "H2 Console no carga en http://localhost:8080/h2-console"

**Causa**: Configuración de H2 no habilitada  
**Solución**:
1. Verificá `application.properties`:
   ```properties
   spring.h2.console.enabled=true
   ```
2. Usá credenciales: Usuario `sa` sin contraseña

### Cambios en el frontend no se reflejan en el navegador

**Causa**: Hot reload no configurado correctamente  
**Solución**:
1. Verificá que ejecutás `npm run dev` (no `npm run build`)
2. Abrí DevTools (F12) y deshabilitá caché
3. Reiniciá servidor: Ctrl+C en terminal y `npm run dev` nuevamente

### Base de datos vacía después de reiniciar backend

**Causa**: Configuración `ddl-auto=create-drop` elimina y recrea tablas  
**Solución (para desarrollo)**:
- Esto es normal y esperado
- `DataLoader.java` carga automáticamente datos de ejemplo
- Si necesitás persistencia, cambiá a `ddl-auto=update`

### Errores de validación "Nombre ya existe"

**Causa**: Intentando crear profesional con nombre duplicado  
**Solución**:
1. En frontend: El formulario no permite duplicados (JS)
2. En backend: Base de datos rechaza (constraint único)
3. Usá nombres diferentes o eliminá el profesional existente


Última actualización: Mayo 2026
