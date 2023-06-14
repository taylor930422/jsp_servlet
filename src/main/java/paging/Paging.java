package paging;

import java.util.ArrayList;
import java.util.List;

public class Paging {
	private Object data; // 화면에 출력할 페이지 데이터(TopN 조회 쿼리로 조회한 데이터)
	private List<Integer> pageList; // 전체 페이지 번호
	private int currentPageNumber = 1; // 현재 페이지 번호
	private int lastPageNumber; // 마지막 페이지 번호
	private int pageLimit = 10; // 화면에 출력할 목록 수
	private int listLimit = 5; // 화면에 출력할 페이지 번호 제한 수

	public Paging(Object data, int lastPageNumber) {
		this(data, 1, lastPageNumber);
		setPageList();
	}

	public Paging(Object data, int currentPageNumber, int lastPageNumber) {
		this.data = data;
		this.lastPageNumber = lastPageNumber;
		this.currentPageNumber = currentPageNumber;
	}

	public Paging(Object data, int currentPageNumber, int lastPageNumber, int pageLimit, int listLimit) {
		this(data, currentPageNumber, lastPageNumber);
		this.pageLimit = pageLimit;
		this.listLimit = listLimit;
		setPageList();
	}

	private void setPageList() {
		// 현재 페이지 번호, 마지막 페이지 번호, 페이지 번호 제한 수를 이용하여
		// 페이지 번호 목록을 생성
		// 현재 페이지 번호 : 1, 마지막 페이지 번호 : 10, 페이지 번호 제한 : 5
		// 만들어지는건 [1, 2, 3, 4, 5] 이다.
		// 현재 페이지 번호 : 3, 마지막 페이지 번호 : 10, 페이지 번호 제한 : 5
		// 만들어지는건 [3, 4, 5, 6, 7] 이다.
		// 현재 페이지 번호 : 7, 마지막 페이지 번호 : 10, 페이지 번호 제한 : 5
		// 만들어지는건 [7, 8, 9, 10] 이다.
		int start = 1;
		int end = listLimit;
		if (currentPageNumber > (listLimit / 2)) {
			start = currentPageNumber - (listLimit / 2);
		}
		end = start + listLimit - 1;

		if (end > lastPageNumber) {
			end = lastPageNumber;
		}

		this.pageList = new ArrayList<Integer>();
		for (int i = start; i <= end; i++) {
			pageList.add(i);
		}
	}

	public List<Integer> getPageList() {
		return this.pageList;

	}

	public Object getData() {
		return data;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public int getPrevPageNumber() {
		// 현재 페이지 3이면 이전 페이지가 2
		// 현재 페이지가 1이면 이전 페이지가 없다. '없음'을 나타내기 위해 0 -> -1로 변경하여 반환
		int prevPage = currentPageNumber - 1;
		return prevPage == 0 ? -1 : prevPage;
	}

	public int getNextPageNumber() {
		// 현재 페이지 3, 다음 페이지 4, 마지막 페이지 10
		// 현재 페이지10, 다음 페이지 11, 마지막 페이지 10 -> -1로 변경하여 반환
		int nextPage = currentPageNumber + 1;
		return nextPage > lastPageNumber ? -1 : nextPage;

	}

	public int getLastPageNumber() {
		return lastPageNumber;
	}

	public int getPageLimit() {
		return pageLimit;
	}
}
