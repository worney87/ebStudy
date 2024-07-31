package ex03;

public class ex03main {
    public static void main(String[] args) {
        /* 3. 멀티파트 파일 읽어서 파일로 저장 */

        FileUploadHandler firstFile = new FileUploadHandler("text1");
        firstFile.store("C://dev/study/study01_parsingAlgorithm/output/first.txt");

        FileUploadHandler secondFile = new FileUploadHandler("text2");
        secondFile.store("C://dev/study/study01_parsingAlgorithm/output/second.txt");
    }
}
