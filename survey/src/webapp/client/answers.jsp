<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<table width="700" border="2" cellpadding="2" cellspacing="2">
 <tr>
  <td width="700" valign="top" class="surveyspace">
<html:form action="/client/fill">
<table border="0" cellspacing="2" cellpadding="2" width="100%">
 <c:forEach items="${ sessionScope.CurrentSection.questions }" var="question" varStatus="status">
  <tr>
	<td colspan="2"><hr size="3" width="675" color="#4B4E51" noshade></td>
  </tr>
  <tr>
   <td class="question" colspan="2">${ question.description }</td>
  </tr>
  <c:choose>
   <c:when test="${question.class.name == 'ar.com.survey.questions.single.StringQuestion' }">
  <tr>
   <td class="answer" colspan="2">
   <html:text property="txtAnswer" size="25" styleClass="field" />       
   </td>
  </tr>
   </c:when>
   <c:when test="${question.class.name == 'ar.com.survey.questions.single.TextAreaQuestion' }">
  <tr>
   <td class="answer" colspan="2">
   <html:textarea property="txtAnswer" cols="50" rows="6" styleClass="field"></html:textarea>
   </td>
  </tr>
   </c:when>
   <c:when test="${question.class.name == 'ar.com.survey.questions.EmptyQuestion' }">
  <tr>
   <td class="answer" colspan="2">        
   </td>
  </tr>
   </c:when>
   <c:when test="${question.class.name == 'ar.com.survey.questions.list.NumberListQuestion' }">
   	  <c:choose>
  		<c:when test="${ question.validationType=='individual' }">
  			<input type="hidden" name="number${status.index+1}validationType" value="individual">
  			<input type="hidden" name="number${status.index+1}min" value="${ question.min }">
  			<input type="hidden" name="number${status.index+1}max" value="${ question.max }">
  		</c:when>
  		<c:when test="${ question.validationType=='total' }">
  			<input type="hidden" name="number${status.index+1}validationType" value="total">
  			<input type="hidden" name="number${status.index+1}max" value="${ question.total }">
  		</c:when>
  		<c:otherwise>
  			<input type="hidden" name="number${status.index+1}validationType" value="none">
  		</c:otherwise>
  		</c:choose>
	   <c:forEach items="${ question.items }" var="item">
  <tr>
   <td class="answer" colspan="2">${ item } &nbsp; <html:text property="number${status.index+1}" size="10" styleClass="field" />
   </td>
  </tr>
      </c:forEach>
   </c:when>
   <c:when test="${question.class.name == 'ar.com.survey.questions.list.StringListQuestion' }">
	   <c:forEach items="${ question.items }" var="item">
  <tr>
   <td class="answer" width="25"><html:radio property="unique${status.index+1}" value="${ item }" /></td>
   <td class="answer" width="675">${ item }</td>
  </tr>
      </c:forEach>
   </c:when>
   <c:when test="${question.class.name == 'ar.com.survey.questions.list.CheckBoxListQuestion' }">
	   <c:forEach items="${ question.items }" var="item">
  <tr>
   <td class="answer" width="25"><html:checkbox property="check${ status.index +1 }" value="${ item }" /></td>
   <td class="answer" width="675">${ item }</td>
  </tr>
      </c:forEach>
   </c:when>
    <c:when test="${question.class.name == 'ar.com.survey.questions.matrix.CheckBoxMatrixQuestion' }">
    <tr>
     <td colspan="2" class="answer">
      <% int maxSize = 0; %>
      <table border="0" cellspacing="1" cellpadding="1">
       <tr><td class="answer">&nbsp;</td>
	   <c:forEach items="${ question.columnsTitles }" var="title">
   		<td class="answer">${ title }
   		</td>
   		<% maxSize++; %>
      </c:forEach>
        </tr>
        <c:set var="indexName" value="1" />
        <c:set var="subIndexName" value="1" />
       <c:forEach items="${ question.items }" var="item">
        <tr>
        <td class="answer">${ item }</td>
        <% for(int i=0;i<maxSize;i++){ %>
       	<td class="answer"><html:checkbox property="matrix${ status.index + 1 }(value${ indexName }${ subIndexName })" /></td>
       	<c:set var="subIndexName" value="${ subIndexName + 1 }" />
       	<% } %>
       	</tr>
       	<c:set var="indexName" value="${ indexName + 1 }" />
       </c:forEach>
      </table>
      </td>
     </tr>
   </c:when>
   <c:otherwise></c:otherwise>
  </c:choose>
  <tr>
	<td colspan="2"><hr size="3" width="675" color="#4B4E51" noshade></td>
  </tr>
 </c:forEach>
  <tr><td>&nbsp;</td></tr>
 <tr><td align="center" colspan="2"><input type="button" class="button" value="${ flowDTO.description }" onclick="${ flowDTO.action }"></td>
 </tr>
</table>
<html:hidden property="method" value="fillNext"/>
<html:hidden property="nextPos" value="${ flowDTO.section }"/>
</html:form>
</td>
</tr>
</table>
