public class Password {
    private final String value;

    public Password(String s) {
        this.value = s;
    }

    private int charType(char c) {
        if (Character.isUpperCase(c)) return 1;
        if (Character.isLowerCase(c)) return 2;
        if (Character.isDigit(c)) return 3;
        return 4;
    }

    private int passwordStrength() {
        boolean usedUpper = false, usedLower = false, usedNum = false, usedSym = false;
        int score = 0;

        for (char c : value.toCharArray()) {
            int type = charType(c);
            if (type == 1) usedUpper = true;
            if (type == 2) usedLower = true;
            if (type == 3) usedNum = true;
            if (type == 4) usedSym = true;
        }

        if (usedUpper) score++;
        if (usedLower) score++;
        if (usedNum) score++;
        if (usedSym) score++;
        if (value.length() >= 8) score++;
        if (value.length() >= 16) score++;

        return score;
    }

    public String calculateScore() {
        int score = passwordStrength();

        if (score == 6) return "This is a very good password :D check the Useful Information section to make sure it satisfies the guidelines";
        if (score >= 4) return "This is a good password :) but you can still do better";
        if (score >= 3) return "This is a medium password :/ try making it better";
        return "This is a weak password :( definitely find a new one";
    }

    @Override
    public String toString() {
        return value;
    }
}
