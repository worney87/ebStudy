package ex03;

import java.io.*;
import java.util.*;

public class FileUploadHandler {
    private String boundary; // 경계 문자열을 저장할 변수
    Map<String, String> fileMetaData = new HashMap<>(); // 파일의 메타데이터를 저장할 맵
    StringBuilder fileContent = new StringBuilder(); // 파일의 내용을 저장할 StringBuilder

    public FileUploadHandler(String name) {
        File file = new File("C:/dev/study/study01_parsingAlgorithm/request-dummy.txt"); // 파일 객체 생성
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file)); // 파일을 읽기 위해 BufferedReader 생성
            boolean isSaveLine = false; // 현재 줄을 저장할지 여부를 나타내는 플래그
            Map<String, String> currentMetaData = new HashMap<>(); // 현재 파일의 메타데이터를 저장할 맵
            StringBuilder currentContent = new StringBuilder(); // 현재 파일의 내용을 저장할 StringBuilder
            String textLine;

            while ((textLine = reader.readLine()) != null) { // 파일의 각 줄을 읽음
                if (textLine.contains("boundary=")) { // 경계 문자열을 찾음
                    boundary = textLine.split("boundary=")[1]; // 경계 문자열을 저장
                    continue;
                }
                if (boundary != null && textLine.contains(boundary)) { // 경계 문자열을 포함하는 줄을 찾음
                    isSaveLine = false; // 저장을 중지
                    continue;
                }
                if (textLine.contains("Content-Disposition") && textLine.contains("name=" + name)) { // 특정 name을 포함하는 Content-Disposition 헤더를 찾음
                    isSaveLine = true; // 저장을 시작
                }
                if (isSaveLine) { // 현재 줄을 저장할 때
                    if (textLine.contains("Content-Disposition")) { // Content-Disposition 헤더를 처리
                        String[] parts = textLine.split(";|:|=");
                        for (int i = 0; i < parts.length; i += 2) {
                            String key = parts[i]; // 키 추출
                            String value = parts[i + 1]; // 값 추출
                            fileMetaData.put(key, value); // 메타데이터를 맵에 저장
                        }
                    } else if (textLine.contains("Content-Type")) { // Content-Type 헤더를 처리
                        fileMetaData.put("Content-Type", textLine.split(": ")[1]); // 메타데이터를 맵에 저장
                    } else if (!textLine.isEmpty()) { // 빈 줄이 아닌 경우
                        fileContent.append(textLine).append("\n"); // 내용을 StringBuilder에 추가
                    }
                }
            }
            System.out.println("File MetaData: " + fileMetaData); // 파일 메타데이터 출력
            System.out.println("File Content: " + fileContent); // 파일 내용 출력

        } catch (IOException e) {
            throw new RuntimeException(e); // 예외 발생 시 런타임 예외 발생
        }
    }

    public void store(String path) {
        File outputFile = new File(path); // 출력 파일 객체 생성
        outputFile.getParentFile().mkdirs(); // 경로가 존재하지 않으면 생성
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(fileMetaData + "\n"); // 파일 메타데이터를 파일에 씀
            writer.write(fileContent + "\n"); // 파일 내용을 파일에 씀
        } catch (IOException e) {
            throw new RuntimeException(e); // 예외 발생 시 런타임 예외 발생
        }
    }
}
