<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ page import="ar.com.survey.model.enums.FilledSurveyStatus" %>
<table width="780" border="0" align="center" cellpadding="2" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td width="780" height="420" align="center" valign="top">
    <html:form action="/admin/report">
	<html:hidden property="method" value="countFSReport" />
		<table width="70%" border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td colspan=2>&nbsp;</td>
		</tr>
		<tr>
			<td colspan=2>Listar cantidad de encuestas completadas</td>
		</tr>
		<tr>
		 <td colspan="2">&nbsp;
		 </td>
		</tr>
		<tr>
			<td>Búsqueda por nombre del survey a consultar:
			</td>
			<td align="left"><html:text property="surveyName"></html:text>
			</td>
		</tr>
		<tr>
			<td>Filtrar por fecha de inicio:
			</td>
			<td align="left"><html:text property="startDate"></html:text>
			</td>
		</tr>
		<tr>
			<td>Filtrar por fecha de fin:
			</td>
			<td align="left"><html:text property="endDate"></html:text>
			</td>
		</tr>
		<tr>
			<td>Filtrar por estado:
			</td>
			<td align="left">
			<html:select property="fstatus">
			 <html:option value="all">Todos</html:option>
 			 <html:option value="<%= FilledSurveyStatus.COMPLETADO.getCode() %>"><%= FilledSurveyStatus.COMPLETADO.getDescription() %></html:option>
 			 <html:option value="<%= FilledSurveyStatus.INCOMPLETO.getCode() %>"><%= FilledSurveyStatus.INCOMPLETO.getDescription() %></html:option>
			</html:select>
			</td>
		</tr>
		  <tr>
			<td></td>
			<td><html:submit value="Generar Reporte" />
			</td>
		</tr>
		</table>
		</html:form>
	</td>
   </tr>
</table>