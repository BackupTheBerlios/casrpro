<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="ar.com.survey.model.enums.SurveyState" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Panel de Opiniones asd</title>
<style type="text/css">
<!--
body,td {
	color: #666666;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}
body {
	background-color: #CCCCCC;
	/*background-image: url(grass11.jpg);*/
}
a {
	font-family: Arial, Helvetica, sans-serif;
	color: #666666;
}
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
}
a:hover {
	text-decoration: underline;
}
a:active {
	text-decoration: none;
}
-->
</style>

<script language="JavaScript" type="text/javascript">
<!--

function deleteSurvey(surveyName){
	var ret = confirm('Confirma la baja del cuestionario?');
	if(ret){
		document.forms[1].name.value=surveyName;
		document.forms[1].method.value="removePersistedSurvey";
		document.forms[1].submit();
	}
	else return false;
}

function editSurvey(surveyName){
	document.forms[1].name.value=surveyName;
	document.forms[1].method.value="editPersistedSurvey";
	document.forms[1].submit();
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
    <td width="780" height="20" align="center"><a href="index.html">Home</a> | <a href="survey.do">Nuevo Cuestionario</a> | <a href="search.do">Listar Cuestionarios</a> | <a href="#">Logout</a></td>
  </tr>

  <tr>
    <td width="780" height="420" align="center" valign="top">
    <html:form action="/admin/search">
	<html:hidden property="method" value="" />
		<table width="100%" border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>Listado de Cuestionarios</td>

		</tr>
		<tr>
			<td>&nbsp;<logic:messagesPresent>
			    <html:messages id="searchFilters" /> 
			    <bean:write name="searchFilters" />
			     </logic:messagesPresent>
			</td>
		</tr>
		<tr>
			<td>
				<table width="350" border="1" cellpadding="2" cellspacing="0">
				<tr>
					<td width="150" align="left">Nombre</td>
					<td colspan="2" align="left"><html:text property="name" size="10" maxlength="40" /></td>
				</tr>
				<tr>
					<td width="150" align="left">Fecha de Creación</td>

					<td width="175" align="left"><html:text property="creationDate" size="10" maxlength="11" /></td>
					<td width="25"><a href="#"><img src="../img/calendar_icon.gif" width="22" height="21" border="0" alt="" /></a></td>
				</tr>
				<tr>
					<td width="100" align="left">Status</td>
					<td colspan="2" align="left">
						<html:select property="status">
					 <html:option value="">Seleccione un estado
					 </html:option>
					 <html:option value="<%= SurveyState.OPEN.getCode() %>"><%= SurveyState.OPEN.getDescription() %>
					 </html:option>
					 <html:option value="<%= SurveyState.CLOSED.getCode() %>"><%= SurveyState.CLOSED.getDescription() %>
					 </html:option>
					 <html:option value="<%= SurveyState.DESIGN.getCode() %>"><%= SurveyState.DESIGN.getDescription() %>
					 </html:option>
					</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="right"><html:submit value="Buscar" onclick="document.forms[0].method.value='search';" /></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		</table>
	</html:form>
	<c:if test="${ requestScope.surveys != null }">
	<html:form action="/admin/survey">
		<table width="100%" border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td>
				<table width="520" border="1" cellpadding="2" cellspacing="0">
				<tr bgcolor="#CCCCCC">
					<td width="200">Nombre</td>
					<td width="80">Fecha de<br>Creacion</td>
					<td width="60">Status</td>
					<td width="60">&nbsp;</td>
					<td width="60">&nbsp;</td>
				</tr>
				<c:forEach items="${ requestScope.surveys }" var="survey">
				<jsp:useBean id="survey" class="ar.com.survey.model.Survey" />
				<tr>
					<td align="left"><a href="#">${ survey.name }</a></td>
					<td><%= new SimpleDateFormat("dd/MM/yyyy").format(survey.getCreationDate().getTime()) %></td>
					<td><%= SurveyState.valueOf(survey.getStatus()).getDescription() %></td>
					<td><a href="#" onclick="editSurvey('<%= survey.getName() %>');">Editar</a></td>
					<td><a href="#" onclick="deleteSurvey('<%= survey.getName() %>');">Borrar</a></td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="6" align="right"><a href="survey.do">Nuevo Cuestionario</a></td>
				</tr>
				</table>

			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		</table>
		<html:hidden property="name" />
		<html:hidden property="method" /> 
	</html:form>
	</c:if>
	</td>
  </tr>
  <tr>
    <td width="780" height="20" align="center">FOOTER</td>
  </tr>
</table>
</body>
</html>
