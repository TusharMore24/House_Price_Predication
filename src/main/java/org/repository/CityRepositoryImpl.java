package org.repository;

import java.util.ArrayList;
import java.util.List;

import org.model.AminityModel;
import org.model.CityModel;
import org.model.PropertyModel;
import org.model.WardModel;

public class CityRepositoryImpl extends DBSTATE implements CityRepository {
  boolean flag=false;
  List<CityModel> cityList;
  List<WardModel> wardList;
	@Override
	public boolean isAddNewCity(CityModel model) {
		try {
			cstmt = conn.prepareCall("{call saveCity(?,?,?)}");
			cstmt.setString(1, model.getCityName());
			cstmt.setInt(2, model.getStateId());
			cstmt.setInt(3, model.getId());
			boolean b = cstmt.execute();
			return !b;
		} catch (Exception ex) {
      System.out.println("Error is "+ex);
			return false;
		}

	}

	@Override
	public int getCityIdByCityName(String cityName,int stateId,int distId) {
		try {
			 stmt=conn.prepareStatement("select cm.cityid from citymaster cm inner join city_dist_join cdj on cdj.cityid=cm.cityid where cm.cityname=? and cdj.stateid=? and cdj.distid=?");
			 stmt.setString(1, cityName);
			 stmt.setInt(2, stateId);
			 stmt.setInt(3, distId);
			 rs=stmt.executeQuery();
			 if(rs.next()) {
				 return rs.getInt(1);
				 
			 }
			 else {
				 return 0;
			 }
			 
		}
		catch (Exception ex) 
		{
			System.out.println("Error is"+ex);
			return 0;
		}
		
	}

	@Override
	public boolean isAddNewWard(WardModel wardModel) {
		try {
			cstmt=conn.prepareCall("{call saveWard(?,?,?)}");  
			cstmt.setString(1,wardModel.getWardName());
			cstmt.setString(2, wardModel.getWardPinCode());
			cstmt.setInt(3, wardModel.getCityId());
			boolean b=cstmt.execute();
			return !b;
		}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
		}
		return false;
	}

	@Override
	public List<CityModel> getAllCities(int stateid,int distid) {
		cityList= new ArrayList<CityModel>();
		try {
			stmt= conn.prepareStatement("select c.cityname,c.cityid from citymaster c inner join city_dist_join cdj on c.cityid=cdj.cityid inner join districtmaster d on d.distid=cdj.distid where cdj.stateid=? and d.distid=?");
			stmt.setInt(1, stateid);
			stmt.setInt(2, distid);
			rs=stmt.executeQuery();
			while(rs.next()) {
			
				cityList.add(new CityModel(rs.getInt(2),rs.getString(1)));
			}
			return cityList.size()>0?cityList:null;
		}catch(Exception ex) {
			System.out.println("Error is"+ex);
			return null;
		}
		
	 
	}

	@Override
	public List<WardModel> getAllWardByCityName(String cityName) {
		try {
			wardList= new ArrayList<WardModel>();
			stmt= conn.prepareStatement(" select wardname from wardmaster wm inner join  city_ward_join cwj on wm.wardid=cwj.wardid  inner join citymaster cm on cm.cityid=cwj.cityid where cm.cityname=?");
			stmt.setString(1, cityName);
			rs=stmt.executeQuery();
			while(rs.next()) {
				wardList.add(new WardModel(rs.getString(1),0,null));
			}
			return wardList.size()>0?wardList:null;
		}
		catch(Exception e) {
			System.out.println("Error is"+e);
			return null;
		}
		
	}

	@Override
	public int getWardIdByName(String wardName) {
		try {
			stmt=conn.prepareStatement("Select wardId from wardmaster where wardName=?");
			stmt.setString(1, wardName);
		   rs=stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			else {
				return 0;
			}
		}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
			return -1;
			
		}
		
	}
	
	public int getPropIdByName(String propName) {
		int propId=0;
		try {
			stmt=conn.prepareStatement("Select pid from propertymaster where name=?");
			stmt.setString(1, propName);
			rs=stmt.executeQuery(); 
			if(rs.next()) {
//				System.out.println(rs.getInt(1));
				return rs.getInt(1);
			}
			else {
				return -1;
			}
		}
		catch(Exception ex) {
//			  System.out.println("Error is "+ex);
			return -1;
		}
	}

	@Override
	public boolean isAddNewProperty(PropertyModel model) {
		WardModel wardModel= model.getModel();
		int wardId= wardModel.getWardId();
		int propId= model.getPropId();
		String propName=model.getPropName();
		String propAddress=model.getPropAddress();
		int sqFeetArea=model.getSqFeetArea();
		int propPrice=model.getPropPrice();
		try{  
			stmt=conn.prepareStatement("insert into propertymaster values('0',?,?,?,?,?)");
			stmt.setString(1, propName);
			stmt.setString(2, propAddress);
			stmt.setInt(3, propPrice);
			stmt.setInt(4, sqFeetArea);
			stmt.setInt(5, wardId);
			int value=stmt.executeUpdate();
			
			if(value>0) {
				List<AminityModel> aminityList=model.getList(); 
				int pid=this.getPropIdByName(propName);
				if(pid!=-1) {
					for(AminityModel amModel:aminityList) {  
						stmt=conn.prepareStatement("insert into aminities_prop_join values(?,?,?)");
						stmt.setInt(1, amModel.getAmId());
						stmt.setInt(2,pid);
						stmt.setInt(3, amModel.getAmPrice());
						 value=stmt.executeUpdate();
						 if(value>0) {
							 flag=true;
						 }
						propPrice=propPrice+amModel.getAmPrice();   
			     	}
					stmt=conn.prepareStatement("update propertymaster set price=? where pid=?");
					stmt.setInt(1, propPrice);
					stmt.setInt(2, pid);
					value=stmt.executeUpdate();
					if(value>0) {
						flag=true;
					}
			
				}
			}
			else {
				return false;
			}
		   
		System.out.println("\n Total Property price is "+propPrice);
		}
		catch(Exception ex) {
			System.out.println("Error is:"+ex);
			return false;
		}
		return flag;
	}

}
