package org.harrynoob.scripts.drsfighter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.harrynoob.scripts.drsfighter.gui.MainPanel;
import org.harrynoob.scripts.drsfighter.misc.Variables;
import org.harrynoob.scripts.drsfighter.node.*;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.WidgetCache;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.bot.Context;
import org.powerbot.game.client.Client;

@Manifest(name = "DRSFighter", version = 1.01, authors = "harrynoob", description = "Kills deadly red spiders. Supports weapon switching & charm looting!")
public class DRSFighter extends ActiveScript implements PaintListener, MouseListener{
	
	private Node[] NODE_LIST = {new CharmLooter(), new TargetSwitcher(), new EquipWeapon(),  new FindTarget(), new FoodEater(),  new EquipShield(), new RejuvenateSwitcher(),  new RejuvenateUser(), new UltimateUser(), new ThresholdUser(), new AbilityUser()};
	public static DRSFighter instance;
	public MainPanel main;
	public boolean activated;
	private NPC currentTarget;
	private Client client = Context.client();
	
	private int xpHour;
	private int startxp;
	private Timer timer;
	private long startTime;
	private boolean paintShown;
	public String status;
	
	public void onStart()
	{
		instance = this;
		paintShown = true;
		status = "GUI";
		startTime = System.currentTimeMillis();
		startxp = -1;
		try {
			SwingUtilities.invokeLater(new Runnable(){				
				public void run()
				{
					try {
						main = new MainPanel();
						System.out.println("Main created");
						main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						main.setSize(600, 600);
						main.setLocationRelativeTo(null);
						main.setTitle("DRSFighter");
						main.pack();
						main.setVisible(true);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mouse.setSpeed(Mouse.Speed.FAST);
	}
	
	@Override
	public int loop() {
	    if (Game.getClientState() != Game.INDEX_MAP_LOADED) {
	    	return 1000;       
	    }

	    if (client != Context.client()) {
	        WidgetCache.purge();
	        Context.get().getEventManager().addListener(this);
	        client = Context.client();
	    }
	    else if(Players.getLocal() == null) return 0;
	    else if(activated)
		{
			for(Node n : NODE_LIST)
			{
				if(n.activate())
				{
					n.execute();
					Task.sleep(50);
				}
			}
			
		}
		return 0;
	}
	
	public void onStop()
	{
		if(main != null) main.setVisible(false);
	}
	
	public void activate()
	{
		activated = true;
		Variables.initOptions(main);
		timer = new Timer(0);
		startTime = System.currentTimeMillis();
		startxp = 0;
	}

	public NPC getCurrentTarget() {
		return currentTarget;
	}

	public void setCurrentTarget(NPC currentTarget) {
		this.currentTarget = currentTarget;
	}
	
    private final Color color1 = new Color(0, 0, 0);
    private final Color color2 = new Color(250, 250, 250);
    private final Color color3 = new Color(40, 40, 40, 0);

    private final BasicStroke stroke1 = new BasicStroke(5);

    private final Font font1 = new Font("Felix Titling", 0, 19);
    private final Font font2 = new Font("Felix Titling", 0, 14);
    private final int y = 50;
	    
    public int getExpGain() {
        if(startxp == 0) {
            startxp = Settings.get(91);
        }
        int curr = Settings.get(91);
        return (curr - startxp) / 10;
    }
    
    public void onRepaint(Graphics g1) {
		xpHour = (int) ((getExpGain() * 3600000D) / (System.currentTimeMillis() - startTime));
        Graphics2D g = (Graphics2D)g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(paintShown)
        {
	        g.setColor(new Color(150,211-80,128-80));
	        g.drawLine(Mouse.getX() - 5, Mouse.getY() - 5, Mouse.getX() + 5, Mouse.getY() + 5);
	        g.drawLine(Mouse.getX() - 5, Mouse.getY() + 5, Mouse.getX() + 5, Mouse.getY() - 5);
	        g.fillRect(6, 344+y, 506, 129);
	        g.setColor(color1);
	        g.setStroke(stroke1);
	        g.drawRect(6, 344+y, 506, 129);
	        g.setFont(font1);
	        g.drawString("DRSFighter by harrynoob", 180-25, 372+y);
	        g.setColor(color2);
	        g.drawString("DRSFighter by harrynoob", 179-25, 371+y);
	        g.setFont(font2);
	        g.setColor(color1);
	        g.drawString("Time running: "+(timer != null ? timer.toElapsedString() : "00:00:00"), 54, 398+y);
	        g.setColor(color2);
	        g.drawString("Time running: "+(timer != null ? timer.toElapsedString() : "00:00:00"), 53, 397+y);
	        g.setColor(color1);
	        g.drawString("XP: "+getExpGain(), 279, 446+y);
	        g.setColor(color2);
	        g.drawString("XP: "+getExpGain(), 278, 445+y);
	        g.setColor(color1);
	        g.drawString("Status: "+status, 279, 396+y);
	        g.setColor(color2);
	        g.drawString("Status: "+status, 278, 395+y);
	        g.setColor(color1);
	        g.drawString("XP P/H: "+xpHour, 54, 446+y);
	        g.setColor(color2);
	        g.drawString("XP P/H: "+xpHour, 53, 445+y);
	        g.setColor(color3);
	        g.fillRect(10, 348+y, 499, 123);
    	}
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getX() < 510 && arg0.getX() > 10 && arg0.getY() > 398 && arg0.getY() < 398+123)
			paintShown = !paintShown;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {		
	}

}