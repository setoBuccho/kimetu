package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Choices;

@SpringBootTest
@Transactional
public class ChoiceMapperTest {

    @Autowired
    private ChoiceMapper choiceMapper;

    // 質問IDを指定して選択肢を取得できるかテストする
    @Test
    void testFindByQuestionId() {

        // 質問IDが1の選択肢を取得する
        List<Choices> choicesList = choiceMapper.findByQuestionId(1);

        // 取得結果がnullではないことを確認する
        assertNotNull(choicesList);

        // 取得した選択肢の質問IDが1であることを確認する
        for (Choices choice : choicesList) {
            assertEquals(1, choice.getQuestionId());
        }
    }

    // 質問IDとキャラクターIDを指定して選択肢を取得できるかテストする
    @Test
    void testFindFinalChoices() {

        // テスト対象の質問IDを指定する
        int questionId = 1;

        // テスト対象のキャラクターIDを指定する
        List<Integer> characterIds = Arrays.asList(1, 2);

        // 条件に一致する選択肢を取得する
        List<Choices> choicesList =
                choiceMapper.findFinalChoices(questionId, characterIds);

        // 取得結果がnullではないことを確認する
        assertNotNull(choicesList);

        // 取得した選択肢の質問IDが指定したIDと一致することを確認する
        for (Choices choice : choicesList) {
            assertEquals(questionId, choice.getQuestionId());
            assertTrue(characterIds.contains(choice.getCharacterId()));
        }
    }

    // 選択肢IDを指定して1件取得できるかテストする
    @Test
    void testFindById() {

        // IDが1の選択肢を取得する
        Choices choice = choiceMapper.findById(1);

        // 選択肢が取得できたことを確認する
        assertNotNull(choice);

        // 取得した選択肢のIDが1であることを確認する
        assertEquals(1, choice.getId());
    }

    // 存在しないIDを指定した場合にnullが返るかテストする
    @Test
    void testFindByIdNotFound() {

        // 存在しないIDを指定して選択肢を取得する
        Choices choice = choiceMapper.findById(-1);

        // 該当する選択肢が存在しないためnullであることを確認する
        assertNull(choice);
    }
}