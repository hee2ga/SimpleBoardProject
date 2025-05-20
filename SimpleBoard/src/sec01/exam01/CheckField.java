package sec01.exam01;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CheckField {
	private List<Board>list=new ArrayList<Board>();
	private BoardService boardService;
	
	public CheckField(BoardService boardService) {
        this.boardService = boardService;
    }
	
	public BoardService getBoardService() {
        return boardService;
    }
	
	public Set<String> getNicknames() {
        return boardService.inputNickname(); // 필요한 곳에서 호출
    }
	
	
	// 제목 유효성 검사(공백X, 20자 이내)
	public String checkTitle(String title) {
		if(title.isBlank()) {
			return "⚠️제목을 입력해주세요.";
		}
		if(title.length()>20) {
			return "⚠️제목은 최대 20자까지 입력할 수 있습니다.";
		}
		return null; // 유효한 제목
	}
	
	// 내용 유효성 검사(공백X, 200자 이내)
	public String checkContent(String content) {
		
		if(content.isBlank()) {
			return "⚠️ 내용을 입력해주세요.";
		}
		if(content.length()>200) {
			return "⚠️ 내용은 최대 200자까지 입력할 수 있습니다.";
		}
		return null;
	}
	
	// 닉네임 유효성 검사(공백X, 중복X)
	public String checkWriter(String writer) {
		
		if(writer.isBlank()) {
			return "⚠️ 닉네임을 입력해주세요.";
		}
		if(!writer.matches("^[가-힣a-zA-Z0-9]{2,10}$")) {
			return "⚠️ 닉네임은 한글, 영어, 숫자만 사용하며 2~10자여야 합니다.";
		}
		if(boardService.inputNickname().contains(writer)) {
			return "⚠️ 이미 사용 중인 닉네임입니다. 다른 닉네임을 입력해주세요.";
		}
		return null;
	}
	
	// 패스워드 유효성 검사(공백X, 4자에서 10자 이내)
	public String checkPass(String password) {
		
		if(password.isBlank()) {
			return"⚠️ 패스워드를 입력해주세요.";
		}
		if(password.length()<4) {
			return "⚠️ 패스워드는 최소 4자 이상 입력하셔야 합니다.";
		}
		if(password.length()>10) {
			return "⚠️ 패스워드는 최대 10자까지 입력할 수 있습니다.";
		}
		return null;
		
	}
	
	// 패스워드 확인검사(입력한 패스워드와 확인 패스워드 같은지 검사)
	public String checkPassTopass(String password,String checkPassword) {
		
		if(!password.equals(checkPassword)) {
			return "⚠️ 비밀번호가 일치하지 않습니다.";
		}
		return null;
	}
	
	// 글번호에 해당하는 닉네임 존재하는지 
	public String checkNickname(int bno,String writer) {
		
		for(Board board:list) {
			if(board.getBno()==bno) {
				if(!board.getWriter().equals(writer)) {
					return "⚠️ 접근 권한이 없습니다.";
				}
			}
		}
		return null;
	}
	
	// 닉네임과 패스워드 일치검사(닉네임 검사 후 실행)
	public String checkPassAndNickname(int bno,String password) {
		for(Board board:list) {
			if(board.getBno()==bno) {
				if(!board.getPassword().equals(password)){
					return "⚠️ 패스워드가 일치하지않습니다.";
				}
			}
		}
		return null;

		
		
	}
	
	// 글번호 존재하는지
	public String checkBno(int bno) {
		Board board=boardService.getBoardBybno(bno);
		System.out.println(board);
		if(board==null) {
			return "⚠️ 해당번호의 게시물이 없습니다.";
		}
		return null;
		
	}

}
