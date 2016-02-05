
#Has methods  secure Config Server by oauth2 token ?

I plan to implement  spring cloud config-server by oauth2 token,so client-server can fetch property by :

	 cloud:
	    config:
	      uri: http://user:password@localhost:8888
 
Is it feasible ?

 but ... i met some problems .
I start a demo in [https://github.com/keryhu/spring-oauth2-config-server.git](demo github link)

It contains  four services : 

**1 : eureka** : start first,and can implement service register and discovery,it has no oauth2 enviroment.

**2 : auth-server** : JWT OAuth2 server configuration ,start secondly.

		@SessionAttributes("authorizationRequest")
		@EnableResourceServer
		@EnableDiscoveryClient

 and inmemory user :

	  security:
 	    user:
 	      password: password

**3: config-server** : start thirdly

	@EnableDiscoveryClient
	@EnableConfigServer
	@EnableResourceServer
	
and in application.yml :

	spring:  
	  cloud:
	     config:
	       server:
	         git:
	           uri: https://github.com/keryhu/cloud-config


	security:
	  oauth2:
	    resource:
	      jwt:
	        keyValue: |
	          -----BEGIN PUBLIC KEY-----
	          ....
	          -----END PUBLIC KEY-----



**4: pc-gateway** :  is a client-server,also  a ui server. start lastly

When i test the secured uri: [http://localhost:8080/hello](), the page was redirected to "[http://localhost:9999/uua/login]()" . 

   After  entering "**user:password**",it redirects back "[http://localhost:8080/hello]()" ,so i think the oauth-server and oauth-client is fine.

but.. i also set the following configuration in bootstrap.yml

	cloud:
	    config:
	      uri: http://user:password@localhost:8888

When starting pc-gateway service, Fetching config from server has 401 Unauthorized errors :

    INFO 954 --- [main] c.c.c.ConfigServicePropertySourceLocator : Fetching config from server at: http://localhost:8888
    WARN 954 --- [main] c.c.c.ConfigServicePropertySourceLocator : Could not locate PropertySource: 401 Unauthorized


Need help ! thanks !