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

function verifySurvey(){
	var startDate = document.forms[0].startDate.value;
	var finishDate = document.forms[0].endDate.value;
	if(document.forms[0].name.value==""){
		alert("Debe ingresar un nombre!");
		return false;
	} else if(!isValidStringDate(startDate)){
		alert("Debe ingresar una fecha de inicio v?lida!");
		return false;
	} else if (!isValidStringDate(finishDate)){
		alert("Debe ingresar una fecha de fin v?lida!");
		return false;
	}
	return true;
}

function isValidStringDate(sDate){
	if(sDate=="")
		return false;
	if(sDate.indexOf("/")==-1)
		return false;
	var dateArray = sDate.split("/");
	var day = dateArray[0];
	var month = dateArray[1];
	var year = dateArray[2];
	
	if(parseInt(day,10)<1 || parseInt(day,10)>31)
		return false;
	else if(parseInt(month,10)<1 || parseInt(month,10)>12)
		return false;
	else if(parseInt(year,10)>3000 || parseInt(year,10)<2000)
		return false;
		
	return true;
}

function isNumeric(sText){

   var ValidChars = "0123456789.";
   var IsNumber=true;
   var Char;

 
   for (i = 0; i < sText.length && IsNumber == true; i++) 
      { 
      Char = sText.charAt(i); 
      if (ValidChars.indexOf(Char) == -1) 
         {
         IsNumber = false;
         }
      }
   return IsNumber;
   
}

function verifyQuota(){
	if(document.forms[0].name.value==""){
		alert("Debe ingresar una descripci?n v?lida!");
		return false;
	} else if(!isNumeric(document.forms[0].count.value) || (document.forms[0].count.value=="")) {
		alert("Debe ingresar un valor num?rico!");
		return false;
	} else 
		return true;
}