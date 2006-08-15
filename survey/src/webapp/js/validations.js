/**
*
* Javascript validations for questions forms
*
*/

// simple question validations

function verifySimple(){

	if(document.forms[0].name.value==""){
		alert("Debe ingresar un nombre!");
		return false;
	}
	
	if(document.forms[0].question.value==""){
		alert("Debe ingresar una pregunta!");
		return false;
	}
	
	if(document.forms[0].answerType.selectedIndex==0){
		alert("Debe ingresar seleccionar el tipo de respuesta");
		return false;
	}

	fillOpenerRow();
	
}

function verifyNoAnswer(){

	if(document.forms[0].name.value==""){
		alert("Debe ingresar un nombre!");
		return false;
	}
	
	if(document.forms[0].question.value==""){
		alert("Debe ingresar una pregunta!");
		return false;
	}

	fillOpenerRow();
	
}

function verifyUnique(){

	if(document.forms[0].name.value==""){
		alert("Debe ingresar un nombre!");
		return false;
	}
	
	if(document.forms[0].question.value==""){
		alert("Debe ingresar una pregunta!");
		return false;
	}
		
	var tableAnswers = document.getElementById("respuestas");
	var lastRowFound = tableAnswers.rows.length;
	
	if(lastRowFound<2){
		alert("Debe ingresar al menos una respuesta!");
		return false;
	}

	fillOpenerRow();
	
}

function verifyNumeric(){

	if(document.forms[0].name.value==""){
		alert("Debe ingresar un nombre!");
		return false;
	}
	
	if(document.forms[0].question.value==""){
		alert("Debe ingresar una pregunta!");
		return false;
	}
	
	selecter = document.forms[0].validationType.selectedIndex;
	
	if(selecter == 1){
		if(document.forms[0].min.value=="" || document.forms[0].max.value==""){
			alert("Debe ingresar los valores m?nimo y m?ximo!");
			return false;
		}
	}
	else if (selecter == 2) {
		if(document.forms[0].total.value==""){
			alert("Debe ingresar el valor total!");
			return false;
		}
	}

	fillOpenerRow();
	
}

function verifyMatrix(){

	if(document.forms[0].name.value==""){
		alert("Debe ingresar un nombre!");
		return false;
	}
	
	if(document.forms[0].question.value==""){
		alert("Debe ingresar una pregunta!");
		return false;
	}
	
	var tableAnswers = document.getElementById("respuestas");
	var lastRowFound = tableAnswers.rows.length;
	
	if(lastRowFound<2){
		alert("Debe ingresar al menos una respuesta!");
		return false;
	}
	
	var tableColumns = document.getElementById("columnas");
	var lastColFound = tableColumns.rows.length;
	
	if(lastColFound<2){
		alert("Debe ingresar al menos una columna!");
		return false;
	}
	
	fillOpenerRow();
	
}

function verifyText(){

	if(document.forms[0].name.value==""){
		alert("Debe ingresar un texto!");
		return false;
	}
	
	fillOpenerRow();
	
}

function verifyTextCol(){

	if(document.forms[0].name.value==""){
		alert("Debe ingresar un texto!");
		return false;
	}
	
	fillOpenerCol();
	
}

