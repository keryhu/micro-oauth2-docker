
# spring-micro-oauth2-docker 
this is just a spring-micro-oauth2-docker demo !

It contains : four service .

eureka service , oauth2 service  pc-gateway service and config-server service .

when open 192.168.99.100:8080 , it's free .

but , 192.168.99.100:8080/hello , that is a secured page ,and it will redirected to oauth2-service 9999 port -login page . when input the right username and password ,it will return 8080 port hello page back .

