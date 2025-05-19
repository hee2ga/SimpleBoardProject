package sec01.exam01;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BoardServiceExample {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		BoardService boardService=new BoardService();
		boardService.loadFromFile();
		boolean run=true;
		
		while(run) {
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("1. 글작성 | 2. 목록보기 | 3. 상세보기 | 4. 수정하기 | 5. 삭제하기 | 6. 파일저장 | 7. 종료");
			System.out.println("--------------------------------------------------------------------------------------------");
			
			System.out.print("선택 > ");
			String selectNo=scanner.nextLine();
			
			switch(selectNo) {
				case "1":
					System.out.println("[글작성]");
					String title="";
					while(title.isBlank()||title.length()>20) { // 제목 유효성 감사
						System.out.print("제목 : ");
						title=scanner.nextLine().trim(); 
						
						if(title.isBlank()) { // 제목 공백 검사
							System.out.println("⚠️제목을 입력해주세요.");
						}
						if(title.length()>20) { // 제목 글자수 검사
							System.out.println("⚠️제목은 최대 20자까지 입력할 수 있습니다.");
						}
					}
					
					String content="";
					while(content.isBlank()||content.length()>200) { // 내용 유효성 검사
						System.out.print("내용 : ");
						content=scanner.nextLine().trim(); 
						
						if(content.isBlank()) { // 내용 공백 검사
							System.out.println("⚠️내용을 입력해주세요.");
						}
						if(content.length()>200) { // 내용 글자수 검사
							System.out.println("⚠️내용은 최대 200자까지 입력할 수 있습니다.");
						}
					}
					
					String writer="";
					Set<String> nicknameSet=boardService.inputNickname(); // 저장된 닉네임 세트
					
					 while (true) {
				            System.out.print("닉네임 : ");
				            writer = scanner.nextLine().trim();

				            if (writer.isBlank()) { // 닉네임 공백검사
				                System.out.println("⚠️ 닉네임을 입력해주세요.");
				            } else if (!writer.matches("^[가-힣a-zA-Z0-9]{2,10}$")) { // 닉네임 유효성 검사
				                System.out.println("⚠️ 닉네임은 한글, 영어, 숫자만 사용하며 2~10자여야 합니다.");
				            } else if (nicknameSet.contains(writer)) { // 닉네임 중복검사
				                System.out.println("⚠️ 이미 사용 중인 닉네임입니다. 다른 닉네임을 입력해주세요.");
				            } else {
				                break;
				            }
				        }

				        System.out.println("사용 가능한 닉네임입니다: " + writer);
				    
					// 글 작성시 패스워드 입력
					String password="";
					while(true) {
						System.out.print("패스워드 입력 : ");
						password=scanner.nextLine().trim();
						
						int length=password.length();
						
						if(password.isBlank()) {
							System.out.println("⚠️패스워드를 입력해주세요.");
						}else if(length<4) {
							System.out.println("⚠️ 패스워드는 최소 4자 이상 입력하셔야 합니다.");
						}else if(length>10) {
							System.out.println("⚠️ 패스워드는 최대 10자까지 입력할 수 있습니다.");
						}else { // 유효한 패스워드일 경우
							break;
						}
					}
					
					// 글 작성시 패스워드 확인
					String password2="";
					while(true) {
						System.out.print("패스워드 확인 : ");
						password2=scanner.nextLine().trim();
						
						if(password2.equals(password)) {
							break;
						}
						
						System.out.println("패스워드를 다시 입력해주세요");
						System.out.print("패스워드 입력 : ");
						password=scanner.nextLine().trim();
					}
					
					boardService.registerBoard(title, content, writer,password);
					break;
					
				case "2":
					boardService.showList();
					break;
					
				case "3" :
					System.out.print("상세보기 하실 글 번호를 입력해주세요.");
					int showbno=Integer.parseInt(scanner.nextLine());
					boardService.showBoard(showbno);
					break;
				case "4":
					System.out.print("수정하실 글 번호를 입력해주세요.");
					int updateBno=Integer.parseInt(scanner.nextLine());
					System.out.print("수정하실 제목을 입력해주세요.");
					String updateTitle=scanner.nextLine();
					System.out.println("수정하실 내용을 입력해주세요.");
					String updateContent=scanner.nextLine();
					boardService.updateBoard(updateBno, updateTitle, updateContent);
					break;
				case "5":
					System.out.print("삭제하실 글 번호를 입력해주세요.");
					int deleteBno=Integer.parseInt(scanner.nextLine());
					boardService.deleteBoard(deleteBno);
					break;
				case "6":
					boardService.saveFile();
					break;
				case "7":
					boardService.saveFile();
					System.out.println("종료되었습니다.");
					run=false;
					break;
			}
		}
	}
	

}
