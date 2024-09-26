package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete"})
//o comando /insert é ativado quando o novo contato é enviado, sendo pego pelo doGet e ativando o metodo novoContato
//o comando /select é ativado quando o botão editar contato é apertado, sendo pego pelo doGet
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();

	// aqui instanciamos o objeto contato que vai mandar as informaçoes pro dao
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			contatos(request,response);
			
		} else if (action.equals("/insert")) {
			novoContato(request,response);
			} else if (action.equals("/select")){
				listarContato(request,response);
				
			} else if (action.equals("/update")){
				updateContato(request,response); }
				
				else if (action.equals("/delete")){
					deletarContato(request,response);
				
			}  else {
				response.sendRedirect("index.html");
			}
		
		}

	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que ira receber os dados do contato usando como base as
		// variaveis do JavaBeans
		// esses dados serão pegos do banco de dados pela classe DAO
		ArrayList<JavaBeans> lista = dao.listarContatos();

		// Encaminhando o objeto com os dados do contato pegos do banco ate a agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
		
	}

	// novo Contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// as informaçoes do contato sao colocadas no request e podem ser acessadas com
		// o getParameter, assim
		// colocamos elas dentro do objeto contato que é enviado ao DAO

		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// Com as informações dentro do objeto contato, usamos o metodo inserirContato
		// da classe DAO, responsavel por
		// colocar os dados no banco de dados
		dao.inserirContato(contato);
		
		// REDIRECIONAR PARA O DOCUMENTO AGENDA.JSP
		response.sendRedirect("main");
	}
	// editar Contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Recebendo o id do contato que vai ser editado da agenda.jsp
		String idcon = request.getParameter("idcon");
		
		//Colocando o id num objeto JavaBeans para enviar pra classe DAO
		contato.setIdcon(idcon);
		
		//Enviando o objeto com o id a classe DAO
		dao.selecionarContato(contato);
		
		//Mandado os dados obtidos pela classe DAO para a pagina de edição
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
		
		 
		;}
	protected void updateContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Com as informações do contato que vai ser atualizado dentro do request, podemos mandar
		//ela pra classe DAO para atualizar essas informções no banco de dados
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		

		// Com as informações dentro do objeto contato, usamos o metodo atualizarContato
		// da classe DAO, responsavel por
		// colocar os dados no banco de dados
        dao.atualizarContato(contato);
		
		// REDIRECIONAR PARA O DOCUMENTO AGENDA.JSP
		response.sendRedirect("main");
	}
	//Deletar contato
	protected void deletarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Recebendo o id do contato que vai ser editado da agenda.jsp
		String idcon = request.getParameter("idcon");
		
		//Colocando o id num objeto JavaBeans para enviar pra classe DAO
		contato.setIdcon(idcon);
		
		//Enviando o objeto com o id a classe DAO
		dao.deleteContato(contato);
		
		// REDIRECIONAR PARA O DOCUMENTO AGENDA.JSP
		response.sendRedirect("main");
	}


}
