package DAO;

import Interfaces.JDBCTemplate;
import Interfaces.Repository;
import Interfaces.RowMapper;
import Model.Post;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PostRepositoryImpl implements Repository<Post> {
    private final JDBCTemplate<Post> jdbc = new JDBCTemplateGenericImpl<>();

    public Connection getConnection(){
        return ConnectionHandler.getConnection();
    }
    //language=sql
    private final static String updateQuery = "UPDATE post SET author_id = ?, message = ?, time = ?,date = ?,parent_id=? WHERE id = ?";

    //language=sql
    private final static String deleteQuery = "DELETE from post where id = ?";

    //language=sql
    private final static String addQuery = "INSERT into post (author_id, message,parent_id ) VALUES (?,?,?)";

    //language=sql
    private final static String findQuery = "SELECT * FROM post WHERE id = ?";

    //language=sql
    private final static String selectMax = "SELECT MAX(id) as id FROM post";

    //language=sql
    private final static String getCommentsNum = "SELECT count(id) as num FROM post where parent_id = ?";

    //language=sql
    private final static String categoryAddingQuery = "INSERT into posts_category (post_id, category_id ) VALUES (?,?)";

    //language=sql
    private final static String categoryGettingQuery = "SELECT * FROM posts_category WHERE post_id = ?";

    //language=sql
    private final static String getAllCategories = "SELECT category.category FROM category";

    //language=sql
    private final static String findCategory = "SELECT category.category FROM category where id = ?";

    private final static String recursivelyGetAllQuery=
    //language=sql
    "WITH RECURSIVE r AS (SELECT * from post where post.parent_id IS NULL UNION" +
            " SELECT post.parent_id,post.author_id,post.message,post.time,post.date,post.parent_id " +
            "from post join r on post.parent_id = r.id) select * from r;";
    private final static String recursivelyGetAllChildrenQuery=
            //language=sql
            "WITH RECURSIVE r AS (SELECT * from post where post.parent_id = ? UNION" +
                    " SELECT post.parent_id,post.author_id,post.message,post.time,post.date,post.parent_id " +
                    "from post join r on post.parent_id = r.id) select * from r;";
    private final static String getRootPostsQuery=
            //language=sql
            "SELECT * from post where post.parent_id=0";
    @Override
    public void update(Post model) {
        jdbc.update(updateQuery,model.getAuthorId(),model.getMessage(),
                model.getTime(),model.getDate(),model.getParentId(),model.getId());
    }

    @Override
    public void delete(Post model) {
        jdbc.update(deleteQuery,model.getId());
    }
    public List<String> categories() {
        final JDBCTemplate<String> jdbc = new JDBCTemplateGenericImpl<>();
        return jdbc.query(getAllCategories,(ResultSet rS)-> {
            try {
                return rS.getString("category");
            } catch (SQLException throwables) {
                throw new RuntimeException(throwables);
            }
        });
    }
    public String getCategory(long id) {
        final JDBCTemplate<String> jdbc = new JDBCTemplateGenericImpl<>();
        return jdbc.query(findCategory,(ResultSet rS)-> {
            try {
                return rS.getString("category");
            } catch (SQLException throwables) {
                throw new RuntimeException(throwables);
            }
        },id).get(0);
    }
    public long maxPost() {
        final JDBCTemplate<Long> jdbc = new JDBCTemplateGenericImpl<>();
        return jdbc.query(selectMax,(ResultSet rS)-> {
            try {
                return rS.getLong("id");
            } catch (SQLException throwables) {
                throw new RuntimeException(throwables);
            }
        }).get(0);
    }

    @Override
    public void add(Post model) {
        jdbc.update(addQuery,model.getAuthorId(),model.getMessage(),model.getParentId());
    }
    public void addCategory(Post model,long category_id) {
        jdbc.update(categoryAddingQuery,model.getId(),category_id);
    }
    public void addCategoryById(long id,long category_id) {
        jdbc.update(categoryAddingQuery,id,category_id);
    }
    public List<Long>  getCategories(Post model) {
        final JDBCTemplate<Long> jdbc = new JDBCTemplateGenericImpl<>();
        return jdbc.query(categoryGettingQuery, (ResultSet rS)-> {
            try {
                return rS.getLong("category_id");
            } catch (SQLException throwables) {
                throw new RuntimeException(throwables);
            }
        },model.getId());
    }
    public long getCommentsNum(Post post){
        final JDBCTemplate<Long> jdbc = new JDBCTemplateGenericImpl<>();
        return jdbc.query(getCommentsNum, (ResultSet rS)-> {
            try {
                return rS.getLong("num");
            } catch (SQLException throwables) {
                throw new RuntimeException(throwables);
            }
        },post.getId()).get(0);
    }

    @Override
    public Optional<Post> get(long num) {
        List<Post> list = jdbc.query(findQuery,new PostMapper(),num);
        if (list.isEmpty())
            return Optional.empty();
        else {
            return Optional.of(list.get(0));
        }
    }
    public List<Post> getAll(){
        return jdbc.query(recursivelyGetAllQuery,new PostMapper());
    }
    public List<Post> getAllChildren(long id){
        return jdbc.query(recursivelyGetAllChildrenQuery,new PostMapper(),id);
    }
    public List<Post> getRootPosts(){
        return jdbc.query(getRootPostsQuery,new PostMapper());
    }
}

