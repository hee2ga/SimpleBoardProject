package sec01.exam01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoardService {
	private List<Board>list=new ArrayList<Board>();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private int nextId = 1;
	
	// 글 작성하기
	public void registerBoard(String title, String content,String writer,String password) {
		Date now=new Date();
		list.add(new Board(nextId++, title, content, writer, sdf.format(now),password));
		System.out.println("\n게시글을 작성완료했습니다 😊");
	}
	
	// 닉네임 중복검사를 위한 기존에 저장된 닉네임 세트
	public Set<String> inputNickname() {
		Set<String>nickNameSet=new HashSet<String>();
		for(Board board:list) {
			nickNameSet.add(board.getWriter());
		}
		return nickNameSet;
	}
	
	// 목록 보기
	public void showList() {
	    if (list.isEmpty()) {
	        System.out.println("게시글이 없습니다.");
	        return;
	    }

	    System.out.println("[게시글 목록]");
	    System.out.printf("%-6s | %-20s | %-30s | %-10s | %-20s\n",
	                      "글번호", "제목", "내용", "닉네임", "날짜");
	    System.out.println("--------------------------------------------------------------------------------------------");
	    
	    for (Board board : list) {
	        System.out.printf("%-6d | %-20s | %-30s | %-10s | %-20s\n",
	                          board.getBno(),
	                          cutString(board.getTitle(), 10),
	                          cutString(board.getContent(), 10),
	                          board.getWriter(),
	                          board.getDate());
	    }
	    System.out.println();
	}

	// 말줄임표 메소드
	private String cutString(String str, int maxLength) {
	    if (str.length() > maxLength) {
	        return str.substring(0, maxLength - 3) + "...";
	    } else {
	        return str;
	    }
	}
	
	// 글번호 찾기
	public Board getBoardBybno(int bno) {
		for(Board board:list) {
			if(board.getBno()==bno) return board;
		}
		return null;
	}
	// 상세보기
	public void showBoard(int bno) {
		Board board=getBoardBybno(bno);
		if(board==null) {
			System.out.println("해당 번호의 게시글이 없습니다.");
			return;
		}
		System.out.println("[게시글 상세보기]");
		System.out.println("글번호 : "+board.getBno());
		System.out.println("제목 : "+board.getTitle());
		System.out.println("내용 : "+board.getContent());
		System.out.println("닉네임 : "+board.getWriter());
		System.out.println("날짜 : "+board.getDate());
	}
	
	
	// 수정하기
	public void updateBoard(int bno,String newTitle, String newContent) {
		
		Date now=new Date();
		
		Board board=getBoardBybno(bno);
		board.setTitle(newTitle);
		board.setContent(newContent);
		board.setDate(sdf.format(now));
		System.out.println("수정이 완료되었습니다.");
		
	}
	
	// 삭제하기
	public void deleteBoard(int bno) {
		Board board=getBoardBybno(bno);
		if(board==null) {
			System.out.println("해당 번호의 게시글이 없습니다.");
			return;
		}
		list.remove(board);
		System.out.println("삭제가 완료되었습니다.");
	}
	
	// 객체를 파일로 저장(직렬화)
	public void saveFile() {
		try {
			FileOutputStream fos=new FileOutputStream("C:/Temp/board.db");
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(list);
			System.out.println("파일이 저장완료되었습니다.");
			oos.close();
		}catch(Exception e) {
			System.out.println("파일 저장 오류 : " +e.getMessage());
		}
	}
	
	// 파일에서 객체 읽기(역직렬화)-목록에 뿌리기용
	public void loadFromFile() {
		try {
			FileInputStream fis=new FileInputStream("C:/Temp/board.db");
			ObjectInputStream ois=new ObjectInputStream(fis);
			list=(List<Board>)ois.readObject();
			for(Board board:list) {
				nextId=Math.max(nextId,board.getBno()+1); // 다음에 쓸 글번호 설정(글번호 중복X)
			}
			System.out.println("파일 불러오기 완료되었습니다.");
		}catch(FileNotFoundException e) {
			System.out.println("저장된 파일이 없습니다.");
		}catch(IOException|ClassNotFoundException e) {
			System.out.println("불러오기 오류"+e.getMessage());
		}
		
	}

	
}
