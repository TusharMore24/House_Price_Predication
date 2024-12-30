package org.repository;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.List;
import org.custexe.StateException;
import org.model.DistModel;
import org.model.StateModel;

public class StateRepositoryImp extends DBSTATE implements StateRepository {
	List<StateModel> list ;
    List<DistModel>  distList= new ArrayList<DistModel>();
	@Override
	public boolean isAddNewState(StateModel model) {
		try {
			
			stmt = conn.prepareStatement("insert into statemaster values('0',?)");
			stmt.setString(1, model.getStateName());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;
		} catch (Exception ex) {
			System.out.println("error is" + ex);
		}
		return false;
	}

	@Override
	public Optional<List<StateModel>> getAllStates() {
		try {
			list= new ArrayList<StateModel>();
			stmt = conn.prepareStatement("select * from statemaster");
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new StateModel(rs.getInt(1), rs.getString(2)));
			}
			return list.size() > 0 ? Optional.of(list) : Optional.empty();

		} catch (Exception ex) {
			System.out.println("Error is " + ex);
		}
		return null;
	}

	@Override
	public StateModel getStateByName(String stateName) {
		try {
			stmt = conn.prepareStatement(Query.getStateByName);
			stmt.setString(1, stateName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new StateModel(rs.getInt(1), rs.getString(2));
			} else {
				throw new StateException("State Not found Exception " + stateName);
			}

		} catch (StateException ex) {
			System.out.println("Error is:" + ex.getErrorMsg());
			return null;
		} catch (SQLException ex) {
			System.out.println("Error is" + ex);
			return null;
		}

	}

	@Override
	public boolean isDeleteStateById(String stateName) {
		try {
			int stateId = this.getStateIdByName(stateName);
			if (stateId != -1) {
				stmt = conn.prepareStatement("delete from statemaster where stateId=?");
				stmt.setInt(1, stateId);
				int value = stmt.executeUpdate();
				return value > 0 ? true : false;
			} else {
				return false;
			}

		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return false;
		}

	}

	@Override
	public int getStateIdByName(String stateName) {
		try {
			stmt = conn.prepareStatement("select stateid from statemaster where stateName=?");
			stmt.setString(1, stateName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}
		} catch (Exception ex) {
			return -1;
		}

	}

	@Override
	public boolean isUpdateState(String currName, String newName) {
		try {
			int stateId = this.getStateIdByName(currName);
			if (stateId != -1) {
				stmt = conn.prepareStatement("UPDATE statemaster SET statename = ? WHERE stateid = ?");
				stmt.setString(1, newName);
				stmt.setInt(2, stateId);
				int value = stmt.executeUpdate();

				if (value > 0) {
					System.out.println("Record updated successfully. ID: " + stateId);
					return true;
				} else {
					System.out.println("No rows affected. Update failed.");
					return false;
				}
			} else {
				System.out.println("State not found for updating.");
				return false;
			}
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return false;
		}
	}

	@Override
	public boolean isAssociateDistToState(String stateName, String distName) {
		try {
			cstmt = conn.prepareCall("{call savedist(?,?)}");
			cstmt.setString(1, stateName);
			cstmt.setString(2, distName);
			boolean b = cstmt.execute();
			return !b;
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return false;
		}
	}

	@Override
	public boolean isAddBulkDist(String stateName) {
		try {
			boolean b = false;
			FileReader fr = new FileReader("src\\main\\resources\\dist.txt");
			BufferedReader br = new BufferedReader(fr);
			String distName;

			while ((distName = br.readLine()) != null) {
				cstmt = conn.prepareCall("{call savedist(?,?)}");
				cstmt.setString(1, stateName);
				cstmt.setString(2, distName);
				b = cstmt.execute();

			}
			return !b;
		} catch (Exception ex) {

			return false;
		}

	}

	@Override
	public List<DistModel> getDistListByName(String stateName) {
		try {
			stmt=conn.prepareStatement("select d.distname,d.distid from districtmaster d inner join state_dist_join stj on d.distid=stj.distid inner join statemaster sm on sm.stateid=stj.stateid where sm.statename=?");
			stmt.setString(1, stateName);
			rs=stmt.executeQuery();
			while(rs.next()) {
				DistModel model= new DistModel(rs.getString(1),rs.getInt(2));
				this.distList.add(model);
			}
			if(this.distList.size()>0) {
				return distList;
			}
			else {
				throw new StateException("State Not found "+stateName);
			}
		}
	
		catch(SQLException ex) {
			System.out.println("Error is"+ex);
			return null;
		}
		catch(StateException ex) {
			System.out.println(ex.getErrorMsg());
			return null;
		}
		
	}

	@Override
	public int getDistIdByDistName(String distName) {
		try {
			stmt=conn.prepareStatement("Select distid from districtmaster where distname=?");
			stmt.setString(1,distName);
			rs=stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			else {
				return 0;
			}
		}
		catch(Exception ex) {
			return 0;
		}
		
	}

}
