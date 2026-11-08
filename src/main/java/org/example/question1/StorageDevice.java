package org.example.question1;
import java.io.IOException;

public class StorageDevice {

    public String readData(String fileName)
            throws StorageException, IOException {

        if (fileName == null) {
            throw new NullPointerException("fileName must not be null");
        }

        if (fileName.endsWith(".old")) {
            throw new StorageException("unsupported format");
        }

        if (fileName.contains("denied")) {
            throw new IOException("access denied");
        }

        return "data from storage device: " + fileName;
    }
}

class FlashDrive extends StorageDevice {

    @Override
    public String readData(String fileName)
            throws StorageException, IOException {

        if (fileName == null) {
            throw new NullPointerException("fileName must not be null");
        }

        if (fileName.contains("virus")) {
            throw new CorruptedFileException("unsupported format");
        }

        if (fileName.endsWith(".tmp")) {
            throw new IOException("temporary file cannot be read");
        }

        return "data from flash drive: " + fileName;
    }
}
 class StorageTest {

    public static void main(String[] args) {

        StorageDevice device = new FlashDrive();

        String[] files = {
                null,
                "oldfile.old",
                "doc_denied.txt",
                "infected_virus.data",
                "temp.tmp",
                "normal.txt"
        };

        for (int i = 0; i < files.length; i++) {
//لازم الابن ييجي قبل الأب
            //لو كتبنا StorageException الأول، هتمسك CorruptedFileException كمان، وساعتها الـ Catch اللي بعدها مش هتتنفذ أبدًا
            try {

                System.out.println(device.readData(files[i]));

            } catch (NullPointerException e) {

                System.out.println(e.getMessage());

            } catch (CorruptedFileException e) {

                System.out.println(e.getMessage());

            } catch (StorageException e) {

                System.out.println(e.getMessage());

            } catch (IOException e) {

                System.out.println(e.getMessage());
            }
        }
    }
}