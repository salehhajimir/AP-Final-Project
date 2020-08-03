public class FloorBlock {
    private int width, height , dimensionX , dimensionY;

    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
    }

    public void setDimensionY(int dimensionY) {
        this.dimensionY = dimensionY;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }


    public FloorBlock(){
        width = Map.WIDTH_CONSTANT;
        height = Map.HEIGHT_CONSTANT;
    }


    public boolean checkOverlap(int x , int y){
        if (x > dimensionX + 10 && x < dimensionX+ width - 10 && y > dimensionY + 10 && y < dimensionY + height - 10)
            return true;
        return false;
    }
}
