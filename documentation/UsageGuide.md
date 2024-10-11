## 환경세팅

build.gradle
```
plugins {
    
    ...
    
    id("nu.studer.credentials") version "3.0" // jib
}

...

val credentials: CredentialsContainer by project
val githubUsername: String? = credentials.forKey("github.username")
val githubToken: String? = credentials.forKey("github.token")

...

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/vitasoftGit/VitaPack-Java")
        credentials {
            username = githubUsername ?: System.getenv("USERNAME")
            password = githubToken ?: System.getenv("TOKEN")
        }
	}
}
```

properties.gradle
```
gpr.user=$USERNAME
gpr.key=$TOKEN
```
***\$USERNAME**은 GitHub 사용자 아이디, **\$TOKEN**은 read:repository 권한 있는 액세스 토큰입니다


### Commons

pom.xml
```
<dependency>
  <groupId>com.vita</groupId>
  <artifactId>commons</artifactId>
  <version>1.0.0</version>
</dependency> 
```

build.gradle.kts
```
implementation("com.vita:commons:1.0.0")
```


## Mail Utilities

pom.xml
```
<dependency>
  <groupId>com.vita</groupId>
  <artifactId>mail-util</artifactId>
  <version>1.0.0</version>
</dependency> 
```

build.gradle.kts
```
implementation("com.vita:mail-util:1.0.0")
```

application.yml (예시)
```
mail:
    host: smtp.office365.com
    port: 587
    username: marsh@example.com
    password: 12345678
    smtpAuth: true
    starttlsEnable: true
```

STMP 호스트와 포트 목록: https://www.arclab.com/en/kb/email/list-of-smtp-and-pop3-servers-mailserver-list.html

## Security Autoconfiguration

pom.xml
```
<dependency>
  <groupId>com.vita</groupId>
  <artifactId>security-config</artifactId>
  <version>1.0.0</version>
</dependency> 
```

build.gradle.kts
```
implementation("com.vita:security-config:1.0.0")
```

application.yml (예시)
```
jwt:
    secret: 54e74c5e23c9bf5247d95474770ac78b90336a32ab1425c80f310bc38dba8672
    resolver:
        enabled: true

security:
    enabled: true
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
	cors:
	    enabled: true
```

## AspectJ Autoconfiguration

pom.xml
```
<dependency>
  <groupId>com.vita</groupId>
  <artifactId>aspectj-config</artifactId>
  <version>1.0.0</version>
</dependency> 
```

build.gradle.kts
```
implementation("com.vita:aspectj-config:1.0.0")
```

## Demo Project

- 프로젝트 cloning 후 Demo Application 코드로 VitaPack의 기능 체험
