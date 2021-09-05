package ru.sber.io

import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {

    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */
    fun zipLogfile() {
        val fileInput: String  = "io\\logfile.log";
        val fileOutput: String = "io\\logfile.zip";
        try {
            val zos = ZipOutputStream(FileOutputStream(fileOutput));
            val fis = FileInputStream(fileInput);
            val entry = ZipEntry("logfile.log");
            val buffer = ByteArray(fis.available());

            fis.use {
                fis.read(buffer);
            }

            zos.use {
                zos.putNextEntry(entry);
                zos.write(buffer);
                zos.closeEntry();
            }
        } catch(ex: Exception) {
            println(ex.message);
        }
    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile() {

        val fileInput = "io\\logfile.zip";
        val fileOutput = "io\\unzippedLogfile.log";
        try {
            val zis = ZipInputStream(FileInputStream(fileInput));

            zis.use {
                while (zis.nextEntry != null) {

                    val fos = FileOutputStream(fileOutput);
                    fos.use {
                        fos.write(zis.readAllBytes());
                    }

                    zis.closeEntry();
                }
            }

        } catch (ex: Exception) {
            println(ex.message);
        }
    }
}

fun main() {
    val arch: Archivator = Archivator();
    arch.zipLogfile();
    arch.unzipLogfile();
}
