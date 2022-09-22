package Connect_4_classes;

public class Main {

    public static void main(String[] args) {
        Board b1 = new Board();
        b1.renderBoard();

//        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

      public static void makeMove(){
          int col;
          do{
              renderBoard();
              col = Input.integer("\nChoose a column.", 1,7) - 1;
          }while()
      }

}
