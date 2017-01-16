<%@ page errorPage = "error.jsp" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>	
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql" %>   
<!DOCTYPE html>					  
<html>
<head>
<title>Create new user</title>
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
    <sql:update dataSource = "${myDS}" var = "count">
       DELETE FROM skisEtc WHERE id = '${param.id}';
    </sql:update>
    <c:if test = "${count >= 1}">
      <div class = "goodResult">
       <center>Product deleted</center>
       <center><a href="${pageContext.request.contextPath}/skiStuffCRUD.jsp">Back</a></center>                
    </c:if>
</body>
</html>