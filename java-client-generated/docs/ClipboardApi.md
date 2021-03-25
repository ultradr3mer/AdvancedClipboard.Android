# ClipboardApi

All URIs are relative to *https://advancedclipboard.azurewebsites.net*

Method | HTTP request | Description
------------- | ------------- | -------------
[**clipboardAuthorizeGet**](ClipboardApi.md#clipboardAuthorizeGet) | **GET** Clipboard/Authorize | 
[**clipboardGet**](ClipboardApi.md#clipboardGet) | **GET** Clipboard | 
[**clipboardPostImagePost**](ClipboardApi.md#clipboardPostImagePost) | **POST** Clipboard/PostImage | 
[**clipboardPostPlainTextPost**](ClipboardApi.md#clipboardPostPlainTextPost) | **POST** Clipboard/PostPlainText | 

<a name="clipboardAuthorizeGet"></a>
# **clipboardAuthorizeGet**
> Void clipboardAuthorizeGet()



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ClipboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: http
HttpBasicAuth http = (HttpBasicAuth) defaultClient.getAuthentication("http");
http.setUsername("YOUR USERNAME");
http.setPassword("YOUR PASSWORD");

ClipboardApi apiInstance = new ClipboardApi();
try {
    Void result = apiInstance.clipboardAuthorizeGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ClipboardApi#clipboardAuthorizeGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**Void**](.md)

### Authorization

[http](../README.md#http)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="clipboardGet"></a>
# **clipboardGet**
> List&lt;ClipboardGetData&gt; clipboardGet()



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ClipboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: http
HttpBasicAuth http = (HttpBasicAuth) defaultClient.getAuthentication("http");
http.setUsername("YOUR USERNAME");
http.setPassword("YOUR PASSWORD");

ClipboardApi apiInstance = new ClipboardApi();
try {
    List<ClipboardGetData> result = apiInstance.clipboardGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ClipboardApi#clipboardGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;ClipboardGetData&gt;**](ClipboardGetData.md)

### Authorization

[http](../README.md#http)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="clipboardPostImagePost"></a>
# **clipboardPostImagePost**
> ClipboardGetData clipboardPostImagePost(file, fileExtension)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ClipboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: http
HttpBasicAuth http = (HttpBasicAuth) defaultClient.getAuthentication("http");
http.setUsername("YOUR USERNAME");
http.setPassword("YOUR PASSWORD");

ClipboardApi apiInstance = new ClipboardApi();
File file = new File("file_example"); // File | 
String fileExtension = "fileExtension_example"; // String | 
try {
    ClipboardGetData result = apiInstance.clipboardPostImagePost(file, fileExtension);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ClipboardApi#clipboardPostImagePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **file** | **File**|  | [optional]
 **fileExtension** | **String**|  | [optional]

### Return type

[**ClipboardGetData**](ClipboardGetData.md)

### Authorization

[http](../README.md#http)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: text/plain, application/json, text/json

<a name="clipboardPostPlainTextPost"></a>
# **clipboardPostPlainTextPost**
> ClipboardGetData clipboardPostPlainTextPost(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ClipboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: http
HttpBasicAuth http = (HttpBasicAuth) defaultClient.getAuthentication("http");
http.setUsername("YOUR USERNAME");
http.setPassword("YOUR PASSWORD");

ClipboardApi apiInstance = new ClipboardApi();
ClipboardPostPlainTextData body = new ClipboardPostPlainTextData(); // ClipboardPostPlainTextData | 
try {
    ClipboardGetData result = apiInstance.clipboardPostPlainTextPost(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ClipboardApi#clipboardPostPlainTextPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ClipboardPostPlainTextData**](ClipboardPostPlainTextData.md)|  | [optional]

### Return type

[**ClipboardGetData**](ClipboardGetData.md)

### Authorization

[http](../README.md#http)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

