import java.io.*;
import java.util.Arrays;

public class UCIEngine {
    private String engineCommand;

    private ProcessBuilder processBuilder;
    private Process engineProcess;

    private BufferedReader reader;
    private BufferedWriter writer;

    public String engineName;
    public String engineAuthor;

    public static void main(String[] args) {
        System.out.println("new inst");
        UCIEngine engine = new UCIEngine("stockfish");
        try {
            System.out.println("prep");
            engine.prepare();
            System.out.println("set fen");
            engine.setPositionFen("rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 2");
            System.out.println("go");
            System.out.println(engine.go(5));
        } catch(Exception ignored) {}
    }

    /**
     * Create a new UCIEngine instance
     * @param engineCommand The command to run, to get a UCI interface.
     */
    public UCIEngine(String engineCommand) {
        this.engineCommand = engineCommand;
        this.processBuilder = new ProcessBuilder(this.engineCommand);
    }

    /**
     * Launch the engine process with the command defined in the constructor, and create stdin and stdout handles.
     * @throws IOException
     */
    public void prepare() throws IOException {
        // start the engine
        engineProcess = processBuilder.start();

        // get a handle on stdin
        OutputStream stdin = engineProcess.getOutputStream();
        writer = new BufferedWriter(new OutputStreamWriter(stdin));

        // get a handle on stdout
        InputStream stdout = engineProcess.getInputStream();
        reader = new BufferedReader(new InputStreamReader(stdout));

        // some engines send banners which we just discard of here
        String nextLine = this.readLine();
        while (nextLine != null) {
            System.out.println("[engine banner] " + nextLine);
            nextLine = this.readLine();
            System.out.println("loop");
        }

        // check for uci
        System.out.println("check uci");
        this.checkForUCI();
    }

    /**
     * Write a line to the engine process' stdin
     * @param line The line to write
     * @throws IOException
     */
    public void writeLine(String line) throws IOException {
        System.out.println("writeLine " + line);
        writer.write(line + "\n");
        writer.flush();
    }

    /**
     * Read a line from the engine process' stdout
     * @returns The line read
     * @throws IOException
     */
    public String readLine() throws IOException {
        String l = reader.readLine();
        System.out.println("readLine " + l);
        return l;
    }

    /**
     * Check if the engine is actually UCI Compatible, and read engine information.
     * @throws IOException
     */
    private void checkForUCI() throws IOException {
        this.writeLine("uci");

        String nextLine = this.readLine();
        while (nextLine != null) {
            System.out.println(nextLine);
            String[] tokens = nextLine.split(" ");
            if(tokens[0].equals("uciok")) break;

            if(tokens[0].equals("id")) {
                if(tokens[1].equals("name")) this.engineName = String.join(" ", Arrays.copyOfRange(tokens, 2, tokens.length));
                if(tokens[1].equals("author")) this.engineAuthor = String.join(" ", Arrays.copyOfRange(tokens, 2, tokens.length));
            }

            nextLine = this.readLine();
        }
    }

    /**
     * Set a board position in the engine by FEN String
     * @param fen The FEN to set
     * @throws IOException
     */
    public void setPositionFen(String fen) throws IOException {
        this.writeLine("position fen " + fen);
    }

    /**
     * Tell the engine to calculate the best move for the position.
     * @return The best engine in SAN format. (e.g. "a1a3")
     * @throws IOException
     */
    public String go(Integer depth) throws IOException {
        String command = "go";

        if(depth != null) command += " depth " + depth;

        this.writeLine(command);

        String nextLine = this.readLine();
        while (nextLine != null) {
            System.out.println(nextLine);
            String[] tokens = nextLine.split(" ");
            if(tokens[0].equals("bestmove")) return tokens[1];

            if(tokens[0].equals("info")) {
                // This is used by the engine to send telemetry data.
                // We will output this to the user via graphs / a terminal.
            }

            nextLine = this.readLine();
        }

        return null; // There was no move returned. (Engine process crashed?)
    }
}