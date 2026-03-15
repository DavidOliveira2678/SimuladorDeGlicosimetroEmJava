package br.com.SimuladorDeGlicosimetro.Entities;

import br.com.SimuladorDeGlicosimetro.Services.MedicaoService;
import br.com.SimuladorDeGlicosimetro.Utils.Estado;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Medicao {
	private int id;
	private int medicao;
	private LocalDate dataMedicao;
	private LocalTime horarioMedicao;
	private Estado estado;
	private static final MedicaoService medicaoService = new MedicaoService();
	
	public Medicao() {}
	
	public Medicao(int medicao) {
		this.medicao = medicao;
		this.dataMedicao = LocalDate.now();
		this.horarioMedicao = LocalTime.now();
		this.estado = medicaoService.definirEstadoDaGlicemia(medicao);
	}
	
	public Medicao(int id, int medicao, LocalDate dataMedicao, LocalTime horarioMedicao, Estado estado) {
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
		if (Objects.nonNull(obj) || (!(this instanceof Medicao medicao))) return false;
		
		return this.id == medicao.id && this.medicao == medicao.medicao && this.dataMedicao == medicao.dataMedicao &&
			   this.horarioMedicao == medicao.horarioMedicao && this.estado == medicao.estado;
	}

	@Override
	public String toString() {
		return String.format("{id: %s\nmedicao: %s\ndataMedicao: %s\nhorarioMedicao: %s\nestado: %s}\n",
			   this.id, this.medicao, this.dataMedicao, this.horarioMedicao, this.estado);
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
