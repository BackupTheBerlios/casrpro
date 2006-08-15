<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Nueva Columna</title>
<link href="../../css/css.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../js/common.js"></script>
<script language="JavaScript" src="../../js/validations.js"></script>
<script language="JavaScript" type="text/javascript">
<!--
	
	function fillOpenerCol(){
		var name = document.forms[0].name.value;
		if (window.opener && !window.opener.closed){
			window.opener.updateCol(name);
		}
		window.close();
	}
	
	function getOpenerValue(){
		if (window.opener && !window.opener.closed){
			document.forms[0].name.value = window.opener.getColCellValue(0);
		}
	}
-->
</script>
</head>

<body style="background-color: #FFFFFF;" onload="getOpenerValue();">
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
	<td colspan="2">&nbsp;</td>
</tr>
<tr>
	<td colspan="2" align="right">
		<input type="button" name="" value="Aceptar" onClick="verifyTextCol();"/>&nbsp;
		<input type="button" name="" value="Cancelar" onClick="window.close();"/>&nbsp;
	</td>
</tr>
</form>
</table>
</body>
</html>
