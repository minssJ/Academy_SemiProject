package BOOKSTORE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookStoreSQL {

	Connection con = null;

	PreparedStatement pstmt = null;

	ResultSet rs = null;

// DB접속 메소드
	public void connect() {
		con = DBC.DBC();
	}

// DB접속해제 메소드
	public void conClose() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 판매 메소드(도서등록)
	public void dealInsert(Client client) {

		String sql = "INSERT INTO DEAL VALUES(?,?,?,TO_DATE(?,'YYYY/MM'),?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, client.getBookName());
			pstmt.setInt(2, client.getPrice());
			pstmt.setString(3, client.getbId());
			pstmt.setString(4, client.getdDate());
			pstmt.setString(5, client.getWriter());
			pstmt.setString(6, client.getGenre());
			pstmt.setInt(7, client.getdStock());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("도서가 등록되었습니다.");
				System.out.println();
			} else {
				System.out.println("도서등록에 실패하였습니다.");
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// 판매등록된 도서조회 메소드
	public void dBookselect(String bId) {

		String sql = "SELECT BOOKNAME, PRICE, TO_CHAR(DDATE, 'YYYY/MM/DD'), WRITER, GENRE, DSTOCK " + "FROM BMEMBER B, DEAL D "
				+ "WHERE B.BID = D.DID AND B.BID=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String bookName = rs.getString(1);
				int price = rs.getInt(2);
				String writer = rs.getString(4);
				String genre = rs.getString(5);
				int dStock = rs.getInt(6);

				System.out.println("[ 도서명 : " + bookName + " ]\n[ 금액 : " + price + "원 ] [ 저자 : " + writer + " ] [ 장르 : "
						+ genre + " ] [ 재고 : " + dStock + "권 ]\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 아이디,비번확인 메소드
	public boolean checkId(String dId, String dPw) {
		boolean check = false;

		String sql = "SELECT * FROM BMEMBER WHERE BID=? AND BPW=?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dId);
			pstmt.setString(2, dPw);

			rs = pstmt.executeQuery();

			check = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

// 도서명 확인 메소드(등록 삭제용)
	public boolean bookCheck(String bookName, String bId) {
		boolean check2 = false;

		String sql = "SELECT * FROM DEAL WHERE BOOKNAME=? AND DID=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookName);
			pstmt.setString(2, bId);

			rs = pstmt.executeQuery();

			check2 = rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check2;
	}

// 도서명 확인 메소드(판매등록용)
	public boolean bookNameCheck(String bookName) {
		boolean check2 = false;

		String sql = "SELECT * FROM DEAL WHERE BOOKNAME=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookName);

			rs = pstmt.executeQuery();

			check2 = rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check2;
	}

// 재고 추가 메소드
	public void stockPlus(int stock, String bookName) {

		String sql = "UPDATE DEAL SET DSTOCK = DSTOCK+? WHERE BOOKNAME=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, stock);
			pstmt.setString(2, bookName);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println(stock + "권 추가되었습니다.");
				System.out.println();
			} else {
				System.out.println("실패하였습니다.");
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

// 재고 삭제 메소드	
	public void stockDelete(int stock, String bookName) {
		String sql = "UPDATE DEAL SET DSTOCK = DSTOCK-? WHERE BOOKNAME=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, stock);
			pstmt.setString(2, bookName);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println(stock + "권 제거되었습니다.");
				System.out.println();
			} else {
				System.out.println("실패하였습니다.");
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 재고확인 메소드
	public int checkStock(String bookName) {
		int stock = 0;

		String sql = "SELECT DSTOCK FROM DEAL WHERE BOOKNAME=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookName);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				stock = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stock;
	}

// 회원가입 메소드
	public void join(Client client) {
		String sql = "INSERT INTO BMEMBER VALUES(?,?,?,?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, client.getbName());
			pstmt.setString(2, client.getbBirth());
			pstmt.setString(3, client.getbPhone());
			pstmt.setString(4, client.getbId());
			pstmt.setString(5, client.getbPw());
			pstmt.setString(6, client.getbEmail());
			pstmt.setString(7, client.getbAddr());
			pstmt.setInt(8, client.getBalance());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("회원가입이 완료됐습니다.");
				System.out.println();
			} else {
				System.out.println("회원가입에 실패했습니다.");
				System.out.println();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

// 로그인 메소드
	public boolean login(String bId, String bPw) {
		boolean check = false;

		String sql = "SELECT * FROM BMEMBER WHERE BID=? AND BPW=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bId);
			pstmt.setString(2, bPw);

			rs = pstmt.executeQuery();

			check = rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	
// 회원가입시 아이디 중복확인 메소드
	public boolean bIdCheck(String bId) {
		boolean check = false;

		String sql = "SELECT * FROM BMEMBER WHERE BID=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bId);

			rs = pstmt.executeQuery();

			check = rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

// 예약한 도서목록 조회 메소드
	public void select() {
		String sql = "SELECT BOOKNAME, PRICE ,DID,DDATE,WRITER,GENRE,DSTOCK FROM DEAL";// 수정 판매자 확인해야함 조인으로 할예정
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String BookName = rs.getString(1);
				int Price = rs.getInt(2);
				String DId = rs.getString(3);
				String Writher = rs.getString(5);
				String Genre = rs.getString(6);
				int Stock = rs.getInt(7);

				System.out.println("[ 책이름 : " + BookName + "]\n[ 가격 : " + Price + "원 ] [ 아이디 : " + DId + " ] [ 저자 : " + Writher
						+ " ] [ 장르 : " + Genre + " ] [ 재고 : " + Stock + "권 ]\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	예약한 도서목록 출력 메소드
	public void select2(String BookName) {
		String sql = "SELECT BOOKNAME, PRICE ,DID,TO_CHAR(DDATE, 'YYYY/MM/DD'),WRITER,GENRE,DSTOCK FROM DEAL WHERE BOOKNAME=?";
		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, BookName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String BookName1 = rs.getString(1);
				int Price = rs.getInt(2);
				String DId = rs.getString(3);
				String DDate = rs.getString(4);
				String Writher = rs.getString(5);
				String Genre = rs.getString(6);
				int Stock = rs.getInt(7);

				System.out.println("[ 도서명 : " + BookName1 + " ]\n[ 가격 : " + Price + "원 ] [ 아이디 : " + DId + " ] [ 출판일 : " + DDate + " ] [ 저자 : "
						+ Writher + " ] [ 장르 : " + Genre + " ] [ 재고 : " + Stock + "권 ]\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

// 예약도서검색 메소드
	public boolean checkB(String rsBname) {
		boolean check = false;
		String sql = "SELECT *FROM DEAL WHERE BOOKNAME=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rsBname);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				check = true;
				System.out.println();
			} else {
				check = false;
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

// 도서예약 메소드
	public void insert3(Client client) {
		String sql = "INSERT INTO RESERVE VALUES(?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, client.getRsId());
			pstmt.setString(2, client.getRsBname());

			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("요청이 완료 되었습니다!");
				System.out.println();
			} else {
				System.out.println("요청이 실패하였습니다.");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 내가 예약한 도서목록 출력 메소드
	public void select3(String bId) {
		String sql = "SELECT * FROM RESERVE WHERE RSID=?";
		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String RSName = rs.getString(2);

				System.out.println("[ 요청한도서명 : " + RSName + " ]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

// 예약한 도서목록 삭제 메소드
	public void delete(String book,String bId) {
		String sql = "DELETE FROM RESERVE WHERE RSBNAME=? AND RSID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book);
			pstmt.setString(2, bId);
			
			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("요청목록에서 "+book + "을(를) 제거하였습니다.");
				System.out.println();
			} else {
				System.out.println("도서명을 정확히 입력해주세요");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 등록한 도서명 컬럼 PK 삭제 메소드
	public void dropBookPk() {
		String sql = "ALTER TABLE DEAL DROP CONSTRAINT D_B_PK CASCADE";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			int result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// 등록한 도서명 컬럼 PK 추가 메소드
	public void addBookPk() {
		String sql = "ALTER TABLE DEAL ADD CONSTRAINT D_B_PK PRIMARY KEY(BOOKNAME)";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			int result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// 등록한 도서 삭제 메소드
	public void deleteBook(String bookName) {

		String sql = "DELETE FROM DEAL WHERE BOOKNAME=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookName);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println(bookName + "의 등록을 해제하였습니다.\n");
			} else {
				System.out.println("실패했습니다.");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 금액충전 메소드
	public void deposit(String bId, int balance) {
		String sql = "UPDATE BMEMBER SET BALANCE = BALANCE+? WHERE bId=? ";
		String sql2 = "SELECT BALANCE FROM BMEMBER WHERE BID =?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, balance);
			pstmt.setString(2, bId);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println(balance + "원을 충전했습니다.");
				
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, bId);

				rs = pstmt.executeQuery();
				if(rs.next()) {
					int balance2 = rs.getInt(1);
					System.out.println("현재잔액 : " + balance2 + "원");
					System.out.println();
				}
			} else {
				System.out.println("금액충전에 실패했습니다.");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

// 금액충전 후 잔액 조회
	public int checkBalance(String bId) {
		int balance = 0;

		String sql = "SELECT BALANCE FROM BMEMBER WHERE BID=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				balance = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}

// 도서 구매
	public void withdraw(String account, int balance) {
		String sql = "UPDATE ACCOUNT SET BELENCE = BELENCE-? WHERE ACCONUM=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setInt(2, balance);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println(balance + "원 만큼 사용합니다.");
			} else {
				System.out.println("구매에 실패했습니다");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 회원 정보 수정 ID체크
	public boolean Idcheck(String bId, String dPw) {

		boolean check = false;

		String sql = "SELECT BID FROM BMEMBER WHERE BID= ? AND BPW= ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bId);
			pstmt.setString(2, dPw);

			rs = pstmt.executeQuery();

			check = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

// 회원정보 수정 (연락처)
	public void modifyPhone(String bId, String bPhone) {

		String sql = "UPDATE BMEMBER SET BPHONE = ? WHERE BID = ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, bPhone);
			pstmt.setString(2, bId);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("연락처가 변경되었습니다.");
				System.out.println();
			} else {
				System.out.println("연락처 변경을 실패하였습니다.");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 회원정보 수정 (비밀번호)
	public void modifyPw(String bId, String nBPw) {

		String sql = "UPDATE BMEMBER SET BPW = ? WHERE BID = ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, nBPw);
			pstmt.setString(2, bId);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("비밀번호가 변경되었습니다.");
				System.out.println();
			} else {
				System.out.println("비밀번호 변경을 실패하였습니다.");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 회원정보 수정 (이메일)
	public void modifyEmail(String bId, String bEmail) {

		String sql = "UPDATE BMEMBER SET BEMAIL = ? WHERE BID = ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, bEmail);
			pstmt.setString(2, bId);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("이메일이 변경되었습니다.");
				System.out.println();
			} else {
				System.out.println("이메일 변경을 실패하였습니다.");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 회원정보 수정 (주소)
	public void modifyAddr(String bId, String bAddr) {

		String sql = "UPDATE BMEMBER SET BADDR = ? WHERE BID = ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, bAddr);
			pstmt.setString(2, bId);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("주소지가 변경되었습니다.");
				System.out.println();
			} else {
				System.out.println("주소변경을 실패하였습니다.");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 도서 구매 메소드
	public void buyBook(String bId, String bookName, int stock) {
		int balance = 0;
		// 금액 차감
		String sql1 = "SELECT PRICE, BALANCE FROM DEAL, BMEMBER WHERE BID=? AND BOOKNAME=?";
		String sql2 = "UPDATE BMEMBER SET BALANCE = BALANCE-(?*?) WHERE BID=?";
		// 재고 차감
		String sql3 = "SELECT DSTOCK FROM DEAL WHERE BOOKNAME=?";
		String sql4 = "UPDATE DEAL SET DSTOCK = DSTOCK-? WHERE BOOKNAME=?";
		// 테이블에 데이터 삽입
		String sql5 = "INSERT INTO BLIST VALUES(?,?,?,?)";
		// 구매한 금액만큼 판매자에게 입금
		String sql6 = "SELECT DID FROM DEAL WHERE BOOKNAME=?";
		String sql7 = "UPDATE BMEMBER SET BALANCE = BALANCE+(?*?) WHERE BID=?";

		try {
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, bId);
			pstmt.setString(2, bookName);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int price = rs.getInt(1);
				balance = rs.getInt(2);

				int sum = (price * stock);

				if (sum < balance) {
					pstmt = con.prepareStatement(sql2);
					pstmt.setInt(1, price);
					pstmt.setInt(2, stock);
					pstmt.setString(3, bId);

					int result1 = pstmt.executeUpdate();

					pstmt = con.prepareStatement(sql3);
					pstmt.setString(1, bookName);

					rs = pstmt.executeQuery();
					if (rs.next()) {
						int bookStock = rs.getInt(1);

						pstmt = con.prepareStatement(sql4);
						pstmt.setInt(1, stock);
						pstmt.setString(2, bookName);

						int result2 = pstmt.executeUpdate();

						pstmt = con.prepareStatement(sql5);
						pstmt.setString(1, bId);
						pstmt.setString(2, bookName);
						pstmt.setInt(3, stock);
						pstmt.setInt(4, sum);

						int result3 = pstmt.executeUpdate();
						System.out.println(bookName + "을(를) " + stock + "권 구매했습니다.");
						
						pstmt = con.prepareStatement(sql6);
						pstmt.setString(1, bookName);
						
						rs = pstmt.executeQuery();
						if(rs.next()) {
							String boName = rs.getString(1);
							
							pstmt = con.prepareStatement(sql7);
							pstmt.setInt(1, price);
							pstmt.setInt(2, stock);
							pstmt.setString(3, boName);
							
							int result4 = pstmt.executeUpdate();
						}
					} else {
						System.out.println("도서명을 정확히 입력해주세요");
						System.out.println();
					}
				} else {
					System.out.println("금액이 " + (sum - balance) + "원 부족합니다.");
					System.out.println();
				}
			} else {
				System.out.println("도서명을 정확히 입력해주세요.");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 내정보 조회 메소드
	public void myData(String bId) {

		String sql = "SELECT BNAME,TO_CHAR(BBIRTH, 'YYYY/MM/DD'),BPHONE,BID,BPW,BEMAIL,BADDR,BALANCE FROM BMEMBER WHERE BID=?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, bId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String bName = rs.getString(1);
				String bBirth = rs.getString(2);
				String bPhone = rs.getString(3);
				String bbId = rs.getString(4);
				int bPw = rs.getInt(5);
				String bEmail = rs.getString(6);
				String bAddr = rs.getString(7);
				int balance = rs.getInt(8);

				System.out.println("=== " + bbId + "님의 정보 ===" + "\n\n성함 : " + bName + "\n생년월일 : " + bBirth + "\n연락처 : "
						+ bPhone + "\n주소 : " + bAddr + "\n이메일 : " + bEmail + "\n현재비밀번호 : " + bPw + "\n보유캐시 : "
						+ balance + "원\n");
			}
			System.out.println("====================");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 주문목록 조회 메소드
//	public void buyList(String bId) {
//
//		String sql = "SELECT B.LBNAME,B.BUYNUM, D.GENRE, D.PRICE*B.BUYNUM" + " FROM BLIST B, DEAL D,BMEMBER BM"
//				+ " WHERE BM.BID=B.LID AND B.LBNAME=D.BOOKNAME AND BM.BID=?";
//
//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, bId);
//
//			rs = pstmt.executeQuery();
//
//			System.out.println("=== " + bId + "님의 주문목록 ===");
//			while (rs.next()) {
//				String lbName = rs.getString(1);
//				int buyNum = rs.getInt(2);
//				String genre = rs.getString(3);
//				int sumPrice = rs.getInt(4);
//
//				System.out.println("\n[ 도서명 : " + lbName + " ]\n[ 장르 : " + genre + " ] [ 구매수량 : " + buyNum + "권 ] [ 결제금액 : "
//						+ sumPrice + "원 ]\n");
//			}
//			System.out.println("===========================");
//			System.out.println();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

// 주문목록 조회 메소드
	public void buyList(String bId) {

		String sql1 = "SELECT BNAME FROM BMEMBER WHERE BID=?";
		String sql2 = "SELECT B.LBNAME, B.BUYNUM, B.BUYPRICE FROM BLIST B, BMEMBER BM WHERE BM.BID=B.LID AND BM.BID=?";

		try {
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, bId);
			
			
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, bId);

			rs = pstmt.executeQuery();

			System.out.println("=== " + bId + "님의 주문목록 ===");
			while (rs.next()) {
				String lbName = rs.getString(1);
				int buyNum = rs.getInt(2);
				int sumPrice = rs.getInt(3);

				System.out.println("\n[ 도서명 : " + lbName + " ]\n[ 구매수량 : " + buyNum
						+ "권 ] [ 결제금액 : " + sumPrice + "원 ]\n");
			}
			System.out.println("===========================");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 구매한 도서 확인 메소드
	public boolean checkbl(String lbname) { // 추가
		boolean check = false;
		String sql = "SELECT * FROM BLIST WHERE LBNAME=? ";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lbname);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				check = true;
			} else {
				check = false;
				System.out.println("입력한 도서를 구매하신적이 없습니다 다시한번 확인해 주세요!");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

// 리뷰 작성 메소드
	public void insert4(Client client) { // 추가
		String sql = "INSERT INTO REVIEW VALUES(?,?,?,?,SYSDATE)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, client.getbId());
			pstmt.setString(2, client.getRvName());
			pstmt.setString(3, client.getrPost());
			pstmt.setInt(4, client.getRvStar());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("리뷰가 정상적으로 등록되었습니다.");
				System.out.println();
			} else {
				System.out.println("리뷰등록을 실패하였습니다.");
				System.out.println();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void select4() {
		String sql = "SELECT RVNAME, RPOST ,RVSTAR,RVDATE FROM REVIEW";// 수정 판매자 확인해야함 조인으로 할예정
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String RVNAME = rs.getString(1);
				String RPOST = rs.getString(2);
				String RVSTAR = rs.getString(3);
				String RVDATE = rs.getString(4);

				System.out.println("[ 책이름:" + RVNAME + ",후기:" + RPOST + ", 별점:" + RVSTAR + ", 시간:" + RVDATE + " ]");
			}
			;

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int checkCount(String bid) {
		String sql = "SELECT COUNT(*) FROM BLIST WHERE LID=?";
		int cnt = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bid);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

// 리뷰 삭제 메소드
	public void delete2(String lbname,String bId) {
		String sql = "DELETE FROM REVIEW WHERE RVNAME=? AND RID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lbname);
			pstmt.setString(2, bId);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println(lbname +"의 리뷰를 삭제했습니다.");
				System.out.println();
			} else {
				System.out.println("도서명을 정확히 입력해주세요");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 전체 리뷰목록 확인 메소드
	public void allReview() {
		String sql = "SELECT RVNAME,RPOST,RVSTAR,TO_CHAR(RVDATE, 'YYYY/MM/DD HH24:MI') FROM REVIEW ORDER BY RVDATE";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			System.out.println("================ 전체 리뷰 목록 ================\n");
			while(rs.next()) {
				String rvName = rs.getString(1);
				String rPost = rs.getString(2);
				int rvStar = rs.getInt(3);
				String rvDate = rs.getString(4);
				
				System.out.println("[ 도서명 : " + rvName + " ]\n[ 후기 : " + rPost + " ] ");
				System.out.print("[ 별점 : ");
				for(int i=1; i<=rvStar; i++) {
					System.out.print("★");
				}
				System.out.println(" ] [ 작성일시 : " + rvDate + " ]");
				System.out.println();
			}
			System.out.println("============================================");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// 내리뷰목록 확인 메소드
	public void reviewList(String bId) {
		String sql = "SELECT R.RVNAME,R.RPOST,R.RVSTAR,R.RVDATE FROM REVIEW R,BMEMBER BM WHERE BM.BID = R.RID AND BM.BID=? ORDER BY R.RVDATE";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bId);

			rs = pstmt.executeQuery();

			System.out.println("====== " + bId + "님의 리뷰목록 ======");
			System.out.println();
			while (rs.next()) {
				String RVNAME = rs.getString(1);
				String RPOST = rs.getString(2);
				int RVSTAR = rs.getInt(3);
				String RVDATE = rs.getString(4);

				System.out.print("[ 도서명 : " + RVNAME + " ]\n[ 후기 : " + RPOST + " ] ");
				System.out.print("[별점 : ");
				for(int i=1; i<=RVSTAR; i++) {
					System.out.print("★");
				}
				System.out.print(" ] [ 리뷰작성일 : " + RVDATE + " ]\n\n");
			}
			System.out.println("===============================");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

// 도서검색 메소드(전체목록)
	public void seleteAll() {
		String sql = "SELECT BOOKNAME,PRICE,TO_CHAR(DDATE,'YYYY/MM/DD'),WRITER,GENRE,DSTOCK FROM DEAL";

		try {
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			System.out.println("========================== 등록된 도서 목록 ==========================");
			System.out.println();
			while (rs.next()) {
				String bookName = rs.getString(1);
				String price = rs.getString(2);
				String dDate = rs.getString(3);
				String writer = rs.getString(4);
				String genre = rs.getString(5);
				String dStock = rs.getString(6);

				System.out.println("[ 도서명 : " + bookName + " ]\n[ 금액 : " + price + "원 ] [ 저자 : " + writer
						+ " ] [ 장르 : " + genre + " ] [ 출판일 : " + dDate + " ] [ 재고 : " + dStock + "권 ]\n");
			}
			System.out.println("================================================");
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

// 도서검색 메소드(책이름)
	public void searchBook(String title) {
		String sql = "SELECT BOOKNAME,PRICE,TO_CHAR(DDATE,'YYYY/MM/DD'),WRITER,GENRE,DSTOCK FROM DEAL WHERE BOOKNAME LIKE ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, "%" + title + "%");

			rs = pstmt.executeQuery();

			System.out.println("============ " + title + "(으)로 검색한 결과입니다. ============");
			System.out.println();
			while (rs.next()) {
				String bookName = rs.getString(1);
				String price = rs.getString(2);
				String dDate = rs.getString(3);
				String writer = rs.getString(4);
				String genre = rs.getString(5);
				String dStock = rs.getString(6);

				System.out.println("[ 도서명 : " + bookName + " ]\n[ 금액 : " + price + "원 ] [ 저자 : " + writer
						+ " ] [ 장르 : " + genre + " ] [ 출판일 : " + dDate + " ] [ 재고 : " + dStock + "권 ]\n");
			}
			System.out.println("================================================");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 도서검색 메소드(저자)
	public void searchWriter(String writer) {

		String sql = "SELECT BOOKNAME,PRICE,TO_CHAR(DDATE,'YYYY/MM/DD'),WRITER,GENRE,DSTOCK FROM DEAL WHERE WRITER LIKE ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, "%" + writer + "%");

			rs = pstmt.executeQuery();

			System.out.println("============ " + writer + "(으)로 검색한 결과입니다. ============");
			System.out.println();
			while (rs.next()) {
				String bookName = rs.getString(1);
				String price = rs.getString(2);
				String dDate = rs.getString(3);
				String bWriter = rs.getString(4);
				String genre = rs.getString(5);
				String dStock = rs.getString(6);

				System.out.println("[ 도서명 : " + bookName + " ]\n[ 금액 : " + price + "원 ] [ 저자 : " + bWriter
						+ " ] [ 장르 : " + genre + " ] [ 출판일 : " + dDate + " ] [ 재고 : " + dStock + "권 ]\n");
			}
			System.out.println("================================================");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 도서검색 메소드(가격대)
	public void searchPrice(int min, int max) {
		String sql = "SELECT BOOKNAME,PRICE,TO_CHAR(DDATE,'YYYY/MM/DD'),WRITER,GENRE,DSTOCK FROM DEAL WHERE PRICE >=? AND PRICE <=? ORDER BY PRICE";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, min);
			pstmt.setInt(2, max);

			rs = pstmt.executeQuery();

			System.out.println("============================ " + min + "원 ~ " + max + "원으로 검색한 결과입니다. ============================");
			System.out.println();
			while (rs.next()) {
				String bookName = rs.getString(1);
				String price = rs.getString(2);
				String dDate = rs.getString(3);
				String writer = rs.getString(4);
				String genre = rs.getString(5);
				String dStock = rs.getString(6);

				System.out.println("[ 도서명 : " + bookName + " ]\n[ 금액 : " + price + "원 ] [ 저자 : " + writer
						+ " ] [ 장르 : " + genre + " ] [ 출판일 : " + dDate + " ] [ 재고 : " + dStock + "권 ]\n");
			}
			System.out.println("======================================================================================");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 도서검색 메소드(장르)
	public void serchGenre(String genre) {

		String sql = "SELECT BOOKNAME,PRICE,TO_CHAR(DDATE,'YYYY/MM/DD'),WRITER,GENRE,DSTOCK FROM DEAL WHERE GENRE LIKE ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, "%" + genre + "%");

			rs = pstmt.executeQuery();

			System.out.println("============ " + genre + "(으)로 검색한 결과입니다. ============");
			System.out.println();
			while (rs.next()) {
				String bookName = rs.getString(1);
				String price = rs.getString(2);
				String dDate = rs.getString(3);
				String bWriter = rs.getString(4);
				String bGenre = rs.getString(5);
				String dStock = rs.getString(6);

				System.out.println("[ 도서명 : " + bookName + " ]\n[ 금액 : " + price + "원 ] [ 저자 : " + bWriter
						+ " ] [ 장르 : " + bGenre + " ] [ 출판일 : " + dDate + " ] [ 재고 : " + dStock + "권 ]\n");
			}
			System.out.println("================================================");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 도서 등록여부 확인 메소드
	public boolean checkRegList(String bId) {
		boolean regList = false;
		
		String sql ="SELECT * FROM BMEMBER B,DEAL D WHERE B.BID=D.DID AND BID=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bId);
			
			rs = pstmt.executeQuery();
			
			regList = rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return regList;
	}

	

}
