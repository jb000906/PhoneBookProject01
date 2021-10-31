package project1.ver04;

import java.util.Scanner;


public class PhoneBookManager{
	//정보(객체)를 저장할 부모타입의 객체배열
	private PhoneInfo[] phoneInfos;
	//실제 저장된 연락처 갯수
	private int numOfPhoneInfo;
	
	//생성자
	public PhoneBookManager(int num) {
		//num의 크기의 객체배열 생성
		phoneInfos = new PhoneInfo[num];
		//최초 실행시 저장된 객체가 없으므로 0으로 초기화
		numOfPhoneInfo = 0;
	}
	public void dataInput(int select) {
		
		Scanner scan = new Scanner(System.in);
		
		if(select==1) {	
			System.out.println("이름:"); String name = scan.nextLine();
			System.out.println("전화번호:");String phoneNumber = scan.nextLine();
			System.out.println("생년월일:"); String birthday = scan.nextLine();
			PhoneInfo normal= new PhoneInfo(name,phoneNumber,birthday);
			phoneInfos[numOfPhoneInfo++]=normal;
		}
		else if(select==2) { //학교
			System.out.println("이름:"); String name = scan.nextLine();
			System.out.println("전화번호:");String phoneNumber = scan.nextLine();
			System.out.println("학과:"); String major = scan.nextLine();
			System.out.println("학년:"); int grade = scan.nextInt();
			PhoneSchoolInfo school= new PhoneSchoolInfo(name,phoneNumber, major, grade);
			phoneInfos[numOfPhoneInfo++]=school;			
		}
		else if(select==3) {//회사
			System.out.println("이름:"); String name = scan.nextLine();
			System.out.println("전화번호:");String phoneNumber = scan.nextLine();
			System.out.println("회사:"); String company = scan.nextLine();
			PhoneCompanyInfo firm=new PhoneCompanyInfo(name, phoneNumber, company);
			phoneInfos[numOfPhoneInfo++]=firm;
		}
		System.out.println("정보 입력이 완료되었습니다.");
	}
	 
//주소록 전체 출력
	public void dataAllShow() {
		for(int i=0 ; i<numOfPhoneInfo ; i++) {
			phoneInfos[i].showPhoneInfo();
		}
		System.out.println("==전체정보가 출력되었습니다==");
	} 
	
	//검색
	public void dataSearch() {
		boolean isFind = false;//검색한 정보가 있는지 확인하기 위한 변수
		Scanner scan = new Scanner(System.in);
		System.out.print("검색할 이름을 입력하세요:");
		String searchName = scan.nextLine();
		
		//객체배열에 저장된 정보의 갯수만큼 반복
		for(int i=0 ; i<numOfPhoneInfo ; i++) {
			/*
			검색을 위해 입력한 이름과 각 인덱스에 저장된 객체의 name변수의
			비교를 통한 문자열검색을 진행한다. 
			 */
			if(searchName.compareTo(phoneInfos[i].name)==0) {
				//일치하는 이름이 있으면 정보를 출력한다. 
				phoneInfos[i].showPhoneInfo();
				System.out.println("**귀하가 요청하는 정보를 찾았습니다.**");
				isFind = true;//찾는 정보가 있다면 true로 변경
			}
		}		
		if(isFind==false)
			System.out.println("***찾는 정보가 없습니다.***");
	}
	
	//삭제
	public void dataDelete() {
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제할 이름을 입력하세요:");
		String deleteName = scan.nextLine();
		
		int deleteIndex = -1;
		
		 for(int i=0 ; i<numOfPhoneInfo ; i++) {
			//삭제할 이름이 있는지 검색
			if(deleteName.compareTo(phoneInfos[i].name)==0) {
				//객체를 삭제하기 위해 null값으로 변경한다. 
				phoneInfos[i] = null;
				//삭제된 요소의 index를 저장한다. 
				deleteIndex = i;
				//전체카운트를 1 차감한다. 
				numOfPhoneInfo--;
				//하나의 객체를 삭제했다면 즉시 for문 탈출
				break;
			}
		}
		 if(deleteIndex==-1) {
				//검색된 데이터가 없어 삭제되지 않았다면 -1을 유지한다.
				System.out.println("==삭제된 데이터가 없습니다==");
			}
		else {
			
			for(int i=deleteIndex ; i<numOfPhoneInfo ; i++) {
				//phoneInfos[i] = phoneInfos[i+1];
			}
			System.out.println("==데이터("+ deleteIndex
					+"번)가 삭제되었습니다==");
		}
	}////end of deleteInfo
}
	
	
	
	
	
