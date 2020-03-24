# 基础镜像
FROM openjdk:8-jdk-alpine

# 作者信息
MAINTAINER "dingshichen foreverhuiqiao@126.com"

# 设置时区
RUN echo 'Asia/Shanghai' >/etc/timezone

# 添加一个存储空间
VOLUME ["/work/log"]

# 暴露8080端口
EXPOSE 8080

# 往容器中添加jar包
ADD target/fast-docker.jar /work/app.jar

# 切换工作目录
#WORKDIR /work

# 启动镜像自动运行程序
ENTRYPOINT ["java", "-jar", "/work/app.jar"]