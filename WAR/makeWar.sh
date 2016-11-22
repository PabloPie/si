#!/bin/bash

# script sencillo para crear el .war

TOMCATBASE="../../apache-tomcat-7.0.73" # modificar cuando tomcat este situado en otro lado
TOMCATLIB="${TOMCATBASE}/lib/*"
TOMCATAPPS="${TOMCATBASE}/webapps"
SEP=":" # en windows es ";", en unix ":"

if javac -cp WEB-INF/classes${SEP}WEB-INF/lib/*${SEP}${TOMCATLIB} -d WEB-INF/classes codigoJava/db/vo/*.java codigoJava/db/dao/*.java codigoJava/servlets/*.java ;
then
    jar -cvf jaus.war assets *.html *.jsp WEB-INF;
    mv jaus.war ${TOMCATAPPS} # desplegamos el .war
fi
