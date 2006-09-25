<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Editar Pregunta</title>
<link href="../css/css.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../js/validations.js"></script>
<script language="JavaScript" src="../js/common.js"></script>
<script language="JavaScript" type="text/javascript" src="../js/functions.js"></script>
<script language="JavaScript" src="../js/simpleAjax.js"></script>
<script language="JavaScript" type="text/javascript">
<!--
function selectTipo(obj) {
	var dn = getObject('divNone');
	var di = getObject('divInd');
	var dt = getObject('divTot');

	switch (obj.selectedIndex) {
		case 0:
			hideLayer(di); hideLayer(dt); showLayer(dn);
		break;
		case 1:
			hideLayer(dn); hideLayer(dt); showLayer(di);
		break;
		case 2:
			hideLayer(dn); hideLayer(di); showLayer(dt);
		break;
		default:
			hideLayer(di); hideLayer(dt); showLayer(dn);
		break;
	}
}

function fillOpenerRow(){
		var name = document.forms[0].name.value;
		var type = "Numérica";
		var image = document.forms[0].image.value;
		var questionTxt = document.forms[0].question.value;
		var validationType = document.forms[0].validationType[document.forms[0].validationType.selectedIndex].value;
		var min = document.forms[0].min.value;
		var max = document.forms[0].max.value;
		var total = document.forms[0].total.value;
		var row;
		if (window.opener && !window.opener.closed){
			row = window.opener.getCurrentRow();
			updateNumericQuestionInSession(row, name, image, questionTxt, validationType, min, max, total);
			window.opener.updateRow(name, type);
		}
		window.close();
	}
	
	var currentRow = 0;
	
	function getCurrentRow(){
		return currentRow;
	}

function deleteRow(rowNum){
	if(confirm('Seguro desea borrar la respuesta seleccionada?')){
		removeAnswerFromSession(rowNum);
		document.getElementById("respuestas").deleteRow(rowNum);
		updateTableLinks(rowNum);
	}
}

function updateTableLinks(rowNum){
	var table =	document.getElementById("respuestas");
	for(i=rowNum;i<table.rows.length;i++){	
		var row = table.rows[i];
		var editLink = "<a href='javascript:editRow(" + i + ");'>Editar</a>"; 
		row.cells[1].innerHTML=editLink;
		var deleteLink = "<a href='javascript:deleteRow(" + i + ");'>Borrar</a>";
		row.cells[2].innerHTML=deleteLink;
	}
}

function editRow(rowNum){
	currentRow = rowNum;
	popModal('popUp/AnswerUpdate01.jsp','AnswerUpdate011',150,420,0)
}

function updateRow(name){
	var table =	document.getElementById("respuestas");
	var row = table.rows[currentRow];
	row.cells[0].innerHTML=name;
}

function getCellValue(cellPos){
	var table =	document.getElementById("respuestas");
	var row = table.rows[currentRow];
	return row.cells[cellPos].innerHTML;
}

function addRow(name){

var table = document.getElementById("respuestas");
var lastRow = table.rows.length;
var row = table.insertRow(lastRow);
  
  // right cell
  var cellName = row.insertCell(0);
  cellName.innerHTML = name;
   
  // edit cell
  
  var cellEdit = row.insertCell(1);
  var editLink = "<a href='javascript:editRow(" + lastRow + ");'>Editar</a>"; 
  cellEdit.innerHTML = editLink;
  
  // delete cell
  
	var cellDelete = row.insertCell(2);
	var deleteLink = "<a href='javascript:deleteRow(" + lastRow + ");'>Borrar</a>"; 
	cellDelete.innerHTML = deleteLink ;
}

// AJAX Functions


	function removeAnswerFromSession(value){
  
	  var req = newXMLHttpRequest();
  	  var handlerFunction = getReadyStateHandler(req, ajaxDoNothing());
      req.onreadystatechange = handlerFunction;
  	
      // Third parameter specifies request is asynchronous.
      req.open("POST", "survey.do?method=removeAnswerFromSession", true);

      // Specify that the body of the request contains form data
      req.setRequestHeader("Content-Type", 
                       "application/x-www-form-urlencoded");

      req.send("row=" + value);
      
	}
	
	function updateNumericQuestionInSession(row, name, image, questionTxt, validationType, min, max, total){
  
	  var req = newXMLHttpRequest();
  	  var handlerFunction = getReadyStateHandler(req, ajaxDoNothing());
      req.onreadystatechange = handlerFunction;
  	
      // Third parameter specifies request is asynchronous.
      req.open("POST", "survey.do?method=updateNumericQuestionInSection", true);

      // Specify that the body of the request contains form data
      req.setRequestHeader("Content-Type", 
                       "application/x-www-form-urlencoded");

      req.send("row=" + row + "&name=" + name + "&image=" + image + "&questionTxt=" + questionTxt
      	+ "&validationType=" + validationType + "&min=" + min + "&max=" + max + "&total=" + total);
      
	}
-->
</script>
</head>

<body style="background-color: #FFFFFF;">
<br>
<table width="550" border="1" align="center" cellpadding="2" cellspacing="0" bgcolor="#FFFFFF">
<form method="POST" name="formMain">
<tr>
	<td colspan="3" align="center" bgcolor="#CCCCCC">Main</td>
</tr>
<tr>
	<td colspan="3">&nbsp;</td>
</tr>
<tr>
	<td width="150" align="left">Sección</td>
	<td colspan="2" align="left"><script type="text/javascript" language="JavaScript">document.write(opener.document.forms[0].sectionName.value);</script></td>
</tr>
<tr>
	<td width="150" align="left">Tipo de Pregunta</td>
	<td colspan="2" align="left">Numérica</td>
</tr>
<tr>
	<td width="150" align="left">Nombre</td>
	<td colspan="2" align="left"><input type="text" name="name" size="50" value="${ Question.title }" /></td>
</tr>
<tr>
	<td width="150" align="left">Imagen Asociada</td>
	<td colspan="2" align="left"><input type="text" name="image" value="${ Question.image }" /></td>
</tr>
<tr>
	<td width="150" align="left">Pregunta</td>
	<td colspan="2" align="left"><textarea cols="50" rows="5" name="question">${ Question.description }</textarea></td>
</tr>
<tr>
	<td width="150" align="left">Validación</td>
	<td width="80" align="left">
		<select name="validationType" onChange="selectTipo(this);">
			<option value="none">Ninguna</option>
			<option value="individual">Individual</option>
			<option value="total">Total</option>
		</select>
	</td>
	<script language="JavaScript" type="text/javascript">
	 <c:choose>
        <c:when test='${Question.validationType == "none"}'>    	
            document.forms[0].validationType.selectedIndex = 0;
        </c:when>
        <c:when test='${Question.validationType == "individual"}'>    	
            document.forms[0].validationType.selectedIndex = 1;
        </c:when>
        <c:otherwise>
            document.forms[0].validationType.selectedIndex = 2;
        </c:otherwise>
    </c:choose>
    selectTipo(document.forms[0].validationType);
    </script>
	<td width="320" align="left">
		<div id="divNone">
		<table width="320" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="320" align="left">&nbsp;</td>
		</tr>
		</table>
		</div>
		<div id="divInd" style="display:none;">
		<table width="320" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="75" align="left">Mínimo</td>
			<td width="75" align="left"><input type="text" name="min" size="6" value="${ Question.min }" /></td>
			<td width="20" align="left">&nbsp;</td>
			<td width="75" align="left">Máximo</td>
			<td width="75" align="left"><input type="text" name="max" size="6" value="${ Question.max }" /></td>
		</tr>
		</table>
		</div>
		<div id="divTot" style="display:none;">
		<table width="320" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="75" align="left">Total</td>
			<td width="245" align="left"><input type="text" name="total" size="6" value="${ Question.total }" /></td>
		</tr>
		</table>
		</div>
	</td>
</tr>
<tr>
	<td width="150" align="left">Agregar Respuesta</td>
	<td colspan="2" align="left">
		<input type="button" name="" value=">>" onClick="popModal('popUp/AnswerNew01.jsp','newAnsd123',150,420,0);"/>&nbsp;
	</td>
</tr>
<tr>
	<td colspan="3">&nbsp;</td>
</tr>
<tr>
	<td colspan="3" align="center" bgcolor="#CCCCCC">Respuestas</td>
</tr>
<tr>
	<td colspan="3" align="center">
		<table width="400" border="1" cellpadding="2" cellspacing="0" id="respuestas">
		<tr bgcolor="#CCCCCC">
			<td width="240">Texto</td>
			<td width="60">&nbsp;</td>
			<td width="60">&nbsp;</td>
		</tr>
		<c:forEach items="${ sessionScope.answers }" var="answer" varStatus="status">
		<tr>
			<td>${ answer }</td>
			<td><a href='javascript:editRow(${ status.index+1 });'>Editar</a></td>
			<td><a href='javascript:deleteRow(${ status.index+1 });'>Borrar</a></td>
		</tr>
		</c:forEach>
		</table>
	</td>
</tr>
<tr>
	<td colspan="3" align="right">
		<input type="button" name="" value="Aceptar" onClick="verifyNumeric();"/>&nbsp;
		<input type="button" name="" value="Cancelar" onClick="window.close();"/>&nbsp;
	</td>
</tr>
<tr>
	<td colspan="3">&nbsp;</td>
</tr>
</form>
</table>
<br>
</body>
</html>
