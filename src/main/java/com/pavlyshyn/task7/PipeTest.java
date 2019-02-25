package com.pavlyshyn.task7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PipeTest {

    private Pipe pipe;
    private String path;
    private final int BUFFER_SIZE = 256;
    public PipeTest(String path) throws IOException {
        this.pipe = Pipe.open();
        //pipe.sink().configureBlocking(false);
        pipe.source().configureBlocking(false);

        this.path = path;
    }

    private void readFromFileToPipe() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path),BUFFER_SIZE);
             Pipe.SinkChannel sinkChannel = pipe.sink()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sinkChannel.write(ByteBuffer.wrap((line + '\n').getBytes()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void readFromPipe() {
        try (Pipe.SourceChannel sourceChannel = pipe.source()) {


            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

            while (sourceChannel.read(buffer) != -1) {
                buffer.flip();
                System.out.print(new String(buffer.array()));
                buffer.clear();
            }
            sourceChannel.read(buffer);
            buffer.flip();
            System.out.println(new String(buffer.array()));
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void start() {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(this::readFromFileToPipe);
        service.submit(this::readFromPipe);
        service.shutdown();
    }

    public static void main(String[] args) throws IOException {
        PipeTest pipeTest = new PipeTest("src/main/java/com/pavlyshyn/task7/poem.txt");
        pipeTest.start();
    }
}