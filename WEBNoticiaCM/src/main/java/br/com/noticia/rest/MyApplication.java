package br.com.noticia.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;



public class MyApplication extends Application{

	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<>();
		// suporte ao file upload
		singletons.add(new MultiPartFeature());
		return singletons;
	}

	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		// Configura o pacote para fazer scan das classes com anotações REST.
		properties.put("jersey.config.server.provider.packages", "br.com.noticia");
		return properties;
	}
	
	
}
