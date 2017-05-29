package com.club.management.clubmanagement.OL;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.club.management.clubmanagement.Core.NetworkTask;
import com.club.management.clubmanagement.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    // 파레트 아이디 설정 변수
    EditText userId, userPW;
    Button loginBtn;
    //eeeeeeeeeeeeeeeeeeeeeeeeeeeee

    // DB 통신위한 NetworkTask 클래스 생성
    NetworkTask networkTask = new NetworkTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userId = (EditText)findViewById(R.id.userId);
        userPW = (EditText)findViewById(R.id.userPW);
        loginBtn = (Button) findViewById(R.id.btnLogin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("url", "/co/procLogin.do");
                params.put("userId", userId.getText().toString());
                params.put("userPW", userPW.getText().toString());

                try {
                    // db로 전송 networkTask.execute(params);
                    // .get();  String으로 return 값 받기
                    String result = (networkTask.execute(params)).get();

                    JSONObject jsonObject = new JSONObject(result);
                    userId.setText(String.valueOf(jsonObject.getInt("rtnCode")));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
