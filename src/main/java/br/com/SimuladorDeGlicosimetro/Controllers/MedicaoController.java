package br.com.SimuladorDeGlicosimetro.Controllers;

import br.com.SimuladorDeGlicosimetro.Services.MedicaoService;
import br.com.SimuladorDeGlicosimetro.Utils.ListaCircular.CircularLinkedList;
import br.com.SimuladorDeGlicosimetro.Utils.ListaCircular.CircularList;
import br.com.SimuladorDeGlicosimetro.Exceptions.ControllerException;
import br.com.SimuladorDeGlicosimetro.Exceptions.DatabaseException;
import br.com.SimuladorDeGlicosimetro.Exceptions.ServiceException;
import br.com.SimuladorDeGlicosimetro.Entities.Medicao;
import java.util.List;

public class MedicaoController {
	private MedicaoService medService;
	
	public MedicaoController(MedicaoService medService) {
		this.medService = medService;
	}
	
	/**
	 * Simula uma medição de glicemia e a registra no banco de dados.
	 * @return uma medição ({@code Medicao}) para ser manipulada.
	 * @throws ControllerException caso haja algum erro no ato de simulação de medição de glicemia
	 */
	public Medicao realizarMedicao() throws ControllerException {
		
		try {
			
			Medicao med = medService.realizarMedicao();
			return med;
			
		} catch (ServiceException e) {
			throw new ControllerException("Erro ao realizar a medição", e);
		}
		
	}
	
	/**
	 * Busca por <B>todas</B> as medições no banco de dados e as retorna em formato de lista circular duplamente encadeada ({@code CircularLinkedList<Medicao>})
	 * 
	 * @return Uma lista circular duplamente encadeada({@code CircularLinkedList<Medicao>}) das medições presentes no banco de dados.
	 * @throws ControllerException caso haja algum erro no momento de busca das medições.
	 */
	public CircularLinkedList<Medicao> buscarMedicoes() throws ControllerException {
		
		try {
			
			CircularLinkedList<Medicao> meds = medService.buscarTodasMedicoes();
			
			if(meds.isEmpty()) throw new ControllerException("Não há medições para vizualizar.");
			
			return meds;
			
		} catch (ServiceException e) {
			throw new ControllerException("Erro ao buscar medições", e);
		}
		
	}
	
	public CircularLinkedList<Medicao> buscarHipoglicemias() throws ControllerException {
		
		try {
			
			CircularLinkedList<Medicao> meds = medService.buscarHipoglicemias();
			
			if(meds.isEmpty()) throw new ControllerException("Não há hipoglicemias registradas.");
			
			return meds;
			
		} catch (ServiceException e) {
			throw new ControllerException("Erro ao buscar hipoglicemias", e);
			
		}
		
	}
	
	public CircularLinkedList<Medicao> buscarHiperglicemias() throws ControllerException {
		
		try {
			
			CircularLinkedList<Medicao> meds = medService.buscarHiperglicemias();
			
			if(meds.isEmpty()) throw new ControllerException("Não há hiperglicemias registradas.");
			
			return meds;
			
		} catch (ServiceException e) {
			throw new ControllerException("Erro ao buscar hiperglicemias", e);
		}
		
	}
	
	
	/**
	 * Apaga todas os registros de medições do banco de dados.
	 * @return <I>void</I>
	 * @throws ControllerException se haver algum problema no momento de deletar os registros das medições.
	 */
	public void apagarMedicoes() throws ControllerException {
		
		try {
			
			medService.apagarMedicoes();
			
		} catch (ServiceException e) {
			throw new ControllerException("Erro ao deletar medições", e);
		}
		
	}
	
}
