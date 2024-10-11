## Commons > EncUtil 함수

Three general requirements:
- key
- salt
- splitKey

AES256 Encrypt & Decrypt
```
//Encrypt
fun AES256Encrypt (
    text: String,
    key: String,
    salt: String,
    splitKey: String
): String

//Decrypt
fun AES256Decrypt (
    data: String, 
    key: String, 
    salt: String, 
    splitKey: String
): String
```

MD5 Encrypt
```
fun MD5Encrypt (
    text: String, 
    salt: String, 
    splitKey: String
): String
```

SHA 256 Encrypt
```
fun SHA256Encrypt (
    text: String
): String
```