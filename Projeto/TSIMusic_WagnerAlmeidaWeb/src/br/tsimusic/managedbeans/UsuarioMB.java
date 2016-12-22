package br.tsimusic.managedbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.tsimusic.modelo.Musica;
import br.tsimusic.modelo.Usuario;
import br.tsimusic.sessionbeans.IUsuario;

@ManagedBean
@SessionScoped
public class UsuarioMB {
	@EJB
	private IUsuario iUsuario;
	
	private Usuario usuario = new Usuario();
	private List<Musica> playlist;
	
	public String salvar(){
		iUsuario.adiciona(usuario);
		return "usuario_cadastrado_sucesso?faces-redirect=true";
	}
	
	public void addMusica(Musica musica){
		playlist.add(musica);
	}
	
	public void removeMusica(Musica musica){
		playlist.remove(musica);
	}
	
	public String salvaAlteracoes(){
		usuario.setPlaylist(playlist);
		iUsuario.atualiza(usuario);
		return "playlist_salva_sucesso?faces-redirect=true";
	}
	
	public String logout(){
		usuario = new Usuario();
		playlist = null;
		return "login?faces-redirect=true";
	}
	
	public String login(){
		Usuario user = iUsuario.pesquisaUsuario(usuario);
		if(user == null) return "login?faces-redirect=true";
		usuario = user;
		return "playlist?faces-redirect=true";
	}
	
	public List<Musica> getPlaylist(){
		if(playlist == null) playlist = usuario.getPlaylist();
		return playlist;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
