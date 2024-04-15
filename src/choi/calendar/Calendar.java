package choi.calendar;

import java.util.Date;
import java.util.HashMap;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;

public class Calendar {

	private final int[] MAX_DAYS = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	private final int[] LEAP_MAX_DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	private final String SAVE_FILE="calendar.dat";
	
	private HashMap<Date, PlanItem> planMap;

	public Calendar() {
		planMap = new HashMap<Date, PlanItem>();
	}

	/**
	 * 
	 * @param date ex : "2017-06-20"
	 * @param plan
	 * @throws ParseException
	 */
	public void registerPlan(String strDate, String plan) throws ParseException {
		PlanItem p = new PlanItem(strDate, plan);
		planMap.put(p.getDate(), p);
		
		File f = new File(SAVE_FILE);
		String item = p.saveString();
		try {
			FileWriter fw = new FileWriter(f);
			fw.write(item);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public PlanItem searchPlan(String strDate){
	    Date date = PlanItem.getDatefromString(strDate);
	    for (PlanItem item : planMap.values()) {
	        if (item.getDate().equals(date)) {
	            return item;
	        }
	    }
	    return null;
	}

	public boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			return true;
		} else {
			return false;
		}
	}

	public int getmaxDaysOfMonth(int year, int month) {
		if (isLeapYear(year)) {
			return LEAP_MAX_DAYS[month];
		} else {
			return MAX_DAYS[month];
		}

	}

//	월을 입력하면 해당월의 달력을 출력한다.
//	달력은 모양은 1단계에서 작성한 모양으로 만든다.
//	1일은 일요일로 정해도 무방하다.
//	-1을 입력받기 전까지 반복 입력받는다.
//	프롬프트를 출력한다.

	public void printCalendar(int year, int month) {
		System.out.printf("  <<%4d년%3d월>>\n", year, month);
		System.out.println(" SU MO TU WE TH FR SA");
		System.out.println("---------------------");

		// get weekday automatically
		int weekday = getWeekday(year, month, 1);

		// print blank space
		for (int i = 0; i < weekday; i++) {
			System.out.print("   ");
		}

		int maxDay = getmaxDaysOfMonth(year, month);
		int count = 7 - weekday;
		int delim = (count < 7) ? count : 0;
		for (int i = 1; i <= count; i++) {
			System.out.printf("%3d", i);
		}
		System.out.println();
		count++;
		// print from second line to last
		for (int i = count; i <= maxDay; i++) {
			System.out.printf("%3d", i);
			if (i % 7 == delim) {
				System.out.println();
			}
		}
		System.out.println();
	}

	private int getWeekday(int year, int month, int day) {
		int syear = 1970;
		final int STANDARD_WEEKDAY = 4; // 1970/1/1 Thursday
		int count = 0;

		for (int i = syear; i < year; i++) {
			int delta = isLeapYear(i) ? 366 : 365;
			count += delta;
		}

		for (int i = 1; i < month; i++) {
			int delta = getmaxDaysOfMonth(year, i);
			count += delta;
		}
		count += day - 1;

		int weekday = (count + STANDARD_WEEKDAY) % 7;
		return weekday;
	}

	public static void main(String[] args) throws ParseException {
		Calendar c = new Calendar();
		c.registerPlan("2024-06-23", "Let's work");
		System.out.println(c.searchPlan("2024-06-23").equals("Let's work"));
	}

}
