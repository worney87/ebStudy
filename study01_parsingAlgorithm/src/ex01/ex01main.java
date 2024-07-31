package ex01;

public class ex01main {
    public static void main(String[] args) {
        /* 1. 쿼리 스트링 파싱해서 객체 생성 */
        String queryString = "https://www.ebrainsoft.com/?id=kmc774&favorite=001&favorite=002";

        MyRequest request = new MyRequest();
        request.parse(queryString);

        System.out.println("id : " +request.getParam("id"));
        System.out.println("favorite.getClass() : " + request.getParams("favorite").getClass());
        System.out.println("favorite.length : " + request.getParams("favorite").length);
        System.out.println("favorite[0] : " + request.getParams("favorite")[0]);
        System.out.println("favorite[1] : " + request.getParams("favorite")[1]);
    }
}
