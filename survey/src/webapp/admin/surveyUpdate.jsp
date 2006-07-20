<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Panel de Opiniones</title>
<link href="css/css.css" rel="stylesheet" type="text/css">

<script language="JavaScript" type="text/javascript">
<!--
function popModal(url) {
	window.showModalDialog(url,window,'dialogHeight:250px;dialogWidth:420px;center:1;edge:raised;help:0;resizable:0;scroll:0;status:0');
}
-->
</script>
</head>

<body>
<table width="780" border="1" align="center" cellpadding="2" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td width="780" height="60" align="center">HEADER</td>
  </tr>
  <tr>
    <td width="780" height="20" align="center"><a href="Index.html">Home</a> | <a href="SurveyNew.html">Nuevo Cuestionario</a> | <a href="SurveyList.html">Listar Cuestionarios</a> | <a href="#">Logout</a></td>
  </tr>
  <tr>
    <td width="780" height="420" align="center" valign="top">
		<table width="100%" border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>Editar Cuestionario - ID: 000001</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
				<form method="POST" name="formMain">
				<input type="hidden" name="" value="000001" />
				<table width="700" border="1" cellpadding="2" cellspacing="0">
				<tr bgcolor="#CCCCCC">
					<td colspan="7">Main</td>
				</tr>
				<tr>
					<td width="150" align="left">Nombre</td>
					<td colspan="6" align="left"><input type="text" name="" size="50" value="Cuestionario X - Cervezas" /></td>
				</tr>
				<tr>
					<td width="150" align="left">Estado</td>
					<td colspan="6" align="left">
						<select name="">
							<option value="">Seleccione un Estado</option>
							<option value="" selected="selected">Abierto</option>
							<option value="">Cerrado</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="150" align="left">Fecha de Apertura</td>
					<td width="160" align="left"><input type="text" name="" value="01/05/2006" /></td>
					<td width="25"><a href="#"><img src="img/calendar_icon.gif" width="22" height="21" border="0" alt="" /></a></td>
					<td width="30" align="left">&nbsp;</td>
					<td width="150" align="left">Fecha de Cierre</td>
					<td width="160" align="left"><input type="text" name="" value="01/08/2006" /></td>
					<td width="25"><a href="#"><img src="img/calendar_icon.gif" width="22" height="21" border="0" alt="" /></a></td>
				</tr>
				<tr>
					<td width="150" align="left">Agregar Cuota</td>
					<td colspan="6" align="left">
						<input type="button" name="" value=">>" onClick="popModal('QuotaNew.html');"/>&nbsp;
					</td>
				</tr>
				<tr bgcolor="#CCCCCC">
					<td colspan="7">Cuotas</td>
				</tr>
				<tr>
					<td colspan="7">
						<table width="460" border="1" cellpadding="2" cellspacing="0">
						<tr bgcolor="#CCCCCC">
							<td width="260">Nombre</td>
							<td width="80">Cantidad</td>
							<td width="60">&nbsp;</td>
							<td width="60">&nbsp;</td>
						</tr>
						<tr>
							<td align="left"><a href="#" onClick="popModal('QuotaUpdate.html');">Hombres +30</a></td>
							<td>150</td>
							<td><a href="#" onClick="popModal('QuotaUpdate.html');">Editar</a></td>
							<td><a href="#" onClick="confirm('Confirma la baja de la cuota?');">Borrar</a></td>
						</tr>
						<tr>
							<td align="left"><a href="#" onClick="popModal('QuotaUpdate.html');">Mujeres +30</a></td>
							<td>150</td>
							<td><a href="#" onClick="popModal('QuotaUpdate.html');">Editar</a></td>
							<td><a href="#" onClick="confirm('Confirma la baja de la cuota?');">Borrar</a></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="7">&nbsp;</td>
				</tr>
				<tr>
					<td width="150" align="left">Agregar Sección</td>
					<td colspan="6" align="left">
						<input type="button" name="" value=">>" onClick="document.location.href='SectionNew.html';"/>&nbsp;
					</td>
				</tr>
				<tr bgcolor="#CCCCCC">
					<td colspan="7">Secciones</td>
				</tr>
				<tr>
					<td colspan="7">
						<table width="460" border="1" cellpadding="2" cellspacing="0">
						<tr bgcolor="#CCCCCC">
							<td width="20">Orden</td>
							<td width="240">Nombre</td>
							<td width="60">&nbsp;</td>
							<td width="60">&nbsp;</td>
						</tr>
						<tr>
							<td>1</td>
							<td align="left"><a href="SectionUpdate.html">Introduccion</a></td>
							<td><a href="SectionUpdate.html">Editar</a></td>
							<td><a href="#" onClick="confirm('Confirma la baja de la pregunta?');">Borrar</a></td>
						</tr>
						<tr>
							<td>2</td>
							<td align="left"><a href="SectionUpdate.html">Sección 1 - Edad</a></td>
							<td><a href="SectionUpdate.html">Editar</a></td>
							<td><a href="#" onClick="confirm('Confirma la baja de la pregunta?');">Borrar</a></td>
						</tr>
						<tr>
							<td>3</td>
							<td align="left"><a href="SectionUpdate.html">Sección 2 - Bebidas</a></td>
							<td><a href="SectionUpdate.html">Editar</a></td>
							<td><a href="#" onClick="confirm('Confirma la baja de la pregunta?');">Borrar</a></td>
						</tr>
						<tr>
							<td>4</td>
							<td align="left"><a href="SectionUpdate.html">Salto 1 - If Cerveza</a></td>
							<td><a href="SectionUpdate.html">Editar</a></td>
							<td><a href="#" onClick="confirm('Confirma la baja de la pregunta?');">Borrar</a></td>
						</tr>
						<tr>
							<td>5</td>
							<td align="left"><a href="SectionUpdate.html">Sección 3 - Cerveza</a></td>
							<td><a href="SectionUpdate.html">Editar</a></td>
							<td><a href="#" onClick="confirm('Confirma la baja de la pregunta?');">Borrar</a></td>
						</tr>
						<tr>
							<td>6</td>
							<td align="left"><a href="SectionUpdate.html">Cierre</a></td>
							<td><a href="SectionUpdate.html">Editar</a></td>
							<td><a href="#" onClick="confirm('Confirma la baja de la pregunta?');">Borrar</a></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="7">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="7" align="right">
						<input type="button" name="" value="Actualizar" onClick="location.href='SurveyList.html';"/>&nbsp;
						<input type="button" name="" value="Cancelar" onClick="location.href='Index.html';"/>&nbsp;
					</td>
				</tr>
				</table>
				</form>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		</table>
	</td>
  </tr>
  <tr>
    <td width="780" height="20" align="center">FOOTER</td>
  </tr>
</table>
</body>
</html>
