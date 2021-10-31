package project1.ver09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class PhoneBookManager{
	//정보(객체)를 저장할 부모타입의 객체배열
	PhoneInfo[] phoneInfos;
	//실제 저장된 연락처 갯수
	int numOfPhoneInfo;
	Scanner scan = new Scanner(System.in);
	
	
	Connection con;
	Statement stmt;
	ResultSet rs;
	PreparedStatement psmt;
	
	
	//생성자
	public PhoneBookManager() {
		//num의 크기의 객체배열 생성
		phoneInfos = new PhoneInfo[100];
		//최초 실행시 저장된 객체가 없으므로 0으로 초기화
		numOfPhoneInfo = 0;
	}
	
	//입력
	 public void dataInput() {
		 
			System.out.print("이름:");
			String name = scan.nextLine();
			System.out.print("전화번호:");
			String phone = scan.nextLine();
			System.out.print("생일:");
			String birth = scan.nextLine();
		try {
			ConnectDB();
			String query = "INSERT INTO phonebook_tb VALUES (seq_phonebook.nextval, ?, ?, ?)";
			
			//2.prepared객체생성 : 생성시 준비한 쿼리문을 인자로 전달한다.
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, name);
			psmt.setString(2, phone);
			psmt.setString(3, birth);
			
			phoneInfos[numOfPhoneInfo++]= new PhoneInfo(name,phone,birth);
			int data = psmt.executeUpdate();
			System.out.println(data +"행이 입력되었습니다.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	 
	 //삭제
	 public void dataDelete() {
		 try {
			 	ConnectDB();
				//1.쿼리문준비 : 값의 세팅이 필요한 부분을 ?(인파라미터)로 대체한다. 
				String query = "DELETE FROM phonebook_tb WHERE name=?";
				
				//2.prepared객체생성 : 생성시 준비한 쿼리문을 인자로 전달한다.
				psmt = con.prepareStatement(query);
				
				//3.입력할 내용을 사용자로부터 입력받는다. 
				System.out.print("삭제할 이름 입력:");
				String name = scan.nextLine();
				psmt.setString(1, name);
				int data = psmt.executeUpdate();
				System.out.println(data +"행이 삭제 되었습니다.");
				
				int deleteIndex = -1;
				
				for(int i=0 ; i<numOfPhoneInfo ; i++) {
					//삭제할 이름이 있는지 검색
					if(name.equals(phoneInfos[i].name)) {
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
				if (deleteIndex == -1) {
				}
				else {
					for (int i = deleteIndex; i < deleteIndex; i++) {
						phoneInfos[i] =phoneInfos[i+1];
					}
					System.out.println("자바 데이터 삭제가 완료되었습니다.");
				}
		 	}///try
					
			catch(Exception e) {
				System.out.println("알 수 없는 예외발생");
				e.printStackTrace();
			}
			finally {
				close();
			}

	}
	 
	 
	  public void dataAllShow()
	{
		try
		{
			ConnectDB();
			
			stmt = con.createStatement();
			
			String query = "SELECT * FROM phonebook_tb";
			//쿼리문 확인용
//			System.out.println("query : "+query);
			rs = stmt.executeQuery(query);
			
			//출력할 내용이 있는지 확인용 변수
			boolean flag = true;
			
			
			while (rs.next()) {
				String idx = rs.getString("idx");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String birth = rs.getString("birth");
				
				System.out.printf("%s 이름:%s  전화번호:%s 생일:%s\n",idx, name, phone, birth);
				flag = false;
			}
			
			if(flag==true)
			{
				System.out.println("저장된 연락처가 없습니다");
				return;
			}
			System.out.println("주소록에 저장된 정보가 출력되었습니다");
		}
		catch(SQLException e)
		{
			System.out.println("쿼리에 오류가 발생했습니다");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("에러가 발생했습니다");
			e.printStackTrace();
		}
	}//dataAllShow() 끝

	//검색
	public void dataSearch() {
		try {
			ConnectDB();
			
			stmt = con.createStatement();
			
			System.out.println("데이터 검색을 시작합니다.");
			System.out.print("찾는 이름: ");
			String search = scan.nextLine();
			
			String query = "SELECT * FROM phonebook_tb WHERE name='"+search+"'";
			//System.out.println(query);//query test
			boolean flag = true;
		
			
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String idx = rs.getString("idx");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String birth = rs.getString("birth");
				
				System.out.printf("%s 이름:%s  전화번호:%s 생일:%s\n",idx, name, phone, birth);
				flag = false;
			}
			
			if (flag==true) {
				System.out.println("검색 결과가 없습니다.");
				return;
			}
			System.out.println("검색이 완료되었습니다");
		}
		catch(SQLException e)
		{
			System.out.println("쿼리에 오류가 발생했습니다");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("에러가 발생했습니다");
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	public void printMenu() {
		System.out.println("선택하세요...");
		System.out.print("1. 데이터 입력 ");
		System.out.println("2. 데이터 검색");
		System.out.print("3. 데이터 삭제 ");
		System.out.println("4. 주소록 출력");
		System.out.print("5. 프로그램 종료");
		System.out.print("메뉴선택>>>");
	}
	
	
	public void ConnectDB() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin://@localhost:1521:xe",
					"kosmo", "1234");
			System.out.println("오라클 DB 연결성공");
		 }
		 catch (ClassNotFoundException e) {
			 System.out.println("오라클 드라이버 로딩 실패");
			 e.printStackTrace();
		 }
		 catch (SQLException e) {
			 System.out.println("DB 연결 실패");
			 e.printStackTrace();
		 }
		 catch (Exception e) {
			 System.out.println("알 수 없는 예외발생");
		 }
	}
	public void close() {
		try {
			if(stmt!=null) stmt.close();
			if(con!=null) con.close();
			if(rs!=null) rs.close();
			System.out.println("DB자원반납완료");
		}
		catch(SQLException e) {
			System.out.println("자원반납시 오류가 발생하였습니다.");
		}
	}//end of close
	
}
	
	
	
	
	
