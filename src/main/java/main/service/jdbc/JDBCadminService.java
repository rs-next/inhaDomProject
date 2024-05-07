package main.service.jdbc;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import main.service.adminService;
import main.dao.adminDao;
import main.entity.Applicant;
import main.entity.DuplicateEntity;

@Service
public class JDBCadminService implements adminService {

	/**
	 * 신청자들의 학번, 이름 등의 데이터를 저장하는 변수입니다.
	 */
	/**	신청자의 학번  */
	int stuNum;
	
	/**	신청자의 이름  */
	String stuName;
	
	/**	신청자의 생일  */
	String stuBirth;
	
	/**	신청자의 학과  */
	String stuDept;
	
	/**	신청자의 휴대폰번호  */
	int stuPhoneNum;
	
	/**	신청자의 주소 : 상세주소가 아닌 지번주소까지 입력된 값입니다.  */
	String stuAddress;
	
	/**	신청자의 상세 주소  */
	String stuDetailAddress;
	
	/**	신청자의 신청 방 유형  */
	String appliType;
	
	/**	신청자의 직전학기 성적  */
	double stuGrade;
	
	/**	신청자의 이메일  */
	String stuMail;
	
	/**	신청자의 합가산점  */
	double auditScore;
	
	/**	신청자의 성별  */
	String stuGender;
	
	/**	신청자의 주소에서부터 인하공업전문대학까지의 거리  */
	double stuDistance;
	
	/**	각 방 유형의 입사정원  */
	int Limit;
	
	private String apiKey;
	adminDao adminDao;
	
	@Autowired
	public JDBCadminService(adminDao adminDao) {
		super();
		this.adminDao = adminDao;
	}
	/**
	 * audit method 는 심사 기준인 방 유형과, 신청자 성별에 따라 입사정원에 맞추어 합격자 리스트를 반환하는 메소드입니다.
	 * 
	 * @param roomType 방 유형 ("2A", "2B", "4")
	 * @param Gender 성별 ("M", "W")
	 * @return 최종 선발된 합격자 리스트
	 * @throws SQLException 데이터베이스 에러가 발생한 경우
	 * @throws IOException 입출력 관련 에러가 발생한 경우
	 * 
	 */
	@Override
	public List<Applicant> audit(String roomType, String Gender) throws SQLException, IOException {
		
		//합격자 객체 리스트 초기화
		List<Applicant> applicantList = new ArrayList<>();
		//심사 기준 데이터 제네릭 초기화
		Map<String, Object> auditCreterion = new HashMap<>();
		//객실 타입에 따라 합격 정원 제한 설정
		switch (roomType){
			case "2A":
			case "2B": 
				Limit = 4;
				break;
			case "4":				
				Limit = 4;
				break;
		}
		//심사 객실 타입을 제네릭 내 appliType KEY로 저장
		auditCreterion.put("appliType", roomType);
		//심사 성별을 제네릭 내 stuGender KEY로 저장
		auditCreterion.put("stuGender", Gender);
		/**
		 * 심사 합격 정원을 Limit KEY로 저장
		 * 심사 기준 학생을 검색하는 SQL 구문 내 LIMIT 1 OFFSET #{Limit} 형식에 맞추어야 합니다.
		 * OFFSET INDEX가 0부터 시작하는 것을 고려하여 Limit index -1 로 저장하였습니다.	 
		 */
		auditCreterion.put("Limit", Limit-1);	
		
		//심사 기준이 되는 학생의 정보를 신청자 객체 타입으로 불러오는 메소드입니다.	
		Applicant creterionStudent = adminDao.selectLastStudent(auditCreterion);
		
		//심사 기준 학생 객체로부터 심사 기준이 되는 점수를 Double형에 저장합니다.	
		Double dupliScore = creterionStudent.getAuditScore();
		
		//심사 합격 정원을 Limit KEY로 하여 정상 값으로 저장합니다.	
		auditCreterion.put("Limit", Limit);		
		
		//심사 기준 점수를 심사 기준 제너릭에 저장합니다.
		auditCreterion.put("auditScore",dupliScore);
		
		//심사 기준 점수와 동일한 점수를 가진 학생이 있는지 검색하여 객체 리스트에 저장합니다.	
		List<Applicant> DupliStudentList = adminDao.selectDupliStudent(auditCreterion);	
		
		/**
		 * 객체리스트.size() >= 2 인 경우 동점자가 존재하다는 의미로 주소지를 통한 거리비교를 실시해 합격 우선순위를 선별합니다.
		 * 객체리스트.size() == 1 인 경우 동점자가 존재하지 않으므로 심사 기준을 바탕으로 합격자를 반환합니다.	
		 */
		if(DupliStudentList.size() >= 2) {
			/** 동점자의 주소지에 따른 위,경도 배열 */
			double[] latLng = new double[2];
			
			//동점자들의 인하공업전문대학까지의 거리, 학번, 주소를 저장하는 리스트 객체를 초기화합니다.	
			List<DuplicateEntity> DuplicateList = new ArrayList<>();
			
			//동점자들의 수 만큼 반복을 진행합니다.
			for(Applicant applicant : DupliStudentList) {
				// 동점자의 학번을 변수에 저장합니다.	
				stuNum = applicant.getStuNum();
				// 동점자의 주소를 변수에 저장합니다.
				stuAddress = applicant.getStuAddress();
				// Geocoder API의 위,경도 변환 로직을 통해 동점자의 주소를 바탕으로 위도와 경도를 반환하는 메소드입니다.
				latLng = convertAddTolatLng(stuAddress);
				// 하버사인 수식을 사용하여 인하공업전문대학의 위도와 경도, 동점자의 위도와 경도 사이의 거리를 반환하는 메소드입니다.	
				stuDistance = calByHaversign(latLng[0], latLng[1]);
				// 도출된 동점자의 학번, 주소, 거리값을 동점자 객체에 저장합니다.
				DuplicateEntity duplicate = new DuplicateEntity(stuNum, stuAddress, stuDistance);
				// 동점자 리스트에 동점자 객체를 저장합니다.	
				DuplicateList.add(duplicate);				
			}
			// 동점자 리스트를 거리를 기준으로 내림차순 정렬하는 메소드입니다.	
			Collections.sort(DuplicateList);
			// 우선순위가 정해진 동점자 리스트를 바탕으로 합격자 리스트를 반환하는 메소드입니다.	
			applicantList = changeDupliEntity(DuplicateList,auditCreterion);
		}else if(DupliStudentList.size() == 1){
			// 심사 기준 점수를 바탕으로 합격자 리스트를 반환하는 메소드입니다.	
			applicantList = adminDao.selectChosenStudent(creterionStudent);
		}
		// 합격자 리스트를 반환합니다.	
		return applicantList;

	}
	
	
	
	
	@Override
	public double calByHaversign(double stuLat, double stuLng) {
		double[] inhatcLatLng = { 37.4480158, 126.6575041 };
		double distance;
		double radius = 6371; // 지구 반지름(km)
		double toRadian = Math.PI / 180;

		double deltaLatitude = Math.abs(inhatcLatLng[0] - stuLat) * toRadian;
		double deltaLongitude = Math.abs(inhatcLatLng[1] - stuLng) * toRadian;

		double sinDeltaLat = Math.sin(deltaLatitude / 2);
		double sinDeltaLng = Math.sin(deltaLongitude / 2);
		double squareRoot = Math.sqrt(sinDeltaLat * sinDeltaLat
				+ Math.cos(inhatcLatLng[0] * toRadian) * Math.cos(stuLat * toRadian) * sinDeltaLng * sinDeltaLng);

		distance = 2 * radius * Math.asin(squareRoot);
		System.out.println("거리계산완료 : " + distance);
		return distance;
	}

	@Value("${google.maps.api.key}")
	public void setGoogleMapsApiKey(String googleMapsApiKey) {
		this.apiKey = googleMapsApiKey;
	}
	
	@Override
	public double[] convertAddTolatLng(String stuAddress) throws IOException {		

		try {
			// 주소를 URL 인코딩합니다.
			String encodedAddress = URLEncoder.encode(stuAddress, "UTF-8");

			// Geocoding API에 요청할 URL을 생성합니다.
			String urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + encodedAddress + "&key="
					+ apiKey;
			URL url = new URL(urlString);

			// URL을 통해 API 응답을 읽어옵니다.
			InputStreamReader reader = new InputStreamReader(url.openStream());

			// Gson을 사용하여 API 응답을 JsonObject로 변환합니다.
			JsonObject jsonObject = new Gson().fromJson(reader, JsonObject.class);

			// API 응답에서 위도와 경도를 추출합니다.
			JsonArray results = jsonObject.getAsJsonArray("results");
			if (results.size() > 0) {
				JsonObject location = results.get(0).getAsJsonObject().getAsJsonObject("geometry")
						.getAsJsonObject("location");
				double[] latLng = new double[2];
				latLng[0] = location.get("lat").getAsDouble();
				latLng[1] = location.get("lng").getAsDouble();
				System.out.println("위도: " + latLng[0]);
				System.out.println("경도: " + latLng[1]);
				return latLng;
			} else {
				System.out.println("변환된 결과가 없습니다.");
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * changeDupliEntity method 는 
	 * 우선순위로 정렬되어 동점자가 저장된 DuplicateList 리스트와 
	 * 합격 기준이 저장된 auditCreterion 제너릭을 바탕으로 
	 * 최종 합격자 리스트를 반환하는 메소드입니다.	  
	 * 
	 * @param List<DuplicateEntity> DuplicateList 동점자를 합격 우선순위로 정렬한 학번, 주소, 거리가 담긴 리스트 
	 * @param Map<String, Object> auditCreterion 입사 정원, 심사 방 타입, 심사 성별, 합격 기준 점수가 포함된 리스트
	 * @return 최종 선발된 합격자 리스트
	 * @throws SQLException 데이터베이스 에러가 발생한 경우
	 * 
	 */
	@Override
	public List<Applicant> changeDupliEntity(List<DuplicateEntity> DuplicateList, Map<String, Object> auditCreterion) throws SQLException {
		// 합격자 리스트 초기화
		List<Applicant> applicantList = new ArrayList<>();
		// 동점자를 제외한 합격자를 합격자 리스트에 저장하는 메소드
		applicantList = adminDao.selectAcceptStudent(auditCreterion);
		// 입사정원(Limit) 에서 합격자 리스트에 담긴 인원만큼을 제외하여 합격하게 될 동점자의 수를 연산
		int additionalCount = (Integer)auditCreterion.get("Limit") - applicantList.size();
		// 연산된 인원만큼 반복하여 동점자 중 합격 우선순위 순으로 합격자 리스트에 저장
		for(int i=0; i<additionalCount; i++) {
			// i번째 index에 해당하는 동점자의 객체 정보를 저장
			DuplicateEntity duplicate = DuplicateList.get(i);
			// 동점자 객체 정보를 바탕으로 학생 객체를 검색하는 메소드
			Applicant addApplicant = adminDao.selectAddiStudent(duplicate.getStuNum());
			// 검색된 학생 객체를 합격자 리스트에 저장
			applicantList.add(addApplicant);
		}
		// 최종 합격 리스트를 반환
		return applicantList;

	}

}
