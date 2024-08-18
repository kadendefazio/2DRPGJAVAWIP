package entity;

import java.awt.image.BufferedImage;

public class Entity {

	public int worldX, worldY;
	public int speed;
	
	public BufferedImage idle1,up1, up2, down1, down2, left1, left2, right1, right2, back1, back2, back, leftIdle, rightIdle;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
}
