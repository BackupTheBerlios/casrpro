<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<script language="JavaScript" type="text/javascript">
<!--

 var currentRow = 0;

function addNewCuota(){
 popModal('quotaNew.jsp','newQuota');
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
	popModal("quotaUpdate.jsp", "quotaUpdater");
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

function addQuotaToSession(name, value){

  var req = newXMLHttpRequest();
  var handlerFunction = getReadyStateHandler(req, ajaxDoNothing());
  req.onreadystatechange = handlerFunction;
  
  var urlAjax = "survey.do?method=addQuotaToSessionSurvey&name=" + name + "&value=" + value ;
  req.open("GET", urlAjax, true);
  
  req.send("");

} 

function updateQuotaInSession(name, value){
  
  var req = newXMLHttpRequest();
  var handlerFunction = getReadyStateHandler(req, ajaxDoNothing());
  req.onreadystatechange = handlerFunction;
  
  var urlAjax = "survey.do?method=updateQuotaInSessionSurvey&name=" 
  	+ name + "&value=" + value + "&row=" + currentRow ;
  req.open("GET", urlAjax, true);
  
  req.send("");

}

function removeQuotaInSessionSurvey(row){

	var req = newXMLHttpRequest();
    var handlerFunction = getReadyStateHandler(req, ajaxDoNothing());
    req.onreadystatechange = handlerFunction;
  
    var urlAjax = "survey.do?method=removeQuotaInSessionSurvey&row=" + row ;
    req.open("GET", urlAjax, true);
  
    req.send("");
    
}


-->
</script>
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
				<html:form action="/admin/survey">
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
					<td width="160" align="left"><html:text property="startDate" maxlength="10" /></td>
					<td width="25"><a href="#"><img src="img/calendar_icon.gif" width="22" height="21" border="0" alt="" /></a></td>
					<td width="30" align="left">&nbsp;</td>
					<td width="150" align="left">Fecha de Cierre</td>
					<td width="160" align="left"><html:text property="endDate" maxlength="10" /></td>
					<td width="25"><a href="#"><img src="img/calendar_icon.gif" width="22" height="21" border="0" alt="" /></a></td>
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