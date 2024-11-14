package org.todo.specification;

import org.todo.properties.PropertiesConverter;

public class TodoSpecification<T> {

    protected final T todoProperties;

    public TodoSpecification(Class<T> properties, String propertiesPath) {
        this.todoProperties = new PropertiesConverter().ymlToObject(properties, propertiesPath);
    }
}
