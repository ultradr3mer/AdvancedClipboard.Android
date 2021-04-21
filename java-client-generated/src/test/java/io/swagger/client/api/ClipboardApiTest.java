package io.swagger.client.api;

import io.swagger.client.ApiClient;
import io.swagger.client.model.ClipboardGetData;
import io.swagger.client.model.ClipboardPostPlainTextData;
import java.io.File;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for ClipboardApi
 */
public class ClipboardApiTest {

    private ClipboardApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(ClipboardApi.class);
    }


    /**
     * 
     *
     * 
     */
    @Test
    public void clipboardAuthorizeGetTest() {
        // Void response = api.clipboardAuthorizeGet();

        // TODO: test validations
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void clipboardDeleteTest() {
        UUID id = null;
        // Void response = api.clipboardDelete(id);

        // TODO: test validations
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void clipboardGetTest() {
        // List<ClipboardGetData> response = api.clipboardGet();

        // TODO: test validations
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void clipboardPostFilePostTest() {
        File file = null;
        String fileExtension = null;
        // ClipboardGetData response = api.clipboardPostFilePost(file, fileExtension);

        // TODO: test validations
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void clipboardPostNamedFilePostTest() {
        File file = null;
        String fileName = null;
        // ClipboardGetData response = api.clipboardPostNamedFilePost(file, fileName);

        // TODO: test validations
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void clipboardPostPlainTextPostTest() {
        ClipboardPostPlainTextData body = null;
        // ClipboardGetData response = api.clipboardPostPlainTextPost(body);

        // TODO: test validations
    }
}
