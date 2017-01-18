# 2. Getting Started
## 2.1. A Full Code Example To Get Started
- 0:04:30
- <http://localhost:8080/temp/input.html>
- <http://localhost:8080/temp/convert.jsp>
- [./firstWebApp/1stWebApp.txt](Chapter02/firstWebApp/1stWebApp.txt)
- [./firstWebApp/temp.war](Chapter02/firstWebApp/)

## 2.2. A First Look At The Introductory  Web App's Pieces
- 0:05:32
- <http://localhost:8080/temp/input.html>
- <http://localhost:8080/temp/convert.jsp>
- [./webAppPieces/input.html](Chapter02/webAppPieces/input.html)
- [./webAppPieces/web.xml](Chapter02/webAppPieces/web.xml)
- [./webAppPieces/convert.jsp](Chapter02/webAppPieces/convert.jsp)

## 2.3. The JSP Template Piece
- 0:07:08
- [./jsp/jspOverview.txt](Chapter02/jsp/jspOverview.txt)
- [./jsp/convert.jsp](Chapter02/jsp/convert.jsp)

## 2.4. The Web Server Piece: Tomcat
- 0:07:14
- [tomcat.txt](Chapter02/tomcat.txt)

## 2.5. Technologies In Play With Web Apps
- 0:05:37
- [technologies.txt](Chapter02/technologies.txt)

## 2.6. Web Sites, Web Services, And Web Apps: Clearing Up The Jargon
- 0:04:10
- [sitesServicesApps.txt](Chapter02/sitesServicesApps.txt)

## 2.7. Web App Deployment Part - 1
- 0:05:58
- [deployment1.txt](Chapter02/deployment1.txt)
- ./firstWebApp $ jar tvf [temp.war](Chapter02/firstWebApp/)
- ./firstWebApp $ jar xvf [temp.war](Chapter02/firstWebApp/)

## 2.8. Web App Deployment Part - 2
- 0:07:43
- [build.xml](Chapter02/build.xml)
- ./firstWebApp $ ant -Dwar.name=temp deploy
- <http://localhost:8080/temp>
- $ rm $HOME/tomcat8/webapps/temp.war

## 2.9. Exercise: Web App Deployment
- 0:05:58
- [./hello/exercise1/](Chapter02/hello/exercise1/)
- ./hello $ ant -Dwar.name=hello deploy
- [deployExercise.txt](Chapter02/deployExercise.txt)
- [./hello/hi.jsp](Chapter02/hello/hi.jsp)
- [./hello/exercise1/Hi.java](Chapter02/hello/exercise1/Hi.java)
- $HOME/tomcat8/lib/servlet-api.jar
- $HOME/tomcat8/lib/jsp-api.jar

## 2.10. The Database Piece Part - 1
- 0:05:46
- [dbOverview.txt](Chapter02/dbOverview.txt)

## 2.11. The Database Piece Part - 2
- 0:08:07
- [./basicJDBC/BasicJDBC.java](Chapter02/basicJDBC/BasicJDBC.java)
- Welcome to Postgres
- psql (9.4.5)
- $ \h select
- $ \q
- ./basicJDBC $ javac BasicJDBC.java
- ./basicJDBC $ java -cp .:postgresql-jdbc.jar BasicJDBC

## 2.12. The Database Piece Part - 3
- 0:07:56
- [./basicJDBC/BasicJDBC.java](Chapter02/basicJDBC/BasicJDBC.java)

## 2.13. Wrap Up Of Getting Started
- 0:03:46
- [wrapupIntro.txt](Chapter02/wrapupIntro.txt)
