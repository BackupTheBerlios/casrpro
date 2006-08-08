<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Nueva Respuesta</title>
<link href="css/css.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../js/common.js"></script>
<script language="JavaScript" type="text/javascript">
<!--
	
	function fillOpenerRow(){
		var name = document.forms[0].name.value;
		var value = document.forms[0].value.value;
		if (window.opener && !window.opener.closed){
			window.opener.addRow(name, value);
		}
		window.close();
	}
-->
</script>
</head>

<body style="background-color: #FFFFFF;">
<br>
<table width="400" border="1" align="center" cellpadding="2" cellspacing="0" bgcolor="#FFFFFF">
<form method="POST" name="formMain">
<tr bgcolor="#CCCCCC">
	<td colspan="2" align="center">Main</td>
</tr>
<tr>
	<td colspan="2">&nbsp;</td>
</tr>
<tr>
	<td width="150" align="left">Texto</td>
	<td width="250" align="left"><input type="text" name="name" size="50" /></td>
</tr>
<tr>
	<td width="150" align="left">Valor</td>
	<td width="250" align="left"><input type="text" name="value" /></td>
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
</form>
</table>
</body>
</html>
