<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Nueva Columna</title>
<link href="../../css/css.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../js/common.js"></script>
<script language="JavaScript" src="../../js/validations.js"></script>
<script language="JavaScript" src="../../js/simpleAjax.js"></script>
<script language="JavaScript" type="text/javascript">
<!--
	
	function fillOpenerCol(){
		var name = document.forms[0].name.value;
		if (window.opener && !window.opener.closed){
			addColumnToSession(name);
			window.opener.addCol(name);
		}
		window.close();
	}
	
	function addColumnToSession(value){
  
	  var req = newXMLHttpRequest();
  	  var handlerFunction = getReadyStateHandler(req, ajaxDoNothing);
      req.onreadystatechange = handlerFunction;
  	
      // Third parameter specifies request is asynchronous.
      req.open("POST", "../survey.do?method=addColumnToSession", false);

      // Specify that the body of the request contains form data
      req.setRequestHeader("Content-Type", 
                       "application/x-www-form-urlencoded");

      value = replaceHtmlCodes(value);

      req.send("column=" + value);
      
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
