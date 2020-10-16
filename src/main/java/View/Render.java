package View;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class Render {
    private static final String directory ="C:\\Users\\Kakad\\Documents\\cv\\src\\main\\resources\\templatetes";
    private final Configuration cfg;
    public Render() throws IOException{
        cfg = new Configuration(Configuration.VERSION_2_3_27);
        cfg.setDirectoryForTemplateLoading(new File("C:\\Users\\Kakad\\Documents\\cv\\src\\main\\resources\\templatetes"));
        cfg.setDefaultEncoding("UTF-8");

    }
    public void renderMap(String file, Map<String,Object> root, Writer writer) throws IOException, TemplateException {
        Template temp = cfg.getTemplate("reg.ftl");
        temp.process(root, writer);
    }
}
