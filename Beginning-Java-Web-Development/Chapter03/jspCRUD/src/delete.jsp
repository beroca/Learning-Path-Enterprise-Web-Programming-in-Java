<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<%@ page errorPage = "error.jsp" %>
<!DOCTYPE html>					  
<html>
<head>
<title>Delete a product</title>
<link rel = "stylesheet" href = "style.css" type = "text/css"></link>
</head>
<body>
   <fieldset><legend class = "legend">Delete user</legend>
       <div style = "font-weight:bold;font-size:101%;">
         <c:out value="Are you sure you want to delete user with Id ${param.id}?"/>
       </div>
       <p><a href="${pageContext.request.contextPath}/skiStuffCRUD.jsp">Cancel</a>&nbsp;&nbsp;|&nbsp;&nbsp;
          <a href="${pageContext.request.contextPath}/deleteForSure.jsp?id=${param.id}">Delete the product</a></p>
   </fieldset>
</body>
</html>