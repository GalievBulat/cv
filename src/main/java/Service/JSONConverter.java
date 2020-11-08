package Service;

import Interfaces.Localization;
import Model.Post;

import java.util.List;

public class JSONConverter {
    public String[] convertJSON(String json){
        return json.replaceAll("(\":\")+",",").replaceAll("[}\"{]+","").split(",");
    }
    public String postsListToJSON(List<Post> postList,Localization localization){
        StringBuilder json = new StringBuilder("[");
        for (Post post: postList){
            json.append(post.toJSON(localization));
            json.append(",");
        }
        if (json.length() > 1)
            json.deleteCharAt(json.length() - 1);
        json.append("]");
        return json.toString();
    }
}
