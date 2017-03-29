package tsinn.ui;
import javafx.scene.canvas.GraphicsContext;

/**
 * Drawable interface that all classes must implement if they wish to be drawn via a BaseAppVisual
 * @author Timothy Sinnott
 *
 */
public interface Drawable
{
	
	/**
	 * The method that BaseAppVisual calls whenever it draws itself
	 * @param gc The BaseAppVisual's GraphicsContext
	 */
	public void draw(GraphicsContext gc);
	
}