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
function fillOpenerRow(){
		var name = document.forms[0].name.value;
		var type = "Matriz";
		var image = document.forms[0].image.value;
		var questionTxt = document.forms[0].question.value;
		if (window.opener && !window.opener.closed){
			var row = window.opener.getCurrentRow();
			updateMatrixQuestionInSession(name, image, questionTxt, row);
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
	popModal('popUp/AnswerUpdate01.jsp','updaterAnsw1234',150,420,0)
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

// COLS

var currentCol = 0;

function deleteCol(rowNum){
	if(confirm('Seguro desea borrar la columna seleccionada?')){
		removeColumnFromSession(rowNum);
		document.getElementById("columnas").deleteRow(rowNum);
		updateColTableLinks(rowNum);
	}
}

function updateColTableLinks(rowNum){
	var table =	document.getElementById("columnas");
	for(i=rowNum;i<table.rows.length;i++){	
		var row = table.rows[i];
		var editLink = "<a href='javascript:editCol(" + i + ");'>Editar</a>"; 
		row.cells[1].innerHTML=editLink;
		var deleteLink = "<a href='javascript:deleteCol(" + i + ");'>Borrar</a>";
		row.cells[2].innerHTML=deleteLink;
	}
}

function editCol(rowNum){
	currentCol = rowNum;
	popModal('popUp/ColumnUpdate01.jsp','ColUpdate01',150,420,0)
}

function updateCol(name){
	var table =	document.getElementById("columnas");
	var row = table.rows[currentCol];
	row.cells[0].innerHTML=name;
}

function getColCellValue(cellPos){
	var table =	document.getElementById("columnas");
	var row = table.rows[currentCol];
	return row.cells[cellPos].innerHTML;
}

function addCol(name){

var table = document.getElementById("columnas");
var lastCol = table.rows.length;
var row = table.insertRow(lastCol);
  
  // right cell
  var cellName = row.insertCell(0);
  cellName.innerHTML = name;
   
  // edit cell
  
  var cellEdit = row.insertCell(1);
  var editLink = "<a href='javascript:editCol(" + lastCol + ");'>Editar</a>"; 
  cellEdit.innerHTML = editLink;
  
  // delete cell
  
	var cellDelete = row.insertCell(2);
	var deleteLink = "<a href='javascript:deleteCol(" + lastCol + ");'>Borrar</a>"; 
	cellDelete.innerHTML = deleteLink ;
	
}

function removeAnswerFromSession(value){
  
	  var req = newXMLHttpRequest();
  	  var handlerFunction = getReadyStateHandler(req, ajaxDoNothing());
      req.onreadystatechange = handlerFunction;
  	
      // Third parameter specifies request is asynchronous.
      req.open("POST", "survey.do", false);

      // Specify that the body of the request contains form data
      req.setRequestHeader("Content-Type", 
                       "application/x-www-form-urlencoded");

      req.send("method=removeAnswerFromSession&row=" + value);
      
	}
	
	function removeColumnFromSession(value){
  
	  var req = newXMLHttpRequest();
  	  var handlerFunction = getReadyStateHandler(req, ajaxDoNothing());
      req.onreadystatechange = handlerFunction;
  	
      // Third parameter specifies request is asynchronous.
      req.open("POST", "survey.do", false);

      // Specify that the body of the request contains form data
      req.setRequestHeader("Content-Type", 
                       "application/x-www-form-urlencoded");

      req.send("method=removeColumnFromSession&row=" + value);
      
	}
	
	function updateMatrixQuestionInSession(name, image, questionTxt, row){
  
	  var req = newXMLHttpRequest();
  	  var handlerFunction = getReadyStateHandler(req, ajaxDoNothing());
      req.onreadystatechange = handlerFunction;
  	
      // Third parameter specifies request is asynchronous.
      req.open("POST", "survey.do", false);

      // Specify that the body of the request contains form data
      req.setRequestHeader("Content-Type", 
                       "application/x-www-form-urlencoded");
                       
      name = replaceHtmlCodes(name);
      image = replaceHtmlCodes(image);
      questionTxt = replaceHtmlCodes(questionTxt);
      // row = replaceHtmlCodes(row);

      req.send("method=updateMatrixQuestionInSection&name=" + name + "&image=" + image + "&questionTxt=" + questionTxt + "&row=" + row);
      
	}

-->
</script>
</head>

<body style="background-color: #FFFFFF;">
<br>
<table width="550" border="1" align="center" cellpadding="2" cellspacing="0" bgcolor="#FFFFFF">
<form method="POST" name="formMain">
<tr>
	<td colspan="2" align="center" bgcolor="#CCCCCC">Main</td>
</tr>
<tr>
	<td colspan="2">&nbsp;</td>
</tr>
<tr>
	<td width="150" align="left">Sección</td>
	<td width="400" align="left"><script type="text/javascript" language="JavaScript">document.write(opener.document.forms[0].sectionName.value);</script></td>
</tr>
<tr>
	<td width="150" align="left">Tipo de Pregunta</td>
	<td width="400" align="left">Matriz</td>
</tr>
<tr>
	<td width="150" align="left">Nombre</td>
	<td width="400" align="left"><input type="text" name="name" size="50" value="${ Question.title }" /></td>
</tr>
<tr>
	<td width="150" align="left">Imagen Asociada</td>
	<td width="400" align="left"><input type="text" name="image" value="${ Question.image }" /></td>
</tr>
<tr>
	<td width="150" align="left">Pregunta</td>
	<td width="400" align="left"><textarea cols="50" rows="5" name="question">${ Question.description }</textarea></td>
</tr>
<tr>
	<td width="150" align="left">Agregar Columna</td>
	<td width="400" align="left">
		<input type="button" name="" value=">>" onClick="popModal('popUp/ColumnNew01.jsp','col1',150,420,0);"/>&nbsp;
	</td>
</tr>
<tr>
	<td colspan="2">&nbsp;</td>
</tr>
<tr>
	<td colspan="2" align="center" bgcolor="#CCCCCC">Columnas</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<table width="400" border="1" cellpadding="2" cellspacing="0" id="columnas">
		<tr bgcolor="#CCCCCC">
			<td width="240">Texto</td>
			<td width="60">&nbsp;</td>
			<td width="60">&nbsp;</td>
		</tr>
		<c:forEach items="${ sessionScope.columns }" var="column" varStatus="cstatus">
		<tr>
			<td>${ column }</td>
			<td><a href='javascript:editCol(${ cstatus.index+1 });'>Editar</a></td>
			<td><a href='javascript:deleteCol(${ cstatus.index+1 });'>Borrar</a></td>
		</tr>
		</c:forEach>
		</table>
	</td>
</tr>
<tr>
	<td colspan="2">&nbsp;</td>
</tr>
<tr>
	<td width="150" align="left">Agregar Respuesta</td>
	<td width="400" align="left">
		<input type="button" name="" value=">>" onClick="popModal('popUp/AnswerNew01.jsp','answer1123',150,420,0);"/>&nbsp;
	</td>
</tr>
<tr>
	<td colspan="2">&nbsp;</td>
</tr>
<tr>
	<td colspan="2" align="center" bgcolor="#CCCCCC">Respuestas</td>
</tr>
<tr>
	<td colspan="2" align="center">
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
	<td colspan="2" align="right">
		<input type="button" name="" value="Aceptar" onClick="verifyMatrix();"/>&nbsp;
		<input type="button" name="" value="Cancelar" onClick="window.close();"/>&nbsp;
	</td>
</tr>
<tr>
	<td colspan="2">&nbsp;</td>
</tr>
</form>
</table>
<br>
</body>
</html>
