package registro.repositorio;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import registro.model.DocumentoIdentidad;
import registro.model.Estudiante;
@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
	
	
	
	

}
