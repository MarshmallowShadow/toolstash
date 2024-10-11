## SecurityConfig

###  Default Settings
- SessionManagement: STATELESS
- CSRF Disabled
- Passwordless (User and Role ONLY)
- JWT Token Secret Default Value (**NOT RECOMMENDED**)

### JWT Methods

Generate Token
```
fun generateToken (
    username: String,   // 로그인 후 사용자 아이디 
    role: String,       // 로그인한 사용자 Role
    expireIn: Long      // MiCurrentTimeMillis로 설정할 유효기간*
): String               // 토큰
```
*예시: 유효기간을 기간을 3일로 설정 시 현재일자를 CurrentTimeMillis로 변환 + 3일 로 호출 해야 함


Get Username from Token
```
fun getUsername (
    token: String   // JWT 토큰
): String           // 사용자 아이디
```

## JwtArgumentResolver
**@JwtResolver** Annotation

- Used beside controller method parameter
- Returns Username and Role through DTO

Sample:
```
@GetMapping("/getLoginInfo")
@ResponseStatus(HttpStatus.OK)
fun loginInfo(
    @JwtResolver userInfoDto: UserInfoDto, // UserInfoDto 로 사용자 아이디와 Role 가져옵니다
): UserInfoDto {
    return userInfoDto
}
```

UserInfoDto.kt
```
class UserInfoDto (
    val username: String,
    val role: String
)
```