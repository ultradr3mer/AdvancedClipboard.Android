/*
 * AdvancedClipboard
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 0.0.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.UUID;
/**
 * ClipboardGetData
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-01-09T09:04:38.769Z[GMT]")
public class ClipboardGetData {
  @SerializedName("id")
  private UUID id = null;

  @SerializedName("plainTextContent")
  private Object plainTextContent = null;

   /**
   * Get id
   * @return id
  **/
  @Schema(description = "")
  public UUID getId() {
    return id;
  }

   /**
   * Get plainTextContent
   * @return plainTextContent
  **/
  @Schema(description = "")
  public Object getPlainTextContent() {
    return plainTextContent;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClipboardGetData clipboardGetData = (ClipboardGetData) o;
    return Objects.equals(this.id, clipboardGetData.id) &&
        Objects.equals(this.plainTextContent, clipboardGetData.plainTextContent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, plainTextContent);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClipboardGetData {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    plainTextContent: ").append(toIndentedString(plainTextContent)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}