<%@ page errorPage = "error.jsp" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>	
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %>   
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %>

<!DOCTYPE html>					  
<html>
  <head>
    <title>Users</title>
    <link rel = "stylesheet" href = "style.css" type = "text/css"></link>
  </head>
  <body>
    <sql:setDataSource
      var = "myDS"
      driver = "org.postgresql.Driver"
      url = "jdbc:postgresql://localhost:5432/skistuff"
      user = "fred" 
      password = "secret"
    />
    
    <sql:update dataSource = "${myDS}" var = "result">
      UPDATE skisEtc SET product = ?, 
                         category = ?, 
                         price = <%= Float.parseFloat(request.getParameter("price").trim()) %>
                         where id = ${param.id};
      <sql:param value = "${param.product}"/>
      <sql:param value = "${param.category}"/>
    </sql:update>
    
    <c:if test = "${result >= 1}">
      <div class = "goodSave">
	<center>Edited product saved</center>
	<center><a href = "${pageContext.request.contextPath}/skiStuffCRUD.jsp">Back</a></center>                
      </div>
    </c:if>
  </body>
</html>
