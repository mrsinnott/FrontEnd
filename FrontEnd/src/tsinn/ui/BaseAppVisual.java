package tsinn.ui;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import com.sun.glass.ui.Screen;


/**
 * BaseAppVisual is the the class that is responsible for displaying 
 * a BaseApp and any of that BaseApp's Drawable content.
 * It is a direct subclass of a JavaFX Canvas, and adds itself to the 
 * Stage that is passed into it's constructor. In doing this,
 *  it will set up the scene, root pane, and display the stage. 
 * @author Timothy Sinnott
 * @version 1.0
 * @see Drawable
 * @see BaseApp
 *
 */
public class BaseAppVisual extends Canvas
{
	
	/**
	 * A List of Drawable items that this class is responsible for telling to draw.
	 */
	private ArrayList <Drawable> drawableItems  = new ArrayList<>();
	
	/**
	 * Constructs a new BaseAppVisual object using the Stage parameter as the primary stage. 
	 * It will:
	 * <ul>
	 * <li> Create a root pane
	 * <li> Insert itself into that root
	 * <li> Create a new Scene, passing in the new root pane
	 * <li> Add this new scene to the primary stag that was passed into it.
	 * <li> Show the primary stage.
	 * </ul>
	 * @param primaryStage The stage that is to be used as the primary visual stage for the applications.
	 */
	public BaseAppVisual (Stage primaryStage)
	{
		this.widthProperty().addListener(evt -> draw());
		this.heightProperty().addListener(evt -> draw());	
		this.sceneProperty().addListener(evt -> bindToScene());
		
		Pane root = new Pane();
		root.getChildren().add(this);
		Scene scene = new Scene(root);
		List<Screen> ss =  Screen.getScreens();
		Screen s = ss.get(ss.size()-1);
		primaryStage.setScene(scene);
		primaryStage.setX(s.getX());
		primaryStage.setY(s.getY());
		primaryStage.setFullScreen(true);
		primaryStage.show();
		
		this.setFocusTraversable(true);
		//this.getGraphicsContext2D().setFocusTraversable(true);
		this.addEventHandler(KeyEvent.KEY_PRESSED, e->{
		});
		
		primaryStage.setOnHiding(e -> windowHiding(e));
		
	
	}
	
	public void windowHiding(WindowEvent we){System.out.println("window hiding");};
	
	private void bindToScene()
	{
		this.widthProperty().bind(this.getScene().widthProperty());
		this.heightProperty().bind(this.getScene().heightProperty());
	}
	
	/*
	public void add(EventHandler<MouseEvent> listener)
	{
		this.getScene().setOnMousePressed(listener);
	}*/
	
	/**
	 * Adds a Drawable item to this visual class. All drawable items will be asked to draw everytime this class is asked to draw.
	 * @param item The Drawable item that is to be drawn everytime this class is asked to redraw itself. Null values will be ignored.
	 */
	public void addDrawableItem (Drawable item)
	{
		if (item!= null)
		{
			this.drawableItems.add(item);
		}
	}
	
	/**
	 * Used to request that the class redraws itself. 
	 * In doing this, it will:
	 * <ul>
	 * <li>Clear all contents from this object's graphics context
	 * <li> Loop over all Drawable items that this object has a reference to and call draw() on each one.
	 * </ul>
	 * @see Drawable
	 */
	public void draw()
	{
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.clearRect(0.0, 0.0, getWidth(), getHeight());
		
		for (Drawable item : drawableItems)
		{
			item.draw(gc);
		}
	}
		
}
