package Service;

import Model.WritableToFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
@Deprecated
public final class FileOutputHandler  implements OutputHandler{
    private final String USERS_FILE;
    public FileOutputHandler(){
        USERS_FILE = "C:\\Users\\Kakad\\Documents\\webb\\src\\main\\java\\users.txt";
    }
    public FileOutputHandler(String filename){
        USERS_FILE = filename;
    }
    public void writeTheUserToTheFile(final WritableToFile user){
        try (BufferedWriter fW = new BufferedWriter(new FileWriter(USERS_FILE,true))){
            fW.write(user.getDBRepresentation());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
