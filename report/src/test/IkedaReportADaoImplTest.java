package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javassist.tools.framedump;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dhc.dao.IkedaReportADao;
import com.dhc.entity.IkedaReportAParam;
import com.dhc.entity.IkedaReportAReturn;
import com.dhc.entity.MobileClientOrderQueryParam;
import com.dhc.service.MobileClientOrderQueryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class IkedaReportADaoImplTest {

	@Resource(name = "IkedaReportADao")
	private IkedaReportADao ikedaReportADaoImpl;
	
	private IkedaReportAParam ikedaReportAParam;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Before
	public void setUp() throws Exception {
		ikedaReportAParam = new IkedaReportAParam();
		ikedaReportAParam.setStartDate(20150815);
		ikedaReportAParam.setEndDate(20151013);
	}

	@Test
//	@Ignore
	public void testGetIkedaReportA() {
		List<IkedaReportAReturn> ikedaReportAReturn = ikedaReportADaoImpl.getIkedaReportA(ikedaReportAParam);
//		for (int i = 0; i < ikedaReportAReturn.size(); i++) {
//			try {
//				System.out.println(mapper.writeValueAsString(ikedaReportAReturn.get(i)));
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//			}
//		}
		System.out.println("--------------");
		String type = "goods_name";
		String order = "asc";
		Object[] array = ikedaReportAReturn.toArray();
		int innerSelect = 0;
		boolean isAsc = sortMethod(order);
		switch(type){
		case "goods_code":
			System.out.println("goods_code");
			innerSelect = 1;
			bubbleSortInt(array, innerSelect, isAsc);
			break;
		case "goods_name":
			System.out.println("goods_name");
			innerSelect = 1;
			bubbleSortString(array, innerSelect, isAsc);
			break;
		case "middle_syslast":
			System.out.println("middle_syslast");
			innerSelect = 2;
			bubbleSortInt(array, innerSelect, isAsc);
			break;
		case "middle_split_amt":
			System.out.println("middle_split_amt");
			innerSelect = 1;
			bubbleSortDouble(array, innerSelect, isAsc);
			break;
		case "total_syslast":
			System.out.println("total_syslast");
			innerSelect = 3;
			bubbleSortInt(array, innerSelect, isAsc);
			break;
		case "total_split_amt":
			System.out.println("total_split_amt");
			innerSelect = 2;
			bubbleSortDouble(array, innerSelect, isAsc);
			break;
		case "total_turnrate":
			System.out.println("total_turnrate");
			innerSelect = 3;
			bubbleSortDouble(array, innerSelect, isAsc);
			break;
		case "stock":
			System.out.println("stock");
			innerSelect = 4;
			bubbleSortInt(array, innerSelect, isAsc);
			break;
		case "goods_turn_duration":
			System.out.println("goods_turn_duration");
			innerSelect = 4;
			bubbleSortDouble(array, innerSelect, isAsc);
			break;
		default:break;
		}
		
		for (int i = 0; i < array.length; i++) {
			IkedaReportAReturn a = (IkedaReportAReturn) array[i];
//			System.out.println(a.getGoods_code()+ " | "+a.getMiddle_syslast()+" | "+a.getTotal_syslast() + " | "+a.getStock());
//			System.out.println(a.getGoods_code()+ " | "+a.getMiddle_split_amt()+" | "+a.getTotal_split_amt()+" | "+a.getTotal_turnrate()+" | "+a.getGoods_turn_duration());
			System.out.println(a.getGoods_code()+ " | "+a.getGoods_name());
//			try {
//				System.out.println(mapper.writeValueAsString(ikedaReportAReturn.get(i)));
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//			}
		}
		
	}
	
	private boolean sortMethod(String order){
		boolean result = false;
		if("asc".equals(order)){
			result = true;
		}
		return result;
	}
	
	private void bubbleSortInt(Object[] array, int type, boolean flag){
		if(flag){
			System.out.println("asc");
		}else{
			System.out.println("desc");
		}
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length -i -1; j++) {
				IkedaReportAReturn ikedaReportAReturnA = (IkedaReportAReturn) array[j];
				IkedaReportAReturn ikedaReportAReturnB = (IkedaReportAReturn) array[j+1];
				int ikedaReportAReturnAAInt = 0 ;
				int ikedaReportAReturnABInt = 0 ;
				int data[] = initParam(ikedaReportAReturnAAInt, ikedaReportAReturnABInt, type, ikedaReportAReturnA, ikedaReportAReturnB);
				ikedaReportAReturnAAInt = data[0];
				ikedaReportAReturnABInt = data[1];
//				System.out.println("ikedaReportAReturnAAInt "+ikedaReportAReturnAAInt);
//				System.out.println("ikedaReportAReturnABInt "+ikedaReportAReturnABInt);
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
	
	private void bubbleSortDouble(Object[] array, int type, boolean flag){
		if(flag){
			System.out.println("asc");
		}else{
			System.out.println("desc");
		}
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length -i -1; j++) {
				IkedaReportAReturn ikedaReportAReturnA = (IkedaReportAReturn) array[j];
				IkedaReportAReturn ikedaReportAReturnB = (IkedaReportAReturn) array[j+1];
				double ikedaReportAReturnAAInt = 0 ;
				double ikedaReportAReturnABInt = 0 ;
				double data[] = initParam(ikedaReportAReturnAAInt, ikedaReportAReturnABInt, type, ikedaReportAReturnA, ikedaReportAReturnB);
				ikedaReportAReturnAAInt = data[0];
				ikedaReportAReturnABInt = data[1];
//				System.out.println("ikedaReportAReturnAAInt "+ikedaReportAReturnAAInt);
//				System.out.println("ikedaReportAReturnABInt "+ikedaReportAReturnABInt);
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
	
	private void bubbleSortString(Object[] array, int type, boolean flag){
		if(flag){
			System.out.println("asc");
		}else{
			System.out.println("desc");
		}
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length -i -1; j++) {
				IkedaReportAReturn ikedaReportAReturnA = (IkedaReportAReturn) array[j];
				IkedaReportAReturn ikedaReportAReturnB = (IkedaReportAReturn) array[j+1];
				String ikedaReportAReturnAAString = "" ;
				String ikedaReportAReturnABString = "" ;
				String data[] = initParam(ikedaReportAReturnAAString, ikedaReportAReturnABString, type, ikedaReportAReturnA, ikedaReportAReturnB);
				ikedaReportAReturnAAString = data[0];
				ikedaReportAReturnABString = data[1];
//				System.out.println("ikedaReportAReturnAAInt "+ikedaReportAReturnAAInt);
//				System.out.println("ikedaReportAReturnABInt "+ikedaReportAReturnABInt);
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

//	@Test
	public void test(){
//		String start = String.valueOf(20151005);
//		Calendar startCal = Calendar.getInstance();
//		System.out.println(start.substring(0, 4));
//		System.out.println(start.substring(4, 6));
//		System.out.println(start.substring(6));
//		double a = (double)1/(20);
//		System.out.println(a);
//		System.out.println(Math.round(a * 10000 ) / 100.0);
//		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#0.00");  
//		df.format(Math.round(a * 10000 ) / 100.0);
//		System.out.println(df.format(a));
		
//		double d = 1/239.0 ;
//		double total_turnrate_d = (double)1/(20.0) ;
//		System.out.println(Math.round(total_turnrate_d * 10000 ) / 100.0);
//		System.out.println(Math.round(total_turnrate_d * 100.0 ));
//		BigDecimal bg = new BigDecimal(Math.round(total_turnrate_d * 10000 ) / 100.0);  
//		System.out.println(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		
		
		 int score[] = {67, 69, 75, 87, 89, 90, 99, 100};
           for (int i = 0; i < score.length -1; i++){    //最多做n-1趟排序
               for(int j = 0 ;j < score.length - i - 1; j++){    //对当前无序区间score[0......length-i-1]进行排序(j的范围很关键，这个范围是在逐步缩小的)
                   if(score[j] < score[j + 1]){    //把小的值交换到后面
                       int temp = score[j];
                       score[j] = score[j + 1];
                       score[j + 1] = temp;
                  }
              }            
              System.out.print("第" + (i + 1) + "次排序结果：");
              for(int a = 0; a < score.length; a++){
                  System.out.print(score[a] + "\t");
              }
              System.out.println("");
          }
              System.out.print("最终排序结果：");
              for(int a = 0; a < score.length; a++){
                  System.out.print(score[a] + "\t");
         }
		
	}
}
