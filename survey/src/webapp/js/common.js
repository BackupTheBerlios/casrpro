/***
*
* 
*  Common java script functions
*  Claudio Petronio 2006
*
*/
	
	function popModal(url, title) {
			window.open(url,title,'height=300,width=370,resizable=no,scrollbars=no,status=no,toolbar=no,menubar=no');
	}
	
	function popModal(url,title,h,w,s) {
			var attrs = 'height=' + h + ',width=' + w + ',resizable=no,status=no,menubar=no,scrollbars='+s;
			window.open(url,title, attrs);
	}
	
	function getElement(aID){ 
     return (document.getElementById) ? document.getElementById(aID)
                                      : document.all[aID];
   } 
   
   function replaceHtmlCodes(originalTxt){
   
   	var result = "";
	for(i=0;i<originalTxt.length;i++){
		var ix = originalTxt.charCodeAt(i);
		switch(ix){
		  case 225:
			result += "#aacute;";
			break;
		  case 233:
			result += "#eacute;";
			break;
		  case 237:
			result += "#iacute;";
			break;
		  case 243:
			result += "#oacute;";
			break;
		  case 250:
			result += "#uacute;";
			break;
		  case 193:
			result += "#Aacute;";
			break;
		  case 201:
			result += "#Eacute;";
			break;
		  case 205:
			result += "#Iacute;";
			break;
		  case 211:
			result += "#Oacute;";
			break;
		  case 218:
			result += "#Uacute;";
			break;
		  case 241:
			result += "#ntilde;";
			break;
		  case 209:
			result += "#Ntilde;";
			break;
		  case 252:
			result += "#uuml;";
			break;
		  case 220:
			result += "#Uuml;";
			break;
		  case 191:
			result += "##191;";
			break;
		  case 161:
			result += "##161;";
			break;
		  default:
			result += originalTxt.charAt(i);
		}	
	}
	return result;
   }
