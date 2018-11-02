package android.example.com.tflitecamerademo;

public class Resultmodel {

    public Resultmodel() {}

    public String getTextFor(String name) {
        String text = findTextForName(name);
        return text;
    }


    private String findTextForName(String name) {

        String text = "";

        switch (name) {
            case "baldr":
                text = "ddddd";
                break;
            case "forseti":
                text = "fffff";
                break;
            case "nanna":
                text = "nnnn";
                break;
            case "skadi":
                text = "ssssss";
                break;
            case "ullr":
                text = "uuuuu";
                break;
            case "vidar":
                text = "vvv";
                break;

                default:
                    text = "don't found";
                    break;
        }



        return text;
    }
}
