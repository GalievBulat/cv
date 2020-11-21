package Service;

import DAO.TcDAO;

public class TcManagerService {
    TcDAO repository= new TcDAO();
    public long getTcBalance(long tc){
        return repository.getBalance(tc);
    }
}
