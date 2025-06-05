package Bytes;

import java.io.IOException;
import java.io.RandomAccessFile;

public class BinFileAccessor {
    private final String filePath;
    private final int elementSize;

    public BinFileAccessor(String filePath, int elementSize) {

        this.filePath = filePath;
        this.elementSize = elementSize;
    }

    // 🔹 Получает число по индексу
    public byte[] getElement(long index) {
        long byteOffset = index * elementSize; // Смещение по файлу
        byte[] buffer = new byte[elementSize];

        try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
            if (byteOffset >= raf.length()) {
                return null;
            }

            raf.seek(byteOffset);
            raf.readFully(buffer);

            return buffer; // 🔹 Интерпретируем 5 байт как число
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 🔹 Возвращает количество записанных элементов
    public long getTotalElements() {

        try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
            long fileLength = raf.length();

            return raf.length() / elementSize; // 🔥 Количество 5-байтовых чисел
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
