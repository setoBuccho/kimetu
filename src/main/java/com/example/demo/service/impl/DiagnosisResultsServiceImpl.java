package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Diagnosis_results;
import com.example.demo.repository.DiagnosisResultsMapper;
import com.example.demo.service.DiagnosisResultsService;

@Service
public class DiagnosisResultsServiceImpl implements DiagnosisResultsService {

    @Autowired
    private DiagnosisResultsMapper diagnosisResultsMapper;

    @Override
    public List<Diagnosis_results> getDiagnosisResultsByUserId(long userId) {
        return diagnosisResultsMapper.findByUserIdOrderByDiagnosedAtDesc(userId);
    }

    @Override
    public void saveDiagnosisResult(Diagnosis_results diagnosisResult) {
        diagnosisResultsMapper.insert(diagnosisResult);
    }

    @Override
    public void overwriteDiagnosisResult(Diagnosis_results diagnosisResult) {
        diagnosisResultsMapper.deleteByUserId(diagnosisResult.getUserId());
        diagnosisResultsMapper.insert(diagnosisResult);
    }
}