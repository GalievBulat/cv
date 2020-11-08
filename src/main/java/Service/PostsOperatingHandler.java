package Service;

import DAO.PostRepositoryImpl;
import DAO.UserTCRepositoryImpl;
import Interfaces.Repository;
import Model.Post;
import Model.UserTC;

import java.util.List;

public class PostsOperatingHandler{
    private final PostRepositoryImpl repository = new PostRepositoryImpl();
    private final PostCategoriesService service = new PostCategoriesService();
    private final Repository<UserTC> usersRepository = new UserTCRepositoryImpl();

    public List<Post> getChildrenById(long id){
        List<Post> list = repository.getAllChildren(id);
        for(Post post: list){
            post.setCategories(service.getCategories(post));
            setAuthorName(post);
            setParent(post);
        }
        return list;
    }
    private void setAuthorName(Post post){
        post.setAuthor_name(usersRepository.get(post.getAuthorId()).orElseThrow(IllegalArgumentException::new).getName());
    }
    private void setCommentsNum(Post post){
        post.setCommentsNum(repository.getCommentsNum(post));
    }

    private void setParent(Post post){
        if(post.getParentId()!=0){
            Post parent = repository.get(post.getParentId()).orElseThrow(IllegalArgumentException::new);
            post.setParentName(usersRepository.get(parent.getAuthorId()).orElseThrow(IllegalArgumentException::new).getName());
        }
    }
    public List<Post> getRootPosts(){
        List<Post> list = repository.getRootPosts();
        list.remove(0);
        for(Post post: list){
            post.setCategories(service.getCategories(post));
            setAuthorName(post);
            setParent(post);
            setCommentsNum(post);
        }
        return list;
    }
    public long getLast(){
        return repository.maxPost();
    }
    public void add(Post post,List<Long> categories){
        PostRepositoryImpl repository = new PostRepositoryImpl();
        PostCategoriesService service = new PostCategoriesService();
        synchronized (repository.getConnection()){
            repository.add(post);
            long id= getLast();
            for (long category: categories) {
                service.addCategoryById(id,category);
            }
        }
    }
    public Post get(long id){
        Post post = repository.get(id).orElseThrow(IllegalArgumentException::new);
        post.setCategories(service.getCategories(post));
        setAuthorName(post);
        setParent(post);
        setCommentsNum(post);
        return post ;
    }
    public List<Post> getChildren(Post post){
        return getChildrenById(post.getId());
    }
}
