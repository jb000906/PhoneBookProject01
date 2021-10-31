package project1;

import java.util.InputMismatchException;
import java.util.Scanner;

import project1.ver07.MenuSelectException;
import project1.ver07.MenuItem;
import project1.ver07.PhoneBookManager;
import project1.ver07.PhoneInfo;


 
public class PhoneBookVer07 {

	public static void main(String[] args) {
		PhoneBookManager manager=new PhoneBookManager();
		Scanner scanner =new Scanner(System.in);
	
		while(true) {
			int choice=0;
			manager.printMenu();
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
