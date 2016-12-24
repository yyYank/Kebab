package kebab.report;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;


/**
 * Created by yy_yank on 2016/10/05.
 */
public class PngUtils {
    static public boolean isPng(byte[] bytes) {
        try {
            return new DataInputStream(new ByteArrayInputStream(bytes)).readLong() == 0x89504e470d0a1a0aL;
        } catch (IOException e) {
            // not going to happen
            return false;
        }

    }
}
