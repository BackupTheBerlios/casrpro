/**
*
* Javascript validations for questions forms on the client side
*
*/


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

function isValidForm(isSubmit){

	var retValue = true;
	var elements = document.forms[0].elements;
	var index = 0;
	var elem = elements[index];
	while (index < elements.length){
		if(elem.type=="text" || elem.type=="textarea"){
			if(elem.name.indexOf("number")!=-1){
				var tname = elem.name;
				var tret = true;
				var total = 0;
				while(tname==elem.name && index<elements.length){
					if(elem.value=="" || !isNumeric(elem.value) || parseInt(elem.value,10)<0)
						tret=false;
					else {
						total += parseInt(elem.value,10);
						var valName = tname + "validationType";
						if(document.forms[0].elements[valName].value=='individual'){
							var max = parseInt(document.forms[0].elements[tname + "max"].value, 10);
							var min = parseInt(document.forms[0].elements[tname + "min"].value, 10);
							if(elem.value>max || elem.value<min){
								alert("Debe ingresar un valor menor o igual a " + max + " y mayor o igual a " + min);
								return false;
							}
						}
					}
					index++;
					elem = elements[index];
				}
				if(!tret){
					alert("Debe ingresar un valor numerico y positivo en cada caso!");
					return false;
				}
				else if(document.forms[0].elements[tname + "validationType"].value=='total'){
							var max = document.forms[0].elements[tname + "max"].value;
							if(total!=max){
								alert("La suma total debe ser igual a " + max);
								return false;
							}
						}		
			}
			else { // validate not empty string
				if(elem.value==""){
					alert("Debe ingresar un texto v?lido");
					return false;
				}
				index++;
				elem = elements[index];
			}
		} else if(elem.type=="radio"){
			var tname = elem.name;
			var tret = false;
			while(tname==elem.name && index<elements.length){
				if(elem.checked)
					tret=true;
				index++;
				elem = elements[index];
			}
			if(!tret){
				alert("Debe seleccionar al menos una opcion de radio!");
				return false;
			}
		} else if(elem.type=="checkbox"){
			var tname = elem.name;
			var tret = false;
			if(elem.name.indexOf("matrix")==-1){
				while(tname==elem.name && index<elements.length){
					if(elem.checked)
						tret=true;
					index++;
					elem = elements[index];
				}
			}
			else {
				tname = elem.name.substr(0,14);
				while(tname==elem.name.substr(0,14) && index<elements.length){
					if(elem.checked)
						tret=true;
					index++;
					elem = elements[index];
				}
			}
			if(!tret){
				alert("Debe seleccionar al menos una opcion de checkbox!");
				return false;
			}
		} else {
			index++;
			elem = elements[index];
		}
	}
	
	if(isSubmit)
		document.forms[0].submit();
	else {
		document.forms[0].method.value="finish";
		document.forms[0].submit();
	}
}

function cleanForm(){

    if(document.forms.length>0){
		var elements = document.forms[0].elements;
		var index = 0;
		while (index < elements.length){
			var elem = elements[index];
			if(elem.type=="checkbox"){
				elem.checked=false;
			} 
			else if(elem.type=="radio"){
				elem.checked=false;
			}
			else if(elem.type=="text" || elem.type=="textarea"){
				elem.value="";
			}
			index++;
		}
	}
}
