package tsinn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import tsinn.ui.Animatable;
import tsinn.ui.BaseApp;
import tsinn.ui.Drawable;

/**
 * An example program that extends BaseApp and implements both Drawable and Animatable.
 * It draws the message 'hello' on the screen and reacts to mouse events.
 * @author Timothy Sinnott
 *
 */
public class Example extends BaseApp implements Drawable, Animatable
{
	private int x; 
	private int y;
	private int[] speed = {0,0};


	public void draw(GraphicsContext gc) 
	{	
		gc.fillText("Hello!", x, y);
		
	}

	public void setup(GraphicsContext gc) 
	{	 
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(new Font(30));
		addAnimatable(this);
		addDrawable(this);
		x= (int) getWidth()/2;
		y=(int) getHeight()/2;
		
		addListener(MouseEvent.MOUSE_PRESSED, e -> clicked( (MouseEvent)e));
		addListener(MouseEvent.MOUSE_DRAGGED, e -> clicked((MouseEvent)e));
		addListener(MouseEvent.MOUSE_RELEASED, e ->{
			speed[0] =(int) ((Math.random()*8)-4);
			speed[1] =(int) ((Math.random()*8)-4);
			});
		
		
		animate();
	}
	
	public void updateAnimation(long howLong) 
	{
		x+=speed[0];
		x +=getWidth();
		x %=getWidth();
		y+=speed[1];
		y+=getHeight();
		y%=getHeight();
	}
	
	public void clicked (MouseEvent me)
	{
		File f = new File("hello.txt");
		try
		{
			FileWriter fw = new FileWriter (f);
			fw.append("lskdfjlksjdf");
			fw.flush();
			fw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		this.playSound("snd\\mySound.wav", 900, 1500);
		x=(int)me.getX();
		y=(int)me.getY();
		speed[0] =0;
		speed[1] =0;
		update();
	}
	
	
	public static void main (String[] args)
	{
		launch();
	}
	

}
