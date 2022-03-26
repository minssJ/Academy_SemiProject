package BOOKSTORE;

public class Client {

	private String bName;	// 이름
	private String bBirth;	// 생년월일
	private String bPhone;	// 연락처
	private String bId;		// 아이디(pk)
	private String bPw;		// 비번
	private String bEmail;	// 이메일
	private String bAddr;	// 주소
	private int balance;	// 잔액
	
	private String bookName;	// 책이름(pk)
	private int price;			// 가격
	private String dId;			// 아이디(fk)
	private String dDate;		// 출판일
	private String writer;		// 저자
	private String genre;		// 장르
	private	int dStock;			// 재고
	
	private String lId;		// 아이디(fk)
	private String lbName;;	// 책이름(fk)
	private int buyNum;		// 구매수량
	
	private String rvName;	// 책이름(fk)
	private String rPost;	// 후기작성
	private int rvStar;		// 별점
	private String rvDate;	// 후기등록일
	
	private String rsId;	// 아이디(fk)
	private String rsBname;	// 예약할 도서이름
	
	public String getbEmail() {
		return bEmail;
	}
	public void setbEmail(String bEmail) {
		this.bEmail = bEmail;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public String getbBirth() {
		return bBirth;
	}
	public void setbBirth(String bBirth) {
		this.bBirth = bBirth;
	}
	public String getbPhone() {
		return bPhone;
	}
	public void setbPhone(String bPhone) {
		this.bPhone = bPhone;
	}
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
	public String getbPw() {
		return bPw;
	}
	public void setbPw(String bPw) {
		this.bPw = bPw;
	}
	public String getbAddr() {
		return bAddr;
	}
	public void setbAddr(String bAddr) {
		this.bAddr = bAddr;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getdId() {
		return dId;
	}
	public void setdId(String dId) {
		this.dId = dId;
	}
	public String getdDate() {
		return dDate;
	}
	public void setdDate(String dDate) {
		this.dDate = dDate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getdStock() {
		return dStock;
	}
	public void setdStock(int dStock) {
		this.dStock = dStock;
	}
	public String getlId() {
		return lId;
	}
	public void setlId(String lId) {
		this.lId = lId;
	}
	public String getLbName() {
		return lbName;
	}
	public void setLbName(String lbName) {
		this.lbName = lbName;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public String getRvName() {
		return rvName;
	}
	public void setRvName(String rvName) {
		this.rvName = rvName;
	}
	public String getrPost() {
		return rPost;
	}
	public void setrPost(String rPost) {
		this.rPost = rPost;
	}
	public int getRvStar() {
		return rvStar;
	}
	public void setRvStar(int rvStar) {
		this.rvStar = rvStar;
	}
	public String getRvDate() {
		return rvDate;
	}
	public void setRvDate(String rvDate) {
		this.rvDate = rvDate;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getRsId() {
		return rsId;
	}
	public void setRsId(String rsId) {
		this.rsId = rsId;
	}
	public String getRsBname() {
		return rsBname;
	}
	public void setRsBname(String rsBname) {
		this.rsBname = rsBname;
	}
	@Override
	public String toString() {
		return "Client [bName=" + bName + ", bBirth=" + bBirth + ", bPhone=" + bPhone + ", bId=" + bId + ", bPw=" + bPw
				+ ", bEmail=" + bEmail + ", bAddr=" + bAddr + ", bookName=" + bookName
				+ ", price=" + price + ", dId=" + dId + ", dDate=" + dDate + ", writer=" + writer + ", genre=" + genre
				+ ", dStock=" + dStock + ", lId=" + lId + ", lbName=" + lbName + ", buyNum=" + buyNum + ", rvName="
				+ rvName + ", rPost=" + rPost + ", rvStar=" + rvStar + ", rvDate=" + rvDate
				+ ", balance=" + balance + ", rsId=" + rsId + ", rsBname=" + rsBname + "]";
	}
	
	
}
