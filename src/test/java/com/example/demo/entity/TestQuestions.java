package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestQuestions {
	// GetterとSetterで値を正しく設定・取得できるかテストする 
	@Test 
	void testGetterAndSetter() { 
		// Questionsクラスのインスタンスを作成する 
		Questions questions = new Questions();
		
		// Setterを使ってIDに100を設定する 
		questions.setId(100);
		// Setterを使って質問本文を設定する
		questions.setBody("テスト用の質問です");
		// Getterで取得したIDが100であることを確認する
		assertEquals(100, questions.getId());
		// Getterで取得した質問本文が設定した文字列と一致することを確認する 
		assertEquals("テスト用の質問です", questions.getBody()); 
		} 
	// 引数なしコンストラクタでインスタンスを作成できるかテストする 
	@Test 
	void testNoArgsConstructor() { 
		// 引数なしでインスタンスを作成する 
		Questions questions = new Questions(); 
		// IDの初期値が0であることを確認する 
		assertEquals(0, questions.getId()); 
		// 質問本文の初期値がnullであることを確認する 
		assertNull(questions.getBody());
		}
	}
	

