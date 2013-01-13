
net stop "Apache Tomcat 7.0 Tomcat7"

del C:\dev\servers\apache-tomcat-7.0.23_win\webapps\football-results-analyser.war
del "C:\Program Files\Apache Software Foundation\Tomcat 7.0\webapps\football-results-analyser.war"

rmdir /S /q C:\dev\servers\apache-tomcat-7.0.23_win\webapps\football-results-analyser
rmdir /S /q "C:\Program Files\Apache Software Foundation\Tomcat 7.0\webapps\football-results-analyser"

copy target\football-results-analyser.war C:\dev\servers\apache-tomcat-7.0.23_win\webapps\
copy target\football-results-analyser.war "C:\Program Files\Apache Software Foundation\Tomcat 7.0\webapps\"
rem copy H:\dev\football-results-analyser\target\football-results-analyser.war "C:\Program Files\Apache Software Foundation\Tomcat 7.0\webapps\"

net start "Apache Tomcat 7.0 Tomcat7"
