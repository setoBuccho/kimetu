package com.example.demo.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Questions;

@SpringBootTest
@Transactional
class QuestionMapperTest {

    @Autowired
    private QuestionMapper questionMapper;

    @Test
    void testFindAll() {
        // 1. findAll のテスト実行
        List<Questions> questionsList = questionMapper.findAll();

        // 2. 検証 (XML側で「id <= 15」と制限されているため、期待値は15件になります)
        assertThat(questionsList).hasSize(15);

        // 1問目の内容が正しく取得できているか確認
        assertThat(questionsList.get(0).getBody()).contains("文化祭の出し物");
    }

    @Test
    void testFindById() {
        // 1. 引数に実在する質問ID（1番）を指定して取得
        int targetId = 1;
        Questions question = questionMapper.findById(targetId);

        // 2. 検証
        assertThat(question).isNotNull();
        assertThat(question.getId()).isEqualTo(targetId);
        assertThat(question.getBody()).isEqualTo("文化祭の出し物、あなたの関わり方は？");
    }

    @Test
    void testFindById_NotFound() {
        // 存在しないID（999番など）で検索した場合のテスト
        Questions question = questionMapper.findById(999);

        // 結果が null になることを確認
        assertThat(question).isNull();
    }
}
