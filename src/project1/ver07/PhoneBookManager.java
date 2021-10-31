package project1.ver07;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import project1.ver06.SubMenuItem;


public class PhoneBookManager {
	
	HashSet<PhoneInfo> hashSet = new HashSet<PhoneInfo>();
	Iterator<PhoneInfo> itr= hashSet.iterator();
	Scanner scan = new Scanner(System.in);
	//생성자
	public PhoneBookManager() {
	}
	
	//입력
	public void dataInput(int select) {

		Scanner scan = new Scanner(System.in);
		System.out.println("이름:"); String name = scan.nextLine();
		if(duplCheck(name)) return;
		
		System.out.println("전화번호:");String phoneNumber = scan.nextLine();
		
		switch(select) {
		
		case SubMenuItem.NORMAL:
			System.out.println("생년월일:"); String birthday = scan.nextLine();
			PhoneInfo s = new PhoneInfo(name, phoneNumber, birthday);
			hashSet.add(s);
			break;
			
		case SubMenuItem.SCHOOL: //학교
			System.out.println("학과:"); String major = scan.nextLine();
			System.out.println("학년:"); int grade = scan.nextInt();
			PhoneSchoolInfo s2 = new PhoneSchoolInfo(name, phoneNumber, major, grade);
			hashSet.add(s2);
			break;
			
		case SubMenuItem.COMPANY://회사
			System.out.println("회사:"); String company = scan.nextLine();
			PhoneCompanyInfo s3 = new PhoneCompanyInfo(name, phoneNumber, company);
			hashSet.add(s3);
			break;
		}
		System.out.println("===입력이 완료되었습니다===");
		
	}
	
	//중복확인
	public boolean duplCheck(String name) {
		for(PhoneInfo pi : hashSet) {
			if(name.equals(pi.name)) {
				System.out.println("이미 저장된 데이터입니다.");
				System.out.println("덮어쓸까요? Y(y) / N(n)");
				try {
					int cover=System.in.read();
					
					if(cover=='Y'||cover=='y') {
						System.out.println("입력한 정보가 저장되었습니다.");
						//삭제후 저장
						hashSet.remove(pi);
						scan.nextLine();
						return false;
					}
					
					else if(cover=='N'||cover=='n') {
						return true;
					}
					
					else {
						System.out.println("**잘못된 입력입니다**");
						return true;
					}
				}
				
				catch(Exception e){
					System.out.println("**에러 발생**");
					System.out.println("초기 화면으로 돌아갑니다");
					return true;
					}
				}
			}
			return false;
	}
	
	//전체 출력
	public void dataAllShow() {
		System.out.println("[전체 정보출력]");
		for(PhoneInfo pi : hashSet) {
			pi.showPhoneInfo();
		}
		System.out.println("==전체정보가 출력되었습니다==");
	} 

	//검색
	public void dataSearch() {
		Iterator<PhoneInfo> itr= hashSet.iterator();
		boolean isFind = false;//검색한 정보가 있는지 확인하기 위한 변수
		System.out.print("검색할 이름을 입력하세요:");
		String searchName = scan.nextLine();

		while(itr.hasNext()) {
			PhoneInfo pi=itr.next();
		if(searchName.equals(pi.name)) {
				System.out.println("정보가 검색되었습니다");
				pi.showPhoneInfo();
				isFind = true;//찾는 정보가 있다면 true로 변경
				return;
			}
		}		
		if(isFind==false) {
			System.out.println("***찾는 정보가 없습니다.***");
	}
}
	
	
	public void dataDelete() {
		Iterator<PhoneInfo> itr= hashSet.iterator();
		System.out.print("삭제할 이름을 입력하세요:");
		String deleteName = scan.nextLine();
		boolean flag= false;
		while(itr.hasNext()) {
			
			PhoneInfo pi= itr.next();
			
			if(deleteName.equals(pi.name)) {
				System.out.println("삭제완료");
				hashSet.remove(pi);
				flag=true;
				return;
				
			}
		}
		if(flag==false) {
			System.out.println("없는 이름입니다.");
		}
		
	}////end of dataDelete
	public void printMenu() {
		System.out.println("선택하세요...");
		System.out.print("1. 데이터 입력 ");
		System.out.println("2. 데이터 검색");
		System.out.print("3. 데이터 삭제 ");
		System.out.println("4. 주소록 출력");
		System.out.println("5. 프로그램 종료");
		System.out.print("메뉴선택>>>");
	}
	}
