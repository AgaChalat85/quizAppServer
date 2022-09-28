package pl.agnieszkachalat.quizapp.enums;

public enum QuizApiParameterEnum {
    
    CATEGORY("category"),
    DIFFICULTY("difficulty"),
    LIMIT("limit"),
    TAGS("tags");
    
    private final String name;
    
    private QuizApiParameterEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
