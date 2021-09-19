public class Rules {
    private static String[][] helpTable = null;

    private Rules() {
    }

    public static String[][] getRules() {
        return helpTable;
    }

    public static void setRules(String[] args) {
        int count_headers = args.length;
        int count_win = count_headers / 2;
        helpTable = new String[count_headers][count_headers];
        for (int i = 0; i < count_headers; i++) {
            for (int j = 0; j < count_headers; j++) {
                if (i == j) helpTable[i][j] = "Draw";
                else if (i > j) {
                    if (i - j < count_win + 1) helpTable[i][j] = "Lose";
                    else helpTable[i][j] = "Win";
                } else if (j - i < count_win + 1)
                    helpTable[i][j] = "Win";
                else helpTable[i][j] = "Lose";
            }
        }
    }

    public static String getWinner(int playerMove, int compMove) {
        return helpTable[playerMove][compMove];
    }
}
