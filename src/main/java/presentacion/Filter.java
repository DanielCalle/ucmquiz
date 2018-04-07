package presentacion;

import java.util.HashMap;

public class Filter {

	/**
	 * Atributo hashmap con clave valor para sustituir los valores de los mensajes
	 * @author Zihao
	 */
	private HashMap<String, String>hashmap;
	
	/**
	 * Constructora
	 * @author Zihao
	 */
	public Filter() {
		hashmap = new HashMap<String, String>();
	}
	
	/**
	 * Anade pares clave valor al hashmap
	 * @param name Clave del hashmap
	 * @param value Valor del hashmap
	 * @return Devuelve la misma clase Filter para tener la caracteristica de builder
	 * @author Zihao
	 */
	public Filter addFilter(String name, String value) {
		hashmap.put("{" + name + "}", value);
		return this;
	}
	
	/**
	 * Dado un mensaje sustituye todo entre corchete por su valor correspondiente en el hashmap
	 * @param message Mensaje a transformar
	 * @return Mensaje transformado
	 */
	public String filter(String message) {
		for(HashMap.Entry<String, String> entry : hashmap.entrySet()) {
			message = message.replace(entry.getKey(), entry.getValue());
		}
		return message;
	}
}
