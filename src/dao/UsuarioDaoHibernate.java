package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Usuario;
import util.PersistenceUtil;

public class UsuarioDaoHibernate implements UsuarioDao{
	
	EntityManager em = PersistenceUtil.getEntityManager();

	public void salvar(Usuario usuario) {
		
		em.getTransaction().begin();		
		try {
			em.persist(usuario);					
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public void atualizar(Usuario usuario) {
		em.getTransaction().begin();		
		try {
			em.merge(usuario);					
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}

	public void excluir(Usuario usuario) {
		
		em.getTransaction().begin();
		try {
			em.remove(usuario);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}

	}

	public Usuario carregar(Integer codigo) {
		
		Usuario usuario = null;
		em.getTransaction().begin();
		try {
			usuario = em.find(Usuario.class, codigo); 	
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		return usuario;
	}

	public Usuario buscarPorLogin(String login) throws Exception {
		
		String hql = "select f from Usuario u where u.login=:login";
		
		Query q = em.createQuery(hql);

		q.setParameter("login", login);
		
		try {
			
			Usuario usuario = (Usuario)  q.getSingleResult();
			
			return usuario;
			
		} catch (Exception e) {
			
			throw new Exception(e); 
			
		}

	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listar() {

		List<Usuario> lista = null;
		em.getTransaction().begin();
		try {
			Query consulta = em.createQuery("select u from Usuario u");
			lista = consulta.getResultList(); 
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}			
		return lista;
	}

}
