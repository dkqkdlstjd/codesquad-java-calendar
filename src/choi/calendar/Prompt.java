package choi.calendar;

import java.util.Scanner;

public class Prompt {

	private final static String PROMPT = "cal>";

	public void runprompt() {
		String PROMPT = "cal>";
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		System.out.println("반복횟수를 입력하세요.");

		int month = 1;
		int year = 1;

		while (true) {
			
			System.out.println("년도를 입력하세요");
			System.out.print(PROMPT);
			year = scanner.nextInt();
			System.out.println("달을 입력하세요");
			System.out.print(PROMPT);
			month = scanner.nextInt();
			// cal.printSampleCalendar();
			if (month == -1) {
				break;
			}
			if (month > 12) {
				continue;
			}
			cal.printCalendar(year, month);
		}
		System.out.println("bye");
	}

	public static void main(String[] args) {
		// 셀 실행
		Prompt p = new Prompt();
		p.runprompt();

	}

}
