package com.example.demo.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Users;

@SpringBootTest
@Transactional // 各テスト終了後に自動ロールバックされ、DBを汚しません
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsertAndFindByIdAndFindByName() {
        // 1. テストデータの準備（bodyは設定せず、nameだけをユニークな値で指定）
        Users newUser = new Users();
        newUser.setName("テスト用ユニーク名");

        // 2. insert のテスト実行
        int insertCount = userMapper.insert(newUser);
        assertThat(insertCount).isEqualTo(1);

        // ※ useGeneratedKeys="true" により、newUserオブジェクト自身に自動採番されたIDがセットされます
        long generatedId = newUser.getId();
        assertThat(generatedId).isGreaterThan(0);

        // 3. findById のテスト実行
        Users foundUser = userMapper.findById(generatedId);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getName()).isEqualTo("テスト用ユニーク名");

        // 4. findByName のテスト実行
        Users foundByNameUser = userMapper.findByName("テスト用ユニーク名");
        assertThat(foundByNameUser).isNotNull();
        assertThat(foundByNameUser.getId()).isEqualTo(generatedId);
    }

    @Test
    void testFindAll() {
        // 1. 現在の全ユーザーリストを取得
        List<Users> beforeList = userMapper.findAll();
        int beforeCount = beforeList.size();

        // 2. 1件追加
        Users user = new Users();
        user.setName("カウント追加用");
        userMapper.insert(user);

        // 3. 再取得して件数が確実に1件増えているか検証
        List<Users> afterList = userMapper.findAll();
        assertThat(afterList).hasSize(beforeCount + 1);
    }

    @Test
    void testUpdate() {
        // 1. テスト用ユーザーを追加
        Users user = new Users();
        user.setName("更新前名前");
        userMapper.insert(user);

        // 2. 名前を変更して更新処理を実行
        user.setName("更新後名前");
        int updateCount = userMapper.update(user);
        assertThat(updateCount).isEqualTo(1);

        // 3. データベースから再取得して、名前が変わっているか検証
        Users updatedUser = userMapper.findById(user.getId());
        assertThat(updatedUser.getName()).isEqualTo("更新後名前");
    }

    @Test
    void testDeleteById() {
        // 1. テスト用ユーザーを追加
        Users user = new Users();
        user.setName("削除対象");
        userMapper.insert(user);

        long id = user.getId();

        // 2. 削除処理を実行
        int deleteCount = userMapper.deleteById(id);
        assertThat(deleteCount).isEqualTo(1);

        // 3. 削除後に再取得を試み、null（存在しない）になることを検証
        Users deletedUser = userMapper.findById(id);
        assertThat(deletedUser).isNull();
    }
}
