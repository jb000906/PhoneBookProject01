package project1.ver05;

public class PhoneCompanyInfo extends PhoneInfo{
	
	String company;
	
	public PhoneCompanyInfo(String name, String phoneNumber, String company) 
	{
		super(name, phoneNumber);
		this.company=company;
	}
	public void showPhoneInfo()
	{
		System.out.println("이름:"+name);
		System.out.println("전화번호:"+phoneNumber);
		System.out.println("회사:"+company);
		
	}
}
