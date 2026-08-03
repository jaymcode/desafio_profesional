package com.centrointegral.backend;

import com.centrointegral.backend.entity.Profesional;
import com.centrointegral.backend.entity.Usuario;
import com.centrointegral.backend.repository.ProfesionalRepository;
import com.centrointegral.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (profesionalRepository.count() == 0) {
            // Profesionales de Kinesiología
            Profesional p1 = new Profesional(
                "Lic. María Rodríguez López",
                "Especialista en Kinesiología con 12 años de experiencia. Experta en rehabilitación post-quirúrgica y lesiones deportivas. Utiliza técnicas modernas de terapia manual y ejercicio terapéutico.",
                "Kinesiología",
                Arrays.asList(
                    "/src/assets/img/pexels-kampus-6111626.jpg",
                    "/src/assets/img/pexels-kampus-6111591.jpg",
                    "/src/assets/img/pexels-kampus-6111594.jpg",
                    "/src/assets/img/pexels-kampus-6111604.jpg",
                    "/src/assets/img/pexels-kampus-6111605.jpg"
                )
            );

            Profesional p6 = new Profesional(
                "Lic. Rodrigo Fernández Gómez",
                "Kinesiólogo con enfoque en rehabilitación funcional. Amplia experiencia en tratamiento de lesiones musculoesqueléticas y neurológicas. Comprometido con la mejora continua y el bienestar de sus pacientes.",
                "Kinesiología",
                Arrays.asList(
                    "/src/assets/img/pexels-karola-g-4506213.jpg",
                    "/src/assets/img/pexels-karola-g-4506218.jpg",
                    "/src/assets/img/pexels-karola-g-4506111.jpg",
                    "/src/assets/img/pexels-karola-g-4506163.jpg",
                    "/src/assets/img/pexels-karola-g-4506112.jpg"
                )
            );

            Profesional p7 = new Profesional(
                "Lic. Gastón Martínez Silva",
                "Destacado en salud física y mejoramiento motriz. Especialista en terapia manual, magnetoterapia y ejercicio terapéutico. Con amplia experiencia en rehabilitación, neurodesarrollo y postura, brindando atención personalizada a cada paciente.",
                "Kinesiología",
                Arrays.asList(
                    "/src/assets/img/pexels-funkcines-terapijos-centras-927573878-20860585.jpg",
                    "/src/assets/img/pexels-funkcines-terapijos-centras-927573878-20860583.jpg",
                    "/src/assets/img/pexels-funkcines-terapijos-centras-927573878-20860623.jpg",
                    "/src/assets/img/pexels-funkcines-terapijos-centras-927573878-20860616.jpg",
                    "/src/assets/img/pexels-funkcines-terapijos-centras-927573878-20860612.jpg"
                )
            );

            // Profesionales de Fisiatría
            Profesional p2 = new Profesional(
                "Dr. Juan Pérez García",
                "Médico fisiatra especializado en terapia neurológica y rehabilitación de movilidad. Con enfoque integral en el tratamiento del paciente, combinando técnicas tradicionales con tecnología moderna.",
                "Fisiatría",
                Arrays.asList(
                    "/src/assets/img/ortopediatri-cocuk-ortopedi-akademisi-DDuPWW3kXwA-unsplash.jpg",
                    "/src/assets/img/ortopediatri-cocuk-ortopedi-akademisi-_WkonwkViTE-unsplash.jpg",
                    "/src/assets/img/ortopediatri-cocuk-ortopedi-akademisi-0ZDWaJW7HTU-unsplash.jpg",
                    "/src/assets/img/ortopediatri-cocuk-ortopedi-akademisi-ogfwXs_la2g-unsplash.jpg",
                    "/src/assets/img/ortopediatri-cocuk-ortopedi-akademisi-8JjW4xVwQqc-unsplash.jpg"
                )
            );

            // Profesionales de Fonoaudiología
            Profesional p3 = new Profesional(
                "Lic. Ana Patricia Sánchez",
                "Fonoaudióloga con especialización en trastornos del lenguaje y audición. Experiencia en terapia individual y grupal. Trabaja con todas las edades desde lactantes hasta adultos mayores.",
                "Fonoaudiología",
                Arrays.asList(
                    "/src/assets/img/ortopediatri-cocuk-ortopedi-akademisi-t63vrrRw6Z0-unsplash.jpg",
                    "/src/assets/img/ortopediatri-cocuk-ortopedi-akademisi-AttDDG5snEI-unsplash.jpg",
                    "/src/assets/img/ortopediatri-cocuk-ortopedi-akademisi-iKCuym5Kt5o-unsplash.jpg",
                    "/src/assets/img/ortopediatri-cocuk-ortopedi-akademisi-RGeklE5XSLY-unsplash.jpg",
                    "/src/assets/img/ortopediatri-cocuk-ortopedi-akademisi-xn0r1U4Irsk-unsplash.jpg"
                )
            );

            // Profesionales de Psicopedagogía
            Profesional p4 = new Profesional(
                "Lic. Laura González Martínez",
                "Psicopedagoga especializada en dificultades de aprendizaje y orientación educativa. Realiza evaluaciones psicopedagógicas completas y planes de intervención personalizados.",
                "Psicopedagogía",
                Arrays.asList(
                    "/src/assets/img/pexels-shvets-production-7176130.jpg",
                    "/src/assets/img/pexels-shvets-production-7176185.jpg",
                    "/src/assets/img/pexels-shvets-production-7176293.jpg",
                    "/src/assets/img/pexels-shvets-production-7176321.jpg"
                )
            );

            // Profesionales de Pediatría
            Profesional p5 = new Profesional(
                "Dr. Carlos Mendez Romero",
                "Médico pediatra con amplia experiencia en atención integral de niños. Especializado en seguimiento del desarrollo psicomotor y prevención de enfermedades infantiles. Enfoque cálido y cercano con los pacientes y sus familias.",
                "Pediatría",
                Arrays.asList(
                    "/src/assets/img/vitaly-gariev-ibZ2QiKkEsg-unsplash.jpg",
                    "/src/assets/img/vitaly-gariev-_oUD74f-Lhk-unsplash.jpg",
                    "/src/assets/img/vitaly-gariev-oCqGFVUpgm4-unsplash.jpg"
                )
            );

            Profesional p8 = new Profesional(
                "Dra. Valentina López Herrera",
                "Médica pediatra con enfoque en salud infantil y desarrollo, con más de 10 años de experiencia en el área.",
                "Pediatría",
                Arrays.asList(
                    "/src/assets/img/pexels-pavel-danilyuk-5998467.jpg",
                    "/src/assets/img/pexels-pavel-danilyuk-5998458.jpg",
                    "/src/assets/img/pexels-pavel-danilyuk-5998449.jpg",
                    "/src/assets/img/pexels-pavel-danilyuk-5998459.jpg"
                )
            );

            profesionalRepository.save(p1);
            profesionalRepository.save(p2);
            profesionalRepository.save(p3);
            profesionalRepository.save(p4);
            profesionalRepository.save(p5);
            profesionalRepository.save(p6);
            profesionalRepository.save(p7);
            profesionalRepository.save(p8);

            System.out.println("✅ 8 profesionales de ejemplo cargados en la base de datos");
        }

        if (usuarioRepository.count() == 0) {
            Usuario demoUser = new Usuario(
                "Usuario",
                "Demo",
                "demo@centrointegral.com",
                "Demo123!"
            );
            usuarioRepository.save(demoUser);
            System.out.println("✅ Usuario de prueba creado para login: demo@centrointegral.com / Demo123!");
        }
    }
}