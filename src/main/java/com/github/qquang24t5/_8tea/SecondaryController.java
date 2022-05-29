package com.github.qquang24t5._8tea;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        EightTeaApplication.setRoot("primary");
    }
}