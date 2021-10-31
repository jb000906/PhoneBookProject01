package project1;

import java.util.Scanner;

import project1.ver04.PhoneBookManager;
import project1.ver04.PhoneInfo;


/*
4단계[상속] : PhoneBookVer04.java
-PhoneInfo 클래스에서 생일과 관련된 멤버변수와 코드를 삭제한다.
-다음 2개의 클래스를 추가한다.
#  Class PhoneSchoolInfo extends PhoneInfo{
String 전공;
Int 학년;
}
#  Class PhoneCompanyInfo extends PhoneInfo {
String 회사명;
}
-프로그램의 흐름을 담당하는  PhoneBookManager 클래스를 정의한다.
-나머지는 실행결과를 보고 확인하도록 한다.

실행결과>
선택하세요...
1. 데이터 입력
2. 데이터 검색
3. 데이터 삭제
4. 주소록 출력
5. 프로그램 종료
선택: 1

데이터 입력을 시작합니다..
1.일반, 2.동창, 3.회사
선택>> 1

이름: 홍길동
전화번호: 1111
데이터 입력이 완료되었습니다.
 
선택하세요...
1. 데이터 입력
2. 데이터 검색
3. 데이터 삭제
4. 주소록 출력
5. 프로그램 종료
선택: 1
데이터 입력을 시작합니다..
1.일반, 2.동창, 3.회사
선택>> 2

이름: 이순신
전화번호: 2222
전공: 거북선제조
학년: 100
데이터 입력이 완료되었습니다.
 
선택하세요...
1. 데이터 입력
2. 데이터 검색
3. 데이터 삭제
4. 주소록 출력
5. 프로그램 종료
선택: 1
데이터 입력을 시작합니다..
1.일반, 2.대학, 3.회사
선택>> 3

이름: 세종대왕
전화번호: 3333
회사: 집현전
데이터 입력이 완료되었습니다.
 
선택하세요...
1. 데이터 입력
2. 데이터 검색
3. 데이터 삭제
4. 주소록 출력
5. 프로그램 종료
선택: 5
프로그램을 종료합니다.

 */
public class PhoneBookVer04 {

	public static void main(String[] args) {
		PhoneInfo phoneInfo= new PhoneInfo(null, null, null);
		PhoneBookManager manager=new PhoneBookManager(100);
		Scanner scanner =new Scanner(System.in);
	
		while(true) {
		    phoneInfo.printMenu();
			int choice = scanner.nextInt();
			switch(choice) {
			case 1:
				System.out.println("데이터 입력을 시작합니다..");
				System.out.println("1.일반, 2.동창, 3.회사");
				System.out.print("선택>>");
				int select = scanner.nextInt();
				manager.dataInput(select);
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


