package br.tsimusic.app;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.tsimusic.modelo.Musica;
import br.tsimusic.modelo.Usuario;
import br.tsimusic.sessionbeans.IMusica;
import br.tsimusic.sessionbeans.IUsuario;

public class TSIMusicApp {

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
				
		properties.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
		properties.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
		
		InitialContext ic;
		
		try {
			ic = new InitialContext(properties);
			IMusica iMusica = (IMusica) ic.lookup("br.tsimusic.sessionbeans.IMusica");
			
			List<Musica> musicas = iMusica.getMusicas();
			
			System.out.println("Lista as m�sicas cadastradas:");
			musicas.forEach(m -> { System.out.println(m.getNome()); });
			
			//Verifica se o usu�rio wagner existe, caso exista, lista suas m�sicas.
			IUsuario iUsuario = (IUsuario) ic.lookup("br.tsimusic.sessionbeans.IUsuario");
			Usuario usuario = new Usuario();
			usuario.setLogin("wagner");
			usuario.setSenha("123456");
			
			Usuario u = iUsuario.pesquisaUsuario(usuario);
			if(u != null){
				System.out.println("\nM�sicas do usu�rio " + u.getLogin());
				List<Musica> m = u.getPlaylist();
				for(Musica ma : m)
					System.out.println(ma.getNome());
			}
			
			//Cadastra uma nova m�sica.
			System.out.println("\nAdicionando a m�sicas � biblioteca.");
			Musica musica = new Musica();
			musica.setArtista("Kiss");
			musica.setNome("We are one");
			musica.setLocalizacao("Psyco Circus");
			iMusica.adiciona(musica);
			
			musica = new Musica();
			musica.setArtista("Metallica");
			musica.setNome("Master of Puppets");
			musica.setLocalizacao("Master of Puppets");
			iMusica.adiciona(musica);
			
			musica = new Musica();
			musica.setArtista("Pink Floyd");
			musica.setNome("Confortubly Numb");
			musica.setLocalizacao("Pulse");
			iMusica.adiciona(musica);
			
			musica = new Musica();
			musica.setArtista("Legi�o Urbana");
			musica.setNome("Que Pa�s � esse");
			musica.setLocalizacao("Que Pa�s � esse");
			iMusica.adiciona(musica);
			
			musica = new Musica();
			musica.setArtista("Avici");
			musica.setNome("Hey Brother");
			musica.setLocalizacao("Hey Brother");
			iMusica.adiciona(musica);
			
			musica = new Musica();
			musica.setArtista("O Rappa");
			musica.setNome("Mis�ria S.A");
			musica.setLocalizacao("Rappa Mundi");
			iMusica.adiciona(musica);
			
			musica = new Musica();
			musica.setArtista("Capital Inicial");
			musica.setNome("N�o olhe pra tr�s");
			musica.setLocalizacao("Rosas e Vinho Tinto");
			iMusica.adiciona(musica);
			System.out.println("M�sica cadastrada com sucesso.");

			
			//Lista as m�sicas cadastradas ap�s a atualiza��o.
			musicas = iMusica.getMusicas();
			
			System.out.println("\nNova lista de m�sicas ap�s a inser��o:");
			musicas.forEach(m -> { System.out.println(m.getNome()); });
			
			//Adiciona a playlist do usu�rio wagner a �ltima m�sica cadastrada.
			System.out.println("\nAdicionando a �ltima m�sica cadastrada � playlist do usu�rio wagner.");
			musicas = u.getPlaylist();
			musicas.add(musica);

			u.setPlaylist(musicas);
			iUsuario.atualiza(u);
			
			u = iUsuario.pesquisaUsuario(usuario);
			if(u != null){
				System.out.println("\nM�sicas do usu�rio " + u.getLogin() + " ap�s a atualiza��o:");
				List<Musica> m = u.getPlaylist();
				for(Musica ma : m)
					System.out.println(ma.getNome());
			}
		} catch (NamingException e) {
			System.out.println(e);
		}	
		
	}//main()
	
/*	public static String leInformacoes(String mensagem){
		String texto = JOptionPane.showInputDialog(null, mensagem, null);
		return texto;
	}
	
	public static void exibePlaylist(IUsuario iUsuario){
		String login = leInformacoes("Insira seu login: ");
		String senha = leInformacoes("Insira sua senha: ");
		
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		Usuario u = iUsuario.pesquisaUsuario(usuario);
		if(u != null){
			JOptionPane.showMessageDialog(null, formataPlaylistExibicao(u.getPlaylist()), "Playlist", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static String formataPlaylistExibicao(List<Musica> playlist){
		String musica = "";
		
		for(Musica m : playlist){
			musica += m.getNome() + " - " + m.getArtista() + " - " + m.getLocalizacao() + "\n";
		}
		return musica;
	}*/
}
