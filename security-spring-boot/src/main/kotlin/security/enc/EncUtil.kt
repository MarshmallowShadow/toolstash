package security.enc

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object EncUtil {

    /**
     * @Description
     * base64encode( encrypt(password + salt) + split-key + iv )
     */
    fun AES256Encrypt(text: String, key: String, salt: String, splitKey: String): String {
        val iv: String = getRandomString(16)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val keySpec = SecretKeySpec(key.toByteArray(StandardCharsets.UTF_8), "AES")
        val ivParamSpec = IvParameterSpec(iv.toByteArray(StandardCharsets.UTF_8))
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec)
        val encrypted = cipher.doFinal((text + salt).toByteArray(StandardCharsets.UTF_8))
        val encryptedDataPlusIv = Base64.getEncoder().encodeToString(encrypted) + splitKey + iv
        return Base64.getEncoder().encodeToString(encryptedDataPlusIv.toByteArray(StandardCharsets.UTF_8))
    }

    /**
     * @Description
     * encryptedData: encrypt(password + salt) .subString(split-key)
     * iv: 16 length 난수
     */
    fun AES256Decrypt(data: String, key: String, salt: String, splitKey: String): String {
        val decoded: ByteArray = Base64.getDecoder().decode(data)
        val decodedString = String(decoded, StandardCharsets.UTF_8)

        val splitIndex = decodedString.indexOf(splitKey)
        if (splitIndex == -1) {
            throw IllegalArgumentException("입력 형식이 잘못되었습니다.")
        }
        val encryptedData = decodedString.substring(0, splitIndex)
        val ivBytes = decoded.copyOfRange(decoded.size - 16, decoded.size)

        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val keySpec = SecretKeySpec(key.toByteArray(StandardCharsets.UTF_8), "AES")
        val ivParamSpec = IvParameterSpec(ivBytes)
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec)

        val encryptedDataBytes = Base64.getDecoder().decode(encryptedData)
        val decryptedBytes = cipher.doFinal(encryptedDataBytes)

        return String(decryptedBytes, StandardCharsets.UTF_8).split(salt)[0]
    }

    fun MD5Encrypt(text: String, salt: String, splitKey: String): String {
        val plainText = "$text$splitKey$salt"
        val md: MessageDigest = MessageDigest.getInstance("MD5")
        md.update(plainText.toByteArray())
        val digestBytes = md.digest()
        return digestBytes.joinToString("") { "%02x".format(it) }
    }

    fun SHA256Encrypt(text: String) =
        MessageDigest.getInstance("SHA-256")
            .digest(text.toByteArray())
            .fold("") { str, byte -> str + "%02x".format(byte) }
    
    
    private fun getRandomString(length: Int): String {
        val random = SecureRandom()
        val sb = StringBuilder()
        for (i in 0 until length) {
            val randomNum = random.nextInt(16)
            sb.append(Integer.toHexString(randomNum))
        }
        return sb.toString()
    }
}