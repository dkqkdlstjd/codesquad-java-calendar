package choi.calendar;

import java.text.ParseException;
import java.util.Scanner;

public class Prompt {

	public void printMenu() {
		System.out.println("+----------------------+");
		System.out.println("| 1. 일정 등록            ");
		System.out.println("| 2. 일정 검색            ");
		System.out.println("| 3. 달력 보기            ");
		System.out.println("| h. 도움말 q. 종료       ");
		System.out.println("+----------------------+");

	}

	/**
	 * 
	 * @param week:
	 * @return 0~6 (0 = Sunday, saturday=6)
	 */
	public int parseDay(String week) {
		switch (week) {
		case "su":
			return 0;
		case "mo":
			return 1;
		case "tu":
			return 2;
		case "we":
			return 3;
		case "th":
			return 4;
		case "fr":
			return 5;
		case "sa":
			return 6;
		default:
			return 0;
		}

	}

	public void runprompt() throws ParseException {
		printMenu();
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		while (true) {
			System.out.println("명령(1, 2, 3, h, q)");
			String cmd = scanner.next();

			boolean isLoop = true;
			while (isLoop) {
				switch (cmd) {
				case "1":
					cmdRegister(scanner, cal);
					break;
				case "2":
					cmdSearch(scanner, cal);
					break;
				case "3":
					cmdCal(scanner, cal);
					break;
				case "4":
					printMenu();
					break;
				case "h":
					break;
				case "q":
					isLoop = false;
					break;
				}
			}
			System.out.println("Thank you. bye");
			scanner.close();
		}
	}

	private void cmdCal(Scanner s, Calendar c) {
		int month = 1;
		int year = 2017;

		System.out.println("년도를 입력하세요.(exit : -1)");
		System.out.print("Year>");
		year = s.nextInt();

		System.out.println("달을 입력하세요");
		System.out.print("Month>");
		month = s.nextInt();

		if (month > 12 || month < 1) {
			System.out.println("잘못된 입력입니다.");
			return;
		}
		c.printCalendar(year, month);

	}

	private void cmdSearch(Scanner s, Calendar c) {
		// TODO Auto-generated method stub
		System.out.println("[일정 검색]");
		System.out.println("날짜를 입력해 주세요(yyyy-MM-dd).");
		String date = s.next();
		String plan = " ";
		try {
			plan = c.searchPlan(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("일정 검색 중 오류가 발생했습니다.");
		}
		System.out.println(plan);
	}

	private void cmdRegister(Scanner scanner, Calendar cal) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println("[새 일정 등록]");
		System.out.println("날짜를 입력해 주세요(yyyy-MM-dd).");
		String date = scanner.next();
		String text = "";
		System.out.println("일정을 입력해 주세요.(문장의 끝에;을 입력해 주세요)");
		while (true) {
			String word = scanner.next();
			text += word + " ";
			if (word.endsWith(";")) {
				break;
			}
		}
	}

	public static void main(String[] args) throws ParseException {
		// 셀 실행
		Prompt p = new Prompt();
		p.runprompt();
	}

}
