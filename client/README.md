# Centro Integral Multidisciplinario - Sistema de Gestión de Profesionales

Una aplicación web moderna y responsiva para la gestión centralizada de profesionales en un Centro Integral Multidisciplinario. La plataforma permite la visualización, búsqueda y administración de profesionales en las disciplinas de Kinesiología, Fisiatría, Fonoaudiología, Psicopedagogía y Pediatría.

## 🎯 Características Principales

- **Home**: Hero section, buscador, filtros por disciplina (Kinesiología, Fisiatría, Fonoaudiología, Psicopedagogía, Pediatría), grid responsivo
- **Detalle de Profesionales**: Galería interactiva de imágenes, descripciones, botón de reserva
- **Panel de Administración**: CRUD completo de profesionales (no disponible en móviles)
- **Diseño Responsive**: Optimizado para desktop, tablet y mobile
- **Validaciones**: Nombres únicos, campos requeridos, mensajes de error

## ✅ Requisitos Previos

Antes de comenzar, asegúrese de tener instalado:

- **Node.js** 16+ (incluye npm)
- **Java JDK** 17 o superior
- **Maven** 3.6+
- **Git** (opcional, para clonar el repositorio)
- Navegador moderno (Chrome, Firefox, Edge, Safari)

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
- **Nota**: El backend se ejecuta en un repositorio separado en el puerto 8080

## 🎨 Paleta de Colores

```
Primario:    #68e9ba (Verde/Turquesa)
Secundario:  #00313e (Azul Oscuro)
Fondo:       #f8f8f8 (Gris Claro)
Texto:       #333 (Gris Oscuro)
Blanco:      #ffffff
```

## 📁 Estructura del Proyecto

### Frontend (React + Vite)
```
client/
├── src/
│   ├── api/
│   │   └── axiosConfig.js              # Configuración de cliente HTTP
│   ├── components/
│   │   ├── Header.jsx / Header.css     # Navegación principal
│   │   ├── Footer.jsx / Footer.css     # Pie de página
│   │   └── logo.jsx                    # Logo reutilizable
│   ├── pages/
│   │   ├── Home.jsx / Home.css         # Página de inicio con buscador
│   │   ├── ProfesionalDetail.jsx / ProfesionalDetail.css   # Detalle con galería
│   │   ├── AdminPanel.jsx / AdminPanel.css                 # Panel de administración
│   │   ├── ListaProfesionales.jsx / ListaProfesionales.css # Tabla de profesionales
│   │   └── AgregarProfesional.jsx / AgregarProfesional.css # Formulario de creación
│   ├── utils/
│   │   ├── errorHandler.js             # Manejo centralizado de errores
│   │   └── imageValidator.js           # Validación de imágenes
│   ├── assets/
│   │   └── img/                        # 41 imágenes de profesionales
│   ├── App.jsx / App.css               # Componente principal
│   └── main.jsx                        # Punto de entrada
├── public/                              # Archivos estáticos
├── index.html                          # HTML base
├── package.json                        # Dependencias npm
├── vite.config.js                      # Configuración de Vite
├── eslint.config.js                    # Configuración de ESLint
└── README.md                           # Documentación frontend
```

### Backend (Spring Boot + Maven)
```
.
├── src/
│   ├── main/
│   │   ├── java/com/centrointegral/backend/
│   │   │   ├── config/
│   │   │   │   ├── SecurityHeaderConfig.java  # Configuración de headers de seguridad
│   │   │   │   └── WebConfig.java            # Configuración CORS y web
│   │   │   ├── controller/
│   │   │   │   └── ProfesionalController.java # Endpoints REST API
│   │   │   ├── entity/
│   │   │   │   └── Profesional.java          # Modelo de datos JPA
│   │   │   ├── repository/
│   │   │   │   └── ProfesionalRepository.java # Acceso a base de datos
│   │   │   ├── service/
│   │   │   │   └── ProfesionalService.java    # Lógica de negocio
│   │   │   ├── util/
│   │   │   │   ├── ErrorResponse.java         # DTO para respuestas de error
│   │   │   │   └── ValidationUtil.java        # Utilidades de validación
│   │   │   ├── CentroIntegralBackendApplication.java  # Clase principal Spring Boot
│   │   │   └── DataLoader.java                # Cargador de datos iniciales
│   │   └── resources/
│   │       └── application.properties         # Configuración de la aplicación
│   └── test/
│       └── java/com/centrointegral/backend/
│           ├── repository/
│           │   └── ProfesionalRepositoryTest.java  # Tests de repositorio
│           ├── service/
│           │   └── ProfesionalServiceTest.java     # Tests de servicio
│           ├── controller/                        # (Vacío) - Para tests de controlador
│           └── integration/                       # (Vacío) - Para tests de integración
├── target/                              # Archivos compilados
├── pom.xml                             # Configuración Maven
└── TESTING_REPORT.md                   # Reporte de pruebas
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

# Ejecutar servidor de desarrollo con hot reload
npm run dev

# Compilar para producción
npm run build
```

**Resultado:**
- Frontend accesible en: http://localhost:5173
- Hot module reloading habilitado

### ⚙️ Configuración del Entorno

**Backend (application.properties):**
- Spring escucha en el puerto `8080`
- CORS habilitado para desarrollo local (localhost:5173)
- H2 se reinicia con datos de ejemplo en cada ejecución
- Esquema de base de datos se crea y destruye automáticamente (`ddl-auto=create-drop`)

**Frontend (variables de entorno):**
```
VITE_API_URL=http://localhost:8080/api
VITE_NODE_ENV=development
```
- Implementado en `.env` - Los URLs se cargan desde variables de entorno
- El cliente axios (`axiosConfig.js`) utiliza `VITE_API_URL` para configurar la base URL
- Fallback a `http://localhost:8080/api` si no está definida la variable

## 📡 API REST - Endpoints Disponibles

**Base URL:** `http://localhost:8080/api/profesionales`

### Listado de Endpoints Implementados

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/` | Obtener todos los profesionales |
| GET | `/{id}` | Obtener detalle de profesional por ID |
| POST | `/` | Crear nuevo profesional |
| DELETE | `/{id}` | Eliminar profesional por ID |

**Endpoints adicionales disponibles (no utilizados actualmente por el frontend):**
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/paged` | Obtener profesionales con paginación (parámetros: page, size) |
| GET | `/random` | Obtener profesionales aleatorios (parámetro: limit)

**Nota sobre Paginación**: La aplicación implementa **paginación del lado del cliente** (4 profesionales por página). Todos los profesionales se obtienen con una única solicitud GET `/api/profesionales` y se paginan en el navegador.

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

- **Desktop (1024px+)**: Diseño completo con sidebar, grid de 4 columnas, navegación horizontal
- **Tablet (768px - 1023px)**: Grid de 2-3 columnas, menús adaptados, elementos redimensionados
- **Mobile (<768px)**: Single column, menú hamburguesa, panel de administración deshabilitado

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
- **Motor**: H2 Database (en memoria)
- **Modo**: Embebido en aplicación Spring Boot
- **Consola**: Accesible en http://localhost:8080/h2-console (usuario: `sa`, sin contraseña)
- **DDL Strategy**: `ddl-auto=create-drop` (recrea esquema en cada inicio)
- **Datos Iniciales**: Cargados automáticamente desde `DataLoader.java` en cada ejecución
- **Persistencia**: Los datos se pierden al reiniciar el backend (comportamiento esperado para desarrollo)

### Configuración CORS
- **Habilitado para**: `http://localhost:5173` (desarrollo local)
- **Método**: Anotación `@CrossOrigin` en `ProfesionalController`
- **Headers permitidos**: Content-Type, Authorization
- **Métodos permitidos**: GET, POST, DELETE, OPTIONS
- **Para producción**: Configurar en `WebConfig.java` con dominios específicos

### Configuración de Seguridad
- **Headers de Seguridad**: Implementados en `SecurityHeaderConfig.java`
- **Validaciones**:
  - Nombre de profesional: Único en base de datos (constraint UNIQUE)
  - Campos requeridos: Validados tanto en cliente como en servidor
  - Disciplinas: Solo 5 opciones válidas
  - Imágenes: Validadas por tipo y tamaño en cliente

### Variables de Entorno
- **Frontend**: Implementado con archivo `.env`
  - `VITE_API_URL`: URL base de la API (`http://localhost:8080/api`)
  - `VITE_NODE_ENV`: Ambiente de ejecución (`development`)
  - Configuración cargada en `axiosConfig.js` para todas las peticiones HTTP
- **Backend**: 
  - Puerto: 8080 (configurado en `application.properties`)
  - Perfil activo: `dev` (configurado en pom.xml)
  - Base de datos: H2 (embedded)

### Archivos de Configuración Clave

**Frontend:**
- `vite.config.js` - Configuración del build tool
- `eslint.config.js` - Reglas de linting
- `package.json` - Dependencias y scripts npm

**Backend:**
- `pom.xml` - Dependencias Maven y plugins
- `application.properties` - Propiedades de Spring Boot
- `application-test.properties` - Configuración para pruebas

### Dependencias Principales

**Frontend:**
- React 19
- React Router 7
- Axios
- Vite
- ESLint

**Backend:**
- Spring Boot 3.2
- Spring Data JPA
- H2 Database
- JUnit 5 (para testing)
- Mockito (para mocking)

### Git y Versionado
- **Repositorio**: https://github.com/jaymcode/desafio_profesional
- **Rama principal**: `main`
- **Estructura**: Monorepo con backend en root y frontend en carpeta `client/`
- **Historial**: Pushes realizados con commits descriptivos

### Monitoreo y Debugging

**Backend:**
- Logs en consola (por defecto en WARN)
- Para debug, cambiar en application.properties: `logging.level.com.centrointegral=DEBUG`
- H2 Console para inspeccionar DB directamente

**Frontend:**
- React DevTools en navegador
- Console logs en development
- Network tab para inspeccionar requests/responses
- Componentes con PropTypes opcionales para validación

## 🐛 Guía de Desarrollo

### Frontend - Componentes y Páginas

**Componentes Globales (`components/`):**
- **Header.jsx**: Navegación fija con logo, lema, botones de "Crear cuenta" e "Iniciar sesión"
- **Footer.jsx**: Pie de página con copyright dinámico y enlaces a redes sociales
- **logo.jsx**: Componente reutilizable del logo

**Páginas (`pages/`):**
- **Home.jsx**: Página de inicio con:
  - Buscador en tiempo real de profesionales
  - Filtros por disciplina (5 opciones)
  - Grid de 4 profesionales por página
  - Paginación completa (Inicio, Anterior, Siguiente, Final)
  
- **ProfesionalDetail.jsx**: Detalle de profesional con:
  - Información del profesional (nombre, descripción, disciplina)
  - Galería interactiva con imagen principal + 4 thumbnails
  - Galería expandible de todas las imágenes
  - Botón "Reservar turno"
  
- **ListaProfesionales.jsx**: Listado en tabla con:
  - Tabla con columnas: ID, Nombre, Profesión
  - Ordenamiento por columnas
  - Paginación integrada
  - Botones de acción (editar, eliminar)
  
- **AgregarProfesional.jsx**: Formulario de creación con:
  - Validación de campos requeridos
  - Validación de nombres únicos
  - Manejo de imágenes múltiples
  - Mensajes de error descriptivos
  
- **AdminPanel.jsx**: Panel de administración con:
  - Menú de opciones principales
  - Navegación entre vistas CRUD
  - Mensaje de no disponibilidad en móviles

**Utilidades (`utils/`):**
- **errorHandler.js**: Manejo centralizado de errores con mensajes descriptivos
- **imageValidator.js**: Validación de imágenes (tipo, tamaño, dimensiones)

**API (`api/`):**
- **axiosConfig.js**: Cliente HTTP preconfigurado con:
  - Base URL del backend
  - Interceptores para manejo de errores
  - Configuración de headers

### Backend - Arquitectura en Capas

**Entity (`entity/`):**
- **Profesional.java**: Modelo de datos con anotaciones JPA
  - Atributos: id, nombre, descripcion, profesion, imagenes
  - Validaciones: nombre único, campos requeridos
  - Relaciones y restricciones de base de datos

**Repository (`repository/`):**
- **ProfesionalRepository.java**: Interfaz JPA que extiende `JpaRepository`
  - Métodos: findAll(), findById(), save(), delete()
  - Método personalizado: findByNombre() para validar duplicados

**Service (`service/`):**
- **ProfesionalService.java**: Lógica de negocio
  - Validaciones de dominio (nombre, descripción, disciplina)
  - Sanitización de entrada para prevenir XSS
  - Validación y filtrado de URLs de imágenes
  - Manejo de excepciones
  - Métodos: obtener todos, obtener por ID, obtener paginado, obtener aleatorios, crear, eliminar

**Controller (`controller/`):**
- **ProfesionalController.java**: Endpoints REST
  - GET `/api/profesionales` - Obtener todos los profesionales
  - GET `/api/profesionales/{id}` - Obtener profesional por ID
  - GET `/api/profesionales/paged` - Obtener profesionales paginados
  - GET `/api/profesionales/random` - Obtener profesionales aleatorios
  - POST `/api/profesionales` - Crear nuevo profesional
  - DELETE `/api/profesionales/{id}` - Eliminar profesional
  - Anotación @CrossOrigin para CORS habilitado

**Config (`config/`):**
- **WebConfig.java**: Configuración CORS y beans
- **SecurityHeaderConfig.java**: Headers de seguridad

**Util (`util/`):**
- **ErrorResponse.java**: DTO para respuestas de error consistentes
- **ValidationUtil.java**: Métodos utilitarios de validación

**DataLoader.java**: Cargador de datos iniciales
- Carga 8 profesionales de ejemplo al iniciar (3 Kinesiología, 1 Fisiatría, 1 Fonoaudiología, 1 Psicopedagogía, 2 Pediatría)
- Ejecuta automáticamente al iniciar el backend
- Solo carga datos si la base de datos está vacía

### Testing

**Pruebas Unitarias (`src/test/`):**
- **ProfesionalRepositoryTest.java**: Tests de acceso a base de datos
  - Validación de persistencia
  - Pruebas de queries personalizadas
  
- **ProfesionalServiceTest.java**: Tests de lógica de negocio
  - Validaciones de reglas de negocio
  - Comportamiento de métodos del servicio

**Pruebas de Controlador**: Estructura lista, sin implementación actual

**Pruebas de Integración**: Estructura lista, sin implementación actual

### Flujo de Desarrollo Típico

1. **Usuario accede al frontend** → React renderiza UI
2. **Usuario interactúa** (busca, filtra, CRUD)
3. **axios realiza HTTP request** a través de axiosConfig
4. **Backend recibe** en ProfesionalController
5. **Service procesa** la lógica de negocio
6. **Repository accede** a H2 Database
7. **JSON response** se envía al frontend
8. **Frontend actualiza** la interfaz con los datos

## 🚦 Solución de Problemas

### Error: "Cannot GET http://localhost:8080/api/profesionales"

**Causa**: Backend no está ejecutándose  
**Solución**:
```bash
# Asegúrese de estar en el directorio del backend
cd <ruta-del-backend>/centro-integral-backend
# Ejecute el backend
mvn spring-boot:run
```

### Error: "Connection refused" o "Network error"

**Causa**: Frontend no puede conectarse al backend  
**Solución**:
1. Verifique que el backend está ejecutándose en http://localhost:8080
2. Abra F12 → Network → Busque la solicitud fallida → Verifique la URL
3. Verifique que no hay firewall bloqueando el puerto 8080

### Error: "Port 5173 already in use"

**Causa**: Otro proceso está utilizando el puerto 5173  
**Solución**:
```bash
# En Windows, encuentre el proceso en puerto 5173
netstat -ano | findstr :5173
# Finalice el proceso (reemplace PID con el número obtenido)
taskkill /PID <PID> /F
```

### Error: "H2 Console no carga en http://localhost:8080/h2-console"

**Causa**: Configuración de H2 no habilitada  
**Solución**:
1. Verifique en `application.properties`:
   ```properties
   spring.h2.console.enabled=true
   ```
2. Utilice credenciales: Usuario `sa` sin contraseña

### Cambios en el frontend no se reflejan en el navegador

**Causa**: Hot reload no configurado correctamente  
**Solución**:
1. Asegúrese de ejecutar `npm run dev` (no `npm run build`)
2. Abra DevTools (F12) y deshabilite la caché del navegador
3. Reinicie el servidor: Ctrl+C en la terminal y ejecute `npm run dev` nuevamente

### Base de datos vacía después de reiniciar backend

**Causa**: Configuración `ddl-auto=create-drop` elimina y recrea las tablas  
**Solución (para desarrollo)**:
- Este es el comportamiento esperado y correcto
- `DataLoader.java` carga automáticamente los datos de ejemplo
- Si necesita persistencia entre reinicios, cambie a `ddl-auto=update`

### Errores de validación "Nombre ya existe"

**Causa**: Intento de crear un profesional con nombre duplicado  
**Solución**:
1. En frontend: El formulario rechaza duplicados (validación JS)
2. En backend: La base de datos también rechaza (constraint único)
3. Utilice nombres diferentes o elimine el profesional existente

## ✅ Testing

### Backend (Java)
```bash
# Ejecutar todas las pruebas unitarias e integración
cd <ruta-del-backend>/centro-integral-backend
mvn test

# Ejecutar pruebas de un módulo específico
mvn test -Dtest=ProfesionalServiceTest

# Ejecutar con reporte de cobertura
mvn test jacoco:report
```

---

**Última actualización**: Mayo 2026  
**Versión**: 1.0.0  
