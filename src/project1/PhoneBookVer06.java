package project1;

import java.util.InputMismatchException;
import java.util.Scanner;

import project1.ver06.MenuItem;
import project1.ver06.PhoneBookManager;
import project1.ver06.PhoneInfo;
import project1.ver06.MenuSelectException;

/*
/*
 6단계[예외처리] : PhoneBookVer06.java
-예외처리1 : 메뉴를 입력할 때 1~5이외의 정수를 입력했을경우 
MenuSelectException(개발자정의) 예외를 발생시키고 이를 적절히 처리해보자.
-예외처리2 : 메뉴를 선택할 때 정수대신 문자열을 입력하면 InputMismatchException 예외가 발생될것이다. 
이를 적절히 예외처리 해보자.
-예외처리3 : 이클립스에서는 검색결과가 없을때 NullPointerException이 발생한다. 
이를 적절히 처리해보자.(테스트시 발생여부가 확인되지 않는다면 그냥 넘어간다)

조건
- 메뉴입력은 nextInt(); 를 사용한다.
- 예외가 발생할 경우 메시지를 띄워주고 메뉴를 재선택 하도록 처리한다. 즉 프로그램이 종료되지 않도록 한다.

 */
 
public class PhoneBookVer06 {

	public static void main(String[] args) {
		PhoneInfo phoneInfo= new PhoneInfo(null, null, null);
		PhoneBookManager manager=new PhoneBookManager(100);
		Scanner scanner =new Scanner(System.in);
	
		while(true) {
			int choice=0;
		    phoneInfo.printMenu();
		    try {
			choice = scanner.nextInt();
			if(choice<=0&&choice>5) {
				MenuSelectException ex = new MenuSelectException();
				throw ex;	
			}
		    }
		    catch(InputMismatchException e) {
				System.out.println("1~5의 정수를 입력하시오");
				scanner.nextLine();
			}
			catch (MenuSelectException e) {
				System.out.println("1~5이외의 정수를 입력하였습니다.");
			}	
		    catch(NullPointerException e) {
		    	System.out.println("검색결과가 없습니다");
		    }
			
		    
		    
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


