package ex02;

import java.io.*;
import java.util.HashMap;

public class MyMultipartRequest {

    String httpMethod, httpPath, httpVersion; // HTTP 메서드, 경로, 버전을 저장할 변수
    HashMap<String, String> headers = new HashMap<>(); // 헤더를 저장할 HashMap

    MyMultipartRequest(File file) {
        try {
            parse(file); // 파일을 처리하기 위해 parse 메서드 호출
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void parse(File file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file)); // 파일을 읽기 위해 BufferedReader 생성
        String textLine; // 파일의 각 줄을 저장할 변수

        try {   // 첫 번째 줄 처리
            textLine = reader.readLine(); // 첫 번째 줄 읽기
            if (textLine != null) {
                String[] splitArr = textLine.split(" "); // 공백으로 줄을 분할
                httpMethod = splitArr[0]; // HTTP 메서드 추출
                httpPath = splitArr[1]; // HTTP 경로 추출
                httpVersion = splitArr[2]; // HTTP 버전 추출
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {   // 두 번째 줄부터 마저 처리
                if ((textLine = reader.readLine()) == null) break; // 더 이상 읽을 줄이 없으면 루프 종료
                if (textLine.startsWith("--")) break; // 줄이 "--"로 시작하면 루프 종료
                String[] splitArr = textLine.split(": |; |="); // ": ", "; ", "="로 줄을 분할
                if (splitArr.length > 1) {
                    for (int i = 0; i < splitArr.length; i += 2) {
                        String key = splitArr[i]; // 키 추출
                        String value = splitArr[i + 1]; // 값 추출
                        headers.put(key, value); // 헤더에 키-값 쌍 추가
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getMethod() {
        return httpMethod; // HTTP 메서드 반환
    }

    public String getHeader(String key) {
        return headers.get(key); // 주어진 키에 대한 헤더 값 반환
    }
}
