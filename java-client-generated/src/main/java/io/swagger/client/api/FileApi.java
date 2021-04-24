package io.swagger.client.api;//retrofit2

import io.swagger.client.CollectionFormats.*;

import rx.Observable;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface FileApi {
  /**
   * Gets an image.
   * 
   * @param token The access token of the image to get. (required)
   * @param filename The filename of the image to get. (required)
   * @return Call&lt;Void&gt;
   */
  @GET("File/{token}/{filename}")
  Observable<Void> fileTokenFilenameGet(
            @retrofit2.http.Path("token") String token            ,         @retrofit2.http.Path("filename") String filename            
  );

}
