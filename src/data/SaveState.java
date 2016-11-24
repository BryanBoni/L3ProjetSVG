package data;

/**
 * Save the position and the zoom of a canvas panel.
 *
 * @author BryanBoni.
 */
public class SaveState {

    private int translateX;
    private int translateY;
    private int zoom;

    /**
     * 
     * @param translateX
     * @param translateY
     * @param zoom 
     */
    public SaveState(int translateX, int translateY, int zoom) {
        this.translateX = translateX;
        this.translateY = translateY;
        this.zoom = zoom;
    }

    /**
     * 
     * @return 
     */
    public int getTranslateX() {
        return translateX;
    }

    /**
     * 
     * @param translateX 
     */
    public void setTranslateX(int translateX) {
        this.translateX = translateX;
    }

    /**
     * 
     * @return 
     */
    public int getTranslateY() {
        return translateY;
    }

    /**
     * 
     * @param translateY 
     */
    public void setTranslateY(int translateY) {
        this.translateY = translateY;
    }

    /**
     * 
     * @return 
     */
    public int getZoom() {
        return zoom;
    }

    /**
     * 
     * @param zoom 
     */
    public void setZoom(int zoom) {
        this.zoom = zoom;
    }
}
