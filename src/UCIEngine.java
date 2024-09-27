import java.io.*;

public class UCIEngine {
    private String engineCommand;

    private ProcessBuilder processBuilder;
    private Process engineProcess;

    private BufferedReader reader;
    private BufferedWriter writer;

    /**
     * Create a new UCIEngine instance
     * @param engineCommand The command to run, to get a UCI interface.
     */
    public UCIEngine(String engineCommand) {
        this.engineCommand = engineCommand;
        this.processBuilder = new ProcessBuilder(engineCommand);
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
    }

    /**
     * Write a line to the engine process' stdin
     * @param line The line to write
     * @throws IOException
     */
    public void writeLine(String line) throws IOException {
        writer.write(line + "\n");
    }

    /**
     * Read a line from the engine process' stdout
     * @returns The line read
     * @throws IOException
     */
    public String readLine() throws IOException {
        return reader.readLine();
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
    public String go() throws IOException {
        this.writeLine("go");

        String nextLine = this.readLine();
        while (nextLine != null) {
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