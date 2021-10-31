package project1.ver08;

public class MenuSelectException extends Exception {
	public MenuSelectException() {
		super("1~6이외의 정수를 입력하였습니다.");
	}
}
