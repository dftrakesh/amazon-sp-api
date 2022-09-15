package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdersPayload {

   @JsonProperty("Orders")
   private List<Order> orders;

   @JsonProperty("NextToken")
   private String nextToken;

   @JsonProperty("LastUpdatedBefore")
   private String lastUpdatedBefore;

   @JsonProperty("CreatedBefore")
   private String createdBefore;
}