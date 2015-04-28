
net stop "%TOMCAT_SERVICE%"

del "%APACHE_HOME%\webapps\football-results-analyser.war"
rmdir /S /q "%APACHE_HOME%\webapps\football-results-analyser"

rmdir /S /q "%APACHE_HOME%\logs"
del "C:\Mark\*.log"

copy target\football-results-analyser.war "%APACHE_HOME%\webapps\"

net start "%TOMCAT_SERVICE%"
