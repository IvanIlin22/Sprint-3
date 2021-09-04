package ru.sber.qa

import java.util.*

/**
 * Готовая справка.
 */
class Certificate(
    val certificateRequest: CertificateRequest,
    val processedBy: Long,
    val data: ByteArray,
) {

    override fun hashCode(): Int {
        return Objects.hash(processedBy, data, certificateRequest);
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || javaClass != other.javaClass) return false;
        val certificate: Certificate = other as Certificate;
        return data.contentEquals(certificate.data) && processedBy == certificate.processedBy && certificateRequest == certificate.certificateRequest;
    }

}
