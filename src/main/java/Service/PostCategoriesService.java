package Service;

import DAO.PostRepositoryImpl;
import Interfaces.Localization;
import Interfaces.Repository;
import Model.Post;

import java.util.List;

public class PostCategoriesService {
    public PostCategoriesService() {
    }

    private final PostRepositoryImpl repository = new PostRepositoryImpl();
    public void addCategory(Post model, long category_num){
        repository.addCategory(model,category_num);
    }
    public List<Long> getCategories(Post model){
        return repository.getCategories(model);
    }
    public void addCategoryById(long id, long category_num){
        repository.addCategoryById(id,category_num);
    }
    public List<String> getAll(){
        return repository.categories();
    }
    public String find(long id){
        return repository.getCategory(id);
    }
}
