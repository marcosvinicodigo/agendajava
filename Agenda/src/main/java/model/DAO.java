
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	/** Modulo de conexão **/
	// Parametros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";

	private String user = "root";
	private String password = "987456123abcd";

	// Metodo de conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;

		}

	}

	// crud create
	public void inserirContato(JavaBeans contato) {
		// comando que sera executado no banco de dados
		String create = "insert into contatos (nome,fone,email) values (?,?,?)";
		try {
			// abrir conexao com o banco de dados
			Connection con = conectar();

			// preparando o comando para ser executado no banco de dados
			PreparedStatement pst = con.prepareStatement(create);

			// Agora com o comando preparado nos usamos o metodo setString subistituindo 
			//as interrogaçoes pelo conteudo das variaveis do objeto contato, que pegamos no controller
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());

			// Executando o comando dentro do banco de dados
			pst.executeUpdate();

			// Encerrando a conexao com o banco
			con.close();
			//Pronto! o novo contato foi colocado dentro do banco de dados.

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// crud read
	
	//O metodo pede um retorno em um array que use como base o javabeans
	public ArrayList<JavaBeans> listarContatos(){
		
		//Criando objeto para armazenar os dados para tirar eles do banco, usuando como base
		//as variaveis do javabeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		
		//Criando o comando que vai ser executado dentro do banco de dados
		String read = "select * from contatos order by nome";
		try {
			//Conectando ao banco de dados
			Connection con = conectar();
			
			//Preparando o comando para ser executado dentro do banco de dados
			PreparedStatement pst = con.prepareStatement(read);
			
			//O comando é executado, e os dados vao parar dentro de RS kkkkkkkkk rs (risos)
			ResultSet rs = pst.executeQuery();
			
			//Loop abaixo sera executado enquanto tiver contatos dentro de rs, pegando os dados de rs e colocando nas variaveis
			while  (rs.next()) {
				
				//Variaiveis que recebem os dados do banco que estavam dentro de rs
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				
				//Inserindo esses dados no array usando um objeto JavaBeans como base 
				contatos.add(new JavaBeans(idcon,nome,fone,email));
				
				//Agora temos um array com varios objetos JavaBeans que tem as informações dos contatos
			}
			//Fechando a conexão com nosso banco de dados
			con.close();
			
			//Mandando os dados de volta a classe controller
			return contatos;
			
		} catch (Exception e) {
			
			System.out.println(e);
			return null;
		}
		
	}
	//crud UPDATE
	
	//Selecionando o contato que tem o idcon igual ao que o controller recebeu
	public void selecionarContato(JavaBeans contato) {
		
		//Criando o comando que vai ser executado no bando de dados
		String read2 = "select * from contatos where idcon = ?";
		
		
		try {
			//Conectando com o banco de dados
			Connection con = conectar();
			
			//Preparando o comando para ser executado dentro do banco de dados
			PreparedStatement pst = con.prepareStatement(read2);
			
			//Substituindo a interrogação pelo id do contato que queremos editar
			pst.setString(1, contato.getIdcon());
			
			//O comando é executado, e os dados vao parar dentro de RS kkkkkkkkk rs (risos)
			ResultSet rs = pst.executeQuery();
			
			//Alterando o objeto e inserindo os dados de RS dentro dele
			while(rs.next()) {
				
			
			contato.setIdcon(rs.getString(1));
			contato.setNome(rs.getString(2));
			contato.setFone( rs.getString(3));
			contato.setEmail(rs.getString(4));
			}
			System.out.println();
			con.close();
			
			
		} catch (Exception e) {
			System.out.println(e);
			
		}
		
	
		
	}
	
	
	public void atualizarContato(JavaBeans contato) {
		
		// comando que sera executado no banco de dados
		String update = "update contatos set nome=?,fone=?,email=? where idcon=?";
		try {
			
			//Se conectando com o banco de dados
			Connection con = conectar();
			
			//Preparando o comando para ser executado dentro do banco de dados
			PreparedStatement pst = con.prepareStatement(update);
			
			// Agora com o comando preparado nos usamos o metodo setString subistituindo 
			//as interrogaçoes pelo conteudo das variaveis do objeto contato, que pegamos no controller
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			
			// Executando o comando dentro do banco de dados
			pst.executeUpdate();

		    // Encerrando a conexao com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);}
		}
		
		public void deleteContato(JavaBeans contato) {
			
			// comando que sera executado no banco de dados
			String delete = "delete from contatos where idcon=?";
			try {
				
				//Se conectando com o banco de dados
				Connection con = conectar();
				
				//Preparando o comando para ser executado dentro do banco de dados
				PreparedStatement pst = con.prepareStatement(delete);
				
				// Agora com o comando preparado nos usamos o metodo setString subistituindo 
				//a interrogação pela id do objeto que vai ser deletado
				pst.setString(1, contato.getIdcon());
		
				// Executando o comando dentro do banco de dados
				pst.executeUpdate();

			    // Encerrando a conexao com o banco
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			
		
	    }
   }
		
}
