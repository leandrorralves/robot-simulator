package org.iosb.model;

import lombok.Data;
import org.iosb.BusinessException;
import org.iosb.util.Orientation;

@Data
public class Robot {

    private int posX = 0;
    private int posY = 0;

    private Orientation orientation;

    public void move(char movementCommand) {
        switch (movementCommand) {
            case 'M':
                moveForward();
                break;
            case 'L':
                orientation = orientation.rotateLeft();
                break;
            case 'R':
                orientation = orientation.rotateRight();
                break;
            default:
                throw new BusinessException("Invalid movement command: " + movementCommand);
        }
    }

    private void moveForward() {
        switch (this.orientation) {
            case N:
                this.posX--;
                break;
            case W:
                this.posY--;
                break;
            case S:
                this.posX++;
                break;
            case E:
                this.posY++;
                break;
            default:
                throw new BusinessException("Invalid orientation detected while moving forward!");
        }
    }

}
