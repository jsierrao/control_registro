package registro.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import registro.model.Curso;

@Repository
public interface CursoRepository  extends JpaRepository<Curso, Long>{
	
	
	
	
	

}
