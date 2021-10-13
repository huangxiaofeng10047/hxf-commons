FROM bitnami/java:1.8-prod
ARG TIME_ZONE=Asia/Shanghai

ENV TZ=${TIME_ZONE}

ENV JAVA_OPTS="\
-server \
-Xmx2g \
-Xms2g \
-Xmn1g \
-XX:SurvivorRatio=8 \
-XX:MetaspaceSize=256m \
-XX:MaxMetaspaceSize=512m \
-XX:+UseParallelGC \
-XX:ParallelGCThreads=4 \
-XX:+UseParallelOldGC \
-XX:+UseAdaptiveSizePolicy \
-XX:+PrintGCDetails \
-XX:+PrintTenuringDistribution \
-XX:+PrintGCTimeStamps \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=/ \
-Xloggc:/gc.log \
-XX:+UseGCLogFileRotation \
-XX:NumberOfGCLogFiles=5 \
-XX:GCLogFileSize=10M"
ADD target/spring-batch-demo-0.0.1-SNAPSHOT.jar /app/
#设置启动时堆栈大小（无用）
#ENV JAVA_OPTS="-Xms400m -Xmx400m"
#这个启动会直接的导致java的进程为1，不能使用jmap jinfo等工具
#ENTRYPOINT ["java","-jar","/bdps.jar"]
WORKDIR /app
EXPOSE 8080
#CMD ["java",${JAVA_OPTS},"-jar", "spring-batch-demo-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["/bin/sh", "-c", "set -e && java ${JAVA_OPTS}  -jar spring-batch-demo-0.0.1-SNAPSHOT.jar"]
#ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar spring-batch-demo-0.0.1-SNAPSHOT.jar
