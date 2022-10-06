package bomberman.entities;

import java.io.*;

public class Map {
    public static char map[][] = null;
    public static Entity mapObjects[][] = null;
    public static int level;
    public static int row;
    public static int column;

    public void loadMap() throws IOException {
        BufferedReader bufferedReader = null;

        try {
            Reader reader = new FileReader("res/levels/Level1.txt");

            bufferedReader = new BufferedReader(reader);


            String firstLine = bufferedReader.readLine();
            System.out.println(firstLine);

            level = 0;
            row = 0;
            column = 0;

            String[] tokens = firstLine.split(" ");
            level = Integer.parseInt(tokens[0]);
            row = Integer.parseInt(tokens[1]);
            column = Integer.parseInt(tokens[2]);

            map = new char[row][column];
            mapObjects = new Entity[row][column];

            for (int i = 0; i < row; i++) {
                String line = bufferedReader.readLine();
                for (int j = 0; j < column; j++) {
                    char character = line.charAt(j);
                    map[i][j] = character;
                }
            }
        }
        catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }
}
