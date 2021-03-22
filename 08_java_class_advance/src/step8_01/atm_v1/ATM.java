package step8_01.atm_v1;
import java.util.Random;
import java.util.Scanner;

public class ATM {
	
	Scanner scan = new Scanner(System.in);
	Random ran   = new Random();
	UserManager userManager = new UserManager();		// 유저매니저 생성자
	int identifier = -1; 								// 판별 객체 생성
	
	void printMainMenu() {								// 메인메뉴 프린트 (로그인하기전 반복실행)
				
		while (true) {
			
			System.out.println("\n[ MEGA ATM ]");
			System.out.print("[1.로그인] [2.로그아웃] [3.회원가입] [4.회원탈퇴] [0.종료] : ");
			int sel = scan.nextInt();
			
			if      (sel == 1) 	    login();
			else if (sel == 2) 		logout();
			else if (sel == 3) 		join();
			else if (sel == 4) 		leave();
			else if (sel == 0) 		break;
			
		}
		
		System.out.println("[메시지] 프로그램을 종료합니다.");
		
	}
	
	
	void login() {										// 로그인
		
		identifier = userManager.logUser();				// 판별객체에 유저매니저의 로그유저 메소드 실행
		
		if (identifier != -1) {							// 리턴받은 identifier가 -1가 아니라면
														// ( = 만약에 로그인에 성공했다면 )
			printAccountMenu();							// printAccountMenu 메소드 실행
		}
		else {											// 리턴받은 identifier가 -1이라면
			System.out.println("[메세지] 로그인실패.");	// 로그인 실패 메세지 출력
		}
		
	}
	
	
	void join() {					// 회원가입
		
		userManager.addUser();		// 유저매니저의 addUser 메소드 실행
	}
	
	
	void logout() {													// 로그아웃
		
		if (identifier == -1) {										// 로그인이 안되어있다면
			System.out.println("[메시지] 로그인을 하신 후 이용하실 수 있습니다."); 
		}															// 로그인 후 이용 가능 메세지 출력
		else {														// 로그인 되어있다면 
			identifier = -1;										// 판별객체 다시 -1 대입
			System.out.println("[메시지] 로그아웃 되었습니다.");	// 로그아웃 성공 메세지 출력
		}
		
	}
	
	
	void leave() {				// 삭제
		
		userManager.leave();	// 유저매니저의 leave 메소드 실행
		
	}
	
	
	void printAccountMenu() {	// 프린트 어카운트 메뉴 ( 로그인 성공 시 실행 되는 메소드 )
		
		while (true) {  		// 무한루프
			
			System.out.print("[1.계좌생성] [2.계좌삭제] [3.조회] [0.로그아웃] : ");
			int sel = scan.nextInt();	// 위의 메뉴의 값을 받아올 객체 생성
			
			String makeAccount = Integer.toString(ran.nextInt(90001) + 10000); // 랜덤 계좌 번호 생성
			 
			
			if (sel == 1) { 													// 계좌 생성
				if (userManager.user[identifier].accCount == 0) { 				// 해당되는 아이디의 계좌가 없으면
					userManager.user[identifier].acc = new Account[1];			// 유저매니저 계좌 배열 한개 생성
					
					userManager.user[identifier].acc[0] = new Account();		// 새로 만든 배열에 생성자
					userManager.user[identifier].acc[0].number = makeAccount;  	// 랜덤으로 만든 계좌번호 넣기
				
				}
				else { 																		// 계좌가 이미 있으면
					Account[] temp = userManager.getUser(identifier).acc; 					// temp 배열에 현재아이디 해당하는 계좌를 넣음 ( 복사 )
					int tempAccCount = userManager.getUser(identifier).accCount; 			// tempAccCount객체에 계좌 갯수 넣음 ( 복사 )
					userManager.user[identifier].acc = new Account[tempAccCount+1]; 		// 계좌 배열 추가 + 1
					for (int i = 0; i < tempAccCount; i++) {								// for문으로 데이터 집어넣기
						userManager.user[identifier].acc[i] = temp[i];						// 계좌번호 배열에 기존 계좌 넣기
					}
					userManager.user[identifier].acc[tempAccCount] = new Account();			// 생성자 생성
					userManager.user[identifier].acc[tempAccCount].number = makeAccount; 	// 새로운 계좌 넣기
					
				}
				userManager.user[identifier].accCount++;									// 계좌 갯수 추가
				System.out.println("[메시지]'"+makeAccount +"'계좌가 생성되었습니다.\n");	// 계좌생성 메세지 출력
			} 	
			else if (sel == 2) { 													// 계좌삭제
				
				if (userManager.user[identifier].accCount == 0) { 					// 해당되는 아이디의 계좌가 없으면
					System.out.println("[메시지] 더 이상 삭제할 수 없습니다.\n");	// 삭제 불가 메세지 출력
					continue;														// 다음 코드 무시하고 다시 처음으로
				}
				
				if ( userManager.user[identifier].accCount == 1) { 					// 해당되는 아이디의 계좌가 한개면
					System.out.println("[메시지] 계좌번호 :'"+ userManager.user[identifier].acc[0].number+"' 삭제 되었습니다.\n");
					userManager.user[identifier].acc = null;						// 배열 초기화
				}
				else {                                                        		// 해당되는 아이디의 계좌가 여러개면
					
					System.out.print("삭제 하고 싶은 계좌 번호를 입력하세요 : ");
					String deleteAccount = scan.next();                      	 	// deleteAccount : 삭제하고싶은 계좌번호
					int tempAccCount = userManager.user[identifier].accCount; 		// 계좌 갯수를 temp에 복사
					int delIdx = -1;                                         		// 삭제 인덱스
					for (int i = 0; i < tempAccCount; i++) {                 		// 있는 계좌중에 삭제하고싶은 계좌가 일치한지 확인
						if (deleteAccount.equals(userManager.user[identifier].acc[i].number)) { // 일치하면
							delIdx = i;                                      		// 해당되는 인덱스 넣기
						}
					}
					
					if ( delIdx == -1 ) {                                       	// 잘못된 계좌번호를 입력하면
						System.out.println("[메시지] 계좌번호를 확인하세요.\n");	// 잘못된 계좌임을 출력
						continue;													// 다음 코드 무시하고 다시 처음으로
					}
					else {                                                       	// 존재하는 계좌번호와 삭제할 계좌번호가 같으면
						System.out.println("[메시지] 계좌번호 :'"+ userManager.user[identifier].acc[delIdx].number+"' 삭제 되었습니다.\n");
						
						Account[] temp = userManager.user[identifier].acc;      	 	// temp 에 복사
						userManager.user[identifier].acc = new Account[tempAccCount-1]; // 배열 인덱스 줄이기
						
						
						for (int i = 0; i < delIdx; i++) {                        		// temp 에 넣어둔 계좌를
							userManager.user[identifier].acc[i] = temp[i];				// 삭제한 계좌를 제외하고
						}																// 데이터 넣기
						for (int i = delIdx; i < tempAccCount - 1; i++) {		
							userManager.user[identifier].acc[i] = temp[i+1];
						}
					}
					
				}
				userManager.user[identifier].accCount--;							// 해당되는 유저 계좌 카운트 감소
				
			}
			
			else if (sel == 3) {     												// 조회하기
				if (userManager.user[identifier].accCount == 0) {					// 생성된 계좌가 없으면
					System.out.println("[메시지] 생성된 계좌가 없습니다.\n");		// 계좌가 없는 메세지 출력
				}
				else {																// 생성된 계좌가 있으면
					userManager.user[identifier].printAccount();					// 존재하는 계좌 출력
				}
			}   
			else if (sel == 0) {	// 로그아웃
				logout();			// 로그아웃 함수 실행
				break;
			}
			
		}
		
	}	
}
