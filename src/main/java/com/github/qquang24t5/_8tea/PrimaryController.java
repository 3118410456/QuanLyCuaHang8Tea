package com.github.qquang24t5._8tea;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        EightTeaApplication.setRoot("secondary");
    }
}
