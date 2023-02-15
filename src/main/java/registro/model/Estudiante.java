package registro.model;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import registro.model.DocumentoIdentidad.DocumentoIdentidadBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "registro_alumno")
public class Estudiante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String primerNombre;

	private String segundoNombre;

	private String primerApellido;

	private String segundoApellido;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_identidad")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private DocumentoIdentidadBuilder tipoDocumento;

	private Integer numeroDocumento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_curso")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Curso curso;

	private String estado;

	@Column(name = "fecha_de_registro")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty(value = "fecha")
	private Date createAt;

}
