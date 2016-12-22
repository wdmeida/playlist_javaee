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
			
			System.out.println("Lista as músicas cadastradas:");
			musicas.forEach(m -> { System.out.println(m.getNome()); });
			
			//Verifica se o usuário wagner existe, caso exista, lista suas músicas.
			IUsuario iUsuario = (IUsuario) ic.lookup("br.tsimusic.sessionbeans.IUsuario");
			Usuario usuario = new Usuario();
			usuario.setLogin("wagner");
			usuario.setSenha("123456");
			
			Usuario u = iUsuario.pesquisaUsuario(usuario);
			if(u != null){
				System.out.println("\nMúsicas do usuário " + u.getLogin());
				List<Musica> m = u.getPlaylist();
				for(Musica ma : m)
					System.out.println(ma.getNome());
			}
			
			//Cadastra uma nova música.
			System.out.println("\nAdicionando a músicas à biblioteca.");
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
			musica.setArtista("Legião Urbana");
			musica.setNome("Que País é esse");
			musica.setLocalizacao("Que País é esse");
			iMusica.adiciona(musica);
			
			musica = new Musica();
			musica.setArtista("Avici");
			musica.setNome("Hey Brother");
			musica.setLocalizacao("Hey Brother");
			iMusica.adiciona(musica);
			
			musica = new Musica();
			musica.setArtista("O Rappa");
			musica.setNome("Miséria S.A");
			musica.setLocalizacao("Rappa Mundi");
			iMusica.adiciona(musica);
			
			musica = new Musica();
			musica.setArtista("Capital Inicial");
			musica.setNome("Não olhe pra trás");
			musica.setLocalizacao("Rosas e Vinho Tinto");
			iMusica.adiciona(musica);
			System.out.println("Música cadastrada com sucesso.");

			
			//Lista as músicas cadastradas após a atualização.
			musicas = iMusica.getMusicas();
			
			System.out.println("\nNova lista de músicas após a inserção:");
			musicas.forEach(m -> { System.out.println(m.getNome()); });
			
			//Adiciona a playlist do usuário wagner a última música cadastrada.
			System.out.println("\nAdicionando a última música cadastrada à playlist do usuário wagner.");
			musicas = u.getPlaylist();
			musicas.add(musica);

			u.setPlaylist(musicas);
			iUsuario.atualiza(u);
			
			u = iUsuario.pesquisaUsuario(usuario);
			if(u != null){
				System.out.println("\nMúsicas do usuário " + u.getLogin() + " após a atualização:");
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
