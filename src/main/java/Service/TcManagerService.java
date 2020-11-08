package Service;

import DAO.TcRepository;

public class TcManagerService {
    TcRepository repository= new TcRepository();
    public long getTcBalance(long tc){
        return repository.getBalance(tc);
    }
}
