/*
This class is use to draw simple compenents in the canvas,
like a line, a circle, a point/pixel , and a square.
*/
package draw;

import Maths.Vector2f;
import Maths.Vector3f;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class SimpleDrawer {
    BufferedImage m_canvasImage;
 
    public SimpleDrawer() {
        //there is the constructor.
    }

    public SimpleDrawer(BufferedImage m_canvasImage) {
        //there is the constructor, with a BufferedImage needed.
        this.m_canvasImage = m_canvasImage;
    }
    
    public void drawPixel(Vector2f position, Vector3f color){
        /*
        There is the function who take a position vector and a color vector
        and draw a pixel on the canvas.
        */
        
        //position
        int x = (int) position.x;
        int y = (int) position.y;
        
        //color
        int r= (int) color.x;
        int g= (int) color.y;
        int b= (int) color.z;
        int a= 0;
        Color cPixel = new Color(r, g, b, a);
        
        //Graphic object
        Graphics gPixel = m_canvasImage.getGraphics();
        
        gPixel.setColor(cPixel);
        
        gPixel.drawRect(x, y, 1, 1);
        
    }
    
}
