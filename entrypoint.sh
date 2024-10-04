#!/bin/bash

### SCRIPT DE STARTUP - PAAS SANTANDER BRASIL ###
## O control.sh, executa os seguintes scripts na ordem (decrescente) abaixo:
# 
# 4.importCerts.sh - Importa os certificados presentes na pasta /etc/certificates
# 3.nat.sh - Copia o arquivo do NAT (mount criado pelo PaaS) concatenando no arquivo hosts do S.O
# 1.start_wily.sh - Inicializa o agente do APM(introscope)
# 0.start.sh - Starta a aplicacao. Comando -> exec java $JAVA_HEAP $JAVA_OPTS_EXT -jar "$JAR_PATH" $JAVA_PARAMETERS
#
#IMAGE_SCRIPTS_HOME=/opt/scripts (valor default)
cd $IMAGE_SCRIPTS_HOME
exec ./control.sh start
#
### FIM - SCRIPT STARTUP ###
