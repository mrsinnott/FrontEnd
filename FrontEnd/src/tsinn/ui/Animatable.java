package tsinn.ui;


/**
 * Animatable is the interface that all classes must implement if they with the BaseApp to update them any time the BaseApp is animating.
 * Note that these objects will only be called if the BaseApp's animat() method has been called.
 * @author Timothy Sinnott
 *
 */
public interface Animatable
{
	
	/**
	 * The method that is called by BaseApp anytime the BaseApp is animating
	 * @param howLong The time since the last animation update was called (I think...)
	 */
	public void updateAnimation (long howLong);
}