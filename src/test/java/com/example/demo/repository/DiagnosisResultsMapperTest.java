package com.example.demo.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Diagnosis_results;

@SpringBootTest
@Transactional // テストが終わったらインサートしたデータは自動で消えます
class DiagnosisResultsMapperTest {

    @Autowired
    private DiagnosisResultsMapper diagnosisResultsMapper;

    @Test
    void testInsertAndFindByUserIdOrderByDiagnosedAtDesc() {
        // 1. 初期データに確実に存在するユーザーID「1L」を使用（外部キー制約を回避）
        long userId = 1L;
        
        // インサート前の件数を記憶しておく
        int beforeCount = diagnosisResultsMapper.findByUserIdOrderByDiagnosedAtDesc(userId).size();
        
        // 初期データに確実に存在するキャラクターID「1」と「2」を使用
        Diagnosis_results result1 = new Diagnosis_results();
        result1.setUserId(userId);
        result1.setCharacterId(1); // 竈門 炭治郎
        result1.setDiagnosedAt(LocalDateTime.now());

        Diagnosis_results result2 = new Diagnosis_results();
        result2.setUserId(userId);
        result2.setCharacterId(2); // 我妻 善逸
        result2.setDiagnosedAt(LocalDateTime.now());

        // 2. insert のテスト実行
        int count1 = diagnosisResultsMapper.insert(result1);
        int count2 = diagnosisResultsMapper.insert(result2);

        assertThat(count1).isEqualTo(1);
        assertThat(count2).isEqualTo(1);

        // 3. findByUserIdOrderByDiagnosedAtDesc のテスト実行
        List<Diagnosis_results> history = diagnosisResultsMapper.findByUserIdOrderByDiagnosedAtDesc(userId);

        // 4. 検証（元の件数から2件増えているか）
        assertThat(history).hasSize(beforeCount + 2);
        
        // 追加したキャラクターID（1 と 2）が結果に含まれているか（順序は不問）
        assertThat(history)
            .extracting(Diagnosis_results::getCharacterId)
            .contains(1, 2);
    }

    @Test
    void testDeleteByUserId() {
        // 1. 初期データに確実に存在するユーザーID「2L」を使用
        long userId = 2L; 
        
        // 確実に削除を実行させるため、1件テストデータを追加する
        Diagnosis_results result = new Diagnosis_results();
        result.setUserId(userId);
        result.setCharacterId(3); // 嘴平 伊之助（実在するキャラID）
        result.setDiagnosedAt(LocalDateTime.now());
        
        diagnosisResultsMapper.insert(result);

        // 2. deleteByUserId のテスト実行
        int deletedCount = diagnosisResultsMapper.deleteByUserId(userId);
        
        // 1件以上削除されたことを検証
        assertThat(deletedCount).isGreaterThanOrEqualTo(1);

        // 3. 削除後に取得して、そのユーザーのデータが完全に空（0件）であることを確認
        List<Diagnosis_results> history = diagnosisResultsMapper.findByUserIdOrderByDiagnosedAtDesc(userId);
        assertThat(history).isEmpty();
    }
}
