package step8_01.atm_v1;

import java.util.Scanner;

public class UserManager {
	
	Scanner scan = new Scanner(System.in);
	User[] user = null;								// user 배열 : 유저 배열
	int userCount = 0;								// userCount : 유저 수
		
	void printAllUser() {							// 모든 유저의 모든 계좌 정보
		for(int i = 0; i < userCount; i++) {
			user[i].printAccount();
		}
	}
	
	
	
	void addUser() {								// 유저 추가
		
		if(userCount == 0) {						// 유저가 한 명도 없으면
			user = new User[1];						// 유저에 유저 배열 1개 생성
		}
		else {										// 유저가 한 명 이상이면
			User[] temp = user;						// 기본 유저값을 temp에 복사
			user = new User[userCount + 1];			// 유저에 기존 배열보다 1개 추가 생성
			for(int i = 0; i < userCount; i++) {	// 새로만든 유저 배열에 기존 값 대입
				user[i] = temp[i];
			}
			temp = null;							// temp 초기화
		}
		
		
		System.out.print("[가입] 아이디를 입력하세요 : ");	// 추가할 아이디 입력
		String id = scan.next();							// 입력 받을 객체 생성
		
		boolean isDuple = false;							// 판별객체 생성 = false
		for (int i = 0; i < userCount; i++) {					
			if (user[i].id.equals(id)) { 					//입력받은 id가 user[i].id 와 맞으면 == 기존의 아이디와 존재하면
				isDuple = true;								// isduple = true
			}
		}
		if (!isDuple) {										//isduple 이 false 의 반대 true 면 (=가입된아이디가아니라면)
			user[userCount] = new User();					// 유저[유저수] = 생성자 생성
			user[userCount].id = id;						// 유저[유저수]의 아이디에 받아온 아이디 대입
			System.out.println("[메시지] ID : '" + id+ "' 가입 되었습니다.\n");	// 가입 완성 메세지 출력
			userCount++;									// 유저수 증가
		}
		else {												// isDuple이 false면 (=이미 가입된 아이디라면)
			System.out.println("[메시지] '"+ id + "'은 이미 가입된 아이디 입니다.\n");	// 부적합한 아이디임을 메세지 출력
		}
		
	}
	
	
	
	User getUser(int idx) {									// 겟유저 (= 받아올 유저 )
		
		return user[idx];									// 반환 : 유저[인덱스]
	}
	
	
	
	
	int logUser() {												// 로그인 유저
		
		int identifier = -1;									// 판별 객체 생성
		System.out.print("[입력] 아이디를 입력하세요 : ");		// 아이디 입력 메세지 출력
		String name = scan.next();								// 아이디를 받아올 객체 생성
		
		for (int i = 0; i < userCount; i++) {					// for
			if (name.equals(user[i].id)) {						// 만약에 아이디가 존재하는 아이디라면
				identifier = i;									// 판별 객체에 해당하는 아이디의(유저의) 인덱스 대입
				System.out.println("[" + user[identifier].id + "] 님 로그인.\n");	// 로그인 성공 메세지 출력
			}
		}
		
		return identifier;										// 판별객체 반환 ( = 유저의 인덱스 )
		
	}
	
	
	
	void leave() {														// 탈퇴
		
		System.out.print("[입력] 탈퇴할 아이디를 입력하세요 : ");		// 탈퇴할 아이디를 입력하는 메세지 출력
		String name = scan.next();										// 아이디를 받아올 객체 생성
		int identifier = -1;											// 판별 객체 생성
		for (int i = 0; i < userCount; i++) {							// for
			if (name.equals(user[i].id)) {								// 입력받은 아이디가 존재하는 아이디라면
				identifier = i;											// 판별 객체에 해당하는 인덱스 대입
			}
		}
		
		if(identifier == -1) {											// 판별객체가 -1이라면 (=존재하지않는 아이디라면)
			System.out.println("[메시지] 아이디를 다시 확인하세요.");	// 잘못된 아이디임을 출력하는 메세지
			return;														// 리턴
		}
		
		System.out.println("ID : '" +user[identifier].id + "' 가 탈퇴되었습니다.");	// 아이디 탈퇴 성공 메세지 출력
		
		User[] temp = user;												// temp배열에 유저를 복사
		user = new User[userCount - 1];									// 유저배열에 기본 유저수보다 -1 줄여서 새로 생성
		
		for(int i = 0; i < identifier; i++) {							// for	삭제한 인덱스 전까지
			user[i] = temp[i];											// 유저배열에 기존데이터 대입
		}
		for(int i =identifier; i < userCount-1; i++) {					// for 삭제한 인덱스 부터 유저카운트 -1 까지 ( 탈퇴했기때문에 -1)
			user[i] =temp[i + 1];										// 유저배열 삭제인덱스 부터 삭제한 데이터 다음 데이터부터 대입
		}	
		
		userCount--;													// 유저수 감소
		
	}
	
}
