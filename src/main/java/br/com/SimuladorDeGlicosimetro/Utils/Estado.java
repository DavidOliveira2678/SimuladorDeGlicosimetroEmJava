package br.com.SimuladorDeGlicosimetro.Utils;

/**
 * Representação do atributo estado de tipo Enum em
 * uma tabela medições de glicose em um banco de dados.
 */
public enum Estado {
	LO, HIPOGLICEMIA, OK, HIPERGLICEMIA_SIGNIFICATIVA, HIPERGLICEMIA_SEVERA, HI;
	
	/**
	 * Returna o estado da glicemia a partir do valor da medição.
	 * @param medicao Um inteiro que representa o valor atual da glicemia.
	 * @return O Estado da medição.
	 */
	public static Estado definirEstadoDaGlicemia(int medicao) {
		
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
	
	
	/**
	 * Recebe uma string e retorna o valor no tipo
	 * Estado, convertendo caracteres vazios para "_".
	 * 
	 * @param str A String que será convertida para Estado.
	 * 
	 * @return Um Estado a partir de uma String.
	 */
	public static Estado fromString(String str) {
		return Estado.valueOf(str.replace(" ", "_"));
	}
}
