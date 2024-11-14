package org.todo.properties;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class HostProperties {

    InputStream inputStream = new FileInputStream("properties.yml");
    Yaml yaml = new Yaml(new Constructor(H.class));
    Student data = yaml.load(inputStream);
}
