package io.swagger.client.api;

import io.swagger.client.CollectionFormats.*;

import rx.Observable;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import io.swagger.client.model.ClipboardGetData;
import io.swagger.client.model.ClipboardPostPlainTextData;
import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ClipboardApi {
  /**
   * 
   * 
   * @return Call&lt;Void&gt;
   */
  @GET("Clipboard/Authorize")
  Observable<Void> clipboardAuthorizeGet();
    

  /**
   * 
   * 
   * @return Call&lt;List&lt;ClipboardGetData&gt;&gt;
   */
  @GET("Clipboard")
  Observable<List<ClipboardGetData>> clipboardGet();
    

  /**
   * 
   * 
   * @param file  (optional)
   * @param fileExtension  (optional)
   * @return Call&lt;ClipboardGetData&gt;
   */
  @retrofit2.http.Multipart
  @POST("Clipboard/PostImage")
  Observable<ClipboardGetData> clipboardPostImagePost(
                        @retrofit2.http.Part("file\"; filename=\"file") RequestBody file,     @retrofit2.http.Query("fileExtension") String fileExtension                
  );

  /**
   * 
   * 
   * @param body  (optional)
   * @return Call&lt;ClipboardGetData&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("Clipboard/PostPlainText")
  Observable<ClipboardGetData> clipboardPostPlainTextPost(
                    @retrofit2.http.Body ClipboardPostPlainTextData body    
  );

}
