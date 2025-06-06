package com.AB.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/*
    A simple utility to read files.
    First the file is searched in the classpath, if found, it is used.
    If not, then the file is searched in the entire project.
 */
public class ResourceLoader {

    public static final Logger log = LoggerFactory.getLogger(ResourceLoader.class);

    public static InputStream getResource(String path) throws IOException {
        log.info("Loading resource from classpath: " + path);
        InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        if (Objects.nonNull(stream)) {
            return stream;
        }
        return Files.newInputStream(Path.of(path));
    }

}
