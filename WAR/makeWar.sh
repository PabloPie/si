#!/bin/bash

# script sencillo para crear el .war

SERVLET="../../apache-tomcat-7.0.73/lib/*" # modificar cuando tomcat este situado en otro lado
SEP=":" # en windows es ";", en unix ":"

if javac -cp WEB-INF/classes${SEP}WEB-INF/lib/*${SEP}$SERVLET: -d WEB-INF/classes codigoJava/db/vo/*.java codigoJava/db/dao/*.java codigoJava/servlets/*.java ;
then
    jar -cvf jaus.war assets *.html *.jsp WEB-INF;
fi
