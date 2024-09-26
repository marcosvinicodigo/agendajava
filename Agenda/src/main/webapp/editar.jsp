<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/telefone.gif">
<link rel="stylesheet" href="style.css">
</head>

<body>
   <div class="form">
	<h1>Editar novo contato</h1>
	<form class="form" name="formContato" action="update">
		<table>
		    <tr>
				<td><input id="caixa3" type="text" name="idcon" readonly value="<%out.print(request.getAttribute("idcon"));%>">
			</tr>
			<tr>
				<td><input class="caixa1" type="text" name="nome" value="<%out.print(request.getAttribute("nome"));%>">
			</tr>
			<tr>
				<td><input class="caixa2" type="text" name="fone" value="<%out.print(request.getAttribute("fone"));%>">
			</tr>
			<tr>
				<td><input class="caixa1" type="text" name="email" value="<%out.print(request.getAttribute("email"));%>">
			</tr>

		</table>
		<input class="botao" type="button" value="Salvar" onclick="validar()">
     </form>
    </div>
    <script src="scripts/validador.js"></script>
</body>
</html>

