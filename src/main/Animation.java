package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Animation {
	
	private GamePanel gp;
	
	public BufferedImage youwon;
	public BufferedImage explosion, explosion_dark_f1, explosion_dark_f2, explosion_dark_f3, explosion_dark_f4, explosion_dark_f5, explosion_dark_f6;
	public boolean endanimation = false;
	private boolean deathanimation = false;
	private int screenX, screenY, screenXb, screenYb, endScreenWidth, endScreenHeigth;
	private int counter = 0;
	private boolean tp = false;
	private String grund;
	private int spriteCounterExplosion = 0;
	private int spriteNumExplosion = 0;
	
	public Animation(GamePanel gp) {
		this.gp = gp;
		
		getImages();
		getDefaultValues();
	}
	
	public void getImages() {
		try {
			
			youwon = ImageIO.read(getClass().getResourceAsStream("/animations/endanimation/youwon.png"));
			
			explosion_dark_f1 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f1.png"));
			explosion_dark_f2 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f2.png"));
			explosion_dark_f3 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f3.png"));
			explosion_dark_f4 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f4.png"));
			explosion_dark_f5 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f5.png"));
			explosion_dark_f6 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f6.png"));
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void startEndanimation() {
		gp.animationPause = true;
		endanimation = true;
	}
	
	public void startDeathanimation(int col, int row, String grund, int colb, int rowb) {
		this.grund = grund;
		screenX = col * gp.tileSize;
		screenY = row * gp.tileSize;
		screenXb = colb * gp.tileSize;
		screenYb = rowb * gp.tileSize;
		gp.animationPause = true;
		deathanimation = true;
	}
	
	public void getDefaultValues() {
		endScreenWidth = 16 * gp.tileSize;
		endScreenHeigth = 8 * gp.tileSize;
		screenX = (gp.maxScreenCol * gp.tileSize) / 2 - endScreenWidth / 2;
		screenY = (gp.maxScreenRow * gp.tileSize) / 2 - endScreenHeigth / 2;
	}
	
	public void explosionSprite() {
		spriteCounterExplosion++;
		if(spriteCounterExplosion >= 4) {
			spriteNumExplosion++;
			switch(spriteNumExplosion){
				case(1):
					explosion = gp.tileM.tile[5].image;
					break;
				case(2):
					explosion = explosion_dark_f1;
					break;
				case(3):
					explosion = explosion_dark_f1;
					break;
				case(4):
					explosion = explosion_dark_f2;
					break;
				case(5):
					explosion = explosion_dark_f2;
					break;
				case(6):
					explosion = explosion_dark_f3;
					break;
				case(7):
					explosion = explosion_dark_f3;
					break;
				case(8):
					explosion = explosion_dark_f4;
					break;
				case(9):
					explosion = explosion_dark_f4;
					break;
				case(10):
					explosion = explosion_dark_f5;
					break;
				case(11):
					explosion = explosion_dark_f5;
					break;
				case(12):
					explosion = explosion_dark_f5;
					break;
				case(13):
					explosion = explosion_dark_f6;
					break;
				case(14):
					explosion = explosion_dark_f6;
					break;
				case(15):
					explosion = explosion_dark_f6;
					break;
				case(16):
					explosion = explosion_dark_f6;
					break;
				case(17):
					explosion = gp.tileM.tile[5].image;
					break;
				case(18):
					explosion = gp.tileM.tile[5].image;
					break;
				case(19):
					explosion = gp.tileM.tile[5].image;
					break;
				case(20):
					explosion = gp.tileM.tile[5].image;
					break;
				case(21):
					explosion = gp.tileM.tile[5].image;
					break;
				case(22):
					explosion = gp.tileM.tile[5].image;
					break;
			}
			
			if(spriteNumExplosion == 22) {
				spriteNumExplosion = 1;
			}
			spriteCounterExplosion = 0;
		}
	}
	
	public void update() {
		if(tp) {
			counter++;
			tp = false;
		}
		else {
			tp = true;
		}
		
		if(deathanimation) {
			explosionSprite();
			if(counter >= 42) {
				gp.animationPause = false;
				deathanimation = false;
				gp.player.sterben2(grund);
				counter = 0;
			}
		}
		
		if(endanimation == true) {
			if(40 < counter  && counter < 58) {
				endScreenWidth = endScreenWidth - (counter - 40) * 2;
				endScreenHeigth = endScreenHeigth - (counter - 40);
				screenX = (gp.maxScreenCol * gp.tileSize) / 2 - endScreenWidth / 2;
				screenY = (gp.maxScreenRow * gp.tileSize) / 2 - endScreenHeigth / 2 - (counter - 40) * 18;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		if(gp.animationPause) {
			if(deathanimation == true) {
				if(grund == "Gegner" || grund == "Gegner mit Loot") {
					g2.drawImage(explosion_dark_f1, screenXb, screenYb, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion_dark_f1, screenXb, screenYb - gp.tileSize, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion_dark_f1, screenXb, screenYb + gp.tileSize, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion_dark_f1, screenXb - gp.tileSize, screenYb, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion_dark_f1, screenXb - gp.tileSize, screenYb - gp.tileSize, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion_dark_f1, screenXb - gp.tileSize, screenYb + gp.tileSize, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion_dark_f1, screenXb + gp.tileSize, screenYb, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion_dark_f1, screenXb + gp.tileSize, screenYb - gp.tileSize, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion_dark_f1, screenXb + gp.tileSize, screenYb + gp.tileSize, gp.tileSize, gp.tileSize, null);
				}
				g2.drawImage(explosion, screenX, screenY, gp.tileSize, gp.tileSize, null);
				g2.drawImage(explosion, screenX, screenY - gp.tileSize, gp.tileSize, gp.tileSize, null);
				g2.drawImage(explosion, screenX, screenY + gp.tileSize, gp.tileSize, gp.tileSize, null);
				g2.drawImage(explosion, screenX - gp.tileSize, screenY, gp.tileSize, gp.tileSize, null);
				g2.drawImage(explosion, screenX - gp.tileSize, screenY - gp.tileSize, gp.tileSize, gp.tileSize, null);
				g2.drawImage(explosion, screenX - gp.tileSize, screenY + gp.tileSize, gp.tileSize, gp.tileSize, null);
				g2.drawImage(explosion, screenX + gp.tileSize, screenY, gp.tileSize, gp.tileSize, null);
				g2.drawImage(explosion, screenX + gp.tileSize, screenY - gp.tileSize, gp.tileSize, gp.tileSize, null);
				g2.drawImage(explosion, screenX + gp.tileSize, screenY + gp.tileSize, gp.tileSize, gp.tileSize, null);
			}
			
			if(endanimation == true) {
				g2.drawImage(youwon, screenX, screenY, endScreenWidth, endScreenHeigth, null);
			}
		}
	}
}
