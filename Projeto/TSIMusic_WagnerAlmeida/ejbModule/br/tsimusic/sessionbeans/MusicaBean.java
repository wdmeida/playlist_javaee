package br.tsimusic.sessionbeans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.tsimusic.modelo.Musica;

@Stateless
@Remote(IMusica.class)
public class MusicaBean implements IMusica {
	
	@PersistenceContext(name = "TSIMusic")
	private EntityManager em;
	
	public void adiciona(Musica musica) {
		em.persist(musica);
	}

	public void remove(Musica musica) {
		em.remove(em.merge(musica));
	}

	public void atualiza(Musica musica) {
		em.merge(musica);
	}

	public List<Musica> getMusicas() {
		TypedQuery<Musica> query = em.createQuery("SELECT m FROM Musica m", Musica.class);
		List<Musica> musicas = query.getResultList();
		return musicas;
	}
}
