package io.github.dft.amazon.fulfillmentinbound.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper = true)
public class InboundShipmentList<InboundShipmentInfo> extends ArrayList<InboundShipmentInfo> {

}
