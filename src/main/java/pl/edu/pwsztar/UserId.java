package pl.edu.pwsztar;

import java.util.Arrays;
import java.util.Optional;

final class UserId implements UserIdChecker {

    private final String id;    // NR. PESEL

    public UserId(final String id) {
        this.id = id;
    }

    @Override
    public boolean isCorrectSize() {
        return id.length() == 11;
    }

    @Override
    public Optional<Sex> getSex() {
        if(isIdValid())
        {
            int[] arr = getIdAsIntArray();
            Sex sex = arr[9] % 2 == 0 ? Sex.WOMAN : Sex.MAN;
            return Optional.of(sex);
        }
        return Optional.empty();
    }

    @Override
    public boolean isCorrect() {
        return isIdValid();
    }

    @Override
    public Optional<String> getDate() {
        if(isIdValid()){
            return Optional.of(getFullDate());
        }
        return Optional.empty();
    }

    private boolean isIdNumber(){
        return id.matches("^[0-9]*$");
    }

    private boolean isIdValid(){
        if(id.length() == 11 && isIdNumber()){
            int[] sumControlNumbers = {9,7,3,1,9,7,3,1,9,7};
            int sum=0;
            int[] idInts = getIdAsIntArray();

            for (int i = 0; i < sumControlNumbers.length; i++) {
                sum += idInts[i] * sumControlNumbers[i];
            }
            return sum % 10 == idInts[10];
        }
        return false;
    }

    private String getMonth(int month) {
        if (month > 80 && month < 93) {
            month -= 80;
        }
        else if (month > 20 && month < 33) {
            month -= 20;
        }
        else if (month > 40 && month < 53) {
            month -= 40;
        }
        else if (month > 60 && month < 73) {
            month -= 60;
        }
        if(month<10){
            return '0'+String.valueOf(month);
        }
        return String.valueOf(month);
    }

    private String getDay(int day) {
        if(day<10){
            return '0'+String.valueOf(day);
        }
        return String.valueOf(day);
    }

    private String getFullDate() {
        int[] idInts = getIdAsIntArray();
        int year = (idInts[0] * 10) + idInts[1];

        int month = (idInts[2] * 10) + idInts[3] ;

        if (month > 80 && month < 93) {
            year += 1800;
        }
        else if (month > 0 && month < 13) {
            year += 1900;
        }
        else if (month > 20 && month < 33) {
            year += 2000;
        }
        else if (month > 40 && month < 53) {
            year += 2100;
        }
        else if (month > 60 && month < 73) {
            year += 2200;
        }

        return getDay((idInts[4] * 10) + idInts[5]) + "-" + getMonth(month) + "-" +year;
    }

    private String[] getIdStringArray(){
        return id.split("");
    }

    private int[] getIdAsIntArray(){
        return Arrays.stream(getIdStringArray()).mapToInt(Integer::parseInt).toArray();
    }

}
