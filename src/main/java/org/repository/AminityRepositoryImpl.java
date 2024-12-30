package org.repository;

import java.util.ArrayList;
import java.util.List;

import org.model.AminityModel;

public class AminityRepositoryImpl extends DBSTATE  implements AminityRepository {
		List<AminityModel> list;
	@Override
	public boolean isAddNewAminity(AminityModel model) {
		try 
		{
			stmt=conn.prepareStatement("insert into  aminities values('0',?) ");
			stmt.setString(1, model.getAmName());
			int value=stmt.executeUpdate();
			return value>0?true:false;	
		}
		catch(Exception ex) {
			System.out.println("AminityRepositoryImpl Error is "+ex);
		}
		return false;
	}

	@Override
	public List<AminityModel> getAllAminity() {
		try {
			list=new ArrayList<AminityModel>();
			stmt=conn.prepareStatement("select * from aminities order by amid  asc");
		    rs= stmt.executeQuery();
		    while(rs.next()) {
		    	AminityModel model= new AminityModel();
		    	model.setAmId(rs.getInt(1));
		    	model.setAmName(rs.getString(2)); 
		    	list.add(model);
		    }
		    return list.size()>0?list:null;
		}
		catch(Exception e) {
			return null;
		}
	}

}
