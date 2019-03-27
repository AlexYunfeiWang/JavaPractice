public class ReachingPoints {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if (sx > tx || sy > ty) {
            return false;
        }
        if ((sx == tx && sy == ty) ||
                (sx == tx && sy%sx == ty%sx) ||
                (sy == ty && sx%sy == tx%ty) ) {
            return true;
        }
        if ((sx == tx && sy%sx != ty%sx) ||
                (sy == ty && sx%sy != tx%sy)) {
            return false;
        }

        while(tx > 0 && ty > 0) {
            if (tx > ty) {
                tx -= ty;
            }
            else {
                ty -= tx;
            }

            if (sx == tx && sy == ty) {
                return true;
            }

            if (tx < sx || ty < sy) {
                return false;
            }
        }
        return false;

    }
}
