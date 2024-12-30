package org.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class CityModel extends DistModel {
	private int cityId;
	private String cityName;
//	private int StateId;
//	private int distId;
	

}
