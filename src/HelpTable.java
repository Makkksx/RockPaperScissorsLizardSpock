import com.bethecoder.ascii_table.ASCIITable;

import java.util.Arrays;
import java.util.stream.Stream;

public final class HelpTable {
    private HelpTable() {
    }

    public static void printHelp(String[][] helpTable, String[] headers) {
        int count_headers = headers.length;
        String[][] table_to_print = new String[count_headers][count_headers + 1];
        for (int i = 0; i < count_headers; i++) {
            table_to_print[i][0] = headers[i];
            System.arraycopy(helpTable[i], 0, table_to_print[i], 1, count_headers);
        }
        ASCIITable.getInstance().printTable(Stream.concat(Stream.of("PC \\ User"),
                Arrays.stream(headers)).toArray(String[]::new), table_to_print);
    }
}