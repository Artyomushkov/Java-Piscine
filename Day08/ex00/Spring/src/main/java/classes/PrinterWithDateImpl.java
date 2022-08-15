package classes;

import java.time.LocalDateTime;

public class PrinterWithDateImpl implements Printer {

    private Renderer renderer;

    public PrinterWithDateImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String text) {
        text = text + " " + LocalDateTime.now();
        renderer.out(text);
    }
}
