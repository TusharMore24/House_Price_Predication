package org.service;

import java.util.List;
import java.util.Optional;

import org.model.DistModel;
import org.model.StateModel;

public interface StateService {
	public boolean isAddNewState(StateModel model);
	public Optional<List<StateModel>> getAllStates();
	public StateModel getStateByName(String stateName);
	public boolean isDeleteStateById(String stateName);
	public boolean isUpdateState(String currentName,String newName );
	public boolean isAssociateDistToState(String stateName,String distName);
	public boolean isAddBulkDist(String stateName);
    public List<DistModel> getDistListByName(String stateName);
    public int getStateIdByName(String stateName);
    public int getDistIdByDistName(String distName);

	
}
