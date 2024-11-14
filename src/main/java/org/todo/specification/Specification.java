package org.todo.specification;

import org.todo.properties.PropertiesConverter;

public class Specification<T> {

    protected final T properties;

    public Specification(Class<T> properties, String propertiesPath) {
        this.properties = new PropertiesConverter().ymlToObject(properties, propertiesPath);
    }
}
