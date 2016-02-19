
#How to config SSO token-url in micro-spring-docker ?

I met some problems with micro-spring-docker , i think maybe the sso token-url is not correct.

The demo [https://github.com/keryhu/micro-oauth2-docker](github)

In local computer , sso service and auth-service works fine .

But not  in docker container . 

**SSO(pc-gateway service) application.yml:**

	security:
	  user:
	    password: none
	  oauth2:
	    client:
	      accessTokenUri: http://${AUTHSERVER_PORT_9999_TCP_ADDR:localhost}:9999/uaa/oauth/token
	      userAuthorizationUri: http://${AUTHSERVER_PORT_9999_TCP_ADDR:localhost}:9999/uaa/oauth/authorize
	     

	
**docker-compose.yml**

	eureka:
	  image: eureka:0.0.1-SNAPSHOT
	  container_name: eureka
	  hostname: eureka
	  ports:
	   - "8761:8761"
	   
	configserver:
	  image: config-server:0.0.1-SNAPSHOT
	  container_name: configserver
	  hostname: configserver
	  links:
	    - eureka
	  ports:
	    - "8888:8888"
	    
	authserver:
	  image: auth-server:0.0.1-SNAPSHOT
	  container_name: authserver
	  hostname: authserver
	  links:
	    - eureka
	    - configserver
	  ports:
	    - "9999:9999"

	pcgateway:
	  image: pc-gateway:0.0.1-SNAPSHOT
	  container_name: pcgateway
	  hostname: pcgateway
	  links:
	    - eureka
	    - configserver
	    - authserver
	  ports:
	    - "8080:8080"





After starting in docker container :	     
	          
http://192.168.99.100:8761/ showing :

	Instances currently registered with Eureka
	Application	  AMIs	   Availability Zones	Status
	AUTHSERVER	 n/a(1)	          (1)          	UP (1) - authserver:authserver:9999
	CONFIGSERVER n/a(1)	          (1)	        UP (1) - configserver:configserver:8888
	PCGATEWAY	 n/a(1)	          (1)        	UP (1) - pcgateway:pcgateway:8080

But when open the auth page: http://192.168.99.100:8080 
	          
It should be redirected to  auth-server login page , but it opened Timeout ， the Address Bar is: 

	http://172.17.0.4:9999/uaa/oauth/authorize?client_id=clientapp&redirect_uri=http://192.168.99.100:8080/login&response_type=code&state=cdXhfg

I don't know why , maybe the above sso tokenurl is not correct . How to resolve ?
	          
	         
	          
	    