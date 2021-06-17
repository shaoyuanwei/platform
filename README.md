# platform
spring cloud alibaba + spring boot + nacos + sentinel + gateway

#工具使用
## redis

redis-server.exe redis.windows.conf

## nacos

startup.cmd -m standalone

## sentinel

java -Dserver.port=8090 -Dcsp.sentinel.dashboard.server=localhost:8090 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.0.jar

## zipkin

java -jar zipkin-server-2.12.9-exec.jar

持久化: java -jar zipkin-server-2.12.9-exec.jar --STORAGE=mysql --MYSQL_HOST=127.0.0.1 --MYSQL_TCP_PORT=3306 --MYSQL+DB=zipkin --MYSQL_USER=root --MYSQL_PASS=123456

## rocketMQ

启动nameServer：
nohup ./bin/mqnamesrv &
查看日志:
tail -f ~/logs/rocketmqlogs/namesr.log
启动broker：
nohup ./bin/mqbroker -m localhost:9876
查看日志:
tail -f ~/logs/rocketmqlogs/broker.log
关闭：
bin/mqshutdown broker
bin/mqshutdown namesrc
测试：
export NAMESRV_ADDR=localhost:9876
bin/tools.sh org.apache.example.quickstart.Producer
bin/tools.sh org.apache.example.quickstart.Consumer