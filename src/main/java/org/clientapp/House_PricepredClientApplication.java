package org.clientapp;

import java.util.*;

import org.model.AminityModel;
import org.model.CityModel;
import org.model.DistModel;
import org.model.PropertyModel;
import org.model.StateModel;
import org.model.WardModel;
import org.service.AminityService;
import org.service.AminityServiceImpl;
import org.service.CityService;
import org.service.CityServiceImpl;
import org.service.StateService;
import org.service.StateServiceImpl;

public class House_PricepredClientApplication {
	static int count = 0;

	public static void main(String args[]) {

		StateService stateService = new StateServiceImpl();
		CityService cityService = new CityServiceImpl();
		AminityService aminityService = new AminityServiceImpl();
		do {
			Scanner xyz = new Scanner(System.in);
			System.out.println("1:  AddNew State");
			System.out.println("2:  View All State");
			System.out.println("3:  enter state name for  search");
			System.out.println("4:  Delete State by Id");
			System.out.println("5:  Update State by name");
			System.out.println("6:  Add new Single distric in state");
			System.out.println("7:  Add Bulk distric on table");
			System.out.println("8:  Add new city");
			System.out.println("9:  Add new Ward");
			System.out.println("10: Add New Amenities");
			System.out.println("11: Add New Property");
			System.out.println("50:  Exist");
			System.out.println("Enter Your Choice");
			int choice = xyz.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter state name");
				xyz.nextLine();
				String stateName = xyz.nextLine();
				System.out
						.println(stateService.isAddNewState(new StateModel(0, stateName)) ? "State added successfully.."
								: "State NOT added successfully..");
				break;
			case 2:
				Optional<List<StateModel>> o = stateService.getAllStates();
				if (o.isPresent()) {
					List<StateModel> list = o.get();
					list.forEach((state) -> System.out.println(state.getStateId() + " \t" + state.getStateName()));
				} else {
					System.out.println("There is no data present");
				}
//		list.forEach((s)->System.out.println(s.getStateId()+"\t"+s.getStateName()));

				break;
			case 3:
				System.out.println("Enter state name");
				xyz.nextLine();
				stateName = xyz.nextLine();
				StateModel model = stateService.getStateByName(stateName);
				if (model != null) {
					System.out.println(model.getStateId() + "\t" + model.getStateName());

				} else {
					System.out.println("State not found");
				}
				break;
			case 4:
				System.out.println("Enter state name");
				xyz.nextLine();
				stateName = xyz.nextLine();
				System.out.println(stateName);
				boolean b = stateService.isDeleteStateById(stateName);
				if (b) {
					System.out.println("Stete Delete SuccessfUlly...");
				} else {
					System.out.println("Some Problem IS there...");
				}
				break;
			case 5:
				xyz.nextLine();
				System.out.println("Enter state current name");
				String currName = xyz.nextLine();
				System.out.println("Eneter new state name");
				String newName = xyz.nextLine();
				b = stateService.isUpdateState(currName, newName);
				if (b) {
					System.out.println("state updated successfully...");
				} else {
					System.out.println("state not updated successfully..");
				}

				break;
			case 6:
				System.out.println("Enter the State name");
				xyz.nextLine(); // To clear the buffer after nextInt()
				stateName = xyz.nextLine();
				System.out.println("Enter distName");
				String distName = xyz.nextLine();
				b = stateService.isAssociateDistToState(stateName, distName);
				if (!b) {
					System.out.println("District added under state");
				} else {
					System.out.println("District not added under state");
				}
				break;

			case 7:
				System.out.println("Enter the State name");
				xyz.nextLine();
				stateName = xyz.nextLine();
				b = stateService.isAddBulkDist(stateName);
				if (!b) {
					System.out.println("District added under state");
				} else {
					System.out.println("District not added under state");
				}

				break;

			case 8:
//				System.out.println("Enter State in Which  you want to add in database ");
				o = stateService.getAllStates();

				if (o.isPresent()) {
					(o.get()).forEach((state) -> System.out.println((++count) + "\t" + state.getStateName()));
					System.out.println("Enter State Namein Which City want to add");
					xyz.nextLine();
					stateName = xyz.nextLine();
					System.out.println("Distric Name");
					System.out.println("=============");
					List<DistModel> list = stateService.getDistListByName(stateName);
					if (list != null) {
						list.forEach((dist) -> System.out.println(dist.getId() + "\t" + dist.getName()));
						list.clear();
						System.out.println("Enter Distric Name In which city want to add");
						distName = xyz.nextLine();
						int stateId = stateService.getStateIdByName(stateName);
						int distId = stateService.getDistIdByDistName(distName);
						System.out.println(stateId + "\t" + distId);
						System.out.println("Enter city name");
						String cityName = xyz.nextLine();
						CityModel cityModel = new CityModel();
						cityModel.setId(distId);
						cityModel.setStateId(stateId);
						cityModel.setCityName(cityName);
						b = cityService.isAddNewCity(cityModel);
						if (b) {
							System.out.println("City Added Successfully..");
						} else {
							System.out.println("city not added... ");
						}
					} else {
						System.out.println("Do you Wnat to add this state" + stateName + "in databadase");
						String msg = xyz.nextLine();
						if (msg.equals("yes")) {
							b = stateService.isAddNewState(new StateModel(1, stateName));
							if (b) {
								System.out.println("State added Successfully...");
							} else {
								System.out.println("state not Added..");
							}

						} else {
							System.out.println("No");
						}
					}
				} else {
					System.out.println("There is no data present");

				}

				break;

			case 9:
				System.out.println("Enter State in Which  you want to add in database ");
				o = stateService.getAllStates();

				if (o.isPresent()) {
					(o.get()).forEach((state) -> System.out.println((++count) + "\t" + state.getStateName()));
					System.out.println("Enter State Namein Which City want to add");
					xyz.nextLine();
					stateName = xyz.nextLine();
					System.out.println("Distric Name");
					System.out.println("=============");
					List<DistModel> list = stateService.getDistListByName(stateName);
					if (list != null) {
						list.forEach((dist) -> System.out.println(dist.getId() + "\t" + dist.getName()));
						list.clear();
						System.out.println("Enter Distric Name In which city want to add");
						distName = xyz.nextLine();
						int stateId = stateService.getStateIdByName(stateName);
						int distId = stateService.getDistIdByDistName(distName);
						System.out.println("Enter city name");
						String cityName = xyz.nextLine();
						int cityId = cityService.getCityIdByCityName(cityName, stateId, distId);
						System.out.println("Enter Ward Name and pincode ");
						String wardName = xyz.nextLine();
						String wardPinCode = xyz.nextLine();
						WardModel wardModel = new WardModel(wardName, 0, wardPinCode);
						wardModel.setCityId(cityId);

						b = cityService.isAddNewWard(wardModel);
						if (b) {
							System.out.println("ward Added Successfully...");
						} else {
							System.out.println("ward Not Added Successfully...");
						}

					} else {
						System.out.println("Do you Want to add this state" + stateName + "in databadase");
						String msg = xyz.nextLine();
						if (msg.equals("yes")) {
							b = stateService.isAddNewState(new StateModel(0, stateName));
							if (b) {
								System.out.println("State added Successfully...");
							} else {
								System.out.println("state not Added..");
							}

						} else {
							System.out.println("No");
						}
					}
				} else {
					System.out.println("There is no data present");

				}
				break;
			case 10:
				System.out.println("Add New Amenities");
				xyz.nextLine();
				String aminityName = xyz.nextLine();
				AminityModel amModel = new AminityModel(0, aminityName, 0);
				b = aminityService.isAddNewAminity(amModel);
				if (b) {
					System.out.println("Aminity Added Successfully....");
				} else {
					System.out.println("There is some Problem...");
				}
				break;
			case 11:
				o = stateService.getAllStates();
				if (o.isPresent()) {
					(o.get()).forEach((state) -> System.out.println((++count) + "\t" + state.getStateName()));
					System.out.println("Enter State Namein Which City want to add");
					xyz.nextLine();
					stateName = xyz.nextLine();
					System.out.println("Distric Name");
					System.out.println("=============");
					List<DistModel> list = stateService.getDistListByName(stateName);
					if (list != null) {
						list.forEach((dist) -> System.out.println(dist.getId() + "\t" + dist.getName()));
						list.clear();
						System.out.println("Enter Distric Name In which city want to add");
						distName = xyz.nextLine();
						int stateId = stateService.getStateIdByName(stateName);
						int distId = stateService.getDistIdByDistName(distName);
						System.out.println("City  List");
						System.out.println("===========================================");
						List<CityModel> cityList = cityService.getAllCities(stateId, distId);
						cityList.forEach((c) -> System.out.println(c.getCityId() + "\t" + c.getCityName()));
						System.out.println("===================================");
						System.out.println("Enter City name");
						String cityName = xyz.nextLine();
						List<WardModel> wardList = cityService.getAllWardByCityName(cityName);
						System.out.println("Choose ward name in wich propery want to add");
						wardList.forEach((w) -> System.out.println(w.getWardName()));
						System.out.println("==========================================");
						System.out.println("Enter Wrad name");
						String wardName = xyz.nextLine();
						int wardId = cityService.getWardIdByName(wardName);
						System.out.println("Enter Property Detail like name address and sqfeet and price");
						String propertyName = xyz.nextLine();
						String PropertyAddress = xyz.nextLine();
						int sqFeetArea = xyz.nextInt();
						int propPrice = xyz.nextInt();
						WardModel wardModel = new WardModel();
						wardModel.setWardId(wardId);
						wardModel.setWardName(wardName);

						System.out.println("===========================");
						System.out.println("Select Aminities...");
						List<AminityModel> aminityList = aminityService.getAllAminity();
						aminityList.forEach((am) -> System.out.println(am.getAmId() + "\t" + am.getAmName()));
						List<AminityModel> listAminities = new ArrayList<AminityModel>();
						xyz.nextLine();
						while (true) {
							System.out.println("Enter aminity Name Id and Price ");
							
							String amName = xyz.nextLine();
							int amId = xyz.nextInt();
							int amPrice = xyz.nextInt();
							
							amModel = new AminityModel();
							amModel.setAmName(amName);
							amModel.setAmId(amId);
							amModel.setAmPrice(amPrice);
							listAminities.add(amModel);
							
							System.out.println("Do you want to add more Aminities");
							xyz.nextLine();
							String confirmMsg = xyz.nextLine();
							if (confirmMsg.equals("no")) {
								break;
							}

						}
						PropertyModel propModel = new PropertyModel(wardModel, 0, propertyName, PropertyAddress,
								sqFeetArea, propPrice, listAminities);
						b = cityService.isAddNewProperty(propModel);
						if (b) {
							System.out.println("Property added SuccessFully......");
						} else {
							System.out.println("Some Problem Is there");
						}
					} else {
						System.out.println("Do you Want to add this state" + stateName + "in databadase");
						String msg = xyz.nextLine();
						if (msg.equals("yes")) {
							b = stateService.isAddNewState(new StateModel(0, stateName));
							if (b) {
								System.out.println("State added Successfully...");
							} else {
								System.out.println("state not Added..");
							}

						} else {
							System.out.println("No");
						}
					}
				} else {
					System.out.println("There is no data present");

				}
				break;
			case 50:
				System.exit(0);
			default:
				System.out.println("Wrong Choice");
			}
		} while (true);
	}
}
