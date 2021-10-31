package project1.ver08;


/*
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
public class AutoSaverT extends Thread{

	PhoneBookManager pm;
	
	//생성자
	public AutoSaverT(PhoneBookManager pm) {
		this.pm=pm;
	}
	
	@Override
	public void run() {
		
		try {
			while(true) {
				pm.saveTxt();
				sleep(5000);
				System.out.println("주소록이 텍스트로 자동저장되었습니다.");
				}
				
			}
		catch (InterruptedException e) {
		}
	}
}
	

	


