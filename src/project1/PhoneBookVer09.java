package project1;

import java.util.Scanner;

import project1.ver09.PhoneBookManager;
import project1.ver09.PhoneInfo;


/*
계정은 KOSMO 를 사용한다. 


입력 : dataInput()
PreparedStatement 클래스 이용
검색 : dataSearch()
Statement 클래스 이용
삭제 : dataDelete()
PreparedStatement 클래스 이용

위 부분을 DML문을 이용하여 구현한다. 
입력은 insert, 검색은 like를 이용한 select, 삭제는 delete로 구현하면 된다.

 */
public class PhoneBookVer09 {

	public static void main(String[] args) {
		PhoneBookManager manager=new PhoneBookManager();
		Scanner scanner =new Scanner(System.in);
		manager.ConnectDB();
	
		while(true) {
			manager.printMenu();
			int choice = scanner.nextInt();
			switch(choice) {
			case 1:
				manager.dataInput();
				break; 
			case 2:
				//System.out.println("데이터 검색");
				manager.dataSearch();
				break;
			case 3:
				//System.out.println("삭제");
				manager.dataDelete();
				break;
			case 4:
				//System.out.println("주소록 출력");
				manager.dataAllShow();
				break;
			case 5:
				System.out.println("프로그램종료");
				return;//main메서드의 종료이므로 프로그램 자체의 종료로 이어진다.
			}
			
		}
		}
	}


