package Service;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PhotoReceiveHandler {
    public String receiveAPhoto(Part part, String rootDir, long id){
        String dirAdr = rootDir  + File.separator + id;
        File dir = new File(dirAdr);
        if (dir.exists()) {
            /*
            if (!dir.delete())
                throw new RuntimeException();*/
        } else if (!dir.mkdir())
            throw new RuntimeException();
        String[] submittedFileName = part.getSubmittedFileName().split("\\.");
        String fileAdr =  "avatar." + submittedFileName[submittedFileName.length-1];
        File file = new File(dirAdr + File.separator + fileAdr);

        try {
            FileOutputStream fS = new FileOutputStream(file);
            try(fS) {
                fS.write(part.getInputStream().readAllBytes());
                return id + File.separator + fileAdr;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
