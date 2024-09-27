import java.io.*;

public class UCIEngine {
    private String engineCommand;
    private ProcessBuilder engineProcess;

    private BufferedReader reader;
    private BufferedWriter writer;

    public UCIEngine(String engineCommand) {
        this.engineCommand = engineCommand;
        this.engineProcess = new ProcessBuilder(engineCommand);
    }

    public void prepare() {
        try {
            // start the engine
            engineProcess.start();

            // get a handle on stdin
            OutputStream stdin = engineProcess.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(stdin));

            // get a handle on stdout
            InputStream stdout = engineProcess.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stdout));
        }
        catch (Exception ignored) {}
    }

    public void writeLine(String line) {
        writer.write(line + "\n");
    }
}