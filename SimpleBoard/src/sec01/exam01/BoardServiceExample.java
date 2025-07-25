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
		CheckField checkField = new CheckField(boardService);

		
		while(run) {
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("1. 글작성 | 2. 목록보기 | 3. 상세보기 | 4. 수정하기 | 5. 삭제하기 | 6. 파일저장 | 7. 종료");
			System.out.println("--------------------------------------------------------------------------------------------");
			
			System.out.print("선택 > ");
			String selectNo=scanner.nextLine();
			
			switch(selectNo) {
				case "1": // 글작성
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
					
				case "2": // 목록보기
					boardService.showList();
					break;
					
				case "3" : // 상세보기
					
					int showBno=0;
					boolean valid=false;
					
					while(!valid) {
						System.out.print("상세보기 하실 글 번호를 입력해주세요.");
						String strBno=scanner.nextLine();
						try {
							showBno=Integer.parseInt(strBno);
							boardService.showBoard(showBno);
							valid=true;
						}catch(NumberFormatException e) {
							System.out.println("⚠️ 숫자만 입력해주세요.");
						}
					}
					
					break;
				case "4": // 수정하기
					
					while(true) {
						
						System.out.print("수정하실 글 번호를 입력해주세요.");
						
						String strBno=scanner.nextLine();
						int updateBno;
						
						try {
							updateBno=Integer.parseInt(strBno);
						}catch(NumberFormatException e) {
							System.out.println("⚠️ 숫자만 입력해주세요.");
							continue;
						}
						
						String error1=checkField.checkBno(updateBno);
						if(error1!=null) {
							System.out.println(error1);
							continue;
						}
						
						while(true) {
							System.out.print("닉네임을 입력해주세요");
							String updateWriter=scanner.nextLine();
							String error2=checkField.checkNickname(updateBno,updateWriter);
							if(error2!=null) {
								System.out.println(error2);
								continue;
							}else {
								break;
							}
						}
						while(true) {
							System.out.print("비밀번호를 입력해주세요.");
							String updatePassword=scanner.nextLine();
							String error3=checkField.checkPassAndNickname(updateBno, updatePassword);
							if(error3!=null) {
								System.out.println(error3);
								continue;
							}else {
								break;
							}
						}
						System.out.println("--------------------------------------------------------------------------------------------");
						System.out.println("[수정하기]");
						
						String originTitle=boardService.loadOriginTitle(updateBno);
						System.out.println("제목  : "+originTitle);
						String updateTitle;
						
						while(true) {
							System.out.print("수정할 제목 : ");
							updateTitle=scanner.nextLine();
							String error4=checkField.checkTitle(updateTitle);
							if(error4!=null) {
								System.out.println(error4);
								continue;
							}else {
								break;
							}
						
						}
						
						String originContent=boardService.loadOriginContent(updateBno);
						System.out.println("내용 : "+originContent);
						String updateContent;
						
						while(true) {
							System.out.print("수정할 내용 : ");
							updateContent=scanner.nextLine();
							String error5=checkField.checkContent(updateContent);
							if(error5!=null) {
								System.out.println(error5);
								continue;
							}else {
								break;
							}
						}
						
						boardService.updateBoard(updateBno,updateTitle, updateContent);
						break;
						
					}
					break;
					
					
					
				case "5": // 삭제하기
					while(true) {
						System.out.print("삭제하실 글 번호를 입력해주세요.");
						String strBno=scanner.nextLine();
						int deleteBno;
						try {
							deleteBno=Integer.parseInt(strBno);
							
						}catch(NumberFormatException e) {
							System.out.println("⚠️ 숫자만 입력해주세요.");
							continue;
						}
						String error1=checkField.checkBno(deleteBno);
						if(error1!=null) {
							System.out.println(error1);
							continue;
						}
						
						while(true) { 
							System.out.print("닉네임을 입력해주세요");
							String updateWriter=scanner.nextLine();
							String error2=checkField.checkNickname(deleteBno,updateWriter);
							if(error2!=null) {
								System.out.println(error2);
								continue;
							}
							break;
						}
						
						while(true) {
							System.out.print("비밀번호를 입력해주세요.");
							String updatePassword=scanner.nextLine();
							String error3=checkField.checkPassAndNickname(deleteBno, updatePassword);
							if(error3!=null) {
								System.out.println(error3);
								continue;
							}
							break;
						}
					
						while(true) { 
							 System.out.print("삭제하시겠습니까?(Y/N): ");
					            String answer = scanner.nextLine().trim();

					            if (answer.equalsIgnoreCase("Y")) {
					                boardService.deleteBoard(deleteBno);
					                break; // 삭제 후 내부 while 탈출
					            } else if (answer.equalsIgnoreCase("N")) {
					                System.out.println("삭제요청이 취소되었습니다.");
					                break;
					            } else {
					                System.out.println("⚠️ Y 또는 N만 입력해주세요.");
					            }
					        }

					        break; // 외부 while 탈출
					    }
					    break;
					
				case "6": // 저장하기
					boardService.saveFile();
					break;
				case "7": // 종료하기
					boardService.saveFile();
					System.out.println("종료되었습니다.");
					run=false;
					break;
				
				default:
					System.out.println("다시 선택해주세요.");
			}
		}
	}
	

}
