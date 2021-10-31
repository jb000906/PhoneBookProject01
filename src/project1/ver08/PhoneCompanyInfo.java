package project1.ver08;

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
	
	@Override
	public String toString() {
		return "이름 : " + name + "\n전화번호 : " + phoneNumber 
				+ "\n회사명 : " + company + "\n";
	}
}
