# toolstash

A small multi-module project by MarshmallowShadow

This project contains a collection of potentially commonly used configurations and utilies for backend development!

## Initial Setting

Gradle

```
repositories {
	mavenCentral()
	maven {
		url = uri("https://maven.pkg.github.com/MarshmallowShadow/toolstash")
		credentials {
			username = $USERNAME
			password = $TOKEN
		}
	}
}
```
***$USERNAME** is your GitHub username and **$TOKEN** is the access token with read:repository scope

## Current Features

### File Utilities

A collection of tasks involving files such as file upload, image resize, Etc.

pom.xml

```
<dependency>
  <groupId>com.marsh.toolstash</groupId>
  <artifactId>file-util</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency> 
```

build.gradle.kts

```
implementation("com.marsh.toolstash:file-util")
```

## Encoding Utilities

A collection of common encryption methods

pom.xml

```
<dependency>
  <groupId>com.marsh.toolstash</groupId>
  <artifactId>encoding-util</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency> 
```

build.gradle.kts

```
implementation("com.marsh.toolstash:encoding-util")
```

## Mail Utilities

With just a few configurations, allows easy mail sending (also includes using Thymeleaf template as text)

pom.xml

```
<dependency>
  <groupId>com.marsh.toolstash</groupId>
  <artifactId>mail-util</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency> 
```

build.gradle.kts

```
implementation("com.marsh.toolstash:mail-util")
```

application.yml (sample)

```
mail:
	host: smtp.office365.com
	port: 587
	username: marsh@example.com
	password: 12345678
	properties: 
	    mail:
            smtp:
                auth: true
                    starttls:
                        enable: true
```

## Swagger Autoconfiguration

One-Step Swagger API configuration with only some properties

pom.xml

```
<dependency>
  <groupId>com.marsh.toolstash</groupId>
  <artifactId>swagger-config</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency> 
```

build.gradle.kts

```
implementation("com.marsh.toolstash:swagger-config")
```

application.yml (sample)

```
swagger:
	basePackage: com.marsh.example
	title: MarshAPI
	version: 1.0.0
	description: A Sample API
	termsOfServiceUrl: https://termsofservice.example.com/
	contactName: Marsh
	contactUrl: https://marsh.shad.co.kr/
	email: marsh@example.com
```

*Using Swagger UI: https://github.com/swagger-api/swagger-ui

## Security Autoconfiguration

Includes JWT TokenProvider and basic security configurations with only some properties

pom.xml

```
<dependency>
  <groupId>com.marsh.toolstash</groupId>
  <artifactId>swagger-config</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency> 
```

build.gradle.kts

```
implementation("com.marsh.toolstash:swagger-config")
```

application.yml (sample)

```
jwtSecret: 3hj546bjk6i7cesrjoi132cfyesr6ib0

security:
	authorizeList:
		- httpMethod: GET
		  pattern: /users/**
		  role: USER, GUEST
		- httpMethod: POST
		  pattern: /boards/**
		  role: USER
		- pattern: /**
		  role: ALL
	ignoreList: /v3/api-docs, /swagger-resources/**, /webjars/**
```