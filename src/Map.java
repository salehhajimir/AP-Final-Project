import java.io.*;
import java.util.Date;
import java.util.Random;


/**
 * this class read the map file and convert it to needed information for program.
 */
public class Map {
    //map coordinates.
    private int sizeX , sizeY;
    // an array which contains map information.
    private int[][] map;
    // an array which contains walls information.
    private Wall[][] walls;
    // an array which contains empty blocks in map.
    private FloorBlock[][] floor;
    // vars which calculate the relation between map file and game frame.
    public static int WIDTH_CONSTANT, HEIGHT_CONSTANT;

    public Map() {
        map = fileReader();
        sizeY = map[0].length;
        sizeX = map.length;

        WIDTH_CONSTANT = GameFrame.GAME_WIDTH / sizeX;
        HEIGHT_CONSTANT = GameFrame.GAME_HEIGHT / sizeY;

        walls = new Wall[sizeX][sizeY];
        floor = new FloorBlock[sizeX][sizeY];



        for (int j = 0; j < sizeX; j++) {
            for (int i = 0; i < sizeY; i++) {
                switch (map[j][i]) {

                    case 0: {
                        floor[j][i] = new FloorBlock();
                        floor[j][i].setDimensionX(j * WIDTH_CONSTANT);
                        floor[j][i].setDimensionY(i * HEIGHT_CONSTANT);
                        Data.floor.add(floor[j][i]);
                        break;
                    }
                    case 1 : {
                        walls[j][i] = new Wall();
                        walls[j][i].setDestructive(false);
                        walls[j][i].setDimensionX(j * WIDTH_CONSTANT);
                        walls[j][i].setDimensionY(i * HEIGHT_CONSTANT);
                        Data.walls.add(walls[j][i]);
                        break;
                    }
                    case 2 : {
                        walls[j][i] = new Wall();
                        walls[j][i].setDestructive(true);
                        walls[j][i].setDimensionX(j * WIDTH_CONSTANT);
                        walls[j][i].setDimensionY(i * HEIGHT_CONSTANT);
                        Data.walls.add(walls[j][i]);
                        break;
                    }
                }
            }
        }
    }



    /**
     * Reads a random file in folder ".\maze\" and saves its content in map[][] array.
     * @author Aylar Sedaei
     */
    public int[][] fileReader()
    {
        final File directory = new File(".\\maze\\");
        File[] filesInDirectory = directory.listFiles();
        Random random = new Random();
        System.out.println(filesInDirectory);
        File selectedFile = filesInDirectory[random.nextInt(filesInDirectory.length)];
        String content = "";
        try (FileReader fileReader = new FileReader(selectedFile))
        {
            int eof;
            while ((eof = fileReader.read()) != -1)
                content = content.concat((""+(char)eof));

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String[] lines = content.split("\n");
        int sizeY = lines.length;
        int sizeX = lines[0].length()-1;
        int[][] mapRead = new int[sizeX][sizeY];
        for (int i=0; i<sizeY; i++)
            for (int j = 0; j < sizeX; j++) {
                try {
                    mapRead[j][i] = Integer.parseInt(String.valueOf(lines[i].charAt(j)));

                }catch (Exception e)
                {
                    System.out.println(lines[i].charAt(j));
                }
            }

        return mapRead;
    }
}

