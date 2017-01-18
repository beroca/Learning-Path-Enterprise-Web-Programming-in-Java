<!doctype html>
<html>
  <head>
    <title>Exercise 1</title>
  </head>
<body>
  <jsp:useBean id = "now" class = "java.util.Date" /> 
  <jsp:useBean id = "hi" class = "exercise1.Hi" />
  <h3>
    <%= hi.getGreeting() + " at " + now %>
  </h3>
</body>
</html>