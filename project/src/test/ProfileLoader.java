package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProfileLoader {
    // 사용자 프로필을 로드하는 정적 메서드입니다.
    public static UserProfile loadProfile(String userId) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\admin\\Desktop\\project\\member_list"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split("/");
                // 사용자 ID를 찾아 해당하는 사용자 프로필을 반환합니다.
                if (userData.length >= 4 && userData[0].equals(userId)) {
                    // 데이터 형식이 "아이디/비밀번호/이름/색상"으로 가정됩니다.
                    String name = userData[2];
                    String color = userData[3];
                    return new UserProfile(name, color);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // 파일 읽기 오류를 처리합니다.
        }
        return null; // 사용자를 찾지 못한 경우 또는 오류가 발생한 경우
    }
}



class UserProfile {
    private String name; // 사용자 이름
    private String color; // 선택한 색상

    // 사용자 프로필 객체를 생성하는 생성자입니다.
    public UserProfile(String name, String color) {
        this.name = name;
        this.color = color;
    }

    // 사용자 이름을 반환하는 메서드입니다.
    public String getName() {
        return name;
    }

    // 선택한 색상을 반환하는 메서드입니다.
    public String getColor() {
        return color;
    }
}
