package DAO;

import Interfaces.RowMapper;
import Model.Post;
import Model.UserTC;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class PostMapper implements RowMapper<Post> {
    @Override
    public Post getInstance(ResultSet resultSet) {
        try {
            long id = resultSet.getLong("id");
            long authorId = resultSet.getLong("author_id");
            String message =  resultSet.getString("message");
            Time time = resultSet.getTime("time");
            Date date = resultSet.getDate("date");
            long parentId =  resultSet.getLong("parent_id");
            return new Post.Builder()
                    .id(id)
                    .authorId(authorId)
                    .message(message)
                    .time(time)
                    .date(date)
                    .parentId(parentId)
                    .build();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
