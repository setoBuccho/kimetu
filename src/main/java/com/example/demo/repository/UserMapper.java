package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Users;

@Mapper
public interface UserMapper {
	 /** 全ユーザーを取得（管理画面用など） */
    List<Users> findAll();
 
    /** IDでユーザーを1件取得（マイページ表示やログインチェック用） */
    Users findById(long id);
 
    /** ユーザーを新規登録（ユーザーの作成） */
    int insert(Users user);
 
    /** ユーザー情報の更新（名前の変更など） */
    int update(Users user);
 
    /** IDでユーザーを削除（退会処理：紐づく診断結果も自動で消えます） */
    int deleteById(long id);
    
    Users findByName(String name);
}
