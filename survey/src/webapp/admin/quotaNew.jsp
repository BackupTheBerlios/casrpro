<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Nueva Cuota</title>
<link href="../css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="JavaScript" src="../js/validations.js"></script>
<script language="JavaScript" type="text/javascript">
<!--

	function fillOpenerRow(){
		if(verifyQuota()){
			var name = document.forms[0].name.value;
			var count = document.forms[0].count.value;
			if (window.opener && !window.opener.closed){
				window.opener.addRow(name, count);
				window.opener.addQuotaToSession(name, count, self);
			}
			window.close();
		}
	}

-->
</script>
</head>

<body style="background-color: #FFFFFF;">
<br>
<table width="400" border="1" align="center" cellpadding="2" cellspacing="0" bgcolor="#FFFFFF">
<form method="POST" name="formMain">
<tr bgcolor="#CCCCCC">
	<td colspan="2" align="center">Nueva Cuota</td>
</tr>
<tr>
	<td colspan="2">&nbsp;</td>
</tr>
<tr>
	<td width="150" align="left">Nombre</td>
	<td width="250" align="left"><input type="text" name="name" size="50" /></td>
</tr>
<tr>
	<td width="150" align="left">Cantidad</td>
	<td width="250" align="left"><input type="text" name="count" /></td>
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
