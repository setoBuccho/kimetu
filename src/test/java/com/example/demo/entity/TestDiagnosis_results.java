package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TestDiagnosis_results {
	// 引数なしコンストラクタでインスタンスを作成できるかテストする 
	@Test 
	void testGetterAndSetter() {
		// Diagnosis_resultsクラスのインスタンスを作成する 
		Diagnosis_results diagnosis_results = new Diagnosis_results(); 
		// Setterを使って診断結果IDに100を設定する 
		diagnosis_results.setId(100); 
		// Setterを使ってユーザーIDに1を設定する 
		diagnosis_results.setUserId(1); 
		// Setterを使ってキャラクターIDに10を設定する 
		diagnosis_results.setCharacterId(10); 
		// テスト用の診断日時を設定する 
		LocalDateTime diagnosedAt = LocalDateTime.of(2026, 10, 13, 10, 30); 
		// Setterを使って診断日時を設定する 
		diagnosis_results.setDiagnosedAt(diagnosedAt); 
		// Getterで取得した診断結果IDが100であることを確認する 
		assertEquals(100, diagnosis_results.getId());
		// Getterで取得したユーザーIDが1であることを確認する 
		assertEquals(1, diagnosis_results.getUserId()); 
		// Getterで取得したキャラクターIDが10であることを確認する 
		assertEquals(10, diagnosis_results.getCharacterId()); 
		// Getterで取得した診断日時が設定した日時と一致することを確認する 
		assertEquals(diagnosedAt, diagnosis_results.getDiagnosedAt()); 
	}
}
