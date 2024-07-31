package ex02;

import java.io.File;

public class ex02main {
    public static void main(String[] args) {
        /* 2. multipart/form-data 파싱 하기 */

        File multipartData = new File("C:/dev/study/study01_parsingAlgorithm/request-dummy.txt");
        MyMultipartRequest myMultipartRequest = new MyMultipartRequest(multipartData);

        System.out.println("Method : " + myMultipartRequest.getMethod());  //출력값: POST
        System.out.println("Host : " + myMultipartRequest.getHeader("Host"));  //localhost:8080
        System.out.println("User-Agent " + myMultipartRequest.getHeader("User-Agent"));  //Apache-HttpClient/4.3.4 (java 1.5)
    }
}
