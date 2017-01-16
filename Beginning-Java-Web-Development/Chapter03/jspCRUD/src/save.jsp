<%@ page errorPage = "error.jsp" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
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
     
    <c:if test="${empty param.product or empty param.category or empty param.price}">
       <c:redirect url = "create.jsp" >
          <c:param name = "errMsg" value = "Please enter product, category, and price." />
       </c:redirect>
    </c:if>
 
    <sql:update dataSource = "${myDS}" var = "result">
       INSERT INTO skisEtc(product, category, price) VALUES 
       ('<%= request.getParameter("product") %>', 
        '<%= request.getParameter("category")%>',
         <%= Float.parseFloat(request.getParameter("price").trim()) %>);
    </sql:update>
           
    <c:if test = "${result >= 1}">
      <div class ="goodSave">
       <center>Product created</center>
       <center><a href = "${pageContext.request.contextPath}/skiStuffCRUD.jsp">Back</a></center>                
      </div>
    </c:if>
</body>
</html>