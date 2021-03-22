package step8_01.atm_v1;

public class Account {
	
	String number = "";		// 계좌 번호 
	int money = 0;			// 계좌 금액
	
	void printOwnAccount() {	// 계좌 정보 출력 , 계좌 번호 : 계좌 금액
		System.out.println(number +  " : " + money);
	}
	
}
