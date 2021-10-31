package project1.ver08;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
/*
8단계[IO / 쓰레드] : PhoneBookVer08.java
컬렉션 기반으로 변경후 인스턴스를 저장하기 위해 IO(입출력)을 적용하자.
ObjectInputStream, ObjectOutputStream 클래스를 기반으로 제작한다.
파일의 저장은 프로그램을 종료하는 시점에 이루어져야 하고, 
프로그램 시작 직후 전체정보를 조회하면 기존에 입력된 정보들이 출력되어야 한다.
저장될파일명 : PhoneBook.obj

쓰레드를 통해  주소록이 텍스트 형식으로 자동저장 될 수 있도록 기능을 추가한다. 
메인메뉴에 ‘5.저장옵션’ 을 추가한다. 
저장옵션의 서브메뉴로 1.자동저장On, 2.자동저장Off 를 추가한다. 
1번을 선택시 쓰레드가 시작된다. -> start()
2번을 선택시 쓰레드가 중지된다. -> interrupt()
만약 이미 자동저장이 실행중인데 다시 1번을 선택하면 ‘이미 자동저장이 실행중입니다’ 라고 경고메세지를 띄워준다.
자동저장은 5초에 한번씩 이루어진다.
해당 쓰레드는 프로그램 종료시 같이 종료되는 데몬쓰레드로 생성한다. 
저장될파일명 : AutoSaveBook.txt
쓰레드로 정의할 클래스명 : AutoSaverT.java

 */

import project1.ver06.SubMenuItem;


public class PhoneBookManager {
	
	HashSet<PhoneInfo> hashSet = new HashSet<PhoneInfo>();
	Iterator<PhoneInfo> itr= hashSet.iterator();
	Scanner scan = new Scanner(System.in);
	
	 File dataFile=
			new File("src/project1/ver08/PhoneBook.obj");
	
	//생성자
	public PhoneBookManager() {
	readPhoneInfo();
	}
	
	public void printMenu() {
		System.out.println("===============메뉴를 선택하세요===============");
		System.out.println("1.주소록입력 2.검색 3.삭제 4.출력 5.저장옵션 6.종료");
		System.out.println("==========================================");
		System.out.print("메뉴선택:");
		
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
	
	//자동저장
	public void dataAutoSave(AutoSaverT auto) {
		System.out.println("자동저장옵션을 선택하시오.");
		System.out.print("1.자동저장 on 2.자동저장 off: ");
		int choice = 0;
		try {
			choice = scan.nextInt();
		}
		catch (InputMismatchException e) {
			System.out.println("1이나 2를 입력하세요");
			return;
		}
		
		if(choice==1) {
			if(!auto.isAlive()) {
				//스레드가 시작되었고 아직 끝나지 않았으면 true 끝났으면 false 반환
				
				auto.setDaemon(true);
				auto.start();
				System.out.println("==자동저장되었습니다==");
			}
			else {
				System.out.println("이미 자동저장 실행중...");
			}
		}///choice==1
		if(choice==2) {
			//자동저장 off이므로  쓰레드 interrupt
			if(auto.isAlive()) {
				auto.interrupt();
				System.out.println("==자동저장을 종료합니다==");
			}
			
		}
		else {
		}
	}
	
	//삭제
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

	//종료시 저장(직렬화)
	public void savePhoneInfo() {
		try {
			ObjectOutputStream out =
					new ObjectOutputStream(
							new FileOutputStream("src/project1/ver08/PhoneBook.obj"));
			
			Iterator itr = hashSet.iterator();
			while(itr.hasNext())
			{
				Object object = itr.next();
				out.writeObject(object);	
			}		
		}
	catch(Exception e)
	{
		System.out.println("**에러발생**");
		e.printStackTrace();
		return;
	}
}////end of savePhoneInfo
	
	//시작시 복원(역직렬화)
	 public void readPhoneInfo() {
		 try {
				ObjectInputStream in = 
						new ObjectInputStream(
								new FileInputStream("src/project1/ver08/PhoneBook.obj"));
						
				while(true) {
					PhoneInfo pi = (PhoneInfo)in.readObject();
					hashSet.add(pi);
					if (pi == null) break;
				}
				in.close();
			}
	 
		catch (ClassNotFoundException e) {
			System.out.println("클래스 없음");
		}
		catch (FileNotFoundException e) {
			System.out.println("파일 없음");
		}
		catch (IOException e) {}
		catch (Exception e) {
			System.out.println("더 이상 읽을 정보가 없습니다.");
		}
		System.out.println("복원완료");
	 }////end of readPhoneInfo
	 
	 public void saveTxt() {
			
			try {
				PrintWriter out = new PrintWriter(
						new FileWriter("src/project1/ver08/PhoneBook.txt"));
				
				
				for (PhoneInfo pi :hashSet) {
					out.println(pi.toString());
				}
				out.close();
			}
			catch (IOException e) {
				System.out.println("==입출력 예외 발생==");
			}
			catch (Exception e) {
				System.out.println("==예외 발생==");
			}
		}
	 
}////end of PhoneBookManager
