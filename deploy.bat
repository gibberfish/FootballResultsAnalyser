
net stop "Apache Tomcat 7.0 Tomcat7"

del "%APACHE_HOME%\webapps\football-results-analyser.war"

rmdir /S /q "%APACHE_HOME%\webapps\football-results-analyser"
rmdir /S /q "%APACHE_HOME%\logs"

del "C:\Mark\*.log"

copy target\football-results-analyser.war "%APACHE_HOME%\webapps\"
rem copy H:\dev\football-results-analyser\target\football-results-analyser.war "C:\Program Files\Apache Software Foundation\Tomcat 7.0\webapps\"

net start "Apache Tomcat 7.0 Tomcat7"
