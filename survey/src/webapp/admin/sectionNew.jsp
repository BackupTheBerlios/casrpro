<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<script language="JavaScript" type="text/javascript">
<!--
function popModal(url) {
	window.showModalDialog(url,window,'dialogHeight:550px;dialogWidth:620px;center:1;edge:raised;help:0;resizable:0;scroll:1;status:0');
}

function submitForm(hiddenValue){
	document.forms[0].method.value=hiddenValue;
	document.forms[0].submit();
}

-->
</script>
		<table width="100%" border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>Nueva Sección - Cuestionario ${ currentSurvey.name }</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
				<html:form action="/admin/survey">
				<table width="700" border="1" cellpadding="2" cellspacing="0">
				<tr bgcolor="#CCCCCC">
					<td colspan="2">Main</td>
				</tr>
				<tr>
					<td width="150" align="left">Nombre</td>
					<td width="550" align="left"><html:text property="sectionName" size="50" /></td>
				</tr>
				<tr>
					<td width="150" align="left">Quota Script</td>
					<td width="550" align="left"><html:textarea property="quotaScript" cols="50" rows="5" /></td>
				</tr>
				<tr>
					<td width="150" align="left">Flow Script</td>
					<td width="550" align="left"><html:textarea property="flowScript" cols="50" rows="5" /></td>
				</tr>
				<tr>
					<td width="150" align="left">Agregar Pregunta</td>
					<td width="550" align="left">
						<html:select property="questionType">
							<html:option value="">Seleccione un Tipo</html:option>
							<html:option value="textBox">Abierta (Textbox)</html:option>
							<html:option value="textArea">Abierta (Textarea)</html:option>
							<html:option value="matrix">Matriz (Radio Buttons)</html:option>
							<html:option value="checkbox">Múltiple (Checkboxes)</html:option>
							<html:option value="textbox">Numérica (Textbox)</html:option>
							<html:option value="textbox">Porcentajes (Textbox)</html:option>
							<html:option value="textbox">Rangos (Textbox)</html:option>
							<html:option value="jump">Salto</html:option>
							<html:option value="noAnswer">Sin Respuesta</html:option>
							<html:option value="radio">Unica (Radio Buttons)</html:option>
						</html:select>&nbsp;
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
							<td align="left"><a href="#" onClick="popModal('QuestionUpdate.html');">Introduccion</a></td>
							<td align="left">Sin Respuesta</td>
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
						<input type="button" value="Siguiente Sección" onclick="submitForm('addSection');" />&nbsp;
						<input type="button" value="Finalizar" onClick="submitForm('persistSurvey');"/>&nbsp;
						<input type="button" name="" value="Cancelar" onClick="location.href='index.html';"/>&nbsp;
					</td>
				</tr>
				</table>
				<html:hidden property="method" />
				</html:form>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		</table>