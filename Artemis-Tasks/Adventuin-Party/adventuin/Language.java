package pgdp.adventuin;

public enum Language {
    ENGLISH, GERMAN;

    public String getLocalizedChristmasGreeting(String greeterName){
        switch(this){
            case GERMAN:
                return "Fröhliche Weihnachten wünscht dir " + greeterName + "!";
            case ENGLISH:
                return greeterName + " wishes you a Merry Christmas!";
            default:
                return "Happy Holidays!";
        }
    }
}
