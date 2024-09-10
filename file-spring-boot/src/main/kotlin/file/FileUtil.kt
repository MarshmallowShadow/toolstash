package com.marsh.file

import com.fasterxml.uuid.Generators
import org.apache.commons.io.FilenameUtils
import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.multipart.MultipartFile
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.nio.file.Files
import java.text.SimpleDateFormat
import java.util.Base64
import java.util.Date
import javax.imageio.ImageIO

object FileUtil {

    /**
     *  
     *  @description 생성 날짜 기준으로 폴더를 만들고 안에 파일을 업로드
     *  @sample /src/test/resources/test.txt
     *  @return 업로드된 파일 경로
     *  
     */
    fun uploadFile(uploadPath: String, originFile: MultipartFile): String {
        val destinationFile = File(makeParentDirectory(uploadPath), convertFileName(originFile))
        originFile.transferTo(destinationFile)
        return destinationFile.absolutePath
    }

    /**
     *
     *  @description 생성 날짜 기준으로 폴더를 만들고 안에 파일을 업로드
     *  @sample /src/test/resources/test.txt
     *  @return 업로드된 파일 경로
     *
     */
    fun viewFile(file: File): ByteArray {
        FileInputStream(file).use { inputStream ->
            ByteArrayOutputStream().use { outputStream ->
                val buffer = ByteArray(8192)
                var length: Int
                while (inputStream.read(buffer).also { length = it } != -1)
                    outputStream.write(buffer, 0, length)
                return outputStream.toByteArray()
            }
        }
    }

    /**
     *
     *  @description 생성 날짜 기준으로 폴더를 만들고 안에 파일을 업로드
     *  @sample /src/test/resources/test.txt
     *  @return 업로드된 파일 경로
     *
     */
    fun deleteFile(file: File) {
        file.delete()
    }

    /**
     *
     *  @description 생성 날짜 기준으로 폴더를 만들고 안에 파일을 업로드
     *  @sample /src/test/resources/test.txt
     *  @return 업로드된 파일 경로
     *
     */
    fun downloadFile(file: File, fileName: String): ResponseEntity<InputStreamResource> {
        val mediaType = MediaType.parseMediaType("application/x-msdownload")
        val resource = InputStreamResource(FileInputStream(file))

        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=$fileName")
            .contentType(mediaType)
            .contentLength(file.length())
            .body(resource)
    }

    fun checkFileSize(files: List<MultipartFile>, size: Long): Boolean {
        var checkSize: Long = 0;
        files.forEach {
            checkSize += it.size
            if(checkSize > size) return false
        }
        return true
    }


    /* =====================Functions Related to Image Files===================== */

    /**
     * 
     * @description 이미지 크기 조정
     * @sample /src/test/resources/
     * @return base64 String
     * 
     */
    fun imageToBase64(image: File): String = Base64.getEncoder().encodeToString(Files.readAllBytes(image.toPath()))

    /**
     * 
     * @description 이미지 크기 조정
     * @sample /src/test/resources/
     * 
     */
    fun resizeImage(originFile: File, specifiedWidth: Int, specifiedHeight: Int) {
        val originalImage = ImageIO.read(originFile)
        val imageWidth = originalImage.width
        val imageHeight = originalImage.height
        val ratio = maxOf(specifiedWidth.toDouble() / imageWidth, specifiedHeight.toDouble() / imageHeight)
        val w = (imageWidth * ratio).toInt()
        val h = (imageHeight * ratio).toInt()


        val resizedImage = BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)
        val graphics = resizedImage.createGraphics()
        try {
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
            graphics.drawImage(originalImage, 0, 0, w, h, null)
        } finally {
            graphics.dispose()
        }

        ImageIO.write(resizedImage, originFile.extension, originFile)
    }


    /* =====================Inner Methods===================== */

    //Create a date-based parent directory
    private fun makeParentDirectory(baseDirectoryPath: String): String {
        val simpleDateFormat = SimpleDateFormat("yyyyMMdd")
        val date = simpleDateFormat.format(Date(System.currentTimeMillis()))
        val directory = File(baseDirectoryPath, date)

        if (!directory.exists()) {
            directory.mkdirs()
        }

        return directory.absolutePath
    }

    //Create Time-Based UUID File Name
    private fun convertFileName(file: MultipartFile): String {
        val uuid = Generators.timeBasedGenerator().generate().toString()
        val extension = "." + FilenameUtils.getExtension(file.originalFilename)
        return "$uuid$extension"
    }
}
