package tsinn.ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class AnotherExample extends SimpleApp
{
	private double x=0;
	private double y=0;
	private double size=100;
	private Color color = null;
	private boolean moving = false;
	private double sy=-1;
	
    public void updateAnimation(long howLong)
    {
    	if(!moving)
    	{
	    	sy+=2;
	    	y=y+sy;
	    	if (y>getHeight()-(size/2))
	    	{
	    		y=getHeight()-(size/2);
	    		sy=sy*-0.8;
	    	}
    	}
	  
    }

    public void draw(GraphicsContext gc)
    {
    	
	    if (moving)
	    {
	    	gc.setFill(Color.CORNFLOWERBLUE);
	    }
	    else
	    {
	    	gc.setFill(Color.DARKBLUE);
	    }
	    	
	    gc.fillOval(x-size/2, y-size/2, size, size); 
    }

    public void setupApp(GraphicsContext gc)
    {
	    x = this.getWidth()/2;
	    y = this.getHeight()/2;
	    if (color == null)
	    {
	    	color = Color.ROYALBLUE;
	    }
    }
	
	public static void main (String[] args)
	{
		launch();
	}
	
	public void onMousePressed(MouseEvent event)
	{
		if (Math.sqrt( Math.pow(x-event.getX(),2) + Math.pow(y-event.getY(),2)) < size/2)
		{
			moving = true;
			this.color = Color.BLUEVIOLET;
			x = event.getX();
			y = event.getY();
		}
		
	}
	
	public void onMouseReleased (MouseEvent me)
	{
		moving = false;
		this.color = Color.ROYALBLUE;
		sy=0;
	}
	
	public void onMouseDragged (MouseEvent me)
	{
		if (moving)
		{
			x = Math.max(size/2, Math.min(getWidth()-size/2,me.getX()));
			y = Math.max(size/2, Math.min(getHeight()-size/2,me.getY()));
		}
	}
	
	
}