package br.com.SimuladorDeGlicosimetro.Entities;

import br.com.SimuladorDeGlicosimetro.Utils.Estado;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * <P>Representação de uma entidade <B>medição de glicose</B> em um
 * banco de dados.
 */
public class Medicao {
	private int id;
	private int medicao;
	private LocalDate dataMedicao;
	private LocalTime horarioMedicao;
	private Estado estado;
	
	public Medicao() {}
	
	/**
	 * Construtor da classe {@code Medicao} para instância de uma medição comum,
	 * sendo definido apenas o valor da glicemia no momento da instância.
	 * 
	 * @param medicao valor da glicemia
	 */
	public Medicao(int medicao, Estado estado) {
		this.medicao = medicao;
		this.dataMedicao = LocalDate.now();
		this.horarioMedicao = LocalTime.now();
		this.estado = estado;
	}
	
	/**
	 * Construtor da classe {@code Medicao} para instância de uma medição de glicemia que possui como atributos
	 * somente o valor da medição, a data realizada, a hora que foi realizada e o estado da medição.
	 * 
	 * @param medicao o valor da medição.
	 * @param dataMedicao a data que a medição foi realizada.
	 * @param horarioMedicao o horário que a medição foi realizada.
	 * @param estado o estado da glicemia (representado pelo tipo {@code Enum}).
	 */
	public Medicao(int medicao, Estado estado, LocalDate dataMedicao, LocalTime horarioMedicao) {
		this.medicao = medicao;
		this.estado = estado;
		this.dataMedicao = dataMedicao;
		this.horarioMedicao = horarioMedicao;
	}
	
	/**
	 * Construtor da classe {@code Medicao} para instância de uma medição de glicemia que possui todos
	 * os atributos da classe.
	 * 
	 * @param id O ID da medição.
	 * @param medicao o valor da medição.
	 * @param estado o estado da glicemia (representado pelo tipo {@code Enum}).
	 * @param dataMedicao a data que a medição foi realizada.
	 * @param horarioMedicao o horário que a medição foi realizada.
	 */
	public Medicao(int id, int medicao, Estado estado, LocalDate dataMedicao, LocalTime horarioMedicao) {
		this.id = id;
		this.medicao = medicao;
		this.dataMedicao = dataMedicao;
		this.horarioMedicao = horarioMedicao;
		this.estado = estado;
	}

	@Override
	public int hashCode() { return Objects.hash(this.id, this.medicao, this.dataMedicao, this.horarioMedicao, this.estado); }

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) return true;
		if (Objects.isNull(obj) || (!(obj instanceof Medicao medicao))) return false;
		
		return this.id == medicao.id && this.medicao == medicao.medicao && this.dataMedicao.equals(medicao.dataMedicao) &&
			   this.horarioMedicao.equals(medicao.horarioMedicao) && this.estado == medicao.estado;
	}

	@Override
	public String toString() {
		if (this.id != 0) {
		return String.format("{\n"
				+ "  id: %s\n  medicao: %s\n  dataMedicao: %s\n  horarioMedicao: %s\n  estado: %s\n}\n",
			   this.id, this.medicao, this.dataMedicao, this.horarioMedicao, this.estado);
		} else {
			return String.format("{\n"
					+ "  medicao: %s\n  dataMedicao: %s\n  horarioMedicao: %s\n  estado: %s\n}\n",
				   this.medicao, this.dataMedicao, this.horarioMedicao, this.estado);
		}
	}

	public int getId() {
		return id;
	}

	public int getMedicao() {
		return medicao;
	}

	public void setMedicao(int medicao) {
		this.medicao = medicao;
	}

	public LocalDate getDataMedicao() {
		return dataMedicao;
	}

	public LocalTime getHorarioMedicao() {
		return horarioMedicao;
	}

	public Estado getEstado() {
		return estado;
	}
	
}
