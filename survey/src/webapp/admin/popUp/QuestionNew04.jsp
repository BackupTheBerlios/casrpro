<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Nueva Pregunta</title>
<link href="../../css/css.css" rel="stylesheet" type="text/css">

<script language="JavaScript" type="text/javascript">
<!--
function fillOpenerRow(){
		var name = document.forms[0].name.value;
		var type = "Abierta";
		if (window.opener && !window.opener.closed){
			window.opener.addRow(name, type);
		}
		window.close();
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
	<td width="400" align="left">Sección 3</td>
</tr>
<tr>
	<td width="150" align="left">Tipo de Pregunta</td>
	<td width="400" align="left">Abierta</td>
</tr>
<tr>
	<td width="150" align="left">Nombre</td>
	<td width="400" align="left"><input type="text" name="name" size="50" /></td>
</tr>
<tr>
	<td width="150" align="left">Imagen Asociada</td>
	<td width="400" align="left"><input type="text" name="image" /></td>
</tr>
<tr>
	<td width="150" align="left">Pregunta</td>
	<td width="400" align="left"><textarea cols="50" rows="5"></textarea></td>
</tr>
<tr>
	<td width="150" align="left">Tipo de Respuesta</td>
	<td width="400" align="left">
		<select name="">
			<option value="">Seleccione un Tipo de Respuesta</option>
			<option value="">Textarea</option>
			<option value="">Textbox</option>
		</select>
	</td>
</tr>
<tr>
	<td colspan="2">&nbsp;</td>
</tr>
<tr>
	<td colspan="2" align="right">
		<input type="button" name="" value="Aceptar" onClick="fillOpenerRow();"/>&nbsp;
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
