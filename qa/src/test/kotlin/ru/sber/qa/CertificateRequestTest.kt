package ru.sber.qa

import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class CertificateRequestTest {

    @BeforeEach
    fun beforeTest() {
        mockkObject(Scanner);
        mockkObject(CertificateType.LABOUR_BOOK);
    }

    @Test
    fun process() {
        //given
        val certificateRequest: CertificateRequest = CertificateRequest(1L, CertificateType.LABOUR_BOOK);
        val byteArray = byteArrayOf(1, 2, 3, 4);
        val certificate = Certificate(certificateRequest, 10L, byteArray);
        every { Scanner.getScanData() } returns byteArray;

        //when
        val result = certificateRequest.process(10L);

        //then
        assertEquals(certificate, result);
        verify(exactly = 1) {
            Scanner.getScanData();
        }
    }

    @AfterEach
    fun afterTest() {
        unmockkAll();
    }
}
