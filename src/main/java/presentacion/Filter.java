package presentacion;

import java.util.HashMap;

public class Filter {

	private HashMap<String, String>hashmap;
	
	public Filter() {
		hashmap = new HashMap<String, String>();
	}
	
	public Filter addFilter(String name, String value) {
		hashmap.put("{" + name + "}", value);
		return this;
	}
	
	public String filter(String message) {
		for(HashMap.Entry<String, String> entry : hashmap.entrySet()) {
			message = message.replace(entry.getKey(), entry.getValue());
		}
		return message;
	}
}
