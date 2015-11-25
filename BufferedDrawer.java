/** *  Apple Worldwide Developer Technical Support * *  Sample demonstrating offscreen drawing. * *  by Levi Brown, Apple Developer Technical Support * *  File:   BufferedDrawer.java * *  Copyright �1999 Apple Computer, Inc. *  All rights reserved. * *	4/99	v. 1.0	Shipped as 'Magic Oracle AppleScript for Java' sample. * *  You may incorporate this sample code into your applications without *  restriction, though the sample code has been provided "AS IS" and the *  responsibility for its operation is 100% yours.  However, what you are *  not permitted to do is to redistribute the source as "Apple Sample *  Code" after having made changes. If you're going to re-distribute the *  source, we require that you make it clear in the source that the code *  was descended from Apple Sample Code, but that you've made changes.**/import java.awt.Component;import java.awt.Image;import java.awt.Graphics;import java.awt.Dimension;/** * A component which does all it's drawing in an offscreen image * the size of the component. * * @author Levi Brown * @author Apple Worldwide Developer Technical Support * @author Apple Computer Inc. * @version 1.0 11/4/1998 */public class BufferedDrawer extends Component{	/**	 * Creates a new BufferedDrawer.	 */	public BufferedDrawer()	{		//Initialize our data members	    workingGraphics     = null;	    workingImage        = null;	}	/**	 * Handles drawing the desired content into the offscreen buffer.	 * Override this to do your own drawing (be sure to call super.draw()).	 * @see #paint	 */	protected void draw()	{		//Make sure the offscreen image is ready to be drawn into.		ensureValidWorkingImage();		//Do drawing here (use workingGraphics)		//Don't forget to erase the buffer if you don't draw over the entire area		//or else you will end up with tracers.		//To erase the entire area, uncomment out the following code:		/*		Dimension s = new Dimension(workingImage.getWidth(this), workingImage.getHeight(this));		workingGraphics.setColor(getBackground());		workingGraphics.fillRect(0, 0, s.width, s.height);		*/	}	/**	 * Makes sure the offscreen image exists.	 */	protected void ensureValidWorkingImage()	{		//If the size changed and we need to create a new pane image, then create one		Dimension size = getSize();		if ((size != null && size.width > 0 && size.height > 0) && (workingImage == null || size.width != workingImage.getWidth(this) || size.height != workingImage.getHeight(this)))		{		    if (workingImage != null)		    {		        workingImage.flush();		        workingImage = null;		    }		    workingImage = createImage(size.width, size.height);		    if (workingGraphics != null)		    {		        workingGraphics.dispose();		        workingGraphics = null;		    }		    workingGraphics = workingImage == null ? null : workingImage.getGraphics();        }	}	/**      * Updates the component. This method is called in     * response to a call to repaint. You can assume that     * the background is not cleared.     * Overriden here to prevent unwanted background erasing.     * @param g the specified Graphics window     * @see #paint     * @see java.awt.Component#repaint     */    public void update(Graphics g)    {		paint(g);	}	/**      * Paints the component.  This method is called when the contents     * of the component should be painted in response to the component     * first being shown or damage needing repair.  The clip rectangle     * in the Graphics parameter will be set to the area which needs     * to be painted.     * @param g the specified Graphics object     * @see #update     */    public void paint(Graphics g)    {		draw();		g.drawImage(workingImage, 0, 0, this);	}	//The offscreen image buffer to render the progress bar into.	protected Image workingImage;	//The Graphics object associated with the offscreen image buffer.	protected Graphics workingGraphics;}