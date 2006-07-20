<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Panel de Opiniones</title>
<link href="css/css.css" rel="stylesheet" type="text/css">

<script language="JavaScript" type="text/javascript">
<!--
function popModal(url) {
	window.showModalDialog(url,window,'dialogHeight:550px;dialogWidth:620px;center:1;edge:raised;help:0;resizable:0;scroll:1;status:0');
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
			<td>Editar Sección - Cuestionario ID: 00001 (Cuestionario X - Cervezas)</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
				<form method="POST" name="formMain">
				<table width="700" border="1" cellpadding="2" cellspacing="0">
				<tr bgcolor="#CCCCCC">
					<td colspan="2">Main</td>
				</tr>
				<tr>
					<td width="150" align="left">Nombre</td>
					<td width="550" align="left"><input type="text" name="" value="Sección 3" size="50" /></td>
				</tr>
				<tr>
					<td width="150" align="left">Quota Script</td>
					<td width="550" align="left"><textarea cols="50" rows="5"></textarea></td>
				</tr>
				<tr>
					<td width="150" align="left">Flow Script</td>
					<td width="550" align="left"><textarea cols="50" rows="5"></textarea></td>
				</tr>
				<tr>
					<td width="150" align="left">Agregar Pregunta</td>
					<td width="550" align="left">
						<select name="">
							<option value="">Seleccione un Tipo</option>
							<option value="">Abierta (Textbox)</option>
							<option value="">Abierta (Textarea)</option>
							<option value="">Matriz (Radio Buttons)</option>
							<option value="">Múltiple (Checkboxes)</option>
							<option value="">Numérica (Textbox)</option>
							<option value="">Porcentajes (Textbox)</option>
							<option value="">Rangos (Textbox)</option>
							<option value="">Salto</option>
							<option value="">Sin Respuesta</option>
							<option value="">Unica (Radio Buttons)</option>
						</select>&nbsp;
						<input type="button" name="" value=">>" onClick="popModal('QuestionNew.html');"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr bgcolor="#CCCCCC">
					<td colspan="2">Preguntas</td>
				</tr>
				<tr>
					<td colspan="2">
						<table width="550" border="1" cellpadding="2" cellspacing="0">
						<tr bgcolor="#CCCCCC">
							<td width="20">Orden</td>
							<td width="240">Nombre</td>
							<td width="150">Tipo de Pregunta</td>
							<td width="60">&nbsp;</td>
							<td width="60">&nbsp;</td>
						</tr>
						<tr>
							<td>1</td>
							<td align="left"><a href="#" onClick="popModal('QuestionUpdate.html');">Pregunta 1</a></td>
							<td align="left">Respuesta Unica</td>
							<td><a href="#" onClick="popModal('QuestionUpdate.html');">Editar</a></td>
							<td><a href="#" onClick="confirm('Confirma la baja de la pregunta?');">Borrar</a></td>
						</tr>
						<tr>
							<td>2</td>
							<td align="left"><a href="#" onClick="popModal('QuestionUpdate.html');">Pregunta 2</a></td>
							<td align="left">Respuesta Múltiple</td>
							<td><a href="#" onClick="popModal('QuestionUpdate.html');">Editar</a></td>
							<td><a href="#" onClick="confirm('Confirma la baja de la pregunta?');">Borrar</a></td>
						</tr>
						<tr>
							<td>3</td>
							<td align="left"><a href="#" onClick="popModal('QuestionUpdate.html');">Pregunta 3</a></td>
							<td align="left">Respuesta Unica</td>
							<td><a href="#" onClick="popModal('QuestionUpdate.html');">Editar</a></td>
							<td><a href="#" onClick="confirm('Confirma la baja de la pregunta?');">Borrar</a></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="button" name="" value="Actualizar" onClick="location.href='SurveyUpdate.html';"/>&nbsp;
						<input type="button" name="" value="Cancelar" onClick="location.href='SurveyUpdate.html';"/>&nbsp;
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
