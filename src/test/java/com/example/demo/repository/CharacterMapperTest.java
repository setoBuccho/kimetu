package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Characters;

@SpringBootTest 
@Transactional
public class CharacterMapperTest {
	@Autowired 
	private CharacterMapper characterMapper; 
	// 全キャラクターを取得できるかテストする 
	@Test
	void testFindAll() { 
		// Mapperを使って全キャラクターを取得する 
	List<Characters> charactersList = characterMapper.findAll();
	// 取得結果がnullではないことを確認する
	assertNotNull(charactersList); 
	// 取得したキャラクターを1件ずつ確認する
	for (Characters character : charactersList) { 
		// キャラクターのIDが0より大きいことを確認する
		assertTrue(character.getId() > 0); 
		// 姓がnullではないことを確認する 
		assertNotNull(character.getFamilyName()); 
		// 名がnullではないことを確認する a\
		assertNotNull(character.getFirstName());
		}
	} 
	// IDを指定してキャラクターを1件取得できるかテストする 
	@Test 
	void testFindById() { 
		// IDが1のキャラクターを取得する
		// ※ テスト用データベースにIDが1のデータが存在することが前提 
		Characters character = characterMapper.findById(1); 
		// キャラクターが取得できたことを確認する 
		assertNotNull(character); 
		// 取得したキャラクターのIDが1であることを確認する 
		assertEquals(1, character.getId()); 
		// 姓がnullではないことを確認する 
		assertNotNull(character.getFamilyName()); 
		// 名がnullではないことを確認する 
		assertNotNull(character.getFirstName()); 
		} 
	// 存在しないIDを指定した場合にnullが返るかテストする 
	@Test
	void testFindByIdNotFound() { 
		// 存在しないIDを指定してキャラクターを取得する
		Characters character = characterMapper.findById(-1); 
		// 該当するキャラクターが存在しないためnullであることを確認する 
		assertNull(character);
		}
	}
	
	

