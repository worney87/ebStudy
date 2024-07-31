package ex01;

import java.util.*;

public class MyRequest {

    private HashMap<String, List<String>> params = new HashMap<>(); // 파라미터를 저장하는 해시맵

    MyRequest() {
    } // 기본 생성자

    // 추가 과제: 특수문자 인코딩(값으로 특수문자를 사용할 경우 에러바생)
    public void parse(String queryString) {

        int questionIndex = queryString.indexOf("?"); // '?'의 인덱스를 찾음

        if (questionIndex != -1) { // '?' 문자가 없으면 -1
            String str = queryString.substring(questionIndex + 1); // '?' 이후의 문자열을 추출
            parseParameter(str); // 파라미터를 파싱
        } else {
            System.out.println("잘못된 url 요청");
            ; // '?'가 없으면 잘못된 요청 메시지 출력
        }
    }

    private void parseParameter(String str) {
        String[] strArr = str.split("[&=]"); // '&' 또는 '='로 문자열을 분리

        for (int i = 0; i < strArr.length; i += 2) {
            String key = strArr[i]; // 키 추출
            String value = strArr[i + 1]; // 값 추출

            List<String> valueList = params.get(key);

            if (valueList == null) {
                valueList = new ArrayList<>();
                params.put(key, valueList);
            }
            valueList.add(value);
        }
    }

    public String getParam(String key) {
        return String.join("", params.get(key)); // 키에 해당하는 값을 하나의 문자열로 반환
    }

    public String[] getParams(String key) {
        return params.get(key).toArray(String[]::new);
    }
}

