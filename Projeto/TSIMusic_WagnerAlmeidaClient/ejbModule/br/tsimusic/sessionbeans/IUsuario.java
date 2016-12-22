package br.tsimusic.sessionbeans;

import java.util.List;

import br.tsimusic.modelo.Usuario;

public interface IUsuario {
	public void adiciona(Usuario usuario);
	public void remove(Usuario usuario);
	public void atualiza(Usuario usuario);
	public Usuario pesquisaUsuario(Usuario usuario);
	public List<Usuario> getUsuarios();
}
