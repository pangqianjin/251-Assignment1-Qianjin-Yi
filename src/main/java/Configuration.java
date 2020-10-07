import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    static Map<String, Object> configurations = new HashMap<>();

    static {
        Configuration notePadConf = new Configuration();
        try {
            configurations = notePadConf.getConfigurations();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> getConfigurations() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        InputStream input = new FileInputStream(new File(
                "src/main/resources/conf.yml"));
        return yaml.load(input);
    }

    public static String getAboutContent() {
        return String.valueOf(configurations.get("aboutContent"));
    }

    public static KeyWordsMap getKeyWordsMap() {
        Map syntaxKeyWordsMap = (Map) configurations.get("syntaxKeyWordsMap");
        KeyWordsMap keyWordsMap = new KeyWordsMap();
        keyWordsMap.setLiteralArray((ArrayList<String>) syntaxKeyWordsMap.get("literal"));
        keyWordsMap.setKeywordArray((ArrayList<String>) syntaxKeyWordsMap.get("keyword"));
        keyWordsMap.setPrimitiveTypeArray((ArrayList<String>) syntaxKeyWordsMap.get("primitiveType"));
        return keyWordsMap;
    }

}
