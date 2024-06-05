package org.iosb.model;

import lombok.Data;
import org.iosb.BusinessException;
import org.iosb.util.Orientation;

@Data
public class Robot {

    private int rowPosition = 0;
    private int columnPosition = 0;

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
                this.rowPosition--;
                break;
            case W:
                this.columnPosition--;
                break;
            case S:
                this.rowPosition++;
                break;
            case E:
                this.columnPosition++;
                break;
            default:
                throw new BusinessException("Invalid orientation detected while moving forward!");
        }
    }

    public String getPosition() {
        return this.rowPosition + " " + this.columnPosition + " " + this.getOrientation().name();
    }
}
