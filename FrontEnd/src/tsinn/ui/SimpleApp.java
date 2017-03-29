package tsinn.ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;

public abstract class SimpleApp extends BaseApp implements Animatable, Drawable
{

	public abstract void setupApp (GraphicsContext gc);
	
	@Override
    public void setup(GraphicsContext gc)
    {
	    // TODO Auto-generated method stub
		this.addDrawable(this);
		this.addAnimatable(this);
		this.animate();
		BaseAppVisual bav = (BaseAppVisual) gc.getCanvas();
	/*	bav.setOnMouseMoved(e->{
			System.out.println("moving");
		});
		*/
		bav.setFocusTraversable(true);
		bav.setOnMouseMoved(e->this.onMouseMove(e));
		bav.setOnMousePressed(e->this.onMousePressed(e));
		bav.setOnMouseReleased(e->this.onMouseReleased(e));
		bav.setOnMouseDragged(e->this.onMouseDragged(e));
		bav.setOnKeyPressed(e->onKeyPressed(e));
		bav.setOnKeyReleased(e->onKeyReleased(e));
		bav.setOnTouchPressed(e->this.onTouchPressed(e));
		bav.setOnTouchReleased(e->this.onTouchReleased(e));
		bav.setOnTouchMoved(e->this.onTouchMoved(e));
		
		setupApp(gc);
		
    }
	
	public void onMouseMove(MouseEvent me){}
	public void onMousePressed(MouseEvent me){}
	public void onMouseReleased(MouseEvent me){}
	public void onMouseDragged(MouseEvent me){}
	public void onKeyPressed(KeyEvent ke){}
	public void onKeyReleased(KeyEvent ke){}
	public void onTouchPressed(TouchEvent te){}
	public void onTouchReleased(TouchEvent te){}
	public void onTouchMoved(TouchEvent te){}
	
	
	
	
	
}