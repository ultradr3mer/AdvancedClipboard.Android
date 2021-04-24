# FileApi

All URIs are relative to *https://advancedclipboard.azurewebsites.net*

Method | HTTP request | Description
------------- | ------------- | -------------
[**fileTokenFilenameGet**](FileApi.md#fileTokenFilenameGet) | **GET** File/{token}/{filename} | Gets an image.

<a name="fileTokenFilenameGet"></a>
# **fileTokenFilenameGet**
> Void fileTokenFilenameGet(token, filename)

Gets an image.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: http
HttpBasicAuth http = (HttpBasicAuth) defaultClient.getAuthentication("http");
http.setUsername("YOUR USERNAME");
http.setPassword("YOUR PASSWORD");

FileApi apiInstance = new FileApi();
String token = "token_example"; // String | The access token of the image to get.
String filename = "filename_example"; // String | The filename of the image to get.
try {
    Void result = apiInstance.fileTokenFilenameGet(token, filename);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FileApi#fileTokenFilenameGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **token** | **String**| The access token of the image to get. |
 **filename** | **String**| The filename of the image to get. |

### Return type

[**Void**](.md)

### Authorization

[http](../README.md#http)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

