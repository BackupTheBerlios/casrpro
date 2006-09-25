<%@ taglib prefix="tiles" uri="/WEB-INF/tld/struts-tiles.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Panel de Opiniones</title>
<script language="JavaScript" type="text/javascript">
<!--

-->
</script>
</head>

<body>
<table width="780" border="1" align="center" cellpadding="2" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td width="780" height="60" align="center"><tiles:insert attribute='header' /></td>
  </tr>
  <tr>
    <td width="780" height="20" align="center"><tiles:insert attribute='menu' /></td>
  </tr>
  <tr>
    <td width="780" height="420" align="center" valign="top">
		<table width="100%" border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><tiles:insert attribute='body' /></td>
		</tr>
		</table>
	</td>
  </tr>
  <tr>
    <td width="780" height="20" align="center"><tiles:insert attribute='footer'/></td>
  </tr>
</table>
</body>
</html>
