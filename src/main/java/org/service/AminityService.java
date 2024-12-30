package org.service;

import java.util.List;

import org.model.AminityModel;

public interface AminityService {
	public boolean isAddNewAminity(AminityModel model);
	 public List<AminityModel> getAllAminity();
}
