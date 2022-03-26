package BOOKSTORE;

import java.util.Scanner;

public class BookStoreMainFin {

	public static void main(String[] args) {

		Client client = new Client();
		BookStoreSQL sql = new BookStoreSQL();
		DBC dbc = new DBC();

		Scanner sc = new Scanner(System.in);

		boolean run = true; // 첫번째페이지 로그인,회원가입메뉴
		boolean run1 = true; // 로그인 후 메뉴선택
		boolean run2 = true; // 2. 도서등록메뉴
		boolean run3 = true; // 2-1. 재고추가 메뉴
		boolean run4 = true; // 5. 도서예약 메뉴
		boolean run5 = true; // 1. 충전/구매 메뉴
		boolean run6 = true; // 3. 리뷰메뉴
		boolean run7 = true; // 4. 정보조회 메뉴
		boolean run8 = true; // 1-2. 도서검색 메뉴
		boolean run9 = true; // 3-2. 리뷰보기 메뉴
		boolean run10 = true; // 4-1 정보수정 메뉴

		boolean check = false; // 아이디,비번 체크
		boolean check1 = false; // 2. 도서 [등록삭제용] 책이름 체크
		boolean check2 = false; // 5. 도서 [예약용] 책이름 체크
		boolean check3 = false;	// 3. 리뷰용 구매도서 체크
		boolean check4 = false; // 2-1. 도서 [등록용] 책이름중복 체크
		boolean check5 = false; // 1. 회원가입시 아이디 중복 체크
		boolean check6 = false; // 1-1. 도서 [구매용] 책이름중복 체크
		boolean check7 = false; // 2-2. 판매도서 확인용 책등록여부 체크

		int menu = 0; // 첫번째페이지 로그인,회원가입 메뉴
		int menu1 = 0; // 로그인 후 메뉴선택
		int menu2 = 0; // 2. 도서등록메뉴의 메뉴선택
		int menu3 = 0; // 5. 도서예약 메뉴선택
		int menu4 = 0; // 3. 리뷰메뉴
		int menu5 = 0; // 4. 정보조회 메뉴
		int menu6 = 0; // 1-2. 도서검색 메뉴
		int menu7 = 0; // 3-2. 리뷰보기 메뉴
		int menu8 = 0; // 4-1. 정보수정 메뉴

		String bName, bBirth, bPhone, bId, bPw, bEmail, bAddr, bookName = null;
		int balance,dStock = 0;

		sql.connect();

		while (run) {
			System.out.println("======= JJYM BOOKSTORE =======");
			System.out.println("   1.회원가입  2.로그인  3.종료");
			System.out.println("==============================");
			System.out.print(">> ");
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				
				System.out.print("아이디 : ");
				bId = sc.next();

				check5 = sql.bIdCheck(bId);
				if(check5) {
					System.out.println("중복된 ID입니다. 다른 ID를 사용해주십시오.");
					System.out.println();
				}else {
					System.out.println("비밀번호를 8자리이상 입력해주세요");
					System.out.print(">> ");
					bPw = sc.next();
					
					if(bPw.length() < 8) {
						System.out.println("비밀번호는 8자리가 넘어야 합니다.(현재 : " + bPw.length() +"자리)\n");
					}else {
						System.out.print("이름 : ");
						bName = sc.next();

						System.out.println("생년월일 ex) 920204 ");
						System.out.print(">> ");
						bBirth = sc.next();

						System.out.print("연락처 : ");
						bPhone = sc.next();

						System.out.print("이메일 : ");
						bEmail = sc.next();

						sc.nextLine().trim();
						System.out.print("주소 : ");
						bAddr = sc.nextLine();

						balance = 0;

						client.setbName(bName);
						client.setbBirth(bBirth);
						client.setbPhone(bPhone);
						client.setbId(bId);
						client.setbPw(bPw);
						client.setbEmail(bEmail);
						client.setbAddr(bAddr);
						client.setBalance(balance);
						
						sql.join(client);
					}
				}
				break;
			case 2:
				run1 = true;
				System.out.print("아이디 : ");
				bId = sc.next();

				System.out.print("비밀번호 : ");
				bPw = sc.next();

				check = sql.login(bId, bPw);

				if (check) {
					System.out.println("로그인 성공");
					System.out.println();
					while (run1) {
						System.out.println("======= JJYM BOOKSTORE =======");
						System.out.println(" 1.도서구매   2.도서판매   3.리뷰");
						System.out.println(" 4.정보조회   5.도서요청   6.로그아웃");
						System.out.println("==============================");
						System.out.print("선택 >> ");
						menu1 = sc.nextInt();

						switch (menu1) {
						case 1:
							System.out.println("==== 도서구매 메뉴를 선택하셨습니다. ====");
							System.out.println();
							run5 = true;
							System.out.println("비밀번호를 입력해주세요");
							System.out.print(">> ");
							String dPw = sc.next();

							check = sql.checkId(bId, dPw);
							System.out.println();
							if (check) {
								while (run5) {
									System.out.println("============= 도서구매/캐시충전 메뉴 ==============");
									System.out.println("  1.캐시충전   2.도서검색   3.도서구매   4.돌아가기");
									System.out.println("============================================");
									System.out.print(">> ");
									menu3 = sc.nextInt();

									switch (menu3) {
									case 1:
										System.out.println("==== 캐시충전을 선택하셨습니다. ====");
										System.out.println();
										System.out.print("현재캐시 : " + sql.checkBalance(bId) + "원\n");
										System.out.println("충전할 금액을 입력해주세요");
										System.out.print(">> ");
										balance = sc.nextInt();

										if (balance > 0) {
											sql.deposit(bId, balance);
										} else {
											System.out.println("1이상을 입력해주세요");
											System.out.println();
										}
										break;
									case 2:
										run8 = true;
										System.out.println("===== 도서검색을 선택하셨습니다. =====");
										System.out.println();
										while (run8) {
											System.out.println("===== 원하시는 검색조건을 선택해주세요. =====");
											System.out.println("   1.전체목록보기  2.제목   3.저자");
											System.out.println("   4.가격대      5.장르   6.뒤로가기");
											System.out.println("===================================");
											System.out.print(">> ");
											menu6 = sc.nextInt();
											
											switch(menu6) {
											case 1:
												sql.seleteAll();
												break;
											case 2:
												sc.nextLine();
												System.out.println("== 제목으로 검색을 선택하셨습니다. ==");
												System.out.println();
												System.out.print("도서명 : ");
												String title = sc.nextLine();
												sql.searchBook(title);
												break;
											case 3:
												sc.nextLine();
												System.out.println("== 저자로 검색을 선택하셨습니다. ==");
												System.out.println();
												System.out.print("저자 : ");
												String writer = sc.nextLine();
												sql.searchWriter(writer);
												break;
											case 4:
												System.out.println("== 가격대로 검색을 선택하셨습니다. ==");
												System.out.println();
												System.out.println("원하시는 가격대를 숫자만 입력해주세요");
												System.out.print("최소금액 : ");
												int min = sc.nextInt();
												System.out.print("최대금액 : ");
												int max = sc.nextInt();
												
												if(min>=0 && max>min) {
													sql.searchPrice(min,max);
												} else if(min < 0) {
													System.out.println("최소금액은 0보다 커야합니다.");
													System.out.println();
												} else {
													System.out.println("최대금액이 최소금액보다 커야합니다.");
													System.out.println();
												}
												break;
											case 5:
												sc.nextLine();
												System.out.println("== 장르로 검색을 선택하셨습니다. ==");
												System.out.println();
												System.out.print("장르 : ");
												String genre = sc.nextLine();
												sql.serchGenre(genre);
												break;
											case 6:
												run8 = false;
												System.out.println("이전 화면으로 돌아갑니다.");
												System.out.println();
												break;
											default:
												System.out.println("다시 입력해주세요");
												System.out.println();
												break;
											}
										}
										break;
									case 3:
										System.out.println("==== 도서구매를 선택하셨습니다. ====");
										System.out.println();
										System.out.println("구매하실 도서명과 수량을 입력해주세요");

										sc.nextLine().trim();
										System.out.print("도서명 : ");
										bookName = sc.nextLine();

										check6 = sql.bookNameCheck(bookName);
										if(check6) {
											System.out.print("수량 : ");
											int stock = sc.nextInt();
											
											int bStock = sql.checkStock(bookName);

											if (stock > 0 && stock <= bStock) {
												sql.buyBook(bId, bookName, stock);
												System.out.println();
											} else if(stock > bStock){
												System.out.println("재고가 "+ (stock-bStock) +"권 부족하여 구매가 불가합니다.");
												System.out.println();
											} else {
												System.out.println("1이상 입력해주세요");
												System.out.println();
											}
										} else {
											System.out.println("도서명을 정확히 입력해주세요\n");
										}
										break;
									case 4:
										run5 = false;
										System.out.println("이전 화면으로 돌아갑니다.");
										System.out.println();
										break;
									default:
										System.out.println("다시 입력해주세요!");
										System.out.println();
										break;
									}
								}
							} else {
								System.out.println("비밀번호가 틀렸습니다.");
								System.out.println();
							} // 도서구매 메뉴 종료
							break;
						case 2:
							run2 = true;
							while (run2) {
								System.out.println("===============  도서판매 메뉴  ===============");
								System.out.println("   1.판매도서등록   2.등록한도서조회   3.등록도서삭제");
								System.out.println("          4.재고변경        5.뒤로가기");
								System.out.println("===========================================");
								System.out.print("선택 >> ");
								menu2 = sc.nextInt();

								switch (menu2) {
								case 1:
									System.out.println("==== 판매도서 등록을 선택하셨습니다. ====");
									System.out.println();
									System.out.println("비밀번호를 입력해주세요");
									System.out.print(">> ");
									dPw = sc.next();

									check = sql.checkId(bId, dPw);

									if (check) {
										sc.nextLine().trim();
										System.out.println("판매하실 도서의 제목을 입력해주세요");
										System.out.print(">> ");
										String bookname = sc.nextLine();
										
										check4 = sql.bookNameCheck(bookname);
										if(check4) {
											System.out.println("이미 등록된 도서명입니다.\n다른도서명을 사용해주세요.\n");
										} else {
											System.out.println("판매하실 수량을 입력해주세요");
											System.out.print(">> ");
											dStock = sc.nextInt();
											
											if(dStock <= 0) {
												System.out.println("재고를 1개 이상 등록해주세요\n");
											} else {
												System.out.println("금액을 책정해주세요");
												System.out.print(">> ");
												int price = sc.nextInt();
												
												if(price < 0) {
													System.out.println("가격은 0원 이상만 입력 가능합니다.\n");
												} else {
													sc.nextLine().trim();
													System.out.println("해당 도서의 장르를 입력해주세요");
													System.out.print(">> ");
													String genre = sc.nextLine();

													System.out.println("해당 도서의 출판일을 월까지만 입력해주세요");
													System.out.println("ex ) 1900-00");
													System.out.print(">> ");
													String dDate = sc.next();

													sc.nextLine().trim();
													System.out.println("해당 도서의 저자를 입력해주세요");
													System.out.print(">> ");
													String writer = sc.nextLine();

													client.setBookName(bookname);
													client.setPrice(price);
													client.setbId(bId);
													client.setdDate(dDate);
													client.setWriter(writer);
													client.setGenre(genre);
													client.setdStock(dStock);
													
													sql.dealInsert(client);
												}
											}
										}
									} else {
										System.out.println("비밀번호가 틀렸습니다.");
										System.out.println();
									}
									break;
								case 2:
									System.out.println("==== 등록도서 조회를 선택하셨습니다. ====");
									System.out.println();
									System.out.println("비밀번호를 입력해주세요");
									System.out.print(">> ");
									dPw = sc.next();

									check = sql.checkId(bId, dPw);
									check7 = sql.checkRegList(bId);
									
									if (check) {
										if(check7) {
											sql.dBookselect(bId);
										} else {
											System.out.println("등록하신 도서가 없습니다\n");
										}
									} else {
										System.out.println("비밀번호가 틀렸습니다.");
										System.out.println();
									}
									break;
								case 3:
									System.out.println("==== 등록도서 삭제를 선택하셨습니다. ====");
									System.out.println();
									sc.nextLine().trim();
									System.out.println("도서명을 입력해주세요");
									System.out.print(">> ");
									bookName = sc.nextLine();

									check1 = sql.bookCheck(bookName,bId);

									if (check1) {
										System.out.println(bookName + "책의 등록을 해제 하시겠습니까?");
										System.out.println();
										System.out.println("== [y] or [n] ==");
										System.out.print(">> ");
										String button1 = sc.next();

										if (button1.equals("y") || button1.equals("Y")) {
											sql.dropBookPk();
											sql.deleteBook(bookName);
											sql.addBookPk();
										} else if (button1.equals("n") || button1.equals("N")) {
											System.out.println("이전화면으로 돌아갑니다.");
											System.out.println();
										} else {
											System.out.println(" [y] or [n] 만 입력해주세요.");
											System.out.println();
										}
									} else {
										System.out.println("도서명을 확인해주세요.");
										System.out.println();
									}
									break;
								case 4:
									run3 = true;
									System.out.println("==== 재고변경을 선택하셨습니다. ====");
									System.out.println();
									System.out.println("비밀번호를 입력해주세요.");
									System.out.print(">> ");
									dPw = sc.next();

									check = sql.checkId(bId, dPw);
									System.out.println();
									if (check) {
										while (run3) {
											System.out.println("=====원하시는 메뉴를 선택해주세요=====");
											System.out.println("  1.재고추가  2.재고삭제  3.뒤로가기");
											System.out.println("=============================");
											System.out.print(">> ");
											menu2 = sc.nextInt();

											switch (menu2) {
											case 1:
												System.out.println("==== 재고추가를 선택하셨습니다. ====");
												System.out.println();
												sc.nextLine().trim();
												System.out.println("재고를 추가할 도서명을 입력해주세요");
												System.out.print(">> ");
												bookName = sc.nextLine();

												check1 = sql.bookCheck(bookName,bId);

												if (check1) {
													System.out.println("추가하실 재고의 수량을 숫자만 입력해주세요");
													System.out.print(">> ");
													int stock = sc.nextInt();
													
													if(stock <= 0) {
														System.out.println("1이상만 입력해주세요\n");
													} else {
														sql.stockPlus(stock, bookName);
													}
												} else {
													System.out.println("도서명을 정확히 입력해주세요");
													System.out.println();
												}
												break;
											case 2:
												System.out.println("==== 재고삭제를 선택하셨습니다. ====");
												System.out.println();
												sc.nextLine().trim();
												System.out.println("재고를 삭제할 도서명을 입력해주세요");
												System.out.print(">> ");
												bookName = sc.nextLine();

												check1 = sql.bookCheck(bookName,bId);

												if (check1) {
													System.out.println("삭제하실 재고의 수량을 숫자만 입력해주세요");
													System.out.print(">> ");
													int stock = sc.nextInt();

													if(stock <= 0) {
														System.out.println("1이상만 입력해주세요\n");
													} else{
														int cStock = sql.checkStock(bookName);

														if (cStock >= stock) {
															sql.stockDelete(stock, bookName);
														} else {
															System.out.println("재고가 " + (stock - cStock) + "권 부족하여 삭제가 불가합니다.");
															System.out.println();
														}
													}
												} else {
													System.out.println("도서명을 확인해주세요");
													System.out.println();
												}
												break;
											case 3:
												run3 = false;
												System.out.println("이전페이지로 돌아갑니다.");
												System.out.println();
												break;
											default:
												System.out.println("다시 입력해주세요");
												System.out.println();
												break;
											}
										}
									} else {
										System.out.println("비밀번호가 틀렸습니다.");
										System.out.println();
									}
									break;
								case 5:
									run2 = false;
									System.out.println("이전 화면으로 돌아갑니다.");
									System.out.println();
									break;
								default:
									System.out.println("다시 입력해주세요.");
									System.out.println();
									break;
								}
							} // 도서등록 메뉴 종료
							break;
						case 3:
							System.out.println();
							System.out.println("==== 리뷰메뉴를 선택하셨습니다. ====");
							System.out.println("비밀번호를 입력해주세요.");
							System.out.print(">> ");
							bPw = sc.next();
							run6 = true;
							
							String lbname = null;
							String Rpost = null;
							int rvStar = 0;

							check = sql.checkId(bId, bPw);

							if (check) {
								System.out.println();
								while (run6) {
									System.out.println("================ 리뷰 메뉴 =================");
									System.out.println("         1.리뷰작성       2.리뷰보기");
									System.out.println("         3.리뷰삭제       4.뒤로가기");
									System.out.println("=========================================");
									System.out.print(">> ");
									menu4 = sc.nextInt();

									switch (menu4) {
									case 1:
										sql.buyList(bId);
										System.out.println("==== 리뷰작성 메뉴를 선택하셨습니다. ====\n");
										sc.nextLine().trim();
										System.out.println("리뷰를 작성할 도서의 제목을 입력해주세요");
										System.out.print(">> ");
										lbname = sc.nextLine();
										check3 = sql.checkbl(lbname);
										if (check3) {
											System.out.println("별점을 입력해주세요(1~5의 숫자만 입력해주세요.)");
											System.out.print(">> ");
											rvStar = sc.nextInt();
											
											if (0 < rvStar && rvStar < 6) {
												System.out.println("후기를 입력해주세요");
												System.out.print(">> ");
												sc.nextLine().trim();
												Rpost = sc.nextLine();
												
												client.setbId(bId);
												client.setRvName(lbname);
												client.setrPost(Rpost);
												client.setRvStar(rvStar);
												sql.insert4(client);
											} else {
												System.out.println("1 ~ 5 만 입력 가능합니다.");
												System.out.println();
											}
										} else {
										}
										break;
									case 2:
										run9 = true;
										System.out.println("===== 리뷰보기 메뉴를 선택하셨습니다. =====");
										System.out.println();
										while(run9) {
											System.out.println("========== 원하시는 메뉴를 선택해주세요 ==========");
											System.out.println(" 1.전체리뷰 보기   2.내가쓴 리뷰 보기   3.뒤로가기");
											System.out.println("=========================================");
											System.out.print(">> ");
											menu7 = sc.nextInt();
											
											switch(menu7) {
											case 1:
												System.out.println("==== 전체리뷰보기 메뉴를 선택하셨습니다. ====\n");
												sql.allReview();
												break;
											case 2:
												System.out.println("==== 내가쓴 리뷰 보기 메뉴를 선택하셨습니다. ====\n");
												sql.reviewList(bId);
												break;
											case 3:
												run9 = false;
												System.out.println("이전 화면으로 돌아갑니다.");
												System.out.println();
												break;
											default:
												System.out.println("다시 입력해주세요\n");
												break;
											}
										}
										break;
									case 3:
										sql.reviewList(bId);
										System.out.println("==== 리뷰 삭제 메뉴를 선택하셨습니다. ====\n");
										sc.nextLine().trim();
										System.out.println("삭제할 리뷰의 도서명을 입력해주시기 바랍니다.");
										System.out.print(">> ");
										lbname = sc.nextLine();
										sql.delete2(lbname,bId);
										break;
									case 4:
										run6 = false;
										System.out.println("이전 화면으로 돌아갑니다.");
										System.out.println();
										break;
									default:
										System.out.println("다시 입력해주세요 \n");
										break;
									}
								}
							} else {
								System.out.println("비밀번호가 틀렸습니다.");
								System.out.println();
							}
							break;
						case 4:
							run7 = true;
							System.out.println("비밀번호를 입력해주세요");
							System.out.print(">> ");
							dPw = sc.next();

							check = sql.checkId(bId, dPw);
							System.out.println();
							if (check) {
								while (run7) {
									System.out.println("=================== 정보조회 메뉴 ===================");
									System.out.println(" 1.내정보 조회   2.내정보 변경   3.주문목록 조회   4.뒤로가기");
									System.out.println("=================================================");
									System.out.print(">> ");
									menu5 = sc.nextInt();

									switch (menu5) {
									case 1:
										sql.myData(bId);
										break;
									case 2:
										run10 = true;
										System.out.println("==== 내정보 변경을 선택하셨습니다. ====");
										System.out.println();
										while(run10) {
											System.out.println("============ 변경하실 정보를 선택해주세요. ==========");
											System.out.println(" 1.연락처  2.비밀번호  3.이메일  4.주소  5.뒤로가기");
											System.out.println("============================================");
											System.out.print(">> ");
											menu8 = sc.nextInt();
											
											switch(menu8) {
											case 1:
												System.out.println("변경할 연락처를 입력해주세요");
												System.out.print(">> ");
												bPhone = sc.next();
												sql.modifyPhone(bId,bPhone);
												break;
											case 2:
												System.out.println("변경할 비밀번호를 입력해주세요");
												System.out.print(">> ");
												String nBPw = sc.next();
												sql.modifyPw(bId,nBPw);
												break;
											case 3:
												System.out.println("변경할 이메일을 입력해주세요");
												System.out.print(">> ");
												bEmail = sc.next();
												sql.modifyEmail(bId,bEmail);
												break;
											case 4:
												sc.nextLine().trim();
												System.out.println("변경할 주소지를 입력해주세요");
												System.out.print(">> ");
												bAddr = sc.nextLine();
												sql.modifyAddr(bId,bAddr);
												break;
											case 5:
												run10 = false;
												System.out.println("이전 화면으로 돌아갑니다.");
												System.out.println();
												break;
											default:
												System.out.println("다시 입력해주세요");
												System.out.println();
												break;
											}
										}
										break;
									case 3:
										sql.buyList(bId);
										break;
									case 4:
										run7 = false;
										System.out.println("이전 화면으로 돌아갑니다.");
										System.out.println();
										break;
									default:
										System.out.println("다시 입력해주세요");
										System.out.println();
										break;
									}
								}
							} else {
								System.out.println("비밀번호가 틀렸습니다.");
								System.out.println();
							}
							break;
						case 5:
							run4 = true;
							System.out.println("======== 도서요청 메뉴를 선택하셨습니다. ========");
							System.out.println();
							while (run4) {
								System.out.println("===========  도서요청 메뉴  ===========");
								System.out.println("    1.도서검색/요청     2.요청도서조회");
								System.out.println("    3.도서요청취소      4.뒤로가기");
								System.out.println("===================================");
								System.out.print("선택 >> ");
								menu3 = sc.nextInt();
								switch (menu3) {

								case 1:
									System.out.println("==================전체 도서 목록==================");
									System.out.println();
									sql.select();
									System.out.println("==============================================");
									System.out.println();
									
									System.out.println("== 도서검색/요청 메뉴를 선택하셨습니다. ==\n");
									sc.nextLine().trim();
									System.out.println("찾으시는 도서명을 입력해주세요!");
									System.out.print(">> ");
									String rsBname = sc.nextLine();
									check2 = sql.checkB(rsBname);
									if (check2) {
										sql.select2(rsBname);
									} else {
										System.out.println("죄송합니다. 입력하신 도서는 현재 등록되어있지 않습니다.");
										System.out.println("요청 하시겠습니까?");
										System.out.println("== [y] or [n] ==");
										System.out.print(">> ");
										String button = sc.next();

										if (button.equals("y") || button.equals("Y")) {

											client.setRsId(bId);
											client.setRsBname(rsBname);
											sql.insert3(client);
											System.out.println("감사합니다. 최대한 빠른 시일내에 입고하도록 하겠습니다!");
											System.out.println();

										} else if (button.equals("n") || button.equals("N")) {
											System.out.println("이전 화면으로 돌아갑니다.");
											System.out.println();
										} else {
											System.out.println("[y] or [n] 만 입력해주세요.");
											System.out.println();
										}
									}
									break;
								case 2:
									System.out.println("== 요청도서 조회를 선택하셨습니다. ==");
									System.out.println();
									System.out.println("내가 요청한 도서 목록");
									sql.select3(bId);
									System.out.println();
									break;
								case 3:
									System.out.println("== 도서요청취소를 선택하셨습니다. ==");
									System.out.println();
									System.out.println("내가 요청한 도서들");
									sql.select3(bId);
									System.out.println();
									sc.nextLine();
									System.out.println("요청취소할 도서명을 입력해주세요");
									System.out.print(">> ");
									String book = sc.nextLine();
									sql.delete(book,bId);
									break;
								case 4:
									run4 = false;
									System.out.println("이전 화면으로 돌아갑니다.");
									System.out.println();
									break;
								default:
									System.out.println("다시 입력해주세요");
									System.out.println();
									break;
								}
							} // 도서예약 메뉴 종료
							break;
						case 6:
							run1 = false;
							System.out.println("로그아웃 했습니다.");
							System.out.println();
							break;
						default:
							System.out.println("다시 입력해주세요.");
							System.out.println();
							break;
						}
					}
				} else {
					System.out.println("아이디와 비밀번호를 확인해주세요.");
					System.out.println();
				}
				break;
			case 3:
				run = false;
				System.out.println("이용해 주셔서 감사합니다.");
				break;
			default:
				System.out.println("다시 입력해주세요.");
				System.out.println();
				break;
			}
		}
		sql.conClose();//dd
	}

}
