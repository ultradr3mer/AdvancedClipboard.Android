# SignupApi

All URIs are relative to *https://advancedclipboard.azurewebsites.net*

Method | HTTP request | Description
------------- | ------------- | -------------
[**signupPost**](SignupApi.md#signupPost) | **POST** Signup | 

<a name="signupPost"></a>
# **signupPost**
> Void signupPost(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SignupApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: http
HttpBasicAuth http = (HttpBasicAuth) defaultClient.getAuthentication("http");
http.setUsername("YOUR USERNAME");
http.setPassword("YOUR PASSWORD");

SignupApi apiInstance = new SignupApi();
SignupPostData body = new SignupPostData(); // SignupPostData | 
try {
    Void result = apiInstance.signupPost(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignupApi#signupPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SignupPostData**](SignupPostData.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[http](../README.md#http)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: Not defined

