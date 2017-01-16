<%@ page errorPage = "error.jsp" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html>					  
<html>
<head>
<title>Create new product</title>
<link rel = "stylesheet" href = "style.css" type = "text/css"></link>
</head>
<body>
    <div>
        <fieldset><legend class="legend">Create product</legend>
           <form action = "${pageContext.request.contextPath}/save.jsp" method = "post">
              <table>
                 <tbody>
                   <tr style = "background: white;}">
                     <td><label for = "product">Product:</label></td><td>
		       <input id = "product" name = "product"
			      style = "font-size:16px;"
			      type = "text"/></td>
                   </tr>
                   <tr style = "background: white;}">
                     <td><label for = "category">Category:</label></td><td>
		       <input id = "category" name = "category"
			      style = "font-size:16px;"
			      type = "text"/></td>
                   </tr>
		   <tr style = "background: white;}">
                     <td><label for = "price">Price:</label></td><td>
		       <input id = "price" name = "price"
			      style = "font-size:16px;"
			      type = "number" step = "0.01" min = "0"/></td>
                   </tr>
                 </tbody>
              </table>
              <p><input type="submit" value=" Create product "/></p>
           </form>
           <p><a href = "${pageContext.request.contextPath}/skiStuffCRUD.jsp">Back</a></p>        
        </fieldset>
    </div>
</body>
</html>
