package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    
    // Constructor
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.screenWidth /2 -(gp.tileSize/2);
        screenY =gp.screenHeight/2 -(gp.tileSize/2);
        setDefaultValues();
        getPlayerImage();// Initialize default values in constructor
    }
    
    // Method to set default values
    public void setDefaultValues() {
        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 25;
        speed = 4;
        direction = "idle";
    }
    
    public void getPlayerImage() {
        try {
            idle1 = ImageIO.read(getClass().getResourceAsStream("/player/idle.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/back-left.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/back-right.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/walk-left1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/walk-right1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/side-left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/sidewalk-left.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/side-right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/sidewalk-right.png"));
            back = ImageIO.read(getClass().getResourceAsStream("/player/back.png"));

            // Debug logging
            if (idle1 == null) System.out.println("Failed to load idle1 image");
            if (up1 == null) System.out.println("Failed to load up1 image");
            if (down1 == null) System.out.println("Failed to load down1 image");
            if (left1 == null) System.out.println("Failed to load left1 image");
            if (right1 == null) System.out.println("Failed to load right1 image");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update player position based on key presses
    public void update() {
        if (keyH.upPressed) {
        	direction ="up";
            worldY -= speed;
        } else if (keyH.downPressed) {
        	direction ="down";
            worldY += speed;
        } else if (keyH.leftPressed) {
        	direction="left";
            worldX -= speed;
        } else if (keyH.rightPressed) {
        	direction= "right";
            worldX += speed;
        }
        else if(direction == "down" ){
        	direction = "idle";
        }
        else if(direction =="up"){
        	direction ="back";
        }
        else if(direction =="left"){
        	direction ="leftIdle";
        }
        else if(direction =="right"){
        	direction ="rightIdle";
        }
        
        spriteCounter++;
        if(spriteCounter > 12) {
        	if(spriteNum == 1) {
        		spriteNum =2;
        	}
        	else if(spriteNum==2) {
        		spriteNum = 1;
        	}
        	spriteCounter = 0;
        }
    }
    
    // Draw the player on the screen
    public void draw(Graphics2D g2) {
       /// g2.setColor(Color.white);
       /// g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    	BufferedImage image = null;
    	
    	switch(direction) {
    	case "idle":
        	image=idle1;
        	break;
    	
    	case "up":
    		if(spriteNum == 1) {
    			image=up1;	 
    		}
    		if(spriteNum == 2) {
    			image=up2;
    		}
    	break;
    	
    	case "down":
    		if(spriteNum == 1) {
    			image=down1;	
    		}
    		if(spriteNum == 2) {
    			image=down2;
    		}
    	break;
    	case "left":
    		if(spriteNum == 1) {
    			image=left1;	
    		}
    		if(spriteNum == 2) {
    			image=left2;
    		}
    	break;
    	
    	case "leftIdle":
    		image=left1;
    	break;
    	case "rightIdle":
    		image=right1;
    	break;
    	case "right":
    		if(spriteNum == 1) {
    			image=right1;	
    		}
    		if(spriteNum == 2) {
    			image=right2;
    		}
    	break; 
    	case "back":
    		image=back;
    		break;
    	}
    	
    	g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
