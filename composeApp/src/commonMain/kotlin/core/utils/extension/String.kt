package core.utils.extension

import java.security.MessageDigest

fun String.toSha256(): String {
    val bytes = this.toByteArray()

    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)

    return digest.fold("") { str, it -> str + "%02x".format(it) }
}
