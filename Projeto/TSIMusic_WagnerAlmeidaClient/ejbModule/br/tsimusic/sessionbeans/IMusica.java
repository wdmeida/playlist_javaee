package br.tsimusic.sessionbeans;

import java.util.List;

import br.tsimusic.modelo.Musica;

public interface IMusica {
	public void adiciona(Musica musica);
	public void remove(Musica musica);
	public void atualiza(Musica musica);
	public List<Musica> getMusicas();
}
