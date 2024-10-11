## Commons > FileUtil 함수

File Upload
```
fun uploadFile(
    uploadPath: String,         // 업로드 파일 경로
    originFile: MultipartFile   // Request를 통한 업로드할 파일
): String                       // 업로드된 파일 절대경로
```

Delete File
```
fun deleteFile (
    file: File // 삭제할 파일
)
```

View Image File
```
fun viewFile (
    file: File  // 조회할 파일
): ByteArray    // 이미지 파일 바이트 Array
```

File Download
```
fun downloadFile (
    file: File,                         // 파일명
    fileName: String                    // 다운로드 시 표시할 파일 이름
): ResponseEntity<InputStreamResource>  // 파일 다운로드하는 Response
```

Check File Size Limit
```
fun checkFileSize (
    files: List<MultipartFile>, // 크기 합산할 파일 리스트
    size: Long                  // 제한 크기
): Boolean                      // 제한 크기 넘을 경우 False
```

Image File to Base64 String
```
fun imageToBase64(
    image: File     //변환할 파일
): String           // base64 형식의 String
```

Image Resize (recommended for thumbnails)
```
fun resizeImage(
    originFile: File,       // 이미지 파일
    specifiedWidth: Int,    // 조정할 특정 너비
    specifiedHeight: Int    // 조정할 특정 높이
)                           // 리턴값 없음 (함수 끝나면 originFile 크기가 조정됨)
```
