import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

public class Save implements Serializable{
	
	private Map map;
	private int blockSize;
	
	public Save(Map map, int blockSize) {
		this.map = map;
		this.blockSize = blockSize;
	}
	public Block[][] loadSave(File loadPath) {
		Block[][] block = new Block[map.getMapHeight()][map.getMapWidth()];
			
		try {
			Scanner loadScanner = new Scanner(loadPath);
			
			while(loadScanner.hasNext()) {
				
			
				
				for(int y = 0; y < map.getMapHeight();y++) {
					for(int x = 0; x < map.getMapWidth();x++) {
						block[y][x] = new Block(x, y, blockSize, loadScanner.nextInt());
						
					}
				}
				
				
				
			}
			
			loadScanner.close();
			
		} catch (FileNotFoundException e) { System.out.println("Can't load map!");}
		System.out.print(block);
		return block;
			
			
	}
}
