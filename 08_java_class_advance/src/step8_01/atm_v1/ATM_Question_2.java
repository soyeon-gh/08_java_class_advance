package step8_01.atm_v1;
import java.util.Random;
import java.util.Scanner;

public class ATM_Question_2 {
	
	Scanner scan = new Scanner(System.in);
	Random ran   = new Random();
	UserManager userManager = new UserManager();
	int identifier = -1;
	
	void printMainMenu() {
				
		while (true) {
			
			System.out.println("\n[ MEGA ATM ]");
			System.out.print("[1.로그인] [2.로그아웃] [3.회원가입] [4.회원탈퇴] [0.종료] : ");
			int sel = scan.nextInt();
			
			if      (sel == 1) 	    login();
			else if (sel == 2) 		logout();
			else if (sel == 3) 		join();
			else if (sel == 4) 		leave();
			else if (sel == 0) {
				System.out.println("[메시지] 프로그램을 종료합니다.");
				break;
			}
			
		}
		
	}
	
	
	
	void login() {
		
		identifier = userManager.logUser();
		
		if (identifier != -1) {
			printAccountMenu();
		}
		else {
			System.out.println("[메세지] 로그인실패.");
		}
		
	}
	
	
	
	void join() {	
		
		userManager.addUser();
		
	}
	
	
	
	void logout() {
		
		if (identifier == -1) {
			System.out.println("[메시지] 로그인을 하신 후 이용하실 수 있습니다.");
		}
		else {
			identifier = -1;
			System.out.println("[메시지] 로그아웃 되었습니다.");
		}
		
	}
	
	
	
	void leave() {
		
		userManager.leave();
		
	}
	
	
	
	void printAccountMenu() {
		
		while (true) {
			System.out.print("[1.계좌생성] [2.계좌삭제] [3.조회] [0.로그아웃] : ");
			int sel = scan.nextInt();
		 		
			String randomAccNumber = Integer.toString(ran.nextInt(90001) + 10000);
			if 		(sel == 1) { // 계좌 생성
				if (userManager.user[identifier].accCount == 0) { // 계좌가 없으면
					userManager.user[identifier].acc = new Account[1];
					userManager.user[identifier].acc[0] = new Account();
					userManager.user[identifier].acc[0].number = randomAccNumber;
				}
				
				// 계좌가 이미 있으면
				// temp 배열에 현재아이디 해당하는 계좌를 넣음 ( 복사 )
				// tempAccCount객체에 계좌 갯수 넣음 ( 복사 )
				// 계좌 배열 추가 + 1
				// for문으로 데이터 집어넣기
				// 계좌번호 배열에 기존 계좌 넣기
				// 생성자
				// 새로운 계좌 넣기
				
				else {
					Account[] tempAcc = userManager.getUser(identifier).acc;
					int tempAccCount = userManager.getUser(identifier).accCount;
					 userManager.user[identifier].acc = new Account[tempAccCount + 1];
					
					 for (int i = 0; i < tempAccCount; i++) {
						 userManager.user[identifier].acc[i] = tempAcc[i];
					}
					 userManager.user[identifier].acc[tempAccCount] = new Account();
					 userManager.user[identifier].acc[tempAccCount].number = randomAccNumber;
					 
				}
				
				userManager.user[identifier].accCount++;
				System.out.println("계좌생성되었습니다.");
				System.out.println("계좌번호 : " + randomAccNumber);
				
			} 	
			else if (sel == 2) { // 계좌 삭제

				// 해당되는 아이디의 계좌가 없으면
				// 삭제 불가 메세지 출력

				if (userManager.user[identifier].accCount == 0) {
					System.out.println("계좌번호가 존재하지않습니다.");
					continue;
				}
				
				// 해당되는 아이디의 계좌가 한개면
				// 배열 초기화
				
				if(userManager.user[identifier].accCount == 1) {
					userManager.user[identifier].acc = null;
				}
				
				
				// 해당되는 아이디의 계좌가 여러개면
				// deleteAccount : 삭제하고싶은 계좌번호
				// 계좌 갯수를 temp에 복사
				// 삭제 인덱스
				// 있는 계좌중에 삭제하고싶은 계좌가 일치한지 확인
				// 일치하면
				// 해당되는 인덱스 넣기

				else {
					System.out.print("삭제하고싶은 계좌번호를 입력해주세요 : ");
					String deleteAccount = scan.next();
					int tempAccCount = userManager.user[identifier].accCount;
					int delidx = -1;
					
					for (int i = 0; i < tempAccCount; i++) {
						if (deleteAccount.equals(userManager.user[identifier].acc[i].number)) {
							delidx = i;
						}
					}
					
					 // 잘못된 계좌번호를 입력하면
					if (delidx == -1) {
						System.out.println("잘못된 계좌번호입니다");
						System.out.println("계좌번호를 확인해주세요");
						continue;
					}
					
					// 존재하는 계좌번호와 삭제할 계좌번호가 같으면
					// temp 에 복사

					// 배열 인덱스 줄이기
					// temp 에 넣어둔 계좌를
					// 삭제한 계좌를 제외하고
					// 데이터 넣기
					// 해당되는 유저 계좌 카운트 감소
					
					else {
						
						Account[] tempAcc = userManager.getUser(identifier).acc;
						
						userManager.user[identifier].acc = new Account[tempAccCount - 1];
						
						for (int i = 0; i < delidx; i++) {
							userManager.user[identifier].acc[i] = tempAcc[i];
						}
						for (int j = delidx; j < tempAccCount - 1; j++) {
							userManager.user[identifier].acc[j] = tempAcc[j+1];
						}
						
					}
				}
				userManager.user[identifier].accCount--;
			} 	
			else if (sel == 3) { // 조회

				
				// 생성된 계좌가 없으면
				// 계좌가 없는 메세지 출력
				if (userManager.user[identifier].accCount == 0) { // 계좌가 없으면
					System.out.println("계좌가 존재하지않습니다.");
					continue;
				}
				// 생성된 계좌가 있으면
				// 존재하는 계좌 출력
				else {
					userManager.user[identifier].printAccount();
				}
			}  	
			else if (sel == 0) { // 로그아웃
				System.out.println("로그아웃을 누르셨습니다");
				System.out.println("로그아웃됩니다");
				break;
			} 	
		}
		
	}	
}
