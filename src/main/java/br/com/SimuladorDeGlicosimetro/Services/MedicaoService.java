package br.com.SimuladorDeGlicosimetro.Services;

import br.com.SimuladorDeGlicosimetro.Entities.Medicao;

import br.com.SimuladorDeGlicosimetro.Data.MedicaoDAO;
import br.com.SimuladorDeGlicosimetro.Exceptions.DatabaseException;
import br.com.SimuladorDeGlicosimetro.Exceptions.ServiceException;
import br.com.SimuladorDeGlicosimetro.Utils.Estado;
import br.com.SimuladorDeGlicosimetro.Utils.ListaCircular.CircularLinkedList;

public class MedicaoService {
	
	private MedicaoDAO medicaoDAO;
	
	public MedicaoService(MedicaoDAO medicaoDAO) { 
		this.medicaoDAO = medicaoDAO;
	}
	
	private static int gerarMedicao() {
		
		int min;
		int max;
		int resultado;
		
		double decisao = Math.random();
		
		if(decisao < 0.2) {
			
			min = 650;
			max = 700;
			
			resultado = min + (int) (Math.random() * (max - min));
		
		} else if (decisao < 0.3){
			
			min = 280;
			max = 650;
			
			resultado = min + (int) (Math.random() * (max - min));
			
		} else {
			
			min = 1;
			max = 250;
			
			resultado = min + (int) (Math.random() * (max - min));
			
		}
		
		
		return resultado;
	}
	
	
	public Medicao realizarMedicao() throws ServiceException {
		
		try {
			
			int medicao = gerarMedicao();
			Estado estadoGlicemia = definirEstadoDaGlicemia(medicao);
			
			Medicao med = new Medicao(medicao, estadoGlicemia);
			
			medicaoDAO.cadastrarMedicao(med);
			
			return med;
			
		} catch (DatabaseException e) {
			throw new ServiceException("Erro ao cadastrar medição", e);
			
		}
		
	}
	
	public CircularLinkedList<Medicao> buscarTodasMedicoes() throws ServiceException {
		
		try {
			
			CircularLinkedList<Medicao> meds = medicaoDAO.resgatarTodasMedicoes();
			
			return meds;
			
		} catch (DatabaseException e) {
			throw new ServiceException("Erro ao buscar todas medições", e);
			
		}
		
	}
	
	public CircularLinkedList<Medicao> buscarHipoglicemias() throws ServiceException {
		
		try {
			
			CircularLinkedList<Medicao> hipos = medicaoDAO.resgatarHipoglicemias();
			
			return hipos;
			
		} catch (DatabaseException e) {
			throw new ServiceException("Erro ao buscar hipoglicemias", e);
			
		}
		
	}
	
	public CircularLinkedList<Medicao> buscarHiperglicemias() throws ServiceException {
		
		try {
			
			CircularLinkedList<Medicao> hipers = medicaoDAO.resgatarHiperglicemias();
			
			return hipers;
			
		} catch (DatabaseException e) {
			throw new ServiceException("Erro ao buscar hiperglicemias");
			
		}
	}
	
	
	public void apagarMedicoes() throws ServiceException {
		
		try {
			
			medicaoDAO.deletarMedicoes();
			
		} catch (DatabaseException e) {
			throw new ServiceException("Erro ao deletar todas medições", e);
			
		}
		
	}
	
	
	/**
	 * Returna o estado da glicemia a partir do valor da medição.
	 * @param medicao Um inteiro que representa o valor atual da glicemia.
	 * @return O Estado da medição.
	 */
	private static Estado definirEstadoDaGlicemia(int medicao) {
		
		if(medicao <= 20) {
			return Estado.LO;
			
		} else if (medicao < 70) {
			return Estado.HIPOGLICEMIA;
			
		} else if (medicao <= 140) {
			return Estado.OK;
			
		} else if (medicao <= 279) {
			return Estado.HIPERGLICEMIA_SIGNIFICATIVA;
			
		} else if (medicao >= 280 && medicao < 650) {
			return Estado.HIPERGLICEMIA_SEVERA;
			
		} else {
			return Estado.HI;
			
		}
	}
	
}
