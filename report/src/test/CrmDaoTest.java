package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dhc.dao.CrmDao;
import com.dhc.entity.CrmParam;
import com.dhc.entity.CrmReturn;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CrmDaoTest {

	@Resource(name = "CrmDao")
	private CrmDao crmDaoImpl;
	
	private CrmParam crmParam;
	
	@Before
	public void setUp() throws Exception {
		crmParam = new CrmParam();
		crmParam.setDate_from("20150617");
		crmParam.setDate_to("20150618");
		crmParam.setPathways("71");
	}
	
	//@Test
	public void testGetCrmData() {
		long ts = System.currentTimeMillis();
		List<CrmReturn> crmReturn = crmDaoImpl.getCrmData(crmParam);
		long ts2 = System.currentTimeMillis();
		System.out.println(ts2-ts);
		System.out.println(crmReturn.size());
	}
	
	@Test
//	@Ignore
	public void testPoi(){
		try{
			POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream("test.xls"));
			Workbook wb = new HSSFWorkbook(fs);
			int numberOfSheets = wb.getNumberOfSheets();
//			ExcelUtil.removeSheets(wb, numberOfSheets, "temp");
			
			String file = "out.xls";
			List<CrmReturn> crmReturn = crmDaoImpl.getCrmData(crmParam);
			System.out.println("crmReturnSize "+crmReturn.size());
			
			List<String> li = new ArrayList<String>();
			for (int i = 0; i < crmReturn.size(); i++) {
				CrmReturn cr = crmReturn.get(i);
				li.add(cr.getGoods_code());
				li.add(cr.getGoods_name());
				li.add(cr.getMemb_no());
				li.add(cr.getOrder_date());
				li.add(cr.getOrder_media());
				li.add(cr.getOrder_no());
				li.add(cr.getPre_price());
				li.add(cr.getPre_sale_price());
				li.add(cr.getQty());
				li.add(cr.getReal_sale_price());
				li.add(cr.getSale_price());
			}
			for (int i = 0; i < crmReturn.size(); i++) {
				Sheet sheet = wb.getSheetAt(0);
				Row row = null;
				if(sheet.getRow(i+1) == null){
					row = sheet.createRow(i+1);
				}else{
					row = sheet.getRow(i+1);
				}
				int count_start = i*(CrmReturn.class.getDeclaredFields().length-1);
				System.out.print("count_start "+count_start+"|");
				int count_total = (i+1) * (CrmReturn.class.getDeclaredFields().length-1);
				System.out.println("count_total "+count_total);
				for (int j = count_start; j < (count_total); j++) {
					int cell_locate = j%(CrmReturn.class.getDeclaredFields().length-1);
					Cell cell = null;
					if(row.getCell(cell_locate) == null){
						cell = row.createCell(cell_locate);
					}else{
						cell = row.getCell( j%(CrmReturn.class.getDeclaredFields().length-1) );
					}
					cell.setCellValue(li.get(j));
				}
				
			}
			FileOutputStream fo = new FileOutputStream(file);
			wb.write(fo);
			wb.close();
			fo.close();
			wb.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
