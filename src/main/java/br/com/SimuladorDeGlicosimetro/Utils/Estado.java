package br.com.SimuladorDeGlicosimetro.Utils;

/**
 * Representação do atributo estado de tipo Enum em
 * uma tabela medições de glicose em um banco de dados.
 */
public enum Estado {
	LO, HIPOGLICEMIA, OK, HIPERGLICEMIA_SIGNIFICATIVA, HIPERGLICEMIA_SEVERA, HI;
	
	/**
	 * Recebe uma string e retorna o valor no tipo {@code Estado} convertendo caracteres " " vazios para "_".
	 * 
	 * @param str A string que será convertida para {@code Estado}.
	 * 
	 * @return Um {@code Estado} a partir de uma {@code String}.
	 */
	public static Estado fromString(String str) {
		return Estado.valueOf(str.replace(" ", "_").toUpperCase());
	}
	
	/**
	 * Recebe um estado e retorna o valor no tipo {@code String} convertendo caracteres "_" para " ".
	 * 
	 * @param estado O {@code Estado} que será convertido para {@code String}.
	 * 
	 * @return Uma {@code String} a partir de um {@code Estado}.
	 */
	public static String estadoToString(Estado estado) {
		return String.valueOf(estado).replace("_", " ");
	}
}
