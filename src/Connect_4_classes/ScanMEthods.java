package Connect_4_classes;

public class ScanMEthods {

    public boolean scanVertical(char playCheck, int streak){
        boolean[] streaks = new boolean[streak];
        for (int row = board.length - 1; row >= board.length - 3; row--) {
            for (int col = 0; col < board[0].length; col++) {
                for (int i = 0; i < streak; i++){
                    streaks[i] = board[row - i][col] == playCheck;
                }
            }
        }
        for (boolean what: streaks) {
            if(what == false){
                return false;
            }
        }
        return true;
    }

    public boolean scanHorizontal(char playCheck, int streak){
        boolean[] streaks = new boolean[streak];
        for (int row = board.length - 1; row >= 0; row--) {
            for (int col = 0; col < board[0].length - 3; col++) {
                for (int i = 0; i < streak; i++){
                    streaks[i] = board[row][col + i] == playCheck;
                }
            }
        }
        for (boolean what: streaks) {
            if(what == false){
                return false;
            }
        }
        return true;
    }

    public boolean scanDiagonal1(char playCheck, int streak){
        boolean[] streaks = new boolean[streak];
        for (int row = (board.length - 1); row >= board.length - 3; row--) {
            for (int col = board[0].length - 1; col >= board[0].length - 4; col--) {
                for (int i = 0; i < streak; i++){
                    streaks[i] = board[row - i][col - i] == playCheck;
                }
            }
        }
        for (boolean what: streaks) {
            if(what == false){
                return false;
            }
        }
        return true;
    }

    public boolean scanDiagonal2(char playCheck, int streak){
        boolean[] streaks = new boolean[streak];
        for (int row = board.length - 1; row >= 3 ; row--) {
            for (int col = 0; col < board[0].length - 3; col++) {
                for (int i = 0; i < streak; i++){
                    streaks[i] = board[row - i][col + i] == playCheck;
                }
            }
        }
        for (boolean what: streaks) {
            if(what == false){
                return false;
            }
        }
        return true;
    }
}
