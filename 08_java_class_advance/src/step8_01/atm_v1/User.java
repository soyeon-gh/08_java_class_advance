package step8_01.atm_v1;

public class User {
	
	String id;		// 아이디
	int accCount;	//계좌 갯수
	Account[] acc; 	// 계좌 배열
	
	void printAccount() { // 모든 계좌정보 출력
		
		for (int i = 0; i < accCount; i++) {	
			acc[i].printOwnAccount();
		}	
		
	}
	
}
