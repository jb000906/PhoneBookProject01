package project1;

import java.util.Scanner;

import project1.ver05.MenuItem;
import project1.ver05.PhoneBookManager;
import project1.ver05.PhoneInfo;

/*
5단계[인터페이스] : PhoneBookVer05.java
기존 프로그램은 메뉴입력을 할 때 이름이 부여된 상수가 아닌 정수로 처리되고 있다.
때문에 Ver05 에서는 interface 기반의 상수표현에 의한 처리를 해보도록 한다. 
이를 통하여 코드는 보다 명확해 질 것이다.

1.데이터입력
2.데이터검색
3.데이터삭제
4.출력
5.프로그램종료

1.일반
2.학교동창
3.회사동료
위의 메뉴를 interface형 상수로 처리해본다.
 */
public class PhoneBookVer05 {

	public static void main(String[] args) {
		PhoneInfo phoneInfo= new PhoneInfo(null, null, null);
		PhoneBookManager manager=new PhoneBookManager(100);
		Scanner scanner =new Scanner(System.in);
	
		while(true) {
		    phoneInfo.printMenu();
			int choice = scanner.nextInt();
			switch(choice) {
			case MenuItem.INPUT:
				System.out.println("데이터 입력을 시작합니다..");
				System.out.println("1.일반, 2.동창, 3.회사");
				System.out.print("선택>>");
				int select = scanner.nextInt();
				manager.dataInput(select);
				break;//break문을 만나면 switch문을 탈출한다. 
			case MenuItem.SEARCH:
				//System.out.println("데이터 검색");
				manager.dataSearch();
				break;
			case MenuItem.DELETE:
				//System.out.println("삭제");
				manager.dataDelete();
				break;
			case MenuItem.DATASHOW:
				//System.out.println("주소록 출력");
				manager.dataAllShow();
				break;
			case MenuItem.EXIT:
				System.out.println("프로그램종료");
				return;//main메서드의 종료이므로 프로그램 자체의 종료로 이어진다.
			}
			
		}
		}
	}


