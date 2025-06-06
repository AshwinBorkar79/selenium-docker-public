package com.AB.util;

import com.AB.tests.vendorportal.model.VendorPortalTestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
   Utility class to read data from a JSON file & convert that into a Java Object (of type=java record) --> VendorPortalTestData
   The conversion is done using Jackson ObjectMapper
  */

public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();  //converts input stream to a java object

    public static <T> T getTestData(String filePath, Class<T> type) throws IOException
    {
        try (InputStream stream = ResourceLoader.getResource(filePath)) //try block closes the stream automatically
        {
            return mapper.readValue(stream, type); //this is where the mapping is done between file & object
        }
        catch (IOException e)
        {
            log.error("Error reading JSON file: " + filePath, e);
        }
        return null;
    }

}
