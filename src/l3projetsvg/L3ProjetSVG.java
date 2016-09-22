package l3projetsvg;

import view.MainWindow;

public class L3ProjetSVG {

    public static void main(String[] args) {
        System.out.println("L3ProjetSVG");
		
		MainWindow window = new MainWindow();
		window.resetImage();
		window.drawPoint(1,1);
		window.drawPoint(1,2);
		window.drawPoint(1,3);
		window.drawPoint(1,4);
		window.drawPoint(1,5);
		window.drawPoint(1,6);
		window.drawPoint(10,25);
		window.drawPoint(91,17);
		window.showImage();
    }
    
}
