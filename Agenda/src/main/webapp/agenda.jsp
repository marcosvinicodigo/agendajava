<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
 @ SuppressWarnings ("unchecked")
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
//Recebendo o objeto com os contatos la da classe controller, que vao ser mostrados a partir de um for simples
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/telefone.gif">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="menu">
		<h1>Agenda de contatos</h1>
		<a href="novo.html" class="botao">Novo contato</a>
		</div>
		
		
		
		<div class="menu">
		<table>
			<thead>
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Fone</th>
					<th>Email</th>
					<th>Opções</th>

				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < lista.size(); i++) {
				%>
                  <tr>
                    <td> <%=lista.get(i).getIdcon() %></td>
                    <td> <%=lista.get(i).getNome() %></td>
                    <td> <%=lista.get(i).getFone() %></td>
                    <td> <%=lista.get(i).getEmail() %></td>
                    <td> <a href="select?idcon=<%=lista.get(i).getIdcon()%>" class="botao">Editar</a>
                    <td> <a href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)" class="botao">Deletar</a>
                  </tr>
				<%
				}
				%>
			</tbody>

		</table>
		</div>
	

   <script src="scripts/confirmador.js"></script>
</body>
</html>