package br.com.noticia.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import br.com.noticia.domain.Noticia;

@SuppressWarnings("unchecked")
@Component
public class NoticiaDAO extends HibernateDAO<Noticia>{

	public NoticiaDAO() {
		super(Noticia.class);
	}
	
	//Consulta pelo id
	public Noticia getNoticiaById(Long id){
		return super.get(id);
	}
	
	//Consulta pelo titulo
	public List<Noticia> findByTitulo(String titulo){
		Query query = getSession().createQuery("from Noticia where lower(titulo) like lower(?)");
		query.setString(0, "%"+titulo+"%");
		return query.list();
	}
	
	//Consulta por categoria
	public List<Noticia> findByCategoria(String categ){
		Query query = getSession().createQuery("from Noticia where categoria=?");
		query.setString(0, categ);
		List<Noticia> not = query.list();
		return not;
	}
	
	// Consulta todas as noticias
	public List<Noticia> getNoticias(){
		Query query = getSession().createQuery("from Noticia");
		List<Noticia> not = query.list();
		return not;
	}
	
	// Inseri ou Atualiza Noticia
	public void salvar(Noticia n){
		super.save(n);
	}
	
	// Delete Noticia Pelo ID
	public boolean delete(Long id){
		Noticia n = get(id);
		delete(n);
		return true;
	}
	
	
}
