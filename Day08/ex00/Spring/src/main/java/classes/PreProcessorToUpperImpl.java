package classes;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String process(String text) {
        return text.toUpperCase();
    }
}
