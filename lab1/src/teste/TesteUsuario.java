package teste;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import projeto.perfil.PerfilMusical;
import projeto.perfil.Som;
import projeto.session.Sessao;
import projeto.sistem.YouRadio;
import projeto.user.Usuario;

public class TesteUsuario {
	
	YouRadio sistema;
	Usuario user01;
	Usuario user02;
	
	Sessao sessao1;
	Sessao sessao2;
	
	@Before
	public void zeraSistema(){
		YouRadio sistema = new YouRadio();
		Usuario user01 = new Usuario("mark", "m@rk", "Mark Zuckerberg", "mark@facebook.com");
		Usuario user02 = new Usuario("steve","5t3v3", "Steven Paul Jobs", "jobs@apple.com");
		sistema.addUser(user01);
		sistema.addUser(user02);
		sessao1 = sistema.abrirSessao("mark", "m@rk");
		sessao2 = sistema.abrirSessao("steve", "5t3v3");
		
	}
	
	@Test
	public void testaAbrirSessao(){
		
		
		Assert.assertEquals("Mark Zuckerberg", sessao1.getUser("mark").getNome());
		Assert.assertEquals("mark@facebook.com", sessao1.getUser("mark").getEmail());
		
		Assert.assertEquals("Steven Paul Jobs", sessao2.getUser("steve").getNome());
		Assert.assertEquals("jobs@apple.com", sessao2.getUser("steve").getEmail());	
	}
	
	
	@Test
	public void testaGetPerfilMusical(){
		
		
		Assert.assertEquals(new PerfilMusical(),sistema.getPerfilMusical(sessao1));
		Assert.assertEquals(new PerfilMusical(),sistema.getPerfilMusical(sessao2));

		Som som01 = new Som("http://letras.mus.br/david-guetta/#mais-acessadas/1944260","23/06/2013");
		Som som02 = new Som("http://letras.mus.br/david-guetta/#mais-acessadas/1944260","24/06/2013");
		
		sessao1.getUser("mark").postarSom(som01);
		sessao1.getUser("mark").postarSom(som02);
		
		Assert.assertEquals( "23/06/2013", sistema.getSom(som01.getID()).getDataDeCriacao());
		Assert.assertEquals( "24/06/2013", sistema.getSom(som02.getID()).getDataDeCriacao());
		
		Som som03 = new Som("http://www.youtube.com/watch?v=3KsPIJK17uk","25/12/2013");
		Som som04 = new Som("http://www.youtube.com/watch?v=JRfuAukYTKg","23/06/2014");
		
		sessao2.getUser("steve").postarSom(som03);
		sessao2.getUser("steve").postarSom(som04);
		
		Assert.assertEquals( "25/12/2013", sistema.getSom(som03.getID()).getDataDeCriacao());
		Assert.assertEquals( "23/06/2014", sistema.getSom(som04.getID()).getDataDeCriacao());
		
		
		PerfilMusical perfil01 = new PerfilMusical();
		
		perfil01.addSom(som01);
		perfil01.addSom(som02);
		
		Assert.assertEquals(perfil01, sistema.getPerfilMusical(sessao1));
		PerfilMusical perfil02 = new PerfilMusical();
		
		perfil02.addSom(som03);
		perfil02.addSom(som04);
		
		Assert.assertEquals(perfil02, sistema.getPerfilMusical(sessao2));
	

		
		
		
		
		
	}
	

}