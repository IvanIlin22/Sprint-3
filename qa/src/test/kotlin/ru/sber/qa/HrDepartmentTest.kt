package ru.sber.qa

import io.mockk.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

internal class HrDepartmentTest {

    private val certificateRequest = mockk<CertificateRequest>();

    @Test
    fun receiveRequestWeekendDayException() {
        //given
        HrDepartment.clock = Clock.fixed(Instant.parse("2021-09-04T08:00:00Z"), ZoneId.of("Europe/Moscow"));

        //then
        assertThrows(WeekendDayException::class.java, {HrDepartment.receiveRequest(certificateRequest)});
    }

    @Test
    fun receiveRequestCertificateTypeNdfl() {
        //given
        HrDepartment.clock = Clock.fixed(Instant.parse("2021-09-01T08:00:00Z"), ZoneId.of("Europe/Moscow"));
        every { certificateRequest.certificateType } returns CertificateType.NDFL;

        //then
        assertDoesNotThrow({HrDepartment.receiveRequest(certificateRequest)});
    }

    @Test
    fun receiveRequestCertificateTypeLabourBook() {
        //given
        HrDepartment.clock = Clock.fixed(Instant.parse("2021-09-02T08:00:00Z"), ZoneId.of("Europe/Moscow"));
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK;

        //then
        assertDoesNotThrow({HrDepartment.receiveRequest(certificateRequest)});
    }

    @Test
    fun notAllowReceiveRequestExceptionCertificateTypeNdfl() {
        //given
        HrDepartment.clock = Clock.fixed(Instant.parse("2021-09-02T08:00:00Z"), ZoneId.of("Europe/Moscow"));
        every { certificateRequest.certificateType } returns CertificateType.NDFL;

        //then
        assertThrows(NotAllowReceiveRequestException::class.java, {HrDepartment.receiveRequest(certificateRequest)});
    }

    @Test
    fun notAllowReceiveRequestExceptionCertificateTypeLabourBook() {
        //given
        HrDepartment.clock = Clock.fixed(Instant.parse("2021-09-01T08:00:00Z"), ZoneId.of("Europe/Moscow"));
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK;

        //then
        assertThrows(NotAllowReceiveRequestException::class.java, {HrDepartment.receiveRequest(certificateRequest)});
    }

    @Test
    fun processNextRequest() {
        //given
        val certificate = mockkClass(Certificate::class);
        HrDepartment.clock = Clock.fixed(Instant.parse("2021-09-02T08:00:00Z"), ZoneId.of("Europe/Moscow"));
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK;
        every { certificateRequest.process(10L) } returns certificate;

        //when
        HrDepartment.receiveRequest(certificateRequest);

        //then
        assertDoesNotThrow({HrDepartment.processNextRequest(10L)});
        verify(exactly = 1) { certificateRequest.process(10L) }
    }
}
