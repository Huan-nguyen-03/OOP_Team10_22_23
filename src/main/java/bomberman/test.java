package bomberman;

import java.util.Random;
public class test {

    public Random rand = new Random();
    public boolean hasPortal = false;
    public static int numberOfBrick = 20;


    public static void main(String[] args) {

        while (numberOfBrick > 0) {
            numberOfBrick--;
            int a = ((numberOfBrick / 3) > 3 ? (numberOfBrick / 3 - 2) : 1);
            System.out.println(a);

        }
    }

}
