<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html>
  <head>
    <title>Formatting stuff</title>
  </head>
  <body>
    <h3>Number formats and conversions</h3>
    <c:set var="sample" value="1234567.8912" />
    <p>Unformated: <c:out value="${sample}" />
      
      <p>Formatted  (1): <fmt:formatNumber value="${sample}" 
		 			   type="currency"/></p>
      <p>Formatted  (2): <fmt:formatNumber type="number" 
					   maxIntegerDigits="3" 
					   value="${sample}" /></p>
      <p>Formatted  (3): <fmt:formatNumber type="number" 
					   maxFractionDigits="3" 
					   value="${sample}" /></p>
      <p>Formatted  (4): <fmt:formatNumber type="number" 
					   groupingUsed="false" 
					   value="${sample}" /></p>
      <p>Formatted  (5): <fmt:formatNumber type="percent" 
					   maxIntegerDigits="3" 
					   value="${sample}" /></p>
      <p>Formatted  (6): <fmt:formatNumber type="percent" 
					   minFractionDigits="10" 
					   value="${sample}" /></p>
      <p>Formatted  (7): <fmt:formatNumber type="percent" 
					   maxIntegerDigits="3" 
					   value="${sample}" /></p>
      <p>Formatted  (8): <fmt:formatNumber type="number" 
					   pattern="###.###E0" 
					   value="${sample}" /></p>
      <p>Conversions: 
	<fmt:parseNumber var="num" type="number" value="${sample}" />
	<p>Parsed as a floating-point: <c:out value="${num}"/></p>
	
	<fmt:parseNumber var="num" integerOnly="true" 
			 type="number" value="${sample}"/>
	<p>Parased as an integer: <c:out value="${num}" /></p>
      </p>
      <hr/>
      <h3>Date formats</h3>

      <p>Local time (CST): <%= new java.util.Date().toString() %></p>
      
      <p>Locale de_DE:
	<fmt:setLocale value="de_DE"/>    
	<fmt:formatDate value="<%= new java.util.Date() %>"/></p>
	  
	  <p>Locale en_US: 
	    <fmt:setLocale value="en_US"/>    
	    <fmt:formatDate value="<%= new java.util.Date() %>"/></p>
	      
	      <p>Locale en_UK: 
		<fmt:setLocale value="en_UK"/>    
		<fmt:formatDate value="<%= new java.util.Date() %>"/></p>
		  
		  <c:set var="now" value="<%= new java.util.Date() %>" />
		    
		    <p>Formatted date (1): <fmt:formatDate type="time" 
							   value="${now}" /></p>
		    <p>Formatted date (2): <fmt:formatDate type="date" 
							   value="${now}" /></p>
		    <p>Formatted date (3): <fmt:formatDate type="both" 
							   value="${now}" /></p>
		    <p>Formatted date (4): <fmt:formatDate type="both" 
							   dateStyle="short" 
							   timeStyle="short" 
							   value="${now}" /></p>
		    <p>Formatted date (5): <fmt:formatDate type="both" 
							   dateStyle="medium" 
							   timeStyle="medium" 
							   value="${now}" /></p>
		    <p>Formatted date (6): <fmt:formatDate type="both" 
							   dateStyle="long" 
							   timeStyle="long" 
							   value="${now}" /></p>
		    <p>Formatted date (7): <fmt:formatDate pattern="yyyy-MM-dd" 
							   value="${now}" /></p>
  </body>
</html>
