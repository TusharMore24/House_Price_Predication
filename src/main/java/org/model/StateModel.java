package org.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StateModel {
     private int stateId;
     private String stateName;
     
//     public StateModel(){
//    	 
//       }
//    public StateModel(int id,String name){
//    	 this.stateId=id;
//    	 this.stateName=name;
//     }
//	 public int getStateId() {
//		return stateId;
//	}
//	public void setStateId(int stateId) {
//		this.stateId = stateId;
//	}
//	public String getStateName() {
//		return stateName;
//	}
//	public void setStateName(String stateName) {
//		this.stateName = stateName;
//	}
//	public String toString() {
//		return "["+stateId+","+stateName+"]";
//	}
//	public boolean equals(Object obj) {
//		StateModel model=(StateModel)obj;
//		
//		if(model.getStateId()==this.stateId && model.getStateName()==this.stateName) {
//			return true;
//		}else {
//		return false;
//		}
//	}
//     
//	public int hashCode() {
//		return stateId*10000;
//	}
}
