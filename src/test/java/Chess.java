import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class Chess {

    @Test
    public void CheckMoves(){
        Assert.assertEquals(570434068,bitHorse(19));
    }

    private int bitHorse(int comingPosition){
        int horsePosition = 1 << comingPosition;
        BigInteger positionStart = BigInteger.valueOf(horsePosition);

        BigInteger notA  = new BigInteger("18374403900871474942");
        BigInteger notAB = new BigInteger("18229723555195321596");
        BigInteger  notH = new BigInteger("9187201950435737471");
        BigInteger notGH = new BigInteger("4557430888798830399");

        BigInteger move = notGH.and(positionStart.shiftLeft(6).or(positionStart.shiftRight(10)))
                .or(notH.and(positionStart.shiftLeft(15).or(positionStart.shiftRight(17)))
                        .or(notA.and(positionStart.shiftLeft(17).or(positionStart.shiftRight(15)))
                                .or(notAB.and(positionStart.shiftLeft(10).or(positionStart.shiftRight(6))))));
        int numberOfMoves = 0;
        while (move.intValue() != 0){
            BigInteger minus = new BigInteger("1");
            move = move.subtract(minus);
            numberOfMoves++;
        }
        return numberOfMoves;
    }
}
