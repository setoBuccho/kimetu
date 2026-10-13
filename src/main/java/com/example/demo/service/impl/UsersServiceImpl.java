package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Characters;
import com.example.demo.entity.Diagnosis_results;
import com.example.demo.entity.Users;
import com.example.demo.form.UserEditForm;
import com.example.demo.helper.UserEditHelper;
import com.example.demo.repository.CharacterMapper;
import com.example.demo.repository.DiagnosisResultsMapper;
import com.example.demo.repository.UserMapper;
import com.example.demo.service.UsersService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UserMapper userMapper;
    private final DiagnosisResultsMapper diagnosisResultsMapper;
    private final CharacterMapper characterMapper;
    private final UserEditHelper userEditHelper;

    @Override
    public List<Users> getAllUsers() {
        return userMapper.findAll();
    }

    @Override
    public Users getUserById(long id) {
        return userMapper.findById(id);
    }

    @Override
    public void saveUser(Users user) {
        userMapper.insert(user);
    }

    @Override
    public void updateUser(Users user) {
        userMapper.update(user);
    }

    /** ユーザー一覧を診断結果付きで取得 */
    @Override
    public List<UserEditForm> getUserList() {

        List<Users> users = userMapper.findAll();

        return users.stream().map(user -> {

            UserEditForm form = userEditHelper.toForm(user);

            // ユーザーの診断履歴を新しい順で取得
            List<Diagnosis_results> results =
                    diagnosisResultsMapper.findByUserIdOrderByDiagnosedAtDesc(user.getId());

            // 診断結果が存在する場合
            if (!results.isEmpty()) {

                // 最新の診断結果を取得
                Diagnosis_results latestResult = results.get(0);

                // キャラクターIDからキャラクター情報を取得
                Characters character =
                        characterMapper.findById(latestResult.getCharacterId());

                if (character != null) {
                    form.setDiagnosisResult(
                            character.getFamilyName() + character.getFirstName()
                    );
                }
            }

            return form;

        }).toList();
    }

    /** ユーザー削除 */
    @Override
    public void deleteUser(long id) {

        // 先に診断結果を削除
        diagnosisResultsMapper.deleteByUserId(id);

        // その後ユーザーを削除
        userMapper.deleteById(id);
    }
    
    @Override
    public Users findUserByName(String name) {
        return userMapper.findByName(name);
    }
}