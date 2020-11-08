package View;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Render {
    //private static final String directory ="C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\cv\\templates";
    private static final String directory ="C:\\Users\\Kakad\\Documents\\cv\\templates";
    private final Configuration cfg;

    public Render(){
        cfg = new Configuration(Configuration.VERSION_2_3_30);
        try {
            cfg.setDefaultEncoding("UTF-8");
            cfg.setDirectoryForTemplateLoading(new File(directory));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void renderMap(String file, Map<String,Object> root, Writer writer){
        try {
            Template temp = cfg.getTemplate(file);
            temp.process(root, writer);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
