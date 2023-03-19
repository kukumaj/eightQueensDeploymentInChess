import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EightQueens {
    private static boolean canPlace(int[] board, int row, int col) {
        for (int r = 0; r < row; r++) {
            if (board[r] == col ||
                    board[r] - r == col - row ||
                    board[r] + r == col + row) {
                return false;
            }
        }
        return true;
    }

    private static void solve(int[] board, int row, List<int[]> solutions) {
        if (row == 8) {
            solutions.add(board.clone());
        } else {
            for (int col = 0; col < 8; col++) {
                if (canPlace(board, row, col)) {
                    board[row] = col;
                    solve(board, row + 1, solutions);
                }
            }
        }
    }

    private static void printBoard(int[] board, int num, BufferedWriter writer) throws IOException {
        writer.write("Solution " + num + "\n");
        String line = "+---".repeat(8) + "+\n";
        for (int row = 0; row < 8; row++) {
            StringBuilder sb = new StringBuilder("|");
            for (int col = 0; col < 8; col++) {
                sb.append(board[row] == col ? " H |" : "   |");
            }
            writer.write(sb.toString() + "\n");
            writer.write(line);
        }
        writer.write("\n");
    }

    public static void main(String[] args) {
        List<int[]> solutions = new ArrayList<>();
        solve(new int[8], 0, solutions);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:/solutions.txt"))) {
            for (int i = 0; i < solutions.size(); i++) {
                printBoard(solutions.get(i), i + 1, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
