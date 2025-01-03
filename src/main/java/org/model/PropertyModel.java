package org.model;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyModel {
	private WardModel model= new WardModel();// has a relation ward and property
	private int propId;
	private String propName;
	private String propAddress;
	private int sqFeetArea;
	private int propPrice;
	private List<AminityModel> list;

}
