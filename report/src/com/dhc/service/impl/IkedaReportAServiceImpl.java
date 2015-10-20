package com.dhc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhc.dao.IkedaReportADao;
import com.dhc.entity.IkedaReportAParam;
import com.dhc.entity.IkedaReportAReturn;
import com.dhc.service.IkedaReportAService;

/**
 * 
 * @author hanliang 20151010
 * @update hanliang 20151020
 * -add sortedData return
 */
@Service("IkedaReportAService")
public class IkedaReportAServiceImpl implements IkedaReportAService {

	@Resource(name = "IkedaReportADao")
	private IkedaReportADao ikedaReportADao;
	
	@Override
	public List<IkedaReportAReturn> getIkedaReportA(
			IkedaReportAParam ikedaReportAParam) {
		List<IkedaReportAReturn> result = ikedaReportADao.getIkedaReportA(ikedaReportAParam);
		//sort
		String type = ikedaReportAParam.getSortName();
		String order = ikedaReportAParam.getSortOrder();
		Object[] array = result.toArray();
		int innerSelect = 0;
		//check asc or desc
		boolean isAsc = sortMethod(order);
		switch(type){
		case "goods_code":
			innerSelect = 1;
			bubbleSortInt(array, innerSelect, isAsc);
			break;
		case "goods_name":
			innerSelect = 1;
			bubbleSortString(array, innerSelect, isAsc);
			break;
		case "middle_syslast":
			innerSelect = 2;
			bubbleSortInt(array, innerSelect, isAsc);
			break;
		case "middle_split_amt":
			innerSelect = 1;
			bubbleSortDouble(array, innerSelect, isAsc);
			break;
		case "total_syslast":
			innerSelect = 3;
			bubbleSortInt(array, innerSelect, isAsc);
			break;
		case "total_split_amt":
			innerSelect = 2;
			bubbleSortDouble(array, innerSelect, isAsc);
			break;
		case "total_turnrate":
			innerSelect = 3;
			bubbleSortDouble(array, innerSelect, isAsc);
			break;
		case "stock":
			innerSelect = 4;
			bubbleSortInt(array, innerSelect, isAsc);
			break;
		case "goods_turn_duration":
			innerSelect = 4;
			bubbleSortDouble(array, innerSelect, isAsc);
			break;
		default:break;
		}
		
		result = null;
		List<IkedaReportAReturn> ikedaReportAReturn = new ArrayList<IkedaReportAReturn>();
		for (int i = 0; i < array.length; i++) {
			ikedaReportAReturn.add((IkedaReportAReturn) array[i]);
		}
		return ikedaReportAReturn;
	}

	/**
	 * 
	 * @param order
	 * @return
	 */
	private boolean sortMethod(String order){
		boolean result = false;
		if("asc".equals(order)){
			result = true;
		}
		return result;
	}
	
	/**
	 * 
	 * @param array
	 * @param type
	 * @param flag
	 */
	private void bubbleSortInt(Object[] array, int type, boolean flag){
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length -i -1; j++) {
				IkedaReportAReturn ikedaReportAReturnA = (IkedaReportAReturn) array[j];
				IkedaReportAReturn ikedaReportAReturnB = (IkedaReportAReturn) array[j+1];
				int ikedaReportAReturnAAInt = 0 ;
				int ikedaReportAReturnABInt = 0 ;
				int data[] = initParam(ikedaReportAReturnAAInt, ikedaReportAReturnABInt, type, ikedaReportAReturnA, ikedaReportAReturnB);
				ikedaReportAReturnAAInt = data[0];
				ikedaReportAReturnABInt = data[1];
				if(flag){
					//asc
					if(ikedaReportAReturnAAInt > ikedaReportAReturnABInt){
						IkedaReportAReturn temp = ikedaReportAReturnA;
						array[j] = ikedaReportAReturnB;
						array[j+1] = temp;
					}	
				}else{
					//desc
					if(ikedaReportAReturnAAInt < ikedaReportAReturnABInt){
						IkedaReportAReturn temp = ikedaReportAReturnA;
						array[j] = ikedaReportAReturnB;
						array[j+1] = temp;
					}	
				}
				
			}
		}
	}
	
	/**
	 * 
	 * @param array
	 * @param type
	 * @param flag
	 */
	private void bubbleSortDouble(Object[] array, int type, boolean flag){
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length -i -1; j++) {
				IkedaReportAReturn ikedaReportAReturnA = (IkedaReportAReturn) array[j];
				IkedaReportAReturn ikedaReportAReturnB = (IkedaReportAReturn) array[j+1];
				double ikedaReportAReturnAAInt = 0 ;
				double ikedaReportAReturnABInt = 0 ;
				double data[] = initParam(ikedaReportAReturnAAInt, ikedaReportAReturnABInt, type, ikedaReportAReturnA, ikedaReportAReturnB);
				ikedaReportAReturnAAInt = data[0];
				ikedaReportAReturnABInt = data[1];
				if(flag){
					//asc
					if(ikedaReportAReturnAAInt > ikedaReportAReturnABInt){
						IkedaReportAReturn temp = ikedaReportAReturnA;
						array[j] = ikedaReportAReturnB;
						array[j+1] = temp;
					}	
				}else{
					//desc
					if(ikedaReportAReturnAAInt < ikedaReportAReturnABInt){
						IkedaReportAReturn temp = ikedaReportAReturnA;
						array[j] = ikedaReportAReturnB;
						array[j+1] = temp;
					}	
				}
				
			}
		}
	}
	
	/**
	 * 
	 * @param array
	 * @param type
	 * @param flag
	 */
	private void bubbleSortString(Object[] array, int type, boolean flag){
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length -i -1; j++) {
				IkedaReportAReturn ikedaReportAReturnA = (IkedaReportAReturn) array[j];
				IkedaReportAReturn ikedaReportAReturnB = (IkedaReportAReturn) array[j+1];
				String ikedaReportAReturnAAString = "" ;
				String ikedaReportAReturnABString = "" ;
				String data[] = initParam(ikedaReportAReturnAAString, ikedaReportAReturnABString, type, ikedaReportAReturnA, ikedaReportAReturnB);
				ikedaReportAReturnAAString = data[0];
				ikedaReportAReturnABString = data[1];
				if(flag){
					//asc
					if(ikedaReportAReturnAAString.compareTo(ikedaReportAReturnABString) > 0){
						IkedaReportAReturn temp = ikedaReportAReturnA;
						array[j] = ikedaReportAReturnB;
						array[j+1] = temp;
					}	
				}else{
					//desc
					if(ikedaReportAReturnAAString.compareTo(ikedaReportAReturnABString) < 0){
						IkedaReportAReturn temp = ikedaReportAReturnA;
						array[j] = ikedaReportAReturnB;
						array[j+1] = temp;
					}	
				}
				
			}
		}
	}
	
	/**
	 * 
	 * @param ikedaReportAReturnAAInt
	 * @param ikedaReportAReturnABInt
	 * @param type
	 * @param ikedaReportAReturnA
	 * @param ikedaReportAReturnB
	 * @return
	 */
	private int[] initParam(int ikedaReportAReturnAAInt, int ikedaReportAReturnABInt, int type, IkedaReportAReturn ikedaReportAReturnA, IkedaReportAReturn ikedaReportAReturnB){
		switch(type){
		case 1:
			ikedaReportAReturnAAInt = Integer.parseInt(ikedaReportAReturnA.getGoods_code());
			ikedaReportAReturnABInt = Integer.parseInt(ikedaReportAReturnB.getGoods_code());
			break;
		case 2:
			ikedaReportAReturnAAInt = ikedaReportAReturnA.getMiddle_syslast();
			ikedaReportAReturnABInt = ikedaReportAReturnB.getMiddle_syslast();
			break;
		case 3:
			ikedaReportAReturnAAInt = ikedaReportAReturnA.getTotal_syslast();
			ikedaReportAReturnABInt = ikedaReportAReturnB.getTotal_syslast();
			break;
		case 4:
			ikedaReportAReturnAAInt = ikedaReportAReturnA.getStock();
			ikedaReportAReturnABInt = ikedaReportAReturnB.getStock();
			break;
		default:
			ikedaReportAReturnAAInt = 0;
			ikedaReportAReturnABInt = 0;
			break;
		}
		int[] data = new int[2];
		data[0] = ikedaReportAReturnAAInt;
		data[1] = ikedaReportAReturnABInt;
		return data;
	}
	
	/**
	 * 
	 * @param ikedaReportAReturnAAInt
	 * @param ikedaReportAReturnABInt
	 * @param type
	 * @param ikedaReportAReturnA
	 * @param ikedaReportAReturnB
	 * @return
	 */
	private double[] initParam(double ikedaReportAReturnAAInt, double ikedaReportAReturnABInt, int type, IkedaReportAReturn ikedaReportAReturnA, IkedaReportAReturn ikedaReportAReturnB){
		switch(type){
		case 1:
			ikedaReportAReturnAAInt = Double.parseDouble(ikedaReportAReturnA.getMiddle_split_amt());
			ikedaReportAReturnABInt = Double.parseDouble(ikedaReportAReturnB.getMiddle_split_amt());
			break;
		case 2:
			ikedaReportAReturnAAInt = Double.parseDouble(ikedaReportAReturnA.getTotal_split_amt());
			ikedaReportAReturnABInt = Double.parseDouble(ikedaReportAReturnB.getTotal_split_amt());
			break;
		case 3:
			ikedaReportAReturnAAInt = Double.parseDouble(ikedaReportAReturnA.getTotal_turnrate());
			ikedaReportAReturnABInt = Double.parseDouble(ikedaReportAReturnB.getTotal_turnrate());
			break;
		case 4:
			ikedaReportAReturnAAInt = Double.parseDouble(ikedaReportAReturnA.getGoods_turn_duration());
			ikedaReportAReturnABInt = Double.parseDouble(ikedaReportAReturnB.getGoods_turn_duration());
			break;
		default:
			ikedaReportAReturnAAInt = 0;
			ikedaReportAReturnABInt = 0;
			break;
		}
		double[] data = new double[2];
		data[0] = ikedaReportAReturnAAInt;
		data[1] = ikedaReportAReturnABInt;
		return data;
	}
	
	/**
	 * 
	 * @param ikedaReportAReturnAAString
	 * @param ikedaReportAReturnABString
	 * @param type
	 * @param ikedaReportAReturnA
	 * @param ikedaReportAReturnB
	 * @return
	 */
	private String[] initParam(String ikedaReportAReturnAAString, String ikedaReportAReturnABString, int type, IkedaReportAReturn ikedaReportAReturnA, IkedaReportAReturn ikedaReportAReturnB){
		switch(type){
		case 1:
			ikedaReportAReturnAAString = ikedaReportAReturnA.getGoods_name();
			ikedaReportAReturnABString = ikedaReportAReturnB.getGoods_name();
			break;
		default:
			ikedaReportAReturnAAString = "";
			ikedaReportAReturnABString = "";
			break;
		}
		String[] data = new String[2];
		data[0] = ikedaReportAReturnAAString;
		data[1] = ikedaReportAReturnABString;
		return data;
	}

}
