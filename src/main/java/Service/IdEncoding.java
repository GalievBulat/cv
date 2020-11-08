package Service;

public class IdEncoding {
    public double encrypt(long id){
        return (double) id/1914;
    }
    public long decrypt(double idEnc){
        return (long) (idEnc*1914);
    }

}
