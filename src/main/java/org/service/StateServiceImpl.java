package org.service;

import java.util.List;
import java.util.Optional;

import org.model.DistModel;
import org.model.StateModel;
import org.repository.StateRepository;
import org.repository.StateRepositoryImp;
public class StateServiceImpl implements StateService  {
StateRepository stmtRepo = new StateRepositoryImp();

	public boolean isAddNewState(StateModel model) {
		
		return stmtRepo.isAddNewState(model);
	}

	@Override
	public Optional<List<StateModel>> getAllStates() {
		
		return stmtRepo.getAllStates();
	}

	@Override
	public StateModel getStateByName(String stateName) {
		
		return stmtRepo.getStateByName(stateName);
	}

	@Override
	public boolean isDeleteStateById(String stateName) {
//			System.out.println("StateSecvice class "+stateName);
		return stmtRepo.isDeleteStateById(stateName);
	}

	@Override
	public boolean isUpdateState(String currentName, String newName) {
		// TODO Auto-generated method stub
		return stmtRepo.isUpdateState(currentName, newName);
	}

	@Override
	public boolean isAssociateDistToState(String stateName, String distName) {
		// TODO Auto-generated method stub
		return stmtRepo.isAssociateDistToState(stateName, distName);
	}

	@Override
	public boolean isAddBulkDist(String stateName) {
		// TODO Auto-generated method stub
		return stmtRepo.isAddBulkDist(stateName);
	}

	@Override
	public List<DistModel> getDistListByName(String stateName) {
		// TODO Auto-generated method stub
		return stmtRepo.getDistListByName(stateName);
	}

	public int getStateIdByName(String stateName) {
		// TODO Auto-generated method stub
		return stmtRepo.getStateIdByName(stateName);
	}

	@Override
	public int getDistIdByDistName(String distName) {
		// TODO Auto-generated method stub
		return stmtRepo.getDistIdByDistName(distName);
	}

	
}
