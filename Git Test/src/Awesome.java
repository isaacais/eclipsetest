import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Awesome
{
	public long lastFrame;
	
	public static void main(String args[])
	{
		System.out.println("_ascf___NATE ROCKS____");
		Try.print();
		new Thingy();
		System.out.println("HI NATE");
		
		new Awesome();
	}
	
	public Awesome()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("TESTING");
			Display.create();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		lastFrame = getTime();
		
		// Initialization code OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		int scale = 40;
		int x = -scale / 2, y = -scale / 2;
		
		while(!Display.isCloseRequested())
		{
			glClear(GL_COLOR_BUFFER_BIT);
			int delta = getDelta();
			
			glPushMatrix();
			glTranslatef(x, y, 0);
			glScalef(scale, scale, 0);
			glBegin(GL_QUADS);
				glVertex2f(-0.5f, -0.5f);
				glVertex2f(+0.5f, -0.5f);
				glVertex2f(+0.5f, +0.5f);
				glVertex2f(-0.5f, +0.5f);
			glEnd();
			glPopMatrix();
			
			x += (int) delta / 10f;
			y += (int) delta / 10f;
			if(y >= 480 + scale / 2 || x >= 640 + scale / 2)
			{
				x = -scale / 2;
				y = -scale / 2;
			}
			
			System.out.println(x + ", " + y);
			
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
		System.exit(0);
	}
	
	public int getDelta()
	{
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		
		return delta;
	}
	
	public long getTime()
	{
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
}

