package com.common;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author hanliang 20150623
 * 
 */
public class ExcelUtil {
	
	/**
	 * get file
	 * @param fileName
	 * @return
	 */
	public static File getFile(String fileName){
		File fs = new File(fileName);
		return fs;
	}
	
	/**
	 * get workbook
	 * @param fs
	 * @return
	 */
	public static Workbook getWorkbook(File fs) throws FileNotFoundException,IOException{
		Workbook wb = null;
		FileInputStream is;
		is = new FileInputStream(fs);
		if(fs.toString().contains("xlsx")){
			wb = new XSSFWorkbook(is);
		}else{
			wb = new HSSFWorkbook(is);	
		}
		return wb;
	}
	
	/**
	 * remove all sheet & create new sheet
	 * @param wb
	 * @param numberOfSheets
	 */
	public static void removeSheets(Workbook wb, int numberOfSheets, String sheetName){
		wb.getSheetAt(0);
		for (int i = 0; i < numberOfSheets; i++) {
			wb.removeSheetAt(0);
		}
		wb.createSheet(sheetName);
	}
	
	/**
	 * 
	 * @param wb
	 * @param fo
	 * @throws IOException 
	 */
	public static void writeFile(Workbook wb, String file) throws IOException{
		 FileOutputStream fo = new FileOutputStream(file);
		 wb.write(fo);
		 wb.close();
		 fo.close();
	}
	
	/**
	 * 
	 * @param folderName
	 */
	public static void clearFolder(String folderName){
		File folder = null;
		folder = new File(folderName);
		// 鏂囦欢澶规槸鍚﹀瓨鍦�
		if (folder.exists()) {
			// 鍒犻櫎璇ユ枃浠跺す涓嬫墍鏈夋枃浠跺強鏂囦欢澶�
			for (File obj : folder.listFiles()) {
				try {
					// 濡傛灉鏄枃浠跺す
					if (obj.isDirectory()) {
						clearFolder(obj.getCanonicalPath());
					} else {
						obj.delete();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//鍒犻櫎褰撳墠鏂囦欢澶�
			//folder.delete();
		}
	}
	
	/**
	 * write data 
	 * @param demoReturnSize
	 * @param wb
	 * @param dataList
	 * @param clazz
	 * @throws Exception
	 */
	public void writeData(int demoReturnSize, Workbook wb, List<String> dataList, Class<?> clazz) throws Exception{
		for (int i = 0; i < demoReturnSize; i++) {
			Sheet sheet = wb.getSheetAt(0);
			Row row = null;
			if(sheet.getRow(i+1) == null){
				row = sheet.createRow(i+1);
			}else{
				row = sheet.getRow(i+1);
			}
			//鑾峰彇瀵硅薄鐨勫０鏄庡睘鎬ф暟閲�(鍑忓幓serialVersionUID) 
			int count_start = i*(clazz.getDeclaredFields().length-1);
//			System.out.print("count_start "+count_start+"|");
			//鍗曟鏁版嵁寰幆鐨勬�鏁�
			int count_total = (i+1) * (clazz.getDeclaredFields().length-1);
//			System.out.println("count_total "+count_total);
			for (int j = count_start; j < (count_total); j++) {
				//鑾峰彇鍗曞厓鏍间綅缃�
				int cell_locate = j%(clazz.getDeclaredFields().length-1);
				Cell cell = null;
				if(row.getCell(cell_locate) == null){
					cell = row.createCell(cell_locate);
				}else{
					cell = row.getCell(cell_locate);
				}
				cell.setCellValue(dataList.get(j));
			}
		}
	}
	
	/**
	 * tranform List<Object> 鈫�List<String>
	 * @param obj
	 * @param clazz
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public List<String> transList(Object obj,Class<?> clazz) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		List<String> result = new ArrayList<String>();
		//鑾峰彇澹版槑鐨勫睘鎬�
		Field[] fields = clazz.getDeclaredFields();
		//鏁扮粍缁撴灉闆�
		//String[] resultArr = new String[fields.length];
		List<?> dataList = (List<?>) obj;
		for (int i = 0; i < dataList.size(); i++) {
			Object innerObj = dataList.get(i);
			for (int j = 0; j < (fields.length-1); j++) {
				Field field = fields[j];
				//鑾峰彇瀵瑰簲灞炴�鐨勫�
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
				Method getMethod = pd.getReadMethod();
				Object value = getMethod.invoke(innerObj);
				//resultArr[j] = value+"";
				result.add((String) value);
			}
		}
		return result;
	}
	
}
