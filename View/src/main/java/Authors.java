import java.util.ListResourceBundle;

public class Authors extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"1.", "Adrian Wojtasik",},
                {"2.", "Bartosz Pietrzyba"}
        };
    }
}
