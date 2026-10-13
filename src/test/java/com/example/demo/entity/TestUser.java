package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TestUser {
	@Test 
	void testGetterAndSetter() { 
		// Usersクラスのインスタンスを作成する 
		Users users = new Users(); 
		// Setterを使ってユーザーIDに100を設定する 
		users.setId(100); 
		// Setterを使ってユーザー名を設定する 
		users.setName("テストユーザー"); 
		// Setterを使って本文を設定する 
		users.setBody("テスト用の本文です"); 
		// テスト用の登録日時を設定する 
		LocalDateTime createdAt = LocalDateTime.of(2026, 10, 13, 10, 30); 
		// Setterを使って登録日時を設定する 
		users.setCreatedAt(createdAt); 
		// Getterで取得したユーザーIDが100であることを確認する 
		assertEquals(100L, users.getId()); 
		// Getterで取得したユーザー名が一致することを確認する 
		assertEquals("テストユーザー", users.getName()); 
		// Getterで取得した本文が一致することを確認する 
		assertEquals("テスト用の本文です", users.getBody()); 
		// Getterで取得した登録日時が一致することを確認する 
		assertEquals(createdAt, users.getCreatedAt()); 
		} // 引数なしコンストラクタでインスタンスを作成できるかテストする @Test void testNoArgsConstructor() { // 引数なしでインスタンスを作成する Users users = new Users(); // IDの初期値が0であることを確認する assertEquals(0L, users.getId()); // ユーザー名の初期値がnullであることを確認する assertNull(users.getName()); // 本文の初期値がnullであることを確認する assertNull(users.getBody()); // 登録日時の初期値がnullであることを確認する assertNull(users.getCreatedAt()); }
	}

