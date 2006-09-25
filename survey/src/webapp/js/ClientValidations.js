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

function isValidForm(){

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
							var max = document.forms[0].elements[tname + "max"].value;
							var min = document.forms[0].elements[tname + "min"].value;
							if(elem.value>max || elem.value<min){
								alert("Debe ingresar un valor menor a " + max + " y mayor a " + min);
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
			while(tname==elem.name && index<elements.length){
				if(elem.checked)
					tret=true;
				index++;
				elem = elements[index];
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
	
	document.forms[0].submit();

}

