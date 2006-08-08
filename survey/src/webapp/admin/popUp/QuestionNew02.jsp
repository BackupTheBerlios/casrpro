<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Nueva Pregunta</title>
<link href="../../css/css.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../js/common.js"></script>
<script language="JavaScript" type="text/javascript">
<!--
function fillOpenerRow(){
		var name = document.forms[0].name.value;
		var type = "Numérica";
		if (window.opener && !window.opener.closed){
			window.opener.addRow(name, type);
		}
		window.close();
	}
	
	var currentRow = 0;

function deleteRow(rowNum){
	if(confirm('Seguro desea borrar la respuesta seleccionada?')){
		document.getElementById("respuestas").deleteRow(rowNum);
		removeQuotaInSessionSurvey(rowNum);
		updateTableLinks(rowNum);
	}
}

function updateTableLinks(rowNum){
	var table =	document.getElementById("respuestas");
	for(i=rowNum;i<table.rows.length;i++){	
		var row = table.rows[i];
		var editLink = "<a href='javascript:editRow(" + i + ");'>Editar</a>"; 
		row.cells[2].innerHTML=editLink;
		var deleteLink = "<a href='javascript:deleteRow(" + i + ");'>Borrar</a>";
		row.cells[3].innerHTML=deleteLink;
	}
}

function editRow(rowNum){
	currentRow = rowNum;
	popModal("answerUpdate01.jsp");
}

function updateRow(name, value){
	var table =	document.getElementById("preguntas");
	var row = table.rows[currentRow];
	row.cells[0].innerHTML=name;
	row.cells[1].innerHTML=value;
}

function getCellValue(cellPos){
	var table =	document.getElementById("preguntas");
	var row = table.rows[currentRow];
	return row.cells[cellPos].innerHTML;
}

function addRow(name, value){

var table = document.getElementById("preguntas");
var lastRow = table.rows.length;
var row = table.insertRow(lastRow);
  
  // right cell
  var cellName = row.insertCell(0);
  cellName.innerHTML = name;
  
  // select cell
  var cellValue = row.insertCell(1);
  cellValue.innerHTML = value;
  
  // edit cell
  
  var cellEdit = row.insertCell(2);
  var editLink = "<a href='javascript:editRow(" + lastRow + ");'>Editar</a>"; 
  cellEdit.innerHTML = editLink;
  
  // delete cell
  
	var cellDelete = row.insertCell(3);
	var deleteLink = "<a href='javascript:deleteRow(" + lastRow + ");'>Borrar</a>"; 
	cellDelete.innerHTML = deleteLink ;
	
}
	
-->
</script>
</head>

<body style="background-color: #FFFFFF;">
<br>
<table width="550" border="1" align="center" cellpadding="2" cellspacing="0" bgcolor="#FFFFFF" id="respuestas">
<form method="POST" name="formMain">
<tr>
	<td colspan="5" align="center" bgcolor="#CCCCCC">Main</td>
</tr>
<tr>
	<td colspan="5">&nbsp;</td>
</tr>
<tr>
	<td width="150" align="left">Sección</td>
	<td colspan="4" align="left">Sección 3</td>
</tr>
<tr>
	<td width="150" align="left">Tipo de Pregunta</td>
	<td colspan="4" align="left">Numérica</td>
</tr>
<tr>
	<td width="150" align="left">Nombre</td>
	<td colspan="4" align="left"><input type="text" name="name" size="50" /></td>
</tr>
<tr>
	<td width="150" align="left">Imagen Asociada</td>
	<td colspan="4" align="left"><input type="text" name="image" /></td>
</tr>
<tr>
	<td width="150" align="left">Pregunta</td>
	<td colspan="4" align="left"><textarea cols="50" rows="5"></textarea></td>
</tr>
<tr>
	<td width="150" align="left">Validación</td>
	<td colspan="4" align="left">
		<select name="">
			<option value="">Ninguna</option>
			<option value="">Individual</option>
			<option value="">Total</option>
		</select>
	</td>
</tr>
<tr>
	<td width="150" align="left">Mínimo</td>
	<td width="100" align="left"><input type="text" name="" size="6" /></td>
	<td width="50" align="left">&nbsp;</td>
	<td width="100" align="left">Máximo</td>
	<td width="150" align="left"><input type="text" name="" size="6" /></td>
</tr>
<tr>
	<td width="150" align="left">Agregar Respuesta</td>
	<td colspan="4" align="left">
		<input type="button" name="" value=">>" onClick="popModal('AnswerNew02.jsp',250,420,0);"/>&nbsp;
	</td>
</tr>
<tr>
	<td colspan="5">&nbsp;</td>
</tr>
<tr>
	<td colspan="5" align="center" bgcolor="#CCCCCC">Respuestas</td>
</tr>
<tr>
	<td colspan="5" align="center">
		<table width="400" border="1" cellpadding="2" cellspacing="0">
		<tr bgcolor="#CCCCCC">
			<td width="240">Texto</td>
			<td width="60">&nbsp;</td>
			<td width="60">&nbsp;</td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<td colspan="5" align="right">
		<input type="button" name="" value="Aceptar" onClick="fillOpenerRow();"/>&nbsp;
		<input type="button" name="" value="Cancelar" onClick="window.close();"/>&nbsp;
	</td>
</tr>
<tr>
	<td colspan="5">&nbsp;</td>
</tr>
</form>
</table>
<br>
</body>
</html>
