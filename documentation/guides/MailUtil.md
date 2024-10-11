## MailUtil 함수

Send Mail
```
fun sendEmail (
    mailInfoDto: MailInfoDto,
    text: String
)
```

Mail Info DTO
```
class MailInfoDto(
    val sender: String,                     // 보내는 사람 (Properties에 설정한 이메일)
    val recipient: String,                  // 받는 사람 이메일
    val subject: String,                    // 메일 제목
    val attachment: List<MultipartFile>?    // 첨부파일 (있을 시)
    val logoPath: String? = null            // 회사 로고 (있을 시)
)
```