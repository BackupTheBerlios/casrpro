<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Editar Pregunta</title>
<link href="../css/css.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../js/common.js"></script>
<script language="JavaScript" src="../js/validations.js"></script>
<script language="JavaScript" src="../js/simpleAjax.js"></script>
<script language="JavaScript" type="text/javascript">
<!--

function fillOpenerRow(){
		var name = document.forms[0].name.value;
		var type = "Abierta";
		var image = document.forms[0].image.value;
		var quesTxt = document.forms[0].question.value;
		
		var questType;
		if(document.forms[0].answerType.selectedIndex==1)
			questType = "textArea";
		else
			questType = "textBox";
		if (window.opener && !window.opener.closed){
			var row = window.opener.getCurrentRow();
			updateOpenQuestionInSection(name, image, quesTxt, questType, row);
			window.opener.updateRow(name, type);
		}
		window.close();
	}

/* updates an open question in the section in session */	
function updateOpenQuestionInSection(name, image, quesTxt, txtType, row){
  
  var req = newXMLHttpRequest();
  var handlerFunction = getReadyStateHandler(req, ajaxDoNothing);
  req.onreadystatechange = handlerFunction;
  	
  // Third parameter specifies request is asynchronous.
  req.open("POST", "survey.do?method=updateOpenQuestionInSection", false);

  // Specify that the body of the request contains form data
  req.setRequestHeader("Content-Type", 
                       "application/x-www-form-urlencoded");

  name = replaceHtmlCodes(name);
  image = replaceHtmlCodes(image);
  quesTxt = replaceHtmlCodes(quesTxt);
  txtType = replaceHtmlCodes(txtType);
  row = replaceHtmlCodes(row);

   req.send("name=" + name + "&image=" + image +
   	 "&quesTxt=" + quesTxt + "&txtType=" + txtType + "&row=" + row);	
}
	
-->
</script>
</head>

<body style="background-color: #FFFFFF;">
<br>
<table width="550" border="1" align="center" cellpadding="2" cellspacing="0" bgcolor="#FFFFFF">
<form method="post" name="formMain">
<tr>
	<td colspan="2" align="center" bgcolor="#CCCCCC">Main</td>
</tr>
<tr>
	<td colspan="2">&nbsp;</td>
</tr>
<tr>
	<td width="150" align="left">Sección</td>
	<td width="400" align="left"><script type="text/javascript" language="JavaScript">document.write(opener.document.forms[0].sectionName.value);</script></td>
</tr>
<tr>
	<td width="150" align="left">Tipo de Pregunta</td>
	<td width="400" align="left">Abierta</td>
</tr>
<tr>
	<td width="150" align="left">Nombre</td>
	<td width="400" align="left"><input type="text" name="name" size="50" value="${ Question.title }"/></td>
</tr>
<tr>
	<td width="150" align="left">Imagen Asociada</td>
	<td width="400" align="left"><input type="text" name="image" value="${ Question.image }" /></td>
</tr>
<tr>
	<td width="150" align="left">Pregunta</td>
	<td width="400" align="left"><textarea cols="50" rows="5" name="question">${ Question.description }</textarea></td>
</tr>
<tr>
	<td width="150" align="left">Tipo de Respuesta</td>
	<td width="400" align="left">
		<select name="answerType">
			<option value="">Seleccione un Tipo de Respuesta</option>
			<option value="textArea">Textarea</option>
			<option value="textBox">Textbox</option>
		</select>
		<script language="JavaScript" type="text/javascript">
		<c:choose>
        <c:when test="${Question.class.name == 'ar.com.survey.questions.single.StringQuestion' }">
            document.forms[0].answerType.selectedIndex=2;
        </c:when>
        <c:otherwise>
            document.forms[0].answerType.selectedIndex=1;
        </c:otherwise>
    </c:choose>
        </script>
	</td>
</tr>
<tr>
	<td colspan="2">&nbsp;</td>
</tr>
<tr>
	<td colspan="2" align="right">
		<input type="button" name="" value="Aceptar" onClick="verifySimple();"/>&nbsp;
		<input type="button" name="" value="Cancelar" onClick="window.close();"/>&nbsp;
	</td>
</tr>
<tr>
	<td colspan="2">&nbsp;</td>
</tr>
</form>
</table>
<br>
</body>
</html>
