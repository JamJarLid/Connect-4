package Connect_4_classes;

import static Connect_4_classes.Main.board;

public class ScanMEthods {

    public static int[] scanVertical(char playCheck, int streak){
        for (int row = board.length - 1; row >= board.length - 3; row--) {
            for (int col = 0; col < board[0].length; col++) {
                boolean streaks = true;
                for (int i = 0; i < streak; i++){
                    streaks = board[row - i][col] == playCheck;
                    if (!streaks) {
                        break;
                    }
                }
                if(streaks){
                    return new int[]{row, col};
                }
            }
        }
        return new int[]{-1,-1};
    }

    public static int[] scanHorizontal(char playCheck, int streak){
        for (int row = board.length - 1; row >= 0; row--) {
            for (int col = 0; col < board[0].length - 3; col++) {
                boolean streaks = true;
                for (int i = 0; i < streak; i++){
                    streaks = board[row][col + i] == playCheck;
                    if (!streaks){
                        break;
                    }
                }
                if(streaks){
                    return new int[]{row, col};
                }
            }
        }
        return new int[]{-1,-1};
    }

    public static int[] scanDiagonal1(char playCheck, int streak){

        for (int row = (board.length - 1); row >= board.length - 3; row--) {
            for (int col = board[0].length - 1; col >= board[0].length - 4; col--) {
                boolean streaks = true;
                for (int i = 0; i < streak; i++){
                    streaks = board[row - i][col - i] == playCheck;
                    if (!streaks) {
                        break;
                    }
                }
                if(streaks){
                    return new int[]{row, col};
                }
            }
        }
        return new int[]{-1,-1};
    }

    public static int[] scanDiagonal2(char playCheck, int streak){
        for (int row = board.length - 1; row >= 3 ; row--) {
            for (int col = 0; col < board[0].length - 3; col++) {
                boolean streaks = true;
                for (int i = 0; i < streak; i++){
                    streaks = board[row - i][col + i] == playCheck;
                    if (!streaks) {
                        break;
                    }
                }
                if(streaks){
                    return new int[]{row, col};
                }
            }
        }
        return new int[]{-1,-1};
    }
}
