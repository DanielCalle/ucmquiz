package negocio;

public class ComprobadorSintactico {
	
	public static boolean isPositive(Number number) {
		
		if(number.intValue() >= 0) return true;
		else return false;
		
	}

	public static boolean isName(String name) {
		
		int find = 0;
		
		name = name.toLowerCase();
		
		String diccionario = "abcdefghijkmnlñopqrstuvwxyzáéíóúüç ";
		
		for( int i = 0 ; i < name.length() && find != -1 ; i++ ) {
			
			 find = diccionario.indexOf(name.charAt(i));	
		
		}
		
		return (find != -1);
	}
}