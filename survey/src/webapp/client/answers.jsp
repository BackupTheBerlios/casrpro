<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>

<script type="text/javascript" language="JavaScript" src="../js/ClientValidations.js"></script>

<html:form action="/client/fill">
<table border="0" cellspacing="2" cellpadding="2" width="700">
 <c:forEach items="${ sessionScope.CurrentSection.questions }" var="question" varStatus="status">
  <!-- <tr>
   <td>${ question.title }</td>
  </tr> -->
  <tr>
   <td>${ question.description }</td>
  </tr>
  <c:choose>
   <c:when test="${question.class.name == 'ar.com.survey.questions.single.StringQuestion' }">
  <tr>
   <td>
   <html:text property="txtAnswer" size="25"  />       
   </td>
  </tr>
   </c:when>
   <c:when test="${question.class.name == 'ar.com.survey.questions.single.TextAreaQuestion' }">
  <tr>
   <td>
   <html:textarea property="txtAnswer" cols="30" rows="12"></html:textarea>
   </td>
  </tr>
   </c:when>
   <c:when test="${question.class.name == 'ar.com.survey.questions.EmptyQuestion' }">
  <tr>
   <td>        
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
   <td>${ item } &nbsp; <html:text property="number${status.index+1}" />
   </td>
  </tr>
  		
      </c:forEach>
   </c:when>
   <c:when test="${question.class.name == 'ar.com.survey.questions.list.StringListQuestion' }">
	   <c:forEach items="${ question.items }" var="item">
  <tr>
   <td><html:radio property="unique${status.index+1}" value="${ item }">${ item }</html:radio>
   </td>
  </tr>
      </c:forEach>
   </c:when>
   <c:when test="${question.class.name == 'ar.com.survey.questions.list.CheckBoxListQuestion' }">
	   <c:forEach items="${ question.items }" var="item">
  <tr>
   <td><html:checkbox property="check${ status.index +1 }" value="${ item }">${ item }</html:checkbox>
   </td>
  </tr>
      </c:forEach>
   </c:when>
    
    <c:when test="${question.class.name == 'ar.com.survey.questions.matrix.CheckBoxMatrixQuestion' }">
      <% int maxSize = 0; %>
      <table border="0" cellspacing="1" cellpadding="1">
       <tr><td>&nbsp;</td>
	   <c:forEach items="${ question.columnsTitles }" var="title">
   		<td>${ title }
   		</td>
   		<% maxSize++; %>
      </c:forEach>
        </tr>
        <c:set var="indexName" value="1" />
        <c:set var="subIndexName" value="1" />
       <c:forEach items="${ question.items }" var="item">
        <tr>
        <td>${ item }</td>
        <% for(int i=0;i<maxSize;i++){ %>
       	<td><html:checkbox property="matrix${ status.index + 1 }(value${ indexName }${ subIndexName })" /></td>
       	<c:set var="subIndexName" value="${ subIndexName + 1 }" />
       	<% } %>
       	</tr>
       	<c:set var="indexName" value="${ indexName + 1 }" />
       </c:forEach>
      </table>
   </c:when>
   <c:otherwise></c:otherwise>
  </c:choose>
 </c:forEach>
  <tr><td>&nbsp;</td></tr>
 <tr><td align="right"><input type="button" value="${ flowDTO.description }" onclick="${ flowDTO.action }"></td>
 </tr>
</table>
<html:hidden property="method" value="fillNext"/>
<html:hidden property="nextPos" value="${ flowDTO.section }"/>
</html:form>
