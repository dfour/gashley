package blog.gamedevelopment.gashley.core.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public abstract class GashleyBaseController implements InputProcessor {
	public boolean left = false;
	public boolean right = false;
	public boolean up = false;
	public boolean down = false;
	public boolean isMouse1Down = false; 
	public boolean isMouse2Down = false; 
	public boolean isMouse3Down = false; 
	public boolean isDragged = false;
	public int lastScrollAmount = 0;
	public Vector2 mouseLocation = new Vector2(0,0);
}
