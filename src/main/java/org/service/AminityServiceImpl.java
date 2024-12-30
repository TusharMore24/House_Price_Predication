package org.service;

import java.util.List;

import org.model.AminityModel;
import org.repository.AminityRepository;
import org.repository.AminityRepositoryImpl;

public class AminityServiceImpl implements AminityService  {
	AminityRepository aminityRepo = new AminityRepositoryImpl();
@Override
	public boolean isAddNewAminity(AminityModel model) {
		
	   return aminityRepo.isAddNewAminity(model);
	}
@Override
public List<AminityModel> getAllAminity() {

	return aminityRepo.getAllAminity();
}

}
