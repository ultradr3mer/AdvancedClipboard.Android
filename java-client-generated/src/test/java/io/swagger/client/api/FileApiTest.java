package io.swagger.client.api;

import io.swagger.client.ApiClient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for FileApi
 */
public class FileApiTest {

    private FileApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(FileApi.class);
    }


    /**
     * Gets an image.
     *
     * 
     */
    @Test
    public void fileTokenFilenameGetTest() {
        String token = null;
        String filename = null;
        // Void response = api.fileTokenFilenameGet(token, filename);

        // TODO: test validations
    }
}
