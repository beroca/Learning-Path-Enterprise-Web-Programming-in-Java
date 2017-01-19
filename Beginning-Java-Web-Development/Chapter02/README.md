# 2. Getting Started
## 2.1. A Full Code Example To Get Started
- 0:04:30
- <http://localhost:8080/temp/input.html>
- <http://localhost:8080/temp/convert.jsp>
- [./firstWebApp/1stWebApp.txt](firstWebApp/1stWebApp.txt)
- [./firstWebApp/temp.war](firstWebApp/)

## 2.2. A First Look At The Introductory  Web App's Pieces
- 0:05:32
- <http://localhost:8080/temp/input.html>
- <http://localhost:8080/temp/convert.jsp>
- [./webAppPieces/input.html](webAppPieces/input.html)
- [./webAppPieces/web.xml](webAppPieces/web.xml)
- [./webAppPieces/convert.jsp](webAppPieces/convert.jsp)

## 2.3. The JSP Template Piece
- 0:07:08
- [./jsp/jspOverview.txt](jsp/jspOverview.txt)
- [./jsp/convert.jsp](jsp/convert.jsp)

## 2.4. The Web Server Piece: Tomcat
- 0:07:14
- [tomcat.txt](tomcat.txt)

## 2.5. Technologies In Play With Web Apps
- 0:05:37
- [technologies.txt](technologies.txt)

## 2.6. Web Sites, Web Services, And Web Apps: Clearing Up The Jargon
- 0:04:10
- [sitesServicesApps.txt](sitesServicesApps.txt)

## 2.7. Web App Deployment Part - 1
- 0:05:58
- [deployment1.txt](deployment1.txt)
- ./firstWebApp $ jar tvf [temp.war](firstWebApp/)
- ./firstWebApp $ jar xvf [temp.war](firstWebApp/)

## 2.8. Web App Deployment Part - 2
- 0:07:43
- [build.xml](build.xml)
- ./firstWebApp $ ant -Dwar.name=temp deploy
- <http://localhost:8080/temp>
- $ rm $HOME/tomcat8/webapps/temp.war

## 2.9. Exercise: Web App Deployment
- 0:05:58
- [./hello/exercise1/](hello/exercise1/)
- ./hello $ ant -Dwar.name=hello deploy
- [deployExercise.txt](deployExercise.txt)
- [./hello/hi.jsp](hello/hi.jsp)
- [./hello/exercise1/Hi.java](hello/exercise1/Hi.java)
- $HOME/tomcat8/lib/servlet-api.jar
- $HOME/tomcat8/lib/jsp-api.jar

## 2.10. The Database Piece Part - 1
- 0:05:46
- [dbOverview.txt](dbOverview.txt)

## 2.11. The Database Piece Part - 2
- 0:08:07
- [./basicJDBC/BasicJDBC.java](basicJDBC/BasicJDBC.java)
- Welcome to Postgres
- psql (9.4.5)
- $ \h select
- $ \q
- ./basicJDBC $ javac BasicJDBC.java
- ./basicJDBC $ java -cp .:postgresql-jdbc.jar BasicJDBC

## 2.12. The Database Piece Part - 3
- 0:07:56
- [./basicJDBC/BasicJDBC.java](basicJDBC/BasicJDBC.java)

## 2.13. Wrap Up Of Getting Started
- 0:03:46
- [wrapupIntro.txt](wrapupIntro.txt)
