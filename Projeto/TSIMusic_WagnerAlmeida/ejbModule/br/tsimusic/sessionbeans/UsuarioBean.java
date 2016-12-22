package br.tsimusic.sessionbeans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.tsimusic.modelo.Usuario;

@Stateless
@Remote(IUsuario.class)
public class UsuarioBean implements IUsuario {
	
	@PersistenceContext(name = "TSIMusic")
	private EntityManager em;
	
	public void adiciona(Usuario usuario) {
		em.persist(usuario);
	}

	public void remove(Usuario usuario) {
		em.remove(em.merge(usuario));
	}

	public void atualiza(Usuario usuario) {
		em.merge(usuario);
	}

	public List<Usuario> getUsuarios() {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		return usuarios;
	}

	public Usuario pesquisaUsuario(Usuario usuario) {
		
		try {
			String sql = "SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha";
			TypedQuery<Usuario> query = em.createQuery(sql, Usuario.class);
			query.setParameter("login", usuario.getLogin());
			query.setParameter("senha", usuario.getSenha());
			Usuario user = query.getSingleResult();
			return user;
		} catch (NoResultException e) {
			return null;
		}
	}
}
