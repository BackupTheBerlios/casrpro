<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="ar.com.survey.model.enums.SurveyState" %>
<%@ page import="ar.com.survey.model.enums.RestrictionType" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<script type="text/javascript" language="JavaScript" src="../js/validations.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="../css/calendar/calendar-win2k-1.css" title="win2k-1" />
<script type="text/javascript" src="../js/calendar/calendar.js"></script>
<script type="text/javascript" src="../js/calendar/lang/calendar-es.js"></script>
<script type="text/javascript" src="../js/calendar/calendar-setup.js"></script>
<script language="JavaScript" type="text/javascript">
<!--

 var currentRow = 0;

function addNewCuota(){
 popModal("quotaNew.jsp","newQuota",200,500,"no");
}

function deleteRow(rowNum){
	if(confirm('Seguro desea borrar la cuota seleccionada?')){
		getElement("cuotas").deleteRow(rowNum);
		removeQuotaInSessionSurvey(rowNum);
		updateTableLinks(rowNum);
	}
}

function updateTableLinks(rowNum){
	var table =	getElement("cuotas");
	for(i=rowNum;i<table.rows.length;i++){	
		var row = table.rows[i];
		var editLink = "<a href='javascript:editRow(" + i + ");'>Editar</a>"; 
		row.cells[2].innerHTML=editLink;
		var deleteLink = "<a href='javascript:deleteRow(" + i + ");'>Borrar</a>";
		row.cells[3].innerHTML=deleteLink;
	}
}

function editRow(rowNum){
	currentRow = rowNum;
	popModal("quotaUpdate.jsp","quotaUpdater",200,500,'no');
}

function updateRow(name, count){
	var table =	getElement("cuotas");
	var row = table.rows[currentRow];
	row.cells[0].innerHTML=name;
	row.cells[1].innerHTML=count;
}

function getCellValue(cellPos){
	var table =	getElement("cuotas");
	var row = table.rows[currentRow];
	return row.cells[cellPos].innerHTML;
}

function addRow(name, count)
{
var table = getElement("cuotas");
var lastRow = table.rows.length;
var row = table.insertRow(lastRow);
  
  // right cell
  var cellName = row.insertCell(0);
  cellName.innerHTML = name;
  
  // select cell
  var cellCount = row.insertCell(1);
  cellCount.innerHTML = count;
  
  // edit cell
  
  var cellEdit = row.insertCell(2);
  var editLink = "<a href='javascript:editRow(" + lastRow + ");'>Editar</a>"; 
  cellEdit.innerHTML = editLink;
  
  // delete cell
  
	var cellDelete = row.insertCell(3);
	var deleteLink = "<a href='javascript:deleteRow(" + lastRow + ");'>Borrar</a>"; 
	cellDelete.innerHTML = deleteLink ;
	
}

// Sections javascripts

function deleteSection(rowNum){
	if(confirm('Seguro desea borrar la cuota seleccionada?')){
		getElement("secciones").deleteRow(rowNum);
		removeSectionInSessionSurvey(rowNum);
		updateSectionTableLinks(rowNum);
	}
}

function updateSectionTableLinks(rowNum){
	var table =	getElement("secciones");
	for(i=rowNum;i<table.rows.length;i++){	
		var row = table.rows[i];
		row.cells[0].innerHTML=i;
		var editLink = "<a href='javascript:editSection(" + i + ");'>Editar</a>"; 
		row.cells[2].innerHTML=editLink;
		var deleteLink = "<a href='javascript:deleteSection(" + i + ");'>Borrar</a>";
		row.cells[3].innerHTML=deleteLink;
	}
}

function addQuotaToSession(name, value, popUp){

  var req = newXMLHttpRequest();
  var handlerFunction = getReadyStateHandler(req, ajaxDoNothing);
  req.onreadystatechange = handlerFunction;
  
  var urlAjax = "survey.do?method=addQuotaToSessionSurvey&name=" + name + "&value=" + value ;
  req.open("GET", urlAjax, false);
  
  req.send(null);
  
}

function updateQuotaInSession(name, value){
  
  var req = newXMLHttpRequest();
  var handlerFunction = getReadyStateHandler(req, ajaxDoNothing);
  req.onreadystatechange = handlerFunction;
  
  var urlAjax = "survey.do?method=updateQuotaInSessionSurvey&name=" 
  	+ name + "&value=" + value + "&row=" + currentRow ;
  req.open("GET", urlAjax, false);
  
  req.send(null);

}

function removeQuotaInSessionSurvey(row){

	var req = newXMLHttpRequest();
    var handlerFunction = getReadyStateHandler(req, ajaxDoNothing);
    req.onreadystatechange = handlerFunction;
  
    var urlAjax = "survey.do?method=removeQuotaInSessionSurvey&row=" + row ;
    req.open("GET", urlAjax, false);
  
    req.send(null);
    
}

function removeSectionInSessionSurvey(row){

	var req = newXMLHttpRequest();
    var handlerFunction = getReadyStateHandler(req, ajaxDoNothing);
    req.onreadystatechange = handlerFunction;
  
    var urlAjax = "survey.do?method=removeSectionInSessionSurvey&row=" + row ;
    req.open("GET", urlAjax, false);
  
    req.send(null);
    
}

function addNewSection(){
	document.forms[0].method.value="sections";
	document.forms[0].submit();
}

function updateSurvey(){
	document.forms[0].method.value="updateSurvey";
	document.forms[0].submit();
}

function editSection(rowNum){
	document.forms[0].method.value="editSection";
	document.forms[0].row.value=rowNum;
	document.forms[0].submit();
}

function submitForm(hiddenValue){
	document.forms[0].method.value=hiddenValue;
	document.forms[0].submit();
}

-->
</script>

<c:choose>
<c:when test="${ sessionScope.surveyOp == 'new' }">
<table width="780" border="0" align="center" cellpadding="2" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td width="780" height="420" align="center" valign="top">
		<table width="100%" border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>Nuevo Cuestionario</td>
		</tr>
		<tr>
			<td>&nbsp;<logic:messagesPresent>
			    <html:messages id="missingValues" /> 
			    <bean:write name="missingValues" />
			     </logic:messagesPresent></td>
		</tr>
		<tr>
			<td>
				<html:form action="/admin/survey" onsubmit="return verifySurvey();">
				<table width="700" border="1" cellpadding="2" cellspacing="0">
				<tr bgcolor="#CCCCCC">
					<td colspan="7">Main</td>
				</tr>
				<tr>
					<td width="150" align="left">Nombre</td>
					<td colspan="6" align="left"><html:text property="name" size="50" /></td>
				</tr>
				<tr>
					<td width="150" align="left">Fecha de Apertura</td>
					<td width="160" align="left"><html:text styleId="s_date" property="startDate" maxlength="10" value="<%= new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()) %>" /></td>
					<td width="25"><a href="#"><img src="../img/calendar_icon.gif" width="22" id="si_date" height="21" border="0" alt="" /></a></td>
					<td width="30" align="left">&nbsp;</td>
					<td width="150" align="left">Fecha de Cierre</td>
					<td width="160" align="left"><html:text styleId="e_date" property="endDate" maxlength="10" /></td>
					<td width="25"><a href="#"><img src="../img/calendar_icon.gif" id="ei_date" width="22" height="21" border="0" alt="" /></a></td>
				</tr>
				<script type="text/javascript">
    				Calendar.setup({
				        inputField     :    "s_date",     // id of the input field
  			            ifFormat       :    "%d/%m/%Y",       // format of the input field
				        button         :    "si_date",  // trigger for the calendar (button ID)
        			    showsTime      :    false,
				        singleClick    :    true
				    });
				</script>
				<script type="text/javascript">
    				Calendar.setup({
				        inputField     :    "e_date",     // id of the input field
  			            ifFormat       :    "%d/%m/%Y",       // format of the input field
				        button         :    "ei_date",  // trigger for the calendar (button ID)
        			    showsTime      :    false,
				        singleClick    :    true
				    });
				</script>
				<tr>
					<td width="150" align="left">Uso de token</td>
					<td colspan="6" align="left">
					<html:select property="restrictionType">
					 <html:option value="<%= RestrictionType.OPEN.getCode() %>"><%= RestrictionType.OPEN.getDescription() %>
					 </html:option>
					 <html:option value="<%= RestrictionType.RESTRICTED.getCode() %>"><%= RestrictionType.RESTRICTED.getDescription() %>
					 </html:option>
					</html:select>
					</td>
				</tr>
				<tr>
					<td width="150" align="left">Descripción</td>
					<td colspan="6" align="left"><html:text property="description" size="50" maxlength="255" /></td>
				</tr>
				
				<tr>
					<td width="150" align="left">Agregar Cuota</td>
					<td colspan="6" align="left">
						<input type="button" name="" value=">>" onClick="addNewCuota();"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="7">&nbsp;</td>
				</tr>
				<tr bgcolor="#CCCCCC">
					<td colspan="7">Cuotas</td>
				</tr>
				<tr>
					<td colspan="7">
						<table width="460" border="1" cellpadding="2" cellspacing="0" id="cuotas">
						<tr bgcolor="#CCCCCC">
							<td width="260">Nombre</td>
							<td width="80">Cantidad</td>
							<td width="60">&nbsp;</td>
							<td width="60">&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="7">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="7" align="right">
						<html:submit value="Agregar Secciones" />&nbsp;
						<input type="button" value="Finalizar" onClick="submitForm('persistSurveyOnly');"/>&nbsp;
						<input type="button" value="Cancelar" onClick="location.href='index.html';"/>&nbsp;
					</td>
				</tr>
				</table>
				<html:hidden property="method" value="sections"/>
				</html:form>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		</table>
		</c:when>
        <c:otherwise>
        <c:set var="survey" value="${ currentSurvey }" />
        <jsp:useBean id="survey" class="ar.com.survey.model.Survey" />
        <table width="780" border="0" align="center" cellpadding="2" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td width="780" height="420" align="center" valign="top">
		<table width="100%" border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>Editar Cuestionario</td>
		</tr>
		<tr>
			<td>&nbsp;<logic:messagesPresent>
			    <html:messages id="missingValues" /> 
			    <bean:write name="missingValues" />
			     </logic:messagesPresent></td>
		</tr>
		<tr>
			<td>
				<html:form action="/admin/survey" onsubmit="return verifySurvey();">
				<table width="700" border="1" cellpadding="2" cellspacing="0">
				<tr bgcolor="#CCCCCC">
					<td colspan="7">Main</td>
				</tr>
				<tr>
					<td width="150" align="left">Nombre</td>
					<td colspan="6" align="left"><html:text property="name" size="50" value="${ currentSurvey.name }" /></td>
				</tr>
				<tr>
					<td width="150" align="left">Estado</td>
					<td colspan="6" align="left">
					<html:select property="state">
					 <html:option value="">Seleccione un estado
					 </html:option>
					 <html:option value="<%= SurveyState.OPEN.getCode() %>"><%= SurveyState.OPEN.getDescription() %>
					 </html:option>
					 <html:option value="<%= SurveyState.CLOSED.getCode() %>"><%= SurveyState.CLOSED.getDescription() %>
					 </html:option>
					 <html:option value="<%= SurveyState.DESIGN.getCode() %>"><%= SurveyState.DESIGN.getDescription() %>
					 </html:option>
					</html:select>
					<script type="text/javascript" language="JavaScript">
					<% if(SurveyState.valueOf(survey.getStatus()).getDescription().equals(SurveyState.OPEN.getDescription())){ %>
						document.forms[0].state.selectedIndex=1;
					<% } else if(SurveyState.valueOf(survey.getStatus()).getDescription().equals(SurveyState.CLOSED.getDescription())){ %>
						document.forms[0].state.selectedIndex=2;
					<% } else {%>
						document.forms[0].state.selectedIndex=3;
					<% } %>
					</script>
					</td>
				</tr>
				<tr>
					<td width="150" align="left">Uso de token</td>
					<td colspan="6" align="left">
					<html:select property="restrictionType">
					 <html:option value="<%= RestrictionType.OPEN.getCode() %>"><%= RestrictionType.OPEN.getDescription() %>
					 </html:option>
					 <html:option value="<%= RestrictionType.RESTRICTED.getCode() %>"><%= RestrictionType.RESTRICTED.getDescription() %>
					 </html:option>
					</html:select>
					<script type="text/javascript" language="JavaScript">
					<% if(RestrictionType.valueOf(survey.getRestrictionType()).getDescription().equals(RestrictionType.OPEN.getDescription())){ %>
						document.forms[0].restrictionType.selectedIndex=0;
					<% } else { %>
						document.forms[0].restrictionType.selectedIndex=1;
					<% } %>
					</script>
					</td>
				</tr>
				<tr>
					<td width="150" align="left">Descripción</td>
					<td colspan="6" align="left"><html:text property="description" size="50" maxlength="255" value="${ currentSurvey.description }" /></td>
				</tr>
				<tr>
					<td width="150" align="left">Fecha de Apertura</td>
					<td width="160" align="left"><html:text styleId="s_date" property="startDate" maxlength="10" value="<%= new SimpleDateFormat("dd/MM/yyyy").format(survey.getStartDate().getTime()) %>" /></td>
					<td width="25"><a href="#"><img id="si_date" src="../img/calendar_icon.gif" width="22" height="21" border="0" alt="" /></a></td>
					<td width="30" align="left">&nbsp;</td>
					<td width="150" align="left">Fecha de Cierre</td>
					<td width="160" align="left"><html:text styleId="e_date" property="endDate" maxlength="10" value="<%= new SimpleDateFormat("dd/MM/yyyy").format(survey.getFinishDate().getTime()) %>" /></td>
					<td width="25"><a href="#"><img id="ei_date" src="../img/calendar_icon.gif" width="22" height="21" border="0" alt="" /></a></td>
				</tr>
				<script type="text/javascript">
    				Calendar.setup({
				        inputField     :    "s_date",     // id of the input field
  			            ifFormat       :    "%d/%m/%Y",       // format of the input field
				        button         :    "si_date",  // trigger for the calendar (button ID)
        			    showsTime      :    false,
				        singleClick    :    true
				    });
				</script>
				<script type="text/javascript">
    				Calendar.setup({
				        inputField     :    "e_date",     // id of the input field
  			            ifFormat       :    "%d/%m/%Y",       // format of the input field
				        button         :    "ei_date",  // trigger for the calendar (button ID)
        			    showsTime      :    false,
				        singleClick    :    true
				    });
				</script>
				<tr>
					<td width="150" align="left">Agregar Cuota</td>
					<td colspan="6" align="left">
						<input type="button" name="" value=">>" onClick="addNewCuota();"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="7">&nbsp;</td>
				</tr>
				<tr bgcolor="#CCCCCC">
					<td colspan="7">Cuotas</td>
				</tr>
				<tr>
					<td colspan="7">
						<table width="460" border="1" cellpadding="2" cellspacing="0" id="cuotas">
						<tr bgcolor="#CCCCCC">
							<td width="260">Nombre</td>
							<td width="80">Cantidad</td>
							<td width="60">&nbsp;</td>
							<td width="60">&nbsp;</td>
						</tr>
						<c:forEach items="${ currentSurvey.quotas }" var="quota" varStatus="status">
						<tr>
							<td>${ quota.name }</td>
							<td>${ quota.limit }</td>
							<td><a href='javascript:editRow(${ status.index+1 });'>Editar</a></td>
							<td><a href='javascript:deleteRow(${ status.index+1 });'>Borrar</a></td>
						</tr>
						</c:forEach>
						</table>
					</td>
				</tr>
				<tr>
					<td width="150" align="left">Agregar Sección</td>
					<td colspan="6" align="left">
						<input type="button" name="" value=">>" onclick="addNewSection();"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="7">&nbsp;</td>
				</tr>
				<tr bgcolor="#CCCCCC">
					<td colspan="7">Secciones</td>
				</tr>
				<tr>
					<td colspan="7">
						<table width="460" border="1" cellpadding="2" cellspacing="0" id="secciones">
						<tr bgcolor="#CCCCCC">
							<td width="80">Orden</td>
							<td width="260">Nombre</td>
							<td width="60">&nbsp;</td>
							<td width="60">&nbsp;</td>
						</tr>
						<c:forEach items="${ currentSurvey.sections }" var="section" varStatus="sstatus">
						<tr>
							<td>${ sstatus.index+1 }</td>
							<td>${ section.name }</td>
							<td><a href='javascript:editSection(${ sstatus.index+1 });'>Editar</a></td>
							<td><a href='javascript:deleteSection(${ sstatus.index+1 });'>Borrar</a></td>
						</tr>
						</c:forEach>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="7">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="7" align="right">
						<input type="button" value="Finalizar" onClick="updateSurvey();"/>&nbsp;
						<input type="button" value="Cancelar" onclick="location.href='index.html';"/>&nbsp;
					</td>
				</tr>
				</table>
				<html:hidden property="method" />
				<html:hidden property="row" />
				</html:form>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		</table>
        </c:otherwise>
</c:choose>
