#!/bin/bash
#echo 'Debut de la Compilation du thread Java'
javac ThreadDemo.java
#echo 'Fin de la Compilation du thread Java'


#echo 'Debut de la Compilation du .c'

sudo cc -fPIC -o  libThreadDemo.so -shared -I/usr/jdk/include -I/usr/jdk/include/linux ThreadDemo.c -pthread

#echo 'Fin de la Compilation du .c'

#echo 'Ajout du PATH'

export LD_LIBRARY_PATH='/home/emeraude/Bureau/JNI2504/ChangeThread/Change/Thread2804/'

#echo 'Ajout avec Succes'


#echo 'Execution Du main'

java ThreadDemo
