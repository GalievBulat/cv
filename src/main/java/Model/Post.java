package Model;

import Interfaces.Localization;
import Service.PostCategoriesService;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Post {
    private long id;
    private long authorId;
    private String message;
    private Time time;
    private Date date;

    //
    private long commentsNum = 0;
    private long parentPostId;
    private String parentName;
    private List<Long> categories;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getAuthor_name() {
        return authorName;
    }

    public void setAuthor_name(String author_name) {
        this.authorName = author_name;
    }

    private String authorName;

    private Post() {
    }

    public long getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(long commentsNum) {
        this.commentsNum = commentsNum;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", message='" + message + '\'' +
                ", time=" + time +
                ", date=" + date +
                ", commentsNum=" + commentsNum +
                ", parentPostId=" + parentPostId +
                ", parentName='" + parentName + '\'' +
                ", categories=" + categories +
                ", authorName='" + authorName + '\'' +
                '}';
    }

    public String toJSON(Localization localization) {
        PostCategoriesService service = new PostCategoriesService();
        List<String> categoryNames = new ArrayList<>();
        for (long category: categories){
            categoryNames.add("\"" +localization.localizeCategory(service.find(category))+ "\"");
        }
        return "{" +
                "\"id\":" + id +
                ", \"author_id\":" + authorId +
                ", \"author_name\":\"" + authorName + '\"' +
                ", \"message\":\"" + message + '\"' +
                ", \"comments_num\":" + commentsNum +
                ", \"time\":\"" + time + '\"'+
                ", \"date\":\"" + date +'\"'+
                ", \"parent_id\":" + parentPostId +
                ", \"parent_name\":\"" + parentName + '\"' +
                ", \"categories\":" + categoryNames +
                '}';
    }

    public long getId() {
        return id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public String getMessage() {
        return message;
    }

    public Time getTime() {
        return time;
    }

    public Date getDate() {
        return date;
    }

    public long getParentId() {
        return parentPostId;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public static class Builder {
        private Post post;
        public Builder(){
            post = new Post();
        }
        public Builder id(long id) {
            post.id = id;
            return this;
        }

        public Builder authorId(long authorId) {
            post.authorId = authorId;
            return this;
        }

        public Builder message(String message) {
            post.message = message;
            return this;
        }

        public Builder time(Time time) {
            post.time = time;
            return this;
        }

        public Builder date(Date date) {
            post.date = date;
            return this;
        }

        public Builder parentId(long parentId) {
            post.parentPostId = parentId;
            return this;
        }
        public Post build(){
            return post;
        }
    }
}
