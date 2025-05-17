package sec01.exam01;

import java.util.Scanner;

public class BoardServiceExample {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		BoardService boardService=new BoardService();
		boardService.loadFromFile();
		boolean run=true;
		
		while(run) {
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("1. 글작성 | 2. 목록보기 | 3. 상세보기 | 4. 수정하기 | 5. 삭제하기 | 6. 파일저장 | 7. 종료");
			System.out.println("-----------------------------------------------------------------------------");
			
			System.out.print("선택 > ");
			String selectNo=scanner.nextLine();
			
			switch(selectNo) {
				case "1":
					System.out.println("[글작성]");
					System.out.print("제목 : ");
					String title=scanner.nextLine();
					System.out.print("내용 : ");
					String content=scanner.nextLine();
					System.out.print("글쓴이 : ");
					String writer=scanner.nextLine();
					boardService.registerBoard(title, content, writer);
					break;
					
				case "2":
					System.out.println("[글 목록]");
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
