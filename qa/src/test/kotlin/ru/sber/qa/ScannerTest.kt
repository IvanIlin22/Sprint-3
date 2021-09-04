package ru.sber.qa

import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.random.Random

internal class ScannerTest {

    @BeforeEach
    fun beforeTest() {
        mockkObject(Random);
    }

    @Test
    fun getScanDataException() {
        //given
        every { Random.nextLong(5000L, 15000L) } returns 11000L;

        //then
        assertThrows(ScanTimeoutException::class.java, {Scanner.getScanData()});
    }

    @Test
    fun getScanData() {
        //given
        val arrBytes = Random.nextBytes(100);
        every { Random.nextLong(5000L, 15000L) } returns 10000L;
        every { Random.nextBytes(100) } returns arrBytes;

        //then
        assertEquals(arrBytes, Scanner.getScanData());
    }

    @AfterEach
    fun afterTest() {
        unmockkAll();
    }
}
