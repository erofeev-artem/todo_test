package org.todo.properties;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class PropertiesConverter {

    public <T> T ymlToObject(Class<T> type, String path) {
        Constructor constructor = new Constructor(type, new LoaderOptions());
        Yaml yaml = new Yaml(constructor);
        InputStream inputStream = getClass().getResourceAsStream(path);
        return yaml.loadAs(inputStream, type);
    }
}
