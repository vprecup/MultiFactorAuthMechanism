
package p;


public class EmailTemplate {

    private String token;

    public static String templateGenerator(String token){

        return "<body><h1>Your token was generated:</h1><h2>"+token+"</h2></body>";
    }
}
