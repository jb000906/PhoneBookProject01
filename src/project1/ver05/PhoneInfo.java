package project1.ver05;

import java.util.Scanner;

public class PhoneInfo {
	String name;//이름	
	String phoneNumber;//전화번호 
	String birthday;//생년월일
	
	//3개의 매개변수를 가진 생성자
	public PhoneInfo(String name, String phoneNumber, String birthday) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
	}
	
	//2개의 매개변수를 가진 생성자 오버로딩
	public PhoneInfo(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthday = "입력되지않음";
	}
	public void showPhoneInfo()
	{
		System.out.println("이름:"+name);
		System.out.println("전화번호:"+phoneNumber);
		System.out.println("생년월일:"+birthday);
	}
	public void dataInput() {
		Scanner scan = new Scanner(System.in);
		System.out.print("이름:"); name = scan.nextLine();
		System.out.print("전화번호:");phoneNumber = scan.nextLine();
		System.out.print("생년월일:"); birthday = scan.nextLine();
		showPhoneInfo();
	}
	public static void printMenu() {
		System.out.println("선택하세요...");
		System.out.print("1. 데이터 입력 ");
		System.out.println("2. 데이터 검색");
		System.out.print("3. 데이터 삭제 ");
		System.out.println("4. 주소록 출력");
		System.out.println("5. 프로그램 종료");
		System.out.print("메뉴선택>>>");
	}
}
