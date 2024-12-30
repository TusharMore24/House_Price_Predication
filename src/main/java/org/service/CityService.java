package org.service;

import java.util.List;

import org.model.CityModel;
import org.model.PropertyModel;
import org.model.WardModel;

public interface CityService {
  public  boolean  isAddNewCity(CityModel model);
  public int getCityIdByCityName(String cityName,int stateId,int distId);
  public boolean isAddNewWard(WardModel wardModel);
  public List<CityModel> getAllCities(int stateid,int distid);
  public List<WardModel> getAllWardByCityName(String cityName);
  public int getWardIdByName(String wardName);
  public boolean isAddNewProperty(PropertyModel model);
  
}
