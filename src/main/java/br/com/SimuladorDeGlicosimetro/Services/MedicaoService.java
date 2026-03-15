package br.com.SimuladorDeGlicosimetro.Services;
import br.com.SimuladorDeGlicosimetro.Utils.Estado;

public class MedicaoService {
	
	public MedicaoService() { /* TODO: ADICIONAR NOVAS FUNÇÕES NO CONSTRUTOR CASO NECESSÁRIO */ }
	
	public Estado definirEstadoDaGlicemia(int medicao) {
		
		if(medicao <= 20) {
			return Estado.LO;
			
		} else if (medicao < 70) {
			return Estado.HIPOGLICEMIA;
			
		} else if (medicao <= 180) {
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
