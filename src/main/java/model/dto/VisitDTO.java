package model.dto;

public class VisitDTO {
	private int id;
	private String context;
	private String nickname;

	// 데이터에 저장할 데이터(멤버변수)를 만들어준다.
	// Mapper.xml에 있는 #{문자열}이랑 멤버변수명과 동일해야한다.
	// DTO 만들때는 캡슐화 적용해서 만들어야한다. (게터 세터 만들어야됨)

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "VisitDTO [id=" + id + ", context=" + context + ", nickname=" + nickname + "]";
	}

}
