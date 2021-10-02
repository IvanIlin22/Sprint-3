package ru.sber.qa

import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CertificateTest {

    private val certificateRequest = mockk<CertificateRequest>();
    private val processedBy = 10L;
    private val data = byteArrayOf(1, 2, 3, 4);
    private val certificate: Certificate = Certificate(certificateRequest, processedBy, data);


    @Test
    fun getCertificateRequest() {
        assertEquals(certificateRequest, certificate.certificateRequest);
    }

    @Test
    fun getProcessedBy() {
        assertEquals(processedBy, certificate.processedBy)
    }

    @Test
    fun getData() {
        assertEquals(data, certificate.data);
    }
}
