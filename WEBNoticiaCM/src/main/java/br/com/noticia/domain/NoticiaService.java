package br.com.noticia.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.noticia.dao.NoticiaDAO;

@Component
public class NoticiaService {

	@Autowired
	private NoticiaDAO db;
	
	//listar todos as noticias do banco de dados
	public List<Noticia> getNoticias(){
		List<Noticia> noticias = db.getNoticias();
		return noticias;
	}
	
	//busca pelo id
	public Noticia getNoticia(Long id){
		 return db.getNoticiaById(id);
	}
	
	//deletea carro pelo id
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Long id){
		return db.delete(id);
	}
	
	//Salvar ou atualizar noticia
	@Transactional(rollbackFor = Exception.class)
	public boolean save(Noticia not){
		db.saveOrUpdate(not);
		return true;
	}
	
	
	// Busca noticia pelo titulo
	public List<Noticia> findByTitulo(String titulo) {
		return db.findByTitulo(titulo);
	}
	
		
	// Busca noticia pela categoria
	public List<Noticia> findByCategoria(String categ) {
		return db.findByCategoria(categ);
	}
		
	
	
	
}
