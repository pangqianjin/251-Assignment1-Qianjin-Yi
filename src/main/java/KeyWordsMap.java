import java.util.ArrayList;

public class KeyWordsMap {
    ArrayList<String> literalArray = new ArrayList<>();
    ArrayList<String> keywordArray = new ArrayList<>();
    ArrayList<String> primitiveTypeArray = new ArrayList<>();

    public ArrayList<String> getLiteralArray() {
        return literalArray;
    }

    public void setLiteralArray(ArrayList<String> literalArray) {
        this.literalArray = literalArray;
    }

    public ArrayList<String> getKeywordArray() {
        return keywordArray;
    }

    public void setKeywordArray(ArrayList<String> keywordArray) {
        this.keywordArray = keywordArray;
    }

    public ArrayList<String> getPrimitiveTypeArray() {
        return primitiveTypeArray;
    }

    public void setPrimitiveTypeArray(ArrayList<String> primitiveTypeArray) {
        this.primitiveTypeArray = primitiveTypeArray;
    }


    @Override
    public String toString() {
        return "KeyWordsMap{" +
                "literalArray=" + literalArray +
                ", keywordArray=" + keywordArray +
                ", primitiveTypeArray=" + primitiveTypeArray +
                '}';
    }
}
