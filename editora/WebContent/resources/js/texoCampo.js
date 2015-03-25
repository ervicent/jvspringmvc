function limparPadrao(campo) {
	if (campo.value == campo.defaultValue) {
		campo.value = "";
	}
}

function escreverPadrao(campo) {
	if (campo.value == "") {
		campo.value = campo.defaultValue;
	}
}