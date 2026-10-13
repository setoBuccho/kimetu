package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestChoices {
// 引数なしコンストラクタでインスタンスを作成できるかテストする
@Test
void testNoArgsConstructor() {
	// 引数を指定せずにChoicesクラスのインスタンスを作成する
	Choices choices = new Choices();
	// インスタンスが正常に作成されていることを確認する 
	assertNotNull(choices);
	// int型のidが初期値の0になっていることを確認する 
	assertEquals(0, choices.getId()); 
	// Integer型のquestionIdが初期値のnullになっていることを確認する 
	assertNull(choices.getQuestionId()); 
	// Integer型のcharacterIdが初期値のnullになっていることを確認する 
	assertNull(choices.getCharacterId()); 
	// String型のbodyが初期値のnullになっていることを確認する 
	assertNull(choices.getBody());
}
//GetterとSetterで値を正しく設定・取得できるかテストする
@Test 
void testGetterAndSetter() { 
	// Choicesクラスのインスタンスを作成する 
	Choices choices = new Choices(); 
	// Setterを使ってidに1を設定する 
	choices.setId(1); 
	// Setterを使ってquestionIdに10を設定する 
	choices.setQuestionId(10); 
	// Setterを使ってcharacterIdに20を設定する 
	choices.setCharacterId(20); 
	// Setterを使ってbodyに選択肢の文字列を設定する 
	choices.setBody("選択肢のテスト"); 
	// Getterで取得したidが1であることを確認する 
	assertEquals(1, choices.getId()); 
	// Getterで取得したquestionIdが10であることを確認する 
	assertEquals(Integer.valueOf(10), choices.getQuestionId()); 
	// Getterで取得したcharacterIdが20であることを確認する 
	assertEquals(Integer.valueOf(20), choices.getCharacterId()); 
	// Getterで取得したbodyが設定した文字列と一致することを確認する
	assertEquals("選択肢のテスト", choices.getBody()); 
}
//全引数コンストラクタで値を正しく設定できているかをテスト
@Test 
void testAllArgsConstructor() {
	// 4つのフィールドに値を指定してインスタンスを作成する
	Choices choices = new Choices(1,10,20,"選択肢のテスト");
	// idに設定した値が正しく取得できることを確認する 
	assertEquals(1, choices.getId());
	// questionIdに設定した値が正しく取得できることを確認する 
	assertEquals(Integer.valueOf(10), choices.getQuestionId()); 
	// characterIdに設定した値が正しく取得できることを確認する 
	assertEquals(Integer.valueOf(20), choices.getCharacterId()); 
	// bodyに設定した文字列が正しく取得できることを確認する 
	assertEquals("選択肢のテスト", choices.getBody());
}

}
