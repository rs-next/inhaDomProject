//package main.service.jdbc;
//
//import java.math.BigDecimal;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import main.entity.Applicant;
//import main.service.ApplicantService;
//import java.util.Date;
//
//public class JDBCapplicantService_backup implements ApplicantService{
//
//	@Autowired
//	private DataSource dataSource;
//	
//	@Override
//	public List<Applicant> getList() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int insert(Applicant applicant) throws SQLException {
//		int stuNum = applicant.getStuNum();
//		String stuName = applicant.getStuName();
//		String stuBirth = applicant.getStuBirth();		
//		String stuDept = applicant.getStuDept();
//		int stuPhoneNum = applicant.getStuPhoneNum();
//		String stuAddress = applicant.getStuAddress();
//		String stuDetailAddress = applicant.getStuDetailAddress();
//		String appliType = applicant.getAppliType();
//		double stuGrade = applicant.getStuGrade();
//		String stuMail = applicant.getStuMail();
//		double auditScore = calTotal_Score(calAddress_Score(stuAddress), calGrade_Score(stuGrade, stuNum), stuNum);
//		String stuGender = applicant.getStuGender();
//		
//		Connection con = dataSource.getConnection();
//		
//		String sql = "Insert into Applicant values(?,?,?,?,?,?,?,?,?,?,?,?)";
//		PreparedStatement st = con.prepareStatement(sql);
//		
//		st.setInt(1, stuNum);
//		st.setString(2, stuName);
//		st.setString(3,stuBirth);
//		st.setString(4, stuDept);
//		st.setInt(5, stuPhoneNum);
//		st.setString(6, stuAddress);
//		st.setString(7, stuDetailAddress);
//		st.setString(8, appliType);
//		st.setString(9, stuMail);
//		st.setDouble(10, auditScore);
//		st.setDouble(11, stuGrade);
//		st.setString(12, stuGender);
//		
//		
//		
//		int result = st.executeUpdate();
//		
//		st.close();
//		con.close();
//		
//		return result;
//	}
//
//	@Override
//	public int calAddress_Score(String stuAddress) {
//		String[] user_District = stuAddress.split(" ");	
//		
//		int add_score = 0; //입사 신청자 주소별 가산점
//		boolean dis_flag = false; //가산점 연산 여부 플래그 
//		int[] div_score = {0, 40, 55, 70, 85, 100}; //급지별 가산점 점수 리스트
//		
//		String[][] check_Districts = { // 급지별 거주시 명 
//		        {"계양구", "미추홀구", "남동구", "동구", "부평구", "서구", "연수구", "중구", "금천구", "영등포구", 
//		        "구로구", "동작구", "양천구", "관악구", "종로구", "광명시", "군포시", "부천시", "수원시", "시흥시",
//		        "안양시", "안산시"},
//		        //6급지 (0점)	
//		        {"강서구", "서초구", "중구", "마포구", "성동구", "용산구", "강남구", "성북구", "송파구", "강동구", 
//		        "광진구", "서대문구", "강북구", "동대문구", "은평구", "과천시", "광주시", "김포시", 
//		   		"성남시", "의왕시", "이천시", "화성시"},
//		        //5급지 (40점)
//		        {"노원구", "도봉구", "중랑구", "구리시", "고양시", "오산시", "용인시", "의정부시", "파주시"},
//		        //4급지 (55점) 
//		        {"남양주시", "안성시", "양주시", "여주시", "평택시", "하남시"},
//		        //3급지 (70점)
//		        {"동두천시", "양평군"},
//		        //2급지 (85점)
//		       /*  {"강화군", "옹진군", "가평군", "연천군", "포천시"}  경기/인천/서울을 제외하고 1급지 지역과 나머지 지역구/군은 100점 가산 */ 
//		        //1급지 (100점)
//		    };
//		
//
//		for(int i=0;i<check_Districts.length;i++){ // 급지별 거주시 구분 배열 1차원 반복 
//			for(int j=0; j<check_Districts[i].length;j++){ // 급지별 거주시 구분 배열 2차원 반복
//				if(user_District[1].indexOf(check_Districts[i][j])>=0){ // 신청자 거주시 명이 급지별 거주시 포함여부 체크				 
//					if(user_District[0].equals("인천") && user_District[1].equals("중구")){ //서울 중구가 아닌 인천 중구의 경우 0점
//						add_score = 0;
//						dis_flag = true;
//					}else{
//						add_score = div_score[i];
//						dis_flag = true;
//					}				
//				}
//			}
//		}
//		if(dis_flag == false){ // 신청자의 거주시가 경기, 인천, 서울권 외의 주소지일 경우 100점 가산
//			add_score = div_score[5];
//			dis_flag = true;
//		}
//		System.out.println("주소 점수 : " + add_score);
//		return add_score;
//	}
//
//	@Override
//	public double calGrade_Score(double stuGrade,int stuNum) {
//		
//		double Score = 0;
//		LocalDate LD = LocalDate.now();		
//		String NowYear = String.valueOf(LD.getYear()).substring(2);	
//		String NowSemester = String.valueOf(LD.getMonthValue());
//		
//		System.out.println("현재 신입생 년도 : "+NowYear);
//		System.out.println("현재 심사 학기 (월) : "+NowSemester);
//		
//		String stuYear = String.valueOf(stuNum).substring(2,4);
//
//		System.out.printf("학생 학번 : %s\n",stuYear);
//		if(stuYear.equals(NowYear)) {
//			if(NowSemester.equals("4")) {
//				Score = (11 - stuGrade) * 10;
//			}
//			else if(NowSemester.equals("7")){
//				Score = 22 * stuGrade + 1;
//			}
//			else {
//				System.out.println("심사기간이 아닙니다.");
//			}
//		}else{
//			Score = 22 * stuGrade + 1;
//		}		
//		System.out.println("성적 점수 : " + Score);
//		return Score;
//	}
//
//	@Override
//	public double calTotal_Score(int Address_Score, double Grade_Score, int stuNum) {
//		LocalDate LD = LocalDate.now();			
//		String NowYear = String.valueOf(LD.getYear()).substring(2);
//		String NowSemester = String.valueOf(LD.getMonthValue());
//		String stuYear = String.valueOf(stuNum).substring(2,4);
//		
//		double totalScore = 0;
//		if(NowSemester.equals("4")) {
//			if(stuYear.equals(NowYear)) {
//			
//				totalScore = ( Address_Score * 7 + Grade_Score * 3 ) / 10.0;
//			}else {
//				System.out.println("총점가산 주소 점수 : "+Address_Score +"\n성적점수 : "+Grade_Score+"\n");
//				totalScore = ( Address_Score * 3 + Grade_Score * 7 ) / 10.0;
//			}
//		}else if(NowSemester.equals("7")) {
//			System.out.println("총점가산 주소 점수 : "+Address_Score +"\n성적점수 : "+Grade_Score+"\n");
//			totalScore = ( Address_Score * 3 + Grade_Score * 7 ) / 10.0;
//		}
//		//부동 소수점을 정확하게 표현할 수 있게하는 자바에서 제공하는 클래스 BigDecimal
//		//setScale을 통해 소수자리, 그리고 반올림 여부를 정하여 값을 변환하고
//		// .doubleValue로 값을 더블형으로 변환하여 반환
//		BigDecimal originalScore = new BigDecimal(Double.toString(totalScore));
//		BigDecimal round_totalScore = originalScore.setScale(1, BigDecimal.ROUND_HALF_UP);
//		System.out.println("합계 가점 : "+round_totalScore+"반올림전 : "+ totalScore);
//		
//		return round_totalScore.doubleValue();
//	}
//
//}
package backup;


