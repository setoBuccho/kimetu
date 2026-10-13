package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Diagnosis_results;

public interface DiagnosisResultsService {

    List<Diagnosis_results> getDiagnosisResultsByUserId(long userId);

    void saveDiagnosisResult(Diagnosis_results diagnosisResult);

    void overwriteDiagnosisResult(Diagnosis_results diagnosisResult);
}