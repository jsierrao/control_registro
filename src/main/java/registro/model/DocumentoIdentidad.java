package registro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "documentos")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentoIdentidad {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	
	private Long id;
	
	
	private String tipo;
	

	

}
