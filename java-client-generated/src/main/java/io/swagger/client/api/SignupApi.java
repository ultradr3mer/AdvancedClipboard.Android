package io.swagger.client.api;

import io.swagger.client.CollectionFormats.*;

import rx.Observable;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import io.swagger.client.model.SignupPostData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SignupApi {
  /**
   * 
   * 
   * @param body  (optional)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("Signup")
  Observable<Void> signupPost(
                    @retrofit2.http.Body SignupPostData body    
  );

}
