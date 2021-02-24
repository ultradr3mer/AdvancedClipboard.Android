package io.swagger.client.api;

import io.swagger.client.CollectionFormats.*;

import rx.Observable;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import io.swagger.client.model.ClipboardGetData;
import io.swagger.client.model.ClipboardPostPlainTextData;
import io.swagger.client.model.ClipboardPostResultData;

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
   * @param body  (optional)
   * @return Call&lt;ClipboardPostResultData&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("Clipboard/PostPlainText")
  Observable<ClipboardPostResultData> clipboardPostPlainTextPost(
                    @retrofit2.http.Body ClipboardPostPlainTextData body    
  );

}
