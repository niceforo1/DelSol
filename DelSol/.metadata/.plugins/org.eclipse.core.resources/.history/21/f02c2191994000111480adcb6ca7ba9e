package controller;
import java.util.Hashtable;
public class ParametrosAccion {
	private Hashtable<String, Object> params=null;
	
	public ParametrosAccion(){
		params = new Hashtable<String, Object>();
	}
	
	public void add(String k, Object v){
		this.params.put(k,v);
	}
	
	public Object get(String k){
		if(this.params.containsKey(k)){
			return this.params.get(k);
		}
		return null;
	}
}
