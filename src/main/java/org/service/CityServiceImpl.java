package org.service;

import java.util.List;

import org.model.CityModel;
import org.model.PropertyModel;
import org.model.WardModel;
import org.repository.CityRepository;
import org.repository.CityRepositoryImpl;

public class CityServiceImpl implements CityService  {
CityRepository cityRepo=new CityRepositoryImpl() ;
	@Override
	public boolean isAddNewCity(CityModel model) {
		// TODO Auto-generated method stub
		return cityRepo.isAddNewCity(model);
	}
	@Override
	public int getCityIdByCityName(String cityName,int stateId,int distId) {
		// TODO Auto-generated method stub
		return cityRepo.getCityIdByCityName(cityName, stateId, distId);
	}
	@Override
	public boolean isAddNewWard(WardModel wardModel) {
		 
		return cityRepo.isAddNewWard(wardModel);
	}
	@Override
	public List<CityModel> getAllCities(int stateid, int distid) {
		// TODO Auto-generated method stub
		return cityRepo.getAllCities(stateid, distid);
	}
	@Override
	public List<WardModel> getAllWardByCityName(String cityName) {
		// TODO Auto-generated method stub
		return cityRepo.getAllWardByCityName(cityName);
	}
	@Override
	public int getWardIdByName(String wardName) {
		// TODO Auto-generated method stub
		return cityRepo.getWardIdByName(wardName);
	}
	@Override
	public boolean isAddNewProperty(PropertyModel model) {
		// TODO Auto-generated method stub
		return cityRepo.isAddNewProperty(model);
	}

}
