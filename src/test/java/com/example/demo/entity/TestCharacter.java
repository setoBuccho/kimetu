package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestCharacter {
	//引数なしでインスタンスを作成できる()
	@Test
void testNoArgsConstructor() {
		Characters characters = new Characters();
		assertNotNull(characters); 
		assertEquals(0, characters.getId());
}
	//GetterとSetterで値を設定取得できる()
	@Test
	void testGetterAndSetter() {
		Characters characters = new Characters();
		characters.setId(1);
		characters.setFamilyName("竈門");
		characters.setFirstName("炭治郎");
		characters.setFamilyNameKana("カマド");
		characters.setFirstNameKana("タンジロウ");
		characters.setDescription("キャラクターの説明"); 
		characters.setDescriptionResult("診断結果の説明"); 
		characters.setCharacterImagePath("/images/character.png");
		characters.setResultImagePath("/images/result.png");
		assertEquals(1, characters.getId()); 
		assertEquals("竈門", characters.getFamilyName());
		assertEquals("炭治郎", characters.getFirstName()); 
		assertEquals("カマド", characters.getFamilyNameKana());
		assertEquals("タンジロウ", characters.getFirstNameKana()); 
		assertEquals("キャラクターの説明", characters.getDescription()); 
		assertEquals("診断結果の説明", characters.getDescriptionResult()); 
		assertEquals("/images/character.png", characters.getCharacterImagePath()); 
		assertEquals("/images/result.png", characters.getResultImagePath());
	
	}
	//// 全引数コンストラクタのテスト
	@Test
	void testAllArgsConstructor() {
		Characters characters = new Characters(1,"竈門","炭治郎","カマド","タンジロウ","キャラクターの説明","診断結果の説明","/images/character.png","/images/result.png");
		
		assertEquals(1,characters.getId());
		assertEquals("竈門", characters.getFamilyName());
		assertEquals("炭治郎", characters.getFirstName()); 
		assertEquals("カマド", characters.getFamilyNameKana());
		assertEquals("タンジロウ", characters.getFirstNameKana()); 
		assertEquals("キャラクターの説明", characters.getDescription()); 
		assertEquals("診断結果の説明", characters.getDescriptionResult()); 
		assertEquals("/images/character.png", characters.getCharacterImagePath()); 
		assertEquals("/images/result.png", characters.getResultImagePath());
	}
}
