package project1;

import java.util.Scanner;

import project1.ver03.PhoneBookManager;
import project1.ver03.PhoneInfo;


/*
 객체배열을 이용해서, 프로그램 사용자가 입력하는 정보가 
 최대 100개까지 유지되도록 프로그램을 변경하시오.
그리고 다음과 같은 기능을 추가로 삽입한다.
-저장 : 이름, 전화번호, 생년월일 정보를 대상으로 저장의 과정을 진행한다.
-검색 : 이름을 기준으로 데이터를 찾아서 해당 데이터를 출력한다.
-삭제 : 이름을 기준으로 데이터를 찾아서 삭제의 과정을 진행한다.
단 배열의 중간에 저장된 데이터를 삭제할경우 해당 요소의 뒤에 저장된 요소들을 
한칸씩 앞으로 이동시키는 형태로 삭제를 진행한다.
[1,2,3,4,5] 좌측배열에서 인덱스 3을 지웠다면 [1,2,4,5] 처럼 되어야 한다.
 
끝으로 저장, 검색, 삭제의 기능을 담당하는 PhoneBookManager클래스를 정의해서 프로그램을 완성하자.

멤버메소드명 
메뉴출력 : printMenu()
입력 : dataInput()
검색 : dataSearch()

삭제 : dataDelete()
주소록전체출력 : dataAllShow()

실행결과>
[실행결과]

선택하세요...
1. 데이터 입력
2. 데이터 검색
3. 데이터 삭제
4. 주소록 출력
5. 프로그램 종료
선택: 1
데이터 입력을 시작합니다..
이름: 낙자
전화번호: 010-7906-3900
생년월일: 751130
데이터 입력이 완료되었습니다.
 
선택하세요...
1. 데이터 입력
2. 데이터 검색
3. 데이터 삭제
4. 주소록 출력
5. 프로그램 종료
선택: 1
데이터 입력을 시작합니다..
이름: 미르
전화번호: 4120
생년월일: 780521
데이터 입력이 완료되었습니다.
 
선택하세요...
1. 데이터 입력
2. 데이터 검색
3. 데이터 삭제
4. 주소록 출력
5. 프로그램 종료
선택: 2
데이터 검색을 시작합니다..
이름: 낙자

name: 낙자
phone: 3900
birth: 751130
데이터 검색이 완료되었습니다.
 
선택하세요...
1. 데이터 입력
2. 데이터 검색
3. 데이터 삭제
4. 주소록 출력
5. 프로그램 종료
선택: 3
데이터 삭제를 시작합니다..
이름: 낙자
데이터 삭제가 완료되었습니다.
 
선택하세요...
1. 데이터 입력
2. 데이터 검색
3. 데이터 삭제
4. 주소록 출력
5. 프로그램 종료
선택: 5
프로그램을 종료합니다.
 */
public class PhoneBookVer03 {

	public static void main(String[] args) {
		PhoneInfo phoneInfo= new PhoneInfo(null, null, null);
		PhoneBookManager manager=new PhoneBookManager(100);
		Scanner scanner =new Scanner(System.in);
	
			/*
	    1. 데이터 입력
		2. 데이터 검색
		3. 데이터 삭제
		4. 주소록 출력
		5. 프로그램 종료
		*/
		while(true) {
		    phoneInfo.printMenu();
			int choice = scanner.nextInt();
			switch(choice) {
			case 1:
				manager.dataInput();
				break;//break문을 만나면 switch문을 탈출한다. 
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


