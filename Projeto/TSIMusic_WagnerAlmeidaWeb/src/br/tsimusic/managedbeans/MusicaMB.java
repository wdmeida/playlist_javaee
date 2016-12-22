package br.tsimusic.managedbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.tsimusic.modelo.Musica;
import br.tsimusic.sessionbeans.IMusica;

@ManagedBean
@ViewScoped
public class MusicaMB {
	@EJB
	private IMusica iMusica;
	private Musica musica = new Musica();
	private List<Musica> biblioteca;
	
	
	public void salva(){
		if(musica.getId() == null) iMusica.adiciona(musica);
		else iMusica.atualiza(musica);
		
		musica = new Musica();
		biblioteca = iMusica.getMusicas();
	}
	
	public void cancela(){
		musica = new Musica();
	}
	
	public void remove(Musica musica){
		iMusica.remove(musica);
		biblioteca = iMusica.getMusicas();
	}
	
	public List<Musica> getBiblioteca() {
		if(biblioteca == null) biblioteca = iMusica.getMusicas();
		return biblioteca;
	}

	public Musica getMusica() {
		return musica;
	}
	
	public void setMusica(Musica musica) {
		this.musica = musica;
	}
}
