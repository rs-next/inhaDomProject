//package main.service.jdbc;
//
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.google.code.geocoder.Geocoder;
//import com.google.code.geocoder.GeocoderRequestBuilder;
//import com.google.code.geocoder.model.GeocodeResponse;
//import com.google.code.geocoder.model.GeocoderRequest;
//import com.google.code.geocoder.model.GeocoderResult;
//import com.google.code.geocoder.model.GeocoderStatus;
//import com.google.code.geocoder.model.LatLng;
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//
//import main.service.adminService;
//import main.entity.Applicant;
//import main.entity.DuplicateEntity;
//
//public class JDBCadminService_backup_update implements adminService {
//
//	int stuNum;
//	String stuName;
//	String stuBirth;
//	String stuDept;
//	int stuPhoneNum;
//	String stuAddress;
//	String stuDetailAddress;
//	String appliType;
//	double stuGrade;
//	String stuMail;
//	double auditScore;
//	String stuGender;
//	List<Applicant> applicantList = new ArrayList<>();
//
//	@Autowired
//	private DataSource dataSource;
//
//	@Override
//	public void addMember() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public List<Applicant> audit(String roomType, String Gender) throws SQLException, IOException {
//		Connection con = dataSource.getConnection();
//		String auditSql = "select * from applicant where stuGender=? and "
//				+ "appliType=? order by auditScore desc, stuNum limit 1 offset 4";
//		PreparedStatement st = con.prepareStatement(auditSql);
//		st.setString(1, Gender);
//		st.setString(2, roomType);
//		ResultSet rsAudit = st.executeQuery();
//
//		int dupliNum = 0;
//		double dupliScore = 0;
//		if (rsAudit.next()) {
//			dupliNum = rsAudit.getInt("stuNum");
//			dupliScore = rsAudit.getDouble("auditScore");
//		}
//
//		String findDuplicateSql = "select * from applicant where stuGender=? and appliType=?"
//				+ "and auditScore = ? and stuNum != ?";
//		st = con.prepareStatement(findDuplicateSql);
//		st.setString(1, Gender);
//		st.setString(2, roomType);
//		st.setDouble(3, dupliScore);
//		st.setInt(4, dupliNum);
//		ResultSet rs = st.executeQuery();
//
//		if (rs.next()) {
//			String sql = "select * from applicant where auditScore =?";
//			st = con.prepareStatement(sql);
//			st.setDouble(1, dupliScore);
//			rs = st.executeQuery();
//			List<DuplicateEntity> DuplicateList = new ArrayList<>();
//
//			int stuNum;
//			String stuAddress;
//
//			while (rs.next()) {
//				stuNum = rs.getInt("stuNum");
//				stuAddress = rs.getString("stuAddress");
//				// + " " +rs.getString("stuDetailAddress");
//				DuplicateEntity duplicate = new DuplicateEntity(stuNum, stuAddress);
//				DuplicateList.add(duplicate);
//			}
//			double[] latLng = new double[2];
//
//			for (DuplicateEntity duplicate : DuplicateList) {
//				stuAddress = duplicate.getStuAddress();
//				System.out.println(stuAddress);
//				latLng = convertAddTolatLng(stuAddress);
//				duplicate.setDistance(calByHaversign(latLng[0], latLng[1]));
//
//			}
//			if (DuplicateList != null) {
//				for (DuplicateEntity duplicate : DuplicateList) {
//					System.out.println("정렬전 : " + duplicate.getStuNum() + " : " + duplicate.getDistance());
//				}
//				Collections.sort(DuplicateList);
//				for (DuplicateEntity duplicate : DuplicateList) {
//					System.out.println("정렬후 : " + duplicate.getStuNum() + " : " + duplicate.getDistance());
//				}
//				applicantList = (changeDupliEntity(DuplicateList, dupliScore));
//
//			}
//
//		} else {
//
//			String chosenMemSql = "select * from applicant order by auditScore desc limit 5";
//			st = con.prepareStatement(chosenMemSql);
//			ResultSet rsChosenMem = st.executeQuery();
//			while (rsChosenMem.next()) {
//				stuNum = rsChosenMem.getInt("stuNum");
//				stuName = rsChosenMem.getString("stuName");
//				stuBirth = rsChosenMem.getString("stuBirth");
//				stuDept = rsChosenMem.getString("stuDept");
//				stuPhoneNum = rsChosenMem.getInt("stuPhoneNum");
//				stuAddress = rsChosenMem.getString("stuAddress");
//				stuDetailAddress = rsChosenMem.getString("stuDetailAddress");
//				appliType = rsChosenMem.getString("appliType");
//				stuMail = rsChosenMem.getString("stuMail");
//				auditScore = rsChosenMem.getDouble("auditScore");
//				stuGrade = rsChosenMem.getDouble("stuGrade");
//				stuGender = rsChosenMem.getString("stuGender");
//				Applicant applicant = new Applicant(stuNum, stuName, stuBirth, stuDept, stuPhoneNum, stuAddress,
//						stuDetailAddress, appliType, stuGrade, stuMail, auditScore, stuGender);
//				applicantList.add(applicant);
//
//			}
//
//		}
//		return applicantList;
//
//	}
//
//	private double calByHaversign(double stuLat, double stuLng) {
//		double[] inhatcLatLng = { 37.4480158, 126.6575041 };
//		double distance;
//		double radius = 6371; // 지구 반지름(km)
//		double toRadian = Math.PI / 180;
//
//		double deltaLatitude = Math.abs(inhatcLatLng[0] - stuLat) * toRadian;
//		double deltaLongitude = Math.abs(inhatcLatLng[1] - stuLng) * toRadian;
//
//		double sinDeltaLat = Math.sin(deltaLatitude / 2);
//		double sinDeltaLng = Math.sin(deltaLongitude / 2);
//		double squareRoot = Math.sqrt(sinDeltaLat * sinDeltaLat
//				+ Math.cos(inhatcLatLng[0] * toRadian) * Math.cos(stuLat * toRadian) * sinDeltaLng * sinDeltaLng);
//
//		distance = 2 * radius * Math.asin(squareRoot);
//		System.out.println("거리계산완료 : " + distance);
//		return distance;
//	}
//
//	@Override
//	public double[] convertAddTolatLng(String stuAddress) throws IOException {
//		String apiKey = "AIzaSyAMmOiw9Zf-3Kwd51NjfFcA8HfLT3I6u-I";
//
//		try {
//			// 주소를 URL 인코딩합니다.
//			String encodedAddress = URLEncoder.encode(stuAddress, "UTF-8");
//
//			// Geocoding API에 요청할 URL을 생성합니다.
//			String urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + encodedAddress + "&key="
//					+ apiKey;
//			URL url = new URL(urlString);
//
//			// URL을 통해 API 응답을 읽어옵니다.
//			InputStreamReader reader = new InputStreamReader(url.openStream());
//
//			// Gson을 사용하여 API 응답을 JsonObject로 변환합니다.
//			JsonObject jsonObject = new Gson().fromJson(reader, JsonObject.class);
//
//			// API 응답에서 위도와 경도를 추출합니다.
//			JsonArray results = jsonObject.getAsJsonArray("results");
//			if (results.size() > 0) {
//				JsonObject location = results.get(0).getAsJsonObject().getAsJsonObject("geometry")
//						.getAsJsonObject("location");
//				double[] latLng = new double[2];
//				latLng[0] = location.get("lat").getAsDouble();
//				latLng[1] = location.get("lng").getAsDouble();
//				System.out.println("위도: " + latLng[0]);
//				System.out.println("경도: " + latLng[1]);
//				return latLng;
//			} else {
//				System.out.println("변환된 결과가 없습니다.");
//				return null;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	@Override
//	public List<Applicant> changeDupliEntity(List<DuplicateEntity> DuplicateList, double dupliScore) throws SQLException {
//		Connection con = dataSource.getConnection();
//		
//			int rowCount = 0;
//
//			List<Applicant> applicantList = new ArrayList<>();
//			String chosenMemSql = "select * from ( select * from applicant order by auditScore desc limit 5 ) as sub where auditScore not in (?)";
//			PreparedStatement st = con.prepareStatement(chosenMemSql);
//			st.setDouble(1, dupliScore);
//			ResultSet rs = st.executeQuery();
//			while (rs.next()) {				
//				rowCount += 1;
//				stuNum = rs.getInt("stuNum");
//				stuName = rs.getString("stuName");
//				stuBirth = rs.getString("stuBirth");
//				stuDept = rs.getString("stuDept");
//				stuPhoneNum = rs.getInt("stuPhoneNum");
//				stuAddress = rs.getString("stuAddress");
//				stuDetailAddress = rs.getString("stuDetailAddress");
//				appliType = rs.getString("appliType");
//				stuMail = rs.getString("stuMail");
//				auditScore = rs.getDouble("auditScore");
//				stuGrade = rs.getDouble("stuGrade");
//				stuGender = rs.getString("stuGender");
//				Applicant applicant = new Applicant(stuNum, stuName, stuBirth, stuDept, stuPhoneNum, stuAddress,
//						stuDetailAddress, appliType, stuGrade, stuMail, auditScore, stuGender);
//				applicantList.add(applicant);
//
//			}
//			
//			int changeCount = 5 - rowCount;
//			
//			for (int i = 0; i < changeCount; i++) {
//				DuplicateEntity duplicate = DuplicateList.get(i);
//				String InsertChosenMemSql = "select * from applicant where stuNum = ?";
//				st = con.prepareStatement(InsertChosenMemSql);
//				st.setInt(1,duplicate.getStuNum());
//				rs = st.executeQuery();
//				if (rs.next()) {
//					stuNum = rs.getInt("stuNum");
//					stuName = rs.getString("stuName");
//					stuBirth = rs.getString("stuBirth");
//					stuDept = rs.getString("stuDept");
//					stuPhoneNum = rs.getInt("stuPhoneNum");
//					stuAddress = rs.getString("stuAddress");
//					stuDetailAddress = rs.getString("stuDetailAddress");
//					appliType = rs.getString("appliType");
//					stuMail = rs.getString("stuMail");
//					auditScore = rs.getDouble("auditScore");
//					stuGrade = rs.getDouble("stuGrade");
//					stuGender = rs.getString("stuGender");
//					Applicant applicant = new Applicant(stuNum, stuName, stuBirth, stuDept, stuPhoneNum, stuAddress,
//							stuDetailAddress, appliType, stuGrade, stuMail, auditScore, stuGender);
//					applicantList.add(applicant);
//
//				} 
//			}
//			
//		st.close();
//		rs.close();
//		return applicantList;
//
//	}
//
//}
package backup;


