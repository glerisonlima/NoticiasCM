package br.com.noticia.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.noticia.domain.Noticia;
import br.com.noticia.domain.NoticiaService;
import br.com.noticia.domain.Response;

@Path("/noticias")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class NoticiaResources {

	@Autowired
	private NoticiaService noticiaService;
	
	@GET
	public List<Noticia> get(){
		List<Noticia> noticias = noticiaService.getNoticias();
		return noticias;
	}
	
	
	@GET
	@Path("{id}")
	public Noticia get(@PathParam("id")long id){
		Noticia n = noticiaService.getNoticia(id);
		return n;
	}
	
	
	@GET
	@Path("/titulo/{titulo}")
	public List<Noticia> getByTitulo(@PathParam("titulo")String titulo){
		List<Noticia> noticias = noticiaService.findByTitulo(titulo);
		return noticias;
	}
	
	@GET
	@Path("/categoria/{categoria}")
	public List<Noticia> getByCategoria(@PathParam("categoria")String categoria){
		List<Noticia> noticias = noticiaService.findByCategoria(categoria);
		return noticias;
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id){
		noticiaService.delete(id);
		return Response.Ok("Noticia deletado com sucesso!");
	}
	
	@POST
	public Response post(Noticia n){
		noticiaService.save(n);
		return Response.Ok("Noticia Salvo com sucesso!");
	}
	
	@PUT
	public Response put(Noticia n){
		noticiaService.save(n);
		return Response.Ok("Noticia Atualizado com sucesso!");
	}
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response portFoto(final FormDataMultiPart multipart){
		if(multipart != null && multipart.getFields() != null){
			Set<String> Keys = multipart.getFields().keySet();
			for(String key : Keys){
				//Obtem o inputstream para ler o arquivo
				FormDataBodyPart field = multipart.getField(key);
				InputStream in = field.getValueAs(InputStream.class);
				try{
					//Salva o arquivo
					String fileName = field.getFormDataContentDisposition().getFileName();
					//Pasta temporaria da jvm
					File tmpDir = new File(System.getProperty("java.io.tmpdir"), "noticias");
					if(!tmpDir.exists()){
						//cria a pasta noticias se não existir
						tmpDir.mkdir();
					}
					//Cria o arquivo
					File file = new File(tmpDir , fileName);
					FileOutputStream out = new FileOutputStream(file);
					IOUtils.copy(in, out);
					System.out.println("Arquivo: " + file);
					return Response.Ok("Arquivo recebido com sucesso!");
				}catch (IOException e){
					e.printStackTrace();
					return Response.Error("Erro ao enviar o arquivo!");
				}
			}
		}
		return Response.Ok("Requisição invalida.");
	}
	
	
	
	
}
