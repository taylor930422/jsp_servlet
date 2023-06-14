package model.dto;

import java.sql.Date;
// 시, 분, 초까지는 util.Date , 날짜만 나오는건 sql.Date를 import 해야한다.

public class BoardDTO {

	private int id;
	private String btype;
	private String title;
	private String writer;
	private String context;
	private Date createDate;
	private Date updateDate;
	private int viewCnt;
	private int recCnt;
	private int nRecCnt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public int getRecCnt() {
		return recCnt;
	}

	public void setRecCnt(int recCnt) {
		this.recCnt = recCnt;
	}

	public int getnRecCnt() {
		return nRecCnt;
	}

	public void setnRecCnt(int nRecCnt) {
		this.nRecCnt = nRecCnt;
	}

//	@Override
	/*
	 * public String toString() { return "BoardDTO [id=" + id + ", btype=" + btype +
	 * ", title=" + title + ", writer=" + writer + ", context=" + context +
	 * ", createDate=" + createDate + ", updateDate=" + updateDate + ", viewCnt=" +
	 * viewCnt + ", recCnt=" + recCnt + ", nRecCnt=" + nRecCnt + "]"; }
	 */

}
