<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<script type="text/javascript" language="JavaScript" src="../js/validations.js"></script>
<script language="JavaScript" type="text/javascript"><!--


function submitForm(hiddenValue){
	document.forms[0].method.value=hiddenValue;
	if(validateSection())
		document.forms[0].submit();
}

function cancelForm(){
	document.forms[0].method.value="editCurrentSurvey";
	document.forms[0].submit();
}

function selectQuestion() {
	var url;
	var windowHeight = 500;
	var scroll = "no";

	switch (document.forms[0].questionType.selectedIndex) {
		case 0: alert('Seleccione un tipo de pregunta'); 
				return false; 
				break;
		case 1: url = 'popUp/QuestionNew04.jsp'; 
				break;
		case 2: url = 'popUp/QuestionNew03.jsp'; 
				windowHeight = 650;
				scroll = "yes";
				break;
		case 3: url = 'popUp/QuestionNew06.jsp'; 
				windowHeight = 600;
				break;
		case 4: url = 'popUp/QuestionNew02.jsp'; 
				windowHeight = 600;
				break;
		case 5: url = 'popUp/QuestionNew05.jsp'; 
				break;
		case 6: url = 'popUp/QuestionNew01.jsp'; 
				windowHeight = 600;
				break;
	}

	popModal(url,"popQuestion",windowHeight,600,scroll);
}

function deleteRow(rowNum){
	if(confirm('Seguro desea borrar la pregunta seleccionada?')){
		removeQuestionInSection(rowNum);
		document.getElementById("preguntas").deleteRow(rowNum);
		updateTableLinks(rowNum);
	}
}

function editRow(rowNum){
	currentRow = rowNum;
	var table =	document.getElementById("preguntas");
	var row = table.rows[rowNum];
	var url = "survey.do?method=getQuestionFromSection&row=" + rowNum;
	var type = row.cells[2].innerHTML;
	var windowHeight = 500;
	var scroll = "no";
	if(type=="Matriz" || type=="Matriz "){ 
		windowHeight = 650;
		scroll = "yes";
	}
	popModal(url,"editRowQuestion",windowHeight,600,scroll);
}

function updateRow(name, type){
	var table =	getElement("preguntas");
	var row = table.rows[currentRow];
	row.cells[1].innerHTML=name;
	row.cells[2].innerHTML=type;
}

function addRow(name, type){

	var table = document.getElementById("preguntas");
	var lastRow = table.rows.length;
	var row = table.insertRow(lastRow);
  
  	var cellOrder = row.insertCell(0);
  	cellOrder.innerHTML = lastRow;
  
  	// right cell
  	var cellName = row.insertCell(1);
  	cellName.innerHTML = name;
  
  	// select cell
  	var cellType = row.insertCell(2);
  	cellType.innerHTML = type;
  
  	// edit cell
  
  	var cellEdit = row.insertCell(3);
  	var editLink = "<a href='javascript:editRow(" + lastRow + ");'>Editar</a>"; 
  	cellEdit.innerHTML = editLink;
  
  	// delete cell
  
	var cellDelete = row.insertCell(4);
	var deleteLink = "<a href='javascript:deleteRow(" + lastRow + ");'>Borrar</a>"; 
	cellDelete.innerHTML = deleteLink ;
	
} 

function updateTableLinks(rowNum){
	var table =	getElement("preguntas");
	for(i=rowNum;i<table.rows.length;i++){	
		var row = table.rows[i];
		row.cells[0].innerHTML = i;
		var editLink = "<a href='javascript:editRow(" + i + ");'>Editar</a>"; 
		row.cells[3].innerHTML=editLink;
		var deleteLink = "<a href='javascript:deleteRow(" + i + ");'>Borrar</a>";
		row.cells[4].innerHTML=deleteLink;
	}
}


function removeQuestionInSection(row){

	var req = newXMLHttpRequest();
    var handlerFunction = getReadyStateHandler(req, ajaxDoNothing());
    req.onreadystatechange = handlerFunction;
  
    var urlAjax = "survey.do?method=removeQuestionInSection&row=" + row ;
    req.open("GET", urlAjax, true);
  
    req.send("");
    
}

function getCurrentRow(){
	return currentRow;
}

--></script>
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
				<html:form action="/admin/survey" onsubmit="return validateSection();">
				<table width="700" border="1" cellpadding="2" cellspacing="0">
				<tr bgcolor="#CCCCCC">
					<td colspan="2">Main</td>
				</tr>
				<tr>
					<td width="150" align="left">Nombre</td>
					<td width="550" align="left"><html:text property="sectionName" size="50" value="${ currentSection.name }" /></td>
				</tr>
				<tr>
					<td width="150" align="left">Quota Script</td>
					<td width="550" align="left"><html:textarea property="quotaScript" cols="50" rows="5" value="${ currentSection.quotaMgmtScript }" /></td>
				</tr>
				<tr>
					<td width="150" align="left">Flow Script</td>
					<td width="550" align="left"><html:textarea property="flowScript" cols="50" rows="5" value="${ currentSection.flowMgmtScript }" /></td>
				</tr>
				<tr>
					<td width="150" align="left">Agregar Pregunta</td>
					<td width="550" align="left">
						<html:select property="questionType">
							<html:option value="">Seleccione un Tipo</html:option>
							<html:option value="textBox">Abierta</html:option>
							<html:option value="matrix">Matriz (Radio Buttons)</html:option>
							<html:option value="checkbox">Múltiple (Checkboxes)</html:option>
							<html:option value="textbox">Numérica</html:option>
							<html:option value="noAnswer">Sin Respuesta</html:option>
							<html:option value="radio">Unica</html:option>
						</html:select>&nbsp;
						<input type="button" name="" value=">>" onClick="selectQuestion();"/>&nbsp;
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
						<table width="550" border="1" cellpadding="2" cellspacing="0" id="preguntas">
						<tr bgcolor="#CCCCCC">
							<td width="20">Orden</td>
							<td width="240">Nombre</td>
							<td width="150">Tipo de Pregunta</td>
							<td width="60">&nbsp;</td>
							<td width="60">&nbsp;</td>
						</tr>
						<c:forEach items="${ currentSection.questions }" var="question" varStatus="sstatus">
						<tr>
							<td>${ sstatus.index+1 }</td>
							<td>${ question.description }</td>
							<td>
							<c:choose>
					    	    <c:when test="${question.class.name == 'ar.com.survey.questions.single.StringQuestion' }">
								Abierta
					        	</c:when>
					        	<c:when test="${question.class.name == 'ar.com.survey.questions.single.TextAreaQuestion' }">
								Abierta
					        	</c:when>
					        	<c:when test="${question.class.name == 'ar.com.survey.questions.EmptyQuestion' }">
								Sin respuesta
					        	</c:when>
					        	<c:when test="${question.class.name == 'ar.com.survey.questions.list.NumberListQuestion' }">
								Numérica
					        	</c:when>
					        	<c:when test="${question.class.name == 'ar.com.survey.questions.list.StringListQuestion' }">
								Unica
					        	</c:when>
					        	<c:when test="${question.class.name == 'ar.com.survey.questions.list.CheckBoxListQuestion' }">
								Múltiple
					        	</c:when>
					        	<c:otherwise>
				            	Matriz
					        	</c:otherwise>
						    </c:choose>
							</td>
							<td><a href='javascript:editRow(${ sstatus.index+1 });'>Editar</a></td>
							<td><a href='javascript:deleteRow(${ sstatus.index+1 });'>Borrar</a></td>
						</tr>
						</c:forEach>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="button" value="Actualizar" onclick="submitForm('updateSection');" />&nbsp;
						<input type="button" name="" value="Cancelar" onClick="cancelForm();"/>&nbsp;
					</td>
				</tr>
				</table>
				<html:hidden property="method" />
				<html:hidden property="row" value="${ row }" />
				</html:form>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		</table>