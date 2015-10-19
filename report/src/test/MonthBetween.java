package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class MonthBetween {

	
	private int _start = 201502;
	private int _end = 201507;
	
	
	@Test
	@Ignore
	public void test() {
		List<Integer> monthList = new ArrayList<Integer>();
		
		String start = _start + "";
		int start_year = Integer.parseInt(start.substring(0, 4) );
		int start_month = Integer.parseInt(start.substring(4) );
		
		String end = _end + "";
		int end_year = Integer.parseInt(end.substring(0, 4) );
		int end_month = Integer.parseInt(end.substring(4) );
		
		Calendar cal = Calendar.getInstance();
		cal.set(start_year, start_month-1, 01);
		
		Calendar cal2 = Calendar.getInstance();
//		cal2.set(start_year, start_month+10, 01);
		cal2.set(end_year, end_month-1, 01);
		
		monthList.add(Integer.parseInt(getCalendar(cal.getTime())));
		while(cal2.compareTo(cal) > 0){
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
			String month = getCalendar(cal.getTime());
			monthList.add(Integer.parseInt(month));
		}
		
		for (int i = 0; i < monthList.size(); i++) {
			System.out.println(monthList.get(i));
		}
		
		
		
	}
	
	private String getCalendar(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String currentTime = sdf.format(date);
		return currentTime;
	}

	@Test
	@Ignore
	public void testString(){
		String s = "[201501, 201502, 201503, 201504, 201505, 201506]";
		
		System.out.println(s.substring(1, 7));
		
		System.out.println(s.substring(s.length()-7,s.length()-1));
		
	}
	
	@Test
	@Ignore
	public void testEquals(){
		double a = 200d;
		double b = 614d;
		String c = a/b +"";
		System.out.println(a/b);
		System.out.println(c);
		String d = new java.text.DecimalFormat("#.00").format(a/b);
		System.out.println(d);
		
		double e = Math.round(a/b * 100);
		System.out.println(e/100);
		
		String s = "201501";
		System.out.println(s.substring(s.length()-2, s.length()));
		
		
	}
	
	@Test
	public void testDivide(){
		int a = 9;
		int b = 10;
		System.out.println((a+b)/2);
	}
}
