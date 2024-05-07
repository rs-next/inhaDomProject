package main.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import main.entity.Applicant;

@Service
public interface domMemberService {
	Map<Integer, Integer> insertMember(List<String> stuNum);
}
