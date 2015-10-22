package br.com.noticia.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class Hello {
 
	@GET
	public String get(){
		return "ola Jersey";
	}
}
