<%@ taglib prefix="tiles" uri="/WEB-INF/tld/struts-tiles.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Panel de Opiniones</title>
<link href="../css/Style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="JavaScript" src="../js/ClientValidations.js"></script>
<script language="JavaScript" type="text/javascript">
<!--
javascript:window.history.forward(1);
-->
</script>
</head>

<body onload="cleanForm();">
<table width="700" border="0" cellpadding="2" cellspacing="0">
<tr>
    <td width="700" height="65" class="logoHeader"></td>

</tr>
<tr>
    <td width="700">
		<tiles:insert attribute='body' />
	</td>
</tr>
<tr>
    <td width="700" height="20" class="copyright">Copyright &copy; 2006 Great Insights SRL. All rights reserved. Great Insights &#174; is a registered trademark</td>
</tr>
</table>
</body>
</html>
