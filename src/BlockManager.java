import comp127graphics.CanvasWindow;

import java.util.List;
import java.util.ArrayList;

public class BlockManager {
    private CanvasWindow canvas;
    private List<Block> blocks;
    private static final int BLOCK_QUANTITY = 36;
    private final List<String> availableTypes = List.of("Math", "Chem");

    public BlockManager(CanvasWindow canvas) {
        this.canvas = canvas;
    }

    public void generateBlock() {
        this.blocks = new ArrayList<>();

        List<String> BlockbyType = new ArrayList<>();
        int rounds = BLOCK_QUANTITY / availableTypes.size();
        int remainder = BLOCK_QUANTITY % availableTypes.size();
        for(int i = 0; i < rounds; i++){
            BlockbyType.addAll(availableTypes);
        }
        if(remainder != 0){
            BlockbyType.addAll(availableTypes.subList(0,remainder));
        }

        double leftEdge = canvas.getWidth() * 0.1;
        double rightEdge = canvas.getWidth() * 0.9;
        double x = leftEdge;
        double y = canvas.getHeight() * 0.1;
        boolean reverse = false;
        double blockSize = canvas.getWidth() * 0.058;

        for (int i = 0; i < BLOCK_QUANTITY; i++) {
            Block block = new Block(x, y, blockSize, blockSize,i);
            block.setType(BlockbyType.get(i));
            blocks.add(block);
            canvas.add(block);

                if (x + 2 * blockSize >= rightEdge && !reverse || x - blockSize < leftEdge && reverse) {
                    reverse = !reverse;
                    for (int j = 0; j < 2; j++){
                        y += blockSize;
                        x = block.getX();
                        Block cornerblock = new Block(x,y,blockSize, blockSize,j);
                        canvas.add(cornerblock);
                    }
                } else {
                    if (reverse){
                        x -= blockSize;
                    } else {
                        x += blockSize;
                    }
                }
            }
        }

        public Block getBlock ( int index ){
            for (Block block : blocks) {
                if (index == block.getIndex()) {
                    return block;
                }
            }
            return null;
        }

        public List<Block> getPassedBlocks ( int index){
            return blocks.subList(0, index + 1);
        }

        public int getBlockQuantity () {
            return BLOCK_QUANTITY;
        }
        // compensate for last time commit

}

