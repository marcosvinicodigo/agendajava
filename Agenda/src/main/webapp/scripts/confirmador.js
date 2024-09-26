/**
 * 
 */
function confirmar(idcon){
	let resposta = confirm("Confirma a exclusao desse numero?")
	if (resposta === true) {
		window.location.href= "delete?idcon=" + idcon;
	}
}