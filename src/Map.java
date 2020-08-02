import java.io.*;
import java.util.Date;
import java.util.Random;

public class Map
{
    private int sizeX;
    private int sizeY;
    private int[][] map;
    //private Block[][] blocks;
    private Wall[][] walls;
    public static int WIDTH_CONSTANT , HEIGHT_CONSTANT;

    public Map()
    {
        map = fileReader();
        sizeX = map.length;
        sizeY = map[0].length;

        WIDTH_CONSTANT = GameFrame.GAME_WIDTH / sizeX;
        HEIGHT_CONSTANT = GameFrame.GAME_HEIGHT / sizeY;

        //blocks = new Block[sizeX][sizeY];
        walls = new Wall[sizeX][sizeY];
        for (int i=0; i<sizeX; i++)
        {
            for (int j=0; j<sizeY; j++)
            {

                switch (map[i][j])
                {
                    case 0:
                    {
                        //blocks[i][j] = new FloorBlock();
                    }
                    case 1:
                    {
                        //blocks[i][j] = new ReflectorBlock();
                        walls[i][j] = new Wall();
                        walls[i][j].setDimensionX(j * WIDTH_CONSTANT);
                        walls[i][j].setDimensionX(i * HEIGHT_CONSTANT);
                        Data.walls.add(walls[i][j]);
                        System.out.println("wall added.");

                    }
                    case 2:
                    {
                        //blocks[i][j] = new DestructiveBlock();
                        walls[i][j] = new Wall();
                        walls[i][j].setDimensionX(j * WIDTH_CONSTANT);
                        walls[i][j].setDimensionX(i * HEIGHT_CONSTANT);
                        Data.walls.add(walls[i][j]);
                        System.out.println("wall added2222222.");
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
        System.out.println(lines[1]);
        int sizeY = lines.length;
        int sizeX = lines[0].length()-1;
        int[][] mapRead = new int[sizeY][sizeX];
        for (int i=0; i<sizeY; i++)
            for (int j = 0; j < sizeX; j++) {
                try {
                    mapRead[i][j] = Integer.parseInt(String.valueOf(lines[i].charAt(j)));

                }catch (Exception e)
                {
                    System.out.println(lines[i].charAt(j));
                }
            }

        return mapRead;
    }
}

