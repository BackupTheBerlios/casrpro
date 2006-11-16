package ar.com.survey.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * Class used to store and retrieve httpsession atributes in an ordered way
 */
public class ClientSessionManager implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Map attributes;
	
	public ClientSessionManager(){
		attributes = new HashMap();
	}
	
	public Object getAttribute(String key){
		return attributes.get(key);
	}
	
	public void setAttribute(String key, Object value){
		if(attributes.containsKey(key))
			attributes.remove(key);
		attributes.put(key, value);
	}
	
	public void removeAttribute(String key){
		attributes.remove(key);
	}
	
	public boolean containsAttribute(String key){
		return attributes.containsKey(key);
	}

}
