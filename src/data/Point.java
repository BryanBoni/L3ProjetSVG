/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.awt.Color;
import java.awt.Graphics2D;

public class Point  implements IDrawableSVG {
	

	@Override
	public void render(Graphics2D g) {
		int size = Math.round(5);
		g.setColor(Color.red);
		g.drawLine(-size,size,size,-size);
		g.drawLine(-size,-size,size,size);
	}
}