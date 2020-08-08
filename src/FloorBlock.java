public class FloorBlock {

    // coordinates , width and height of empty blocks.
    private int width, height , dimensionX , dimensionY;

    /**
     * getter and setter methods.
     * @param dimensionX
     */
    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
    }

    /**
     *
     * @param dimensionY
     */
    public void setDimensionY(int dimensionY) {
        this.dimensionY = dimensionY;
    }

    /**
     *
     * @return
     */
    public int getDimensionX() {
        return dimensionX;
    }

    /**
     *
     * @return
     */
    public int getDimensionY() {
        return dimensionY;
    }



    public FloorBlock(){
        width = Map.WIDTH_CONSTANT;
        height = Map.HEIGHT_CONSTANT;
    }


    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean checkOverlap(int x , int y){
        if (x > dimensionX + 20 && x < dimensionX+ width - 20 && y > dimensionY + 20 && y < dimensionY + height - 20)
            return true;
        return false;
    }
}
