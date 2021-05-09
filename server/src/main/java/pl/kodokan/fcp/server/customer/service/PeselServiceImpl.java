package pl.kodokan.fcp.server.customer.service;

import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.exception.IncorrectPeselException;
import pl.kodokan.fcp.server.userDetails.entity.Pesel;

import java.util.regex.Pattern;

@Service
public class PeselServiceImpl implements PeselService {

    @Override
    public boolean isCorrect(Pesel pesel){
        Pattern peselPattern = Pattern.compile("\\d{11}");     //PESEL number pattern - 11 digits

        if (peselPattern.matcher(pesel.getValue()).matches() == false) {  //checking if the PESEL number provided matches the pattern
            return false;
        }

        String month = pesel.getValue().substring(2, 4);       //birth month from PESEL number
        String day = pesel.getValue().substring(4, 6);         //birth day from PESEL number

        int monthInt = Integer.parseInt(month);
        monthInt = monthInt % 20;                  //modulo 20 operation to obtain a "clean" month
        int dayInt = Integer.parseInt(day);

        if (monthInt < 1 || monthInt > 12) {       //checking if the month is in the real range (1-12)
            return false;
        }
        if (dayInt < 1 || dayInt > 31) {           //checking if the day is in the real range (1-31)
            return false;
        }

        int[] peselInt = new int[10];              //table for conversions from characters to integers
        char[] peselChar = pesel.getValue().toCharArray();
        //changing type from char to int and putting it into an array in a loop
        for (int i = 0; i < 10; i++) {
            peselInt[i] = Character.getNumericValue(peselChar[i]);
        }

        int checkSum = calcCheckSum(peselInt);  //checksum, needed to determine the check digit

        if (checkSum > 10) //if the checksum is two-digit, we do the modulo 10 operation on it
        {
            checkSum = checkSum % 10;
        }

        int checkNum = 10 - checkSum;       //the check digit is the difference of 10 with the checksum

        if (checkNum != Character.getNumericValue(peselChar[10])) {       //checking whether the calculated check digit is the same as that entered by the user
            return false;
        }
        return true;
    }

    public int calcCheckSum(int peselInt[]) {
        int i = 0, checkSum = 0;
        for (int element : peselInt) {
            //multiplication of digits from the PESEL according to the formula, and adding the result to the checksum
            switch (i % 4) {
                case 0:
                    if (element * 1 > 10) {
                        checkSum += (element * 1) % 10;
                    } else {
                        checkSum += element * 1;
                    }
                    break;
                case 1:
                    if (element * 3 > 10) {
                        checkSum += (element * 3) % 10;
                    } else {
                        checkSum += element * 3;
                    }
                    break;
                case 2:
                    if (element * 7 > 10) {
                        checkSum += (element * 7) % 10;
                    } else {
                        checkSum += element * 7;
                    }
                    break;
                case 3:
                    if (element * 9 > 10) {
                        checkSum += (element * 9) % 10;
                    } else {
                        checkSum += element * 9;
                    }
                    break;
            }
            i++;
        }
        return checkSum;
    }
}
