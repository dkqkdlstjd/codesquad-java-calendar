package choi.calendar;

import java.text.ParseException;
import java.util.Scanner;

public class Prompt {
	private Scanner scanner;
	private Calendar calendar;

	public void printMenu() {
		System.out.println("+----------------------+");
		System.out.println("| 1. 일정 등록            ");
		System.out.println("| 2. 일정 검색            ");
		System.out.println("| 3. 달력 보기            ");
		System.out.println("| h. 도움말 q. 종료       ");
		System.out.println("+----------------------+");

	}

	public Prompt() {
		scanner = new Scanner(System.in);
		calendar = new Calendar();
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

	public void runPrompt() {
		printMenu();
		boolean isRunning = true;

		while (isRunning) {
			System.out.println("명령을 입력하세요(1, 2, 3, h, q): ");
			String cmd = scanner.next();
			switch (cmd) {
			case "1":
				try {
					cmdRegister(scanner, calendar);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "2":
				cmdSearch(scanner, calendar);
				break;
			case "3":
				cmdCal(scanner, calendar);
				break;
			case "h":
				printMenu();
				break;
			case "q":
				isRunning = false;
				break;
			default:
				System.out.println("올바른 명령을 입력하세요.");
				break;
			}
		}

		System.out.println("프로그램을 종료합니다.");
		scanner.close();
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
		PlanItem plan;
		plan = c.searchPlan(date);
		if (plan != null) {
			System.out.println(plan.detail);
		} else {
			System.out.println("일정이 없습니다.");
		}

	}

	private void cmdRegister(Scanner scanner, Calendar cal) throws ParseException {
		System.out.println("[새 일정 등록]");
		System.out.println("날짜를 입력해 주세요(yyyy-MM-dd).");
		String date = scanner.next();
		String text ="";
		System.out.println("일정을 입력해 주세요.(문장의 끝에 ;을 입력해 주세요)");
		String word;
		while(!(word=scanner.next()).endsWith(";")) {
			text+= word+" ";
		}
		word =word.replace(";", "");
		text +=word;
		cal.registerPlan(date, text);
	}

	public static void main(String[] args) throws ParseException {
		// 셀 실행
		Prompt p = new Prompt();
		p.runPrompt();
	}

}
