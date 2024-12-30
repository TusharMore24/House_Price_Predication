package org.repository;
import org.model.DistModel;
import org.model.StateModel;
import java.util.*;
import com.mysql.cj.xdevapi.Statement;
public interface StateRepository {
     public boolean isAddNewState(StateModel model) ;
     public Optional<List<StateModel>> getAllStates();
     public StateModel getStateByName(String stateName);
     public boolean isDeleteStateById(String stateName);
     public int getStateIdByName(String stateName);
     public boolean isUpdateState(String currentName,String newName );
     public boolean isAssociateDistToState(String stateName,String distName);
     public boolean isAddBulkDist(String stateName);
	 public List<DistModel> getDistListByName(String stateName);
   	 public int getDistIdByDistName(String distName);
}