package io.github.dft.amazon.model.orders.v0;

import lombok.Data;
import java.util.List;

@Data
public class OrdersList {

   private List<Order> ordersList;
   private String NextToken;
   private String lastUpdatedBefore;
   private String createdBefore;
}