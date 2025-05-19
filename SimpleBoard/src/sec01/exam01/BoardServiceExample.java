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
		CheckField checkField=new CheckField();
		
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
					while(true) {
						System.out.print("제목 : ");
						title=scanner.nextLine().trim();
						
						String error=checkField.checkTitle(title);
						if(error==null) break;// 유효성 통과
						System.out.println(error);
					}
					
					String content="";
					while(true) { // 내용 유효성 검사
						System.out.print("내용 : ");
						content=scanner.nextLine().trim(); 
						
						String error=checkField.checkContent(content);
						if(error==null) break;
						System.out.println(error);
					}
					
					String writer="";
					
					 while (true) {
				            System.out.print("닉네임 : ");
				            writer = scanner.nextLine().trim();

				            String error=checkField.checkWriter(writer);
				            if(error==null) break;
				            System.out.println(error);
				            
				        }
				    
					// 글 작성시 패스워드 입력
					String password1="";
					String password2="";
					while(true) {
						System.out.print("패스워드 입력 : ");
						password1=scanner.nextLine().trim();
						
						String error1=checkField.checkPass(password1);
						
						if(error1!=null) {
							System.out.println(error1);
							continue;
						}
						
						// 패스워드 확인
					    System.out.print("패스워드 확인 : ");
					    password2 = scanner.nextLine().trim();

					    String error2 = checkField.checkPassTopass(password1, password2);
					    if (error2 != null) {
					        System.out.println(error2);
					        continue; // 두 비밀번호가 다르면 다시 입력
					    }
					    

					    break; // 모든 조건 통과 루프 종료
					}
					
				
					
					boardService.registerBoard(title, content, writer,password1);
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
					System.out.print("닉네임을 입력해주세요");
					String updateWriter=scanner.nextLine();
					System.out.print("비밀번호를 입력해주세요.");
					String updatePassword=scanner.nextLine();
					System.out.print("수정하실 제목을 입력해주세요.");
					String updateTitle=scanner.nextLine();
					System.out.println("수정하실 내용을 입력해주세요.");
					String updateContent=scanner.nextLine();
					boardService.updateBoard(updateBno,updateWriter,updatePassword, updateTitle, updateContent);
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
