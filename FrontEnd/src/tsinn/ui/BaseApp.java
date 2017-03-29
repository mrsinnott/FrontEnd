package tsinn.ui;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.InputEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;



/**
 * BaseApp is the abstract base class for all visual applications. 
 * All applications must subclass this class and then call launch on it. This class itself extends the JavaFX Application class.
 * @author Timothy Sinnott
 *
 */
public abstract class BaseApp extends Application
{
	private BaseAppVisual baseVisual = null;
	private AnimationTimer at = null;
	private ArrayList<Animatable> animations = new ArrayList<>();
	
	//Media med = new Media(Paths.get("snd\\mySound.wav").toUri().toString());
	/*
	
	MediaPlayer mp = new MediaPlayer(med);
	*/
	
	/**
	 * setup is a required method in all subclasses. This object will call this setup method when the application is first starting.
	 * It is expected that all needed initialization needs will be taken care of at that time.
	 * @param gc The GraphicsContext of the canvas being used to draw this application on.
	 */
	public abstract void setup(GraphicsContext gc);
	
	/**
	 *  Starts this application visually. This method will be called as a result of a call to this Application's launch method being called.
	 *  Note that this class itself does not directly launch itself. It is expected that, for example, child classes will call launch as needed
	 *  @param primaryStage The primary stage used by this application. It is subsequently passed to the constructor of this objetc's BaseAppVisual.
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(Stage primaryStage) throws Exception 
	{
		baseVisual = new BaseAppVisual(primaryStage);
		setup(this.baseVisual.getGraphicsContext2D());
		update();
	}
	
	/**
	 * Adds a Drawable item to this object's list of Drawable items. All known drawable items will then be asked to draw anytime this object is asked to draw itself.
	 * @param drawableItem The Drawable item to be added 
	 */
	public void addDrawable (Drawable drawableItem)
	{
		this.baseVisual.addDrawableItem(drawableItem);
	}
	
	/**
	 * A generic way to add EventHandelers to this application. All events will be passed to this object's BaseAppVisual object via it's addEventHandler method
	 * @param et The EventType that is to be added. For Example, MouseEvent.MousePressed
	 * @param listener The eventHandler that is listening for the actual event.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addListener (EventType et, EventHandler<? super InputEvent> listener)
	{
		baseVisual.addEventHandler(et, listener);
	}
	
	/**
	 * Adds an Animatable item to this object's list of Animatable items. All animatable items will then be updated every time this application updates.
	 * Note that the animate() method must be called in order to request that the application enable animation and thereby use these Animatable items.
	 * @param animation The item that is to be animated.
	 */
	public void addAnimatable (Animatable animation)
	{
		if (animation != null)
		{
			this.animations.add(animation);
		}
	}
	
	/**
	 * Gets the width of this application's BaseAppVisual which is this application's canvas. 
	 * Note that the size of the canvas will resize based on the window.
	 * @return The width of the Canvas that this application uses to draw on.
	 */
	public int getWidth()
	{
		return (int)baseVisual.getWidth();
	}
	
	/**
	 * Gets the height of this application's BaseAppVisual which is this application's canvas. 
	 * Note that the size of the canvas will resize based on the window.
	 * @return The height of the Canvas that this application uses to draw on.
	 */
	public int getHeight()
	{
		return (int)baseVisual.getHeight();
	}
	
	/**
	 * Requests that this application updates.
	 * This will result in this application's BaseAppVisual draw method being called
	 */
	public void update()
	{
		//this.baseVisual.dr
		this.baseVisual.draw();
	}
	
	
	/**
	 * Requests that this application began animating.
	 * This will kick off an AnimationTimer which will regularly call all of this object's Animatable objects
	 */
	public void animate()
	{
		at = new AnimationTimer()
		{
			public void handle(long length) 
			{
				for (Animatable a:animations)
				{
					a.updateAnimation(length);
				}
				update();
			}
		};
		
		at.start();
	}
	
	/**
	 * A convenience method for playing an audio file based on the name of the audio file to be played.
	 * This method chains to playSound(Media, Duration, Duration)
	 * Where the duration if from 0 to the end of the specified audio file.
	 * @param audioFileName The name of the audio file to be played. It is used to create a new  javafx.scene.media.Media object
	 */
	public void playSound(String audioFileName)
	{
		Media med = new Media(Paths.get(audioFileName).toUri().toString());
		playSound(med, new Duration(0),med.getDuration());
	}
	
	/**
	 * A convenience method for playing sections of an audio file
	 * This method chains to playSound(Media, Duration, Duration)
	 * @param audioFileName The name of the audio file to be played
	 * @param fromMillis The time in milliseconds that the audio should start at
	 * @param toMillis The time in milliseconds that the audio should end at.
	 */
	public void playSound(String audioFileName, double fromMillis, double toMillis)
	{
		Media med = new Media(Paths.get(audioFileName).toUri().toString());
		playSound(med, new Duration(fromMillis), new Duration(toMillis));
	}
	
	/**
	 * Played the specified Media object from the specified start position to the specified stop position 
	 * @param med The Media object to be played
	 * @param fromMillis The position (in milliseconds) that the Media object's playback should begin
	 * @param toMillis The position (in milliseconds) that the Media object's playback should stop
	 */
	public void playSound (Media med, Duration fromMillis, Duration toMillis)
	{
		MediaPlayer mp = new MediaPlayer(med);
		mp.stop();
		mp.setStartTime(fromMillis);
		mp.setStopTime(toMillis);
		mp.play();
	}
	
	public static void main (String[] args)
	{
		launch();
	}

	
	

	

	
}